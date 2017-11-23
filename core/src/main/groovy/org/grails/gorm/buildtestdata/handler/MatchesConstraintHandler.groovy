package org.grails.gorm.buildtestdata.handler

import grails.gorm.validation.ConstrainedProperty
import grails.gorm.validation.Constraint
import groovy.transform.CompileStatic
import nl.flotsam.xeger.Xeger
import org.grails.gorm.buildtestdata.MockErrors

@CompileStatic
class MatchesConstraintHandler extends AbstractHandler {

    @Override
    void handle(Object instance, String propertyName, Constraint appliedConstraint, ConstrainedProperty constrainedProperty) {
        if(!appliedConstraint.validate(instance,getValue(instance,propertyName),new MockErrors(this))){
            Xeger generator = new Xeger(constrainedProperty.matches)
            setValue(instance,propertyName,generator.generate())
        }
    }
}
