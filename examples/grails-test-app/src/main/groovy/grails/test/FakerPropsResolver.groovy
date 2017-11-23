package grails.test

import com.github.javafaker.Faker
import grails.test.app.inheritance.Dog
import org.grails.gorm.buildtestdata.InitialPropsResolver

class FakerPropsResolver implements InitialPropsResolver{
    Faker faker = Faker.instance()
    @Override
    Map<String, ?> getInitialProps(Class target) {
        switch(target){
            case Dog:
                return [
                    name:faker.name().name(),
                    limbCount: faker.number().randomDigitNotZero()
                ]
            default: 
                return null
        }
    }
}
