package org.grails.gorm.buildtestdata.handler

import grails.gorm.validation.ConstrainedProperty
import groovy.transform.CompileStatic

@CompileStatic
class RangeConstraintHandler extends AbstractHandler {

    @Override
    void handle(Object instance, String propertyName, ConstrainedProperty constrainedProperty) {
        setValue(instance,propertyName,constrainedProperty.range.from)
    }
}
