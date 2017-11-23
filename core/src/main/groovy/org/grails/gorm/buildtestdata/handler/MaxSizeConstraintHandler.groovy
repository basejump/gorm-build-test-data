package org.grails.gorm.buildtestdata.handler

import grails.gorm.validation.ConstrainedProperty
import groovy.transform.CompileStatic

@CompileStatic
class MaxSizeConstraintHandler extends AbstractHandler {

    @Override
    void handle(Object instance, String propertyName, ConstrainedProperty constrainedProperty) {
        handle(instance, propertyName, constrainedProperty, constrainedProperty.getMaxSize())
    }

    void handle(Object instance, String propertyName, ConstrainedProperty constrainedProperty, int maxSize) {
        def value = getValue(instance, propertyName)
        if (value instanceof Collection && value.size() > maxSize) {
            setValue(instance, propertyName, value.drop(value.size() - maxSize))
        }
    }
}
