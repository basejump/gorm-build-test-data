package org.grails.gorm.buildtestdata

import org.grails.gorm.buildtestdata.builders.GormEntityTestDataBuilder
import org.grails.gorm.buildtestdata.builders.PogoTestDataBuilder

final class BuildTestDataApi {
    static InitialPropsResolver initialPropsResolver
    
    static final Map builders = new HashMap<Class,TestDataBuilder>()
    static final List<TestDataBuilderFactory> factories = []
    static{
        factories.add(new GormEntityTestDataBuilder.Factory())
        factories.add(new PogoTestDataBuilder.Factory())
    }
    
    static void setInitialPropsResolver(InitialPropsResolver initialPropsResolver){
        this.initialPropsResolver = initialPropsResolver
    }
    static getInitialPropsResolver(){
        if(initialPropsResolver == null){
            initialPropsResolver = new InitialPropsResolver.EmptyInitialPropsResolver() 
        }
        initialPropsResolver
    }
    
    static TestDataBuilder findBuilder(Class clazz){
        if(!builders.containsKey(clazz)){
            builders.put(clazz,createBuilder(clazz))
        }
        builders.get(clazz)
    }

    static TestDataBuilder createBuilder(Class clazz){
        for(factory in factories.sort()){
            if(factory.supports(clazz)){
                return factory.build(clazz)
            }
        }
    }
}
