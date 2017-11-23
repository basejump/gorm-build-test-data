package org.grails.gorm.buildtestdata.handler

import grails.gorm.validation.ConstrainedProperty
import grails.gorm.validation.Constraint
import groovy.transform.CompileStatic
import org.grails.gorm.buildtestdata.BuildTestDataContext

@CompileStatic
interface ConstraintHandler {
    void handle(Object instance, String propertyName, Constraint appliedConstraint,
                ConstrainedProperty constrainedProperty, BuildTestDataContext ctx)
}
