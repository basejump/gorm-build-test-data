package org.grails.buildtestdata

import org.grails.buildtestdata.pogo.Simple
import org.grails.gorm.buildtestdata.BuildTestDataApi
import org.grails.gorm.buildtestdata.propsresolver.ClosurePropsResolver
import org.grails.gorm.buildtestdata.propsresolver.MapPropsResolver
import spock.lang.Specification

class BuildTestDataSpec extends Specification{
    
    def 'implements trait methods'(){
        given:
            Class<Simple> target = Simple
        expect:
            target.getMethod('build')
            target.getMethod('build',[Map.class] as Class[])
            target.getMethod('buildLazy')
            target.getMethod('buildLazy',[Map.class] as Class[])
            target.build() instanceof Simple
            target.buildLazy() instanceof Simple
            target.build(name:'tkvw').name == 'tkvw'
            target.buildLazy(name:'tkvw').name == 'tkvw'
    }
    
    def 'closure props resolver'() {
        given:
            Map<Class,Closure> testDataResolvers = [
                (Simple.class):{
                    [name:'works']
                }
            ]
            BuildTestDataApi.setInitialPropsResolver(new ClosurePropsResolver(testDataResolvers))
        expect: 
            Simple.build().name == 'works'
    }

    def 'map props resolver'() {
        given:
            Map<Class,Map> testData = [
                (Simple.class):[name:'works']
            ]
            BuildTestDataApi.setInitialPropsResolver(new MapPropsResolver(testData))
        expect:
            Simple.build().name == 'works'
    }
}
