package org.grails.gorm.buildtestdata.handler

import grails.gorm.validation.ConstrainedProperty
import grails.gorm.validation.Constraint
import org.codehaus.groovy.runtime.InvokerHelper
import org.grails.gorm.buildtestdata.BuildTestDataContext

abstract class AbstractHandler implements ConstraintHandler {

    @Override
    void handle(Object instance, String propertyName, Constraint appliedConstraint, ConstrainedProperty constrainedProperty, BuildTestDataContext ctx) {
        handle(instance, propertyName, appliedConstraint, constrainedProperty)
        handle(instance, propertyName, appliedConstraint)
        handle(instance, propertyName, constrainedProperty)
        handle(instance, propertyName)
    }

    void handle(Object instance, String propertyName, Constraint appliedConstraint, ConstrainedProperty constrainedProperty) {}

    void handle(Object instance, String propertyName, Constraint appliedConstraint) {}

    void handle(Object instance, String propertyName, ConstrainedProperty constrainedProperty) {}

    void handle(Object instance, String propertyName) {}

    void setValue(Object instance, String property, Object value) {
        InvokerHelper.setProperty(instance, property, value)
    }

    Object getValue(Object instance, String property) {
        InvokerHelper.getProperty(instance, property);
    }
}
