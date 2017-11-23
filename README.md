[![Build Status](https://api.travis-ci.org/longwa/build-test-data.png?branch=master)](https://travis-ci.org/longwa/build-test-data)

## Gorm Build Test Data

This plugin is a major rewrite of the [build-test-data plugin](http://plugins.grails.org/plugin/longwa/build-test-data).
I started converting the build-test-data plugin for use with Grails 3.3, but because of the changes to Gorm and testing,
support in Grails 3.3, I finally decided to create a separate plugin. 

The project setup is mirrored from gorm-graphql. 

### Differences with build-test-data

Project is split in three modules

Part | Description
--- | ---
core | Contains the BuildTestData trait and depends on gorm and databinding only!
testing | For testing purposes, contains a Build annotation like the Build mixin of build-test-data. 
plugin  | Plugin contains TraitInjector that applies the BuildTestData trait to the available domainclasses

* Binding is changed to grails-databinding
* Configuration is removed, an initialPropsResolver can be setup to resolve default values
* Supports pogo, validateable and gorm entities (plugable to add more)
* Added additional basic-type (LocalDate,LocalDateTime,...)

## Gorm usage


```groovy
@Entity
class User implements BuilderTestData{
    String name
}
..
User.build() // or User.build([name:'whatever'])
User.buildLazy() // or User.buildLazy([name:'whatever'])
```

## Testing usage

For testing the plugin will dynamically add the BuildTestDataApi to the metaclass
of the subjects (User and Role in the example below). This happens in a spock hook. 
```groovy
@Build([User,Role])
class MySpec extends Specification{

    def 'test'(){
       expect:
        User.build() 
    }
}
```
is equivalent to: 
```groovy
class MySpec extends Specification implements DomainUnitTest<User>, BuildTestDataTest{
    Class[] getClassesToBuildTestData(){
        [User,Role] as Class[]
    }
    
    Class<?>[] getDomainClassesToMock() {
        [User,Role] as Class[]
    }
    
    def 'test'(){
       expect:
        User.build() 
    }
}
```

## Plugin usage

Simply add the plugin to your project and each domainclass will implement the `BuildTestData` trait.


## The Build Test Data Grails Plugin 

Creating maintainable test data is hard.  Often an entire object graph needs to be created to support the instantiation of a single domain object.  This leads to either the cutting and pasting of that creation code, or relying on a canned set of objects that we've grown over time and maintained as the domain objects change.  After a while, adding just one more Widget to that set of canned data ends up breaking tests just about every time.

There has to be a better solution, right?  

Yep! Due to the power and the glory of Grails, we have a lot of metadata at our fingertips about those domain objects.  We know what constraints we've placed on our objects, and which objects depend on other objects to live.

Using this additional information, we've created a grails plugin that makes it easy to just provide those values that you want to exercise under test and not worry about the rest of the object graph that you need to create just to instantiate your domain objects.

This plugin is focused on creating test data for integration testing. 


Once installed, all you have to do is call the new "build" method on your domain class and you'll be given a valid instance with all of the required constraints given values. 
```groovy
def a = Author.build()
```
In a unit test, you'll just use the `BuildTestData` trait. 
```groovy
//import grails.buildtestdata.mixin.Build
import org.grails.buildtestdata.testing.Build

@Build(Author)
class AuthorSpec extends Specification {

    void testAuthorStuff() {
        def author = Author.build()
        ...
    }
}
```

This is equivalent to writing: 
```groovy
import org.grails.buildtestdata.testing.BuildTestDataTest

class AuthorSpec extends Specification implements DomainUnitTest<Author>,BuildTestDataTest{
    
    Class[] getClassesToBuildTestData(){
        return [Author] as Class[]
    }    
    
    void testAuthorStuff() {
        def author = Author.build()
        ...
    }
}
```


### Plugin Objectives 

- The definition of the domain objects under test should be next to the test code, this improves test comprehension.
- You should only need to create those fields and objects that are pertinent to the test.  Other test setup is noise that obfuscates the meaning of the test.
- Tests should not be dependent on other tests, only on the code under test.  Therefore, the same test data should not be used by multiple tests, this creates a strong coupling and leads to test fragility.
- Changes to domain objects that do not affect the the code under test should not break the test.


