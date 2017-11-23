package org.grails.gorm.buildtestdata.handler

import grails.gorm.validation.Constrained
import grails.gorm.validation.Constraint
import org.grails.datastore.mapping.model.MappingContext
import org.grails.datastore.mapping.model.PersistentEntity
import org.grails.datastore.mapping.model.PersistentProperty
import org.grails.datastore.mapping.model.types.Association
import org.grails.datastore.mapping.model.types.Embedded
import org.grails.datastore.mapping.model.types.ManyToMany
import org.grails.datastore.mapping.model.types.ManyToOne
import org.grails.datastore.mapping.model.types.OneToOne
import org.grails.gorm.buildtestdata.BuildTestDataContext


class PersistentEntityNullableConstraintHandler extends NullableConstraintHandler {
    PersistentEntity persistentEntity
    MappingContext mappingContext

    PersistentEntityNullableConstraintHandler(PersistentEntity persistentEntity, MappingContext mappingContext) {
        this.persistentEntity = persistentEntity
        this.mappingContext = mappingContext
    }

    @Override
    Object determineNonStandardValue(Object instance, String propertyName, Constraint appliedConstraint, Constrained constrainedProperty, BuildTestDataContext ctx) {
        PersistentProperty persistentProperty = persistentEntity.getPropertyByName(propertyName)
        if(persistentProperty instanceof Association){
            switch (persistentProperty){
                
            }
        }
        
        return super.determineNonStandardValue(instance, propertyName, appliedConstraint, constrainedProperty, ctx)
    }
//    @Override
//    Object determineNonStandardValue(Object instance, String propertyName, Constraint appliedConstraint, Constrained constrainedProperty, BuildTestDataContext ctx) {
//        return super.determineNonStandardValue(instance, propertyName, appliedConstraint, constrainedProperty, ctx)
//    }
//
//    Object determineAssociationValue(Object instance, PersistentProperty persistentProperty) {
//        Association association = (Association) persistentProperty
//        switch (association.type) {
//            case OneToOne:
//
//                break
//            case ManyToOne:
//
//                break
//            case Embedded:
//
//                break
//            case ManyToMany:
//
//                break
//        }
//    }
}
