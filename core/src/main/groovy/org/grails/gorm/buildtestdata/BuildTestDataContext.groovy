package org.grails.gorm.buildtestdata

import grails.gorm.validation.ConstrainedProperty
import org.codehaus.groovy.runtime.InvokerHelper

class BuildTestDataContext {
    Map<String,?> data
    
    Set<String> cyclic = []   
    Object target
    
    BuildTestDataContext(Map<String,?> data){
        this.data=data
    }
    
    boolean isCyclic(Class clazz,String property){
        !cyclic.add("${clazz.name}#${property}")
    }
    boolean removeCyclicReference(Class clazz,String property){
        cyclic.remove("${clazz.name}#${property}")
    }
    
    Object satisfyNested(Object instance, String property, ConstrainedProperty constrainedProperty){        
        if(isCyclic(instance.class,property)){
            throw new RuntimeException("Cyclic build detected for ${instance.class.name} and property ${property}!")
        }
        Object prevTarget = target
        Object prevData = data
        try{
            data = ((Map<String,?>)data[property])?:[:]
            target = InvokerHelper.getProperty(instance,property)
            
            BuildTestDataApi.findBuilder(constrainedProperty.propertyType).build(this)            
        }
        finally {
            data=prevData
            target=prevTarget
            removeCyclicReference(instance.class,property)
        }
    }
}
