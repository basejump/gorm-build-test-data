package org.grails.gorm.buildtestdata.handler

import grails.gorm.validation.ConstrainedProperty
import grails.gorm.validation.Constraint
import groovy.transform.CompileStatic
import org.grails.gorm.buildtestdata.BuildTestDataContext

@CompileStatic
class SizeConstraintHandler extends AbstractHandler {

    MinSizeConstraintHandler minSizeConstraintHandler = new MinSizeConstraintHandler()
    MaxSizeConstraintHandler maxSizeConstraintHandler = new MaxSizeConstraintHandler()

    @Override
    void handle(Object instance, String propertyName, Constraint appliedConstraint, ConstrainedProperty constrainedProperty, BuildTestDataContext ctx) {
        handle(instance,propertyName,constrainedProperty,ctx)
    }

    void handle(Object instance, String propertyName, ConstrainedProperty constrainedProperty, BuildTestDataContext ctx) {
        minSizeConstraintHandler.handle(
            instance, propertyName, constrainedProperty, ctx, constrainedProperty.size.min() as int
        )
        maxSizeConstraintHandler.handle(
            instance, propertyName, constrainedProperty, ctx, constrainedProperty.size.max() as int
        )
    }
}
