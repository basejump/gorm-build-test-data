package org.grails.gorm.buildtestdata.handler

import grails.gorm.validation.ConstrainedProperty
import grails.gorm.validation.Constraint
import groovy.transform.CompileStatic
import org.grails.gorm.buildtestdata.BuildTestDataContext

@CompileStatic
class MaxSizeConstraintHandler extends AbstractHandler {

    @Override
    void handle(Object instance, String propertyName, Constraint appliedConstraint, ConstrainedProperty constrainedProperty, BuildTestDataContext ctx) {
        handle(instance, propertyName, constrainedProperty, ctx,constrainedProperty.getMaxSize())
    }

    void handle(Object instance, String propertyName, ConstrainedProperty constrainedProperty, BuildTestDataContext ctx, int maxSize) {
        def value = getValue(instance, propertyName)
        if (value instanceof Collection && value.size() > maxSize) {
            setValue(instance, propertyName, value.drop(value.size() - maxSize))
        }
    }
}
