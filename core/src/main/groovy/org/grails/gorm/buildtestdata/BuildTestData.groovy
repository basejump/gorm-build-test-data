package org.grails.gorm.buildtestdata

trait BuildTestData{
    static buildLazy(Map propValues=[:]){
        BuildTestDataApi.findBuilder(this).buildLazy(new BuildTestDataContext(propValues))
    }
    static build(Map propValues=[:]){
        BuildTestDataApi.findBuilder(this).build(new BuildTestDataContext(propValues))
    }
}