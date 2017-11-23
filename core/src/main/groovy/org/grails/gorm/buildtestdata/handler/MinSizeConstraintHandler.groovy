package org.grails.gorm.buildtestdata.handler

import grails.gorm.validation.ConstrainedProperty
import grails.gorm.validation.Constraint
import org.codehaus.groovy.runtime.InvokerHelper
import org.grails.gorm.buildtestdata.BuildTestDataContext

class MinSizeConstraintHandler extends AbstractHandler {
//    void handle(domain, propertyName, appliedConstraint, constrainedProperty = null, circularCheckList = null) {
//        switch (domain."$propertyName".class) {
//            case String:
//            // try not to mangle email addresses and urls
//            def appliedConstraints = constrainedProperty?.appliedConstraints
//            if (appliedConstraints?.find {it.name == ConstrainedProperty.URL_CONSTRAINT}) {
//                domain."$propertyName" = 'http://' + 'a'.padRight(appliedConstraint.minSize - 11, 'a') + '.com'
//            } else if (appliedConstraints?.find {it.name == ConstrainedProperty.EMAIL_CONSTRAINT}) {
//                domain."$propertyName" = domain."$propertyName".padLeft(appliedConstraint.minSize, 'a')
//            } else {
//                domain."$propertyName" = domain."$propertyName".padRight(appliedConstraint.minSize, '.')
//            }
//            break
//
//            default:
//            def size = domain."$propertyName".size()
//            if (size < appliedConstraint.minSize) {
//                def defDomain = new DefaultGrailsDomainClass( domain.class )
//                def domainProp = defDomain.properties.find {it.name == constrainedProperty?.propertyName }
//                ((size+1)..appliedConstraint.minSize).each {
//                    domain."addTo${capitalize(propertyName)}"(domainProp?.referencedPropertyType.buildWithoutSave([:], circularCheckList))
//                }
//            }
//        }
//    }

    @Override
    void handle(Object instance, String propertyName, Constraint appliedConstraint, ConstrainedProperty constrainedProperty, BuildTestDataContext ctx) {
        handle(instance,propertyName,constrainedProperty,ctx,constrainedProperty.minSize)
    }
    void handle(Object instance, String propertyName,ConstrainedProperty constrained,BuildTestDataContext ctx,int minSize) {
        switch(constrained.propertyType){
            case String:
                String value
                if(constrained.hasAppliedConstraint(ConstrainedProperty.URL_CONSTRAINT)){
                    value = 'http://' + 'a'.padRight(minSize - 11, 'a') + '.com'
                }
                else if(constrained.hasAppliedConstraint(ConstrainedProperty.EMAIL_CONSTRAINT)){
                    value = ((String)InvokerHelper.getProperty(instance,propertyName)).padLeft(minSize, 'a')
                }
                else{
                    value = ((String)InvokerHelper.getProperty(instance,propertyName)).padRight(minSize, '.')
                }
                InvokerHelper.setProperty(instance,propertyName,value)
                break
        }
    }
}