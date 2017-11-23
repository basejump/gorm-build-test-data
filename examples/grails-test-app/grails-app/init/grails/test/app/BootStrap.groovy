package grails.test.app

import grails.test.FakerPropsResolver
import grails.test.app.inheritance.Dog
import org.grails.gorm.buildtestdata.BuildTestDataApi

class BootStrap {

    def init = { servletContext ->
        BuildTestDataApi.setInitialPropsResolver(new FakerPropsResolver())
        50.times{
            Dog.build()
        }
    }
    def destroy = {
    }
}
