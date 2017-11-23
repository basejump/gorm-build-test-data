package org.grails.gorm.buildtestdata.handler

import grails.gorm.validation.ConstrainedProperty
import groovy.transform.CompileStatic

@CompileStatic
class SizeConstraintHandler extends AbstractHandler {

    MinSizeConstraintHandler minSizeConstraintHandler = new MinSizeConstraintHandler()
    MaxSizeConstraintHandler maxSizeConstraintHandler = new MaxSizeConstraintHandler()


    @Override
    void handle(Object instance, String propertyName, ConstrainedProperty constrainedProperty) {
        minSizeConstraintHandler.handle(
            instance, propertyName, constrainedProperty, constrainedProperty.size.min() as int
        )
        maxSizeConstraintHandler.handle(
            instance, propertyName, constrainedProperty, constrainedProperty.size.max() as int
        )
    }
}
