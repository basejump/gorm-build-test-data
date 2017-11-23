package org.grails.gorm.buildtestdata.testing

import org.grails.gorm.buildtestdata.BuildTestDataApi
import org.grails.gorm.buildtestdata.BuildTestDataContext

trait BuildTestDataTest {
    void setupBuildTestDataApi(){
        for (int i = 0; i < classesToBuildTestData.length; i++) {
            Class testDataClass = classesToBuildTestData[i]
            testDataClass.metaClass.static.build = {Map propValues=[:]->
                BuildTestDataApi.findBuilder(testDataClass).build(new BuildTestDataContext(propValues))                
            }
            testDataClass.metaClass.static.buildLazy = {Map propValues=[:]->
                BuildTestDataApi.findBuilder(testDataClass).buildLazy(new BuildTestDataContext(propValues))
            }
        }
    }
    void cleanupBuildTestDataApi(){
        for (int i = 0; i < classesToBuildTestData.length; i++) {
            Class testDataClass = classesToBuildTestData[i]
            testDataClass.metaClass.static.build = null
            testDataClass.metaClass.static.buildLazy = null
        }
    }
    
    public Class[] getClassesToBuildTestData() {
        getAnnotatedClasses()
    }
    
    private Class[] getAnnotatedClasses(){
        this.getClass().getAnnotation(Build).value()
    }
}