package org.grails.gorm.buildtestdata.builders

import org.grails.gorm.buildtestdata.handler.PersistentEntityNullableConstraintHandler
import grails.gorm.validation.Constrained
import grails.gorm.validation.ConstrainedProperty
import grails.gorm.validation.PersistentEntityValidator
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import org.grails.gorm.buildtestdata.BuildTestDataContext
import org.grails.gorm.buildtestdata.utils.ClassUtils
import org.grails.datastore.gorm.GormEntity
import org.grails.datastore.mapping.model.MappingContext
import org.grails.datastore.mapping.model.PersistentEntity
import org.springframework.validation.Validator

@CompileStatic
class GormEntityTestDataBuilder extends ConstraintsTestDataBuilder{
    static class Factory extends AbstractTestDataBuilderFactory<GormEntityTestDataBuilder>{
        Factory(){
            super(100)
        }
        
        @Override
        GormEntityTestDataBuilder build(Class target) {
            return new GormEntityTestDataBuilder(target)
        }

        @Override
        boolean supports(Class clazz) {
            GormEntity.isAssignableFrom(clazz)
        }
    }

    GormEntityTestDataBuilder(Class target) {
        super(target)        
        handlers.put(
            ConstrainedProperty.NULLABLE_CONSTRAINT, 
            new PersistentEntityNullableConstraintHandler(persistentEntity,mappingContext)
        )
    }

    @Override
    def build(BuildTestDataContext ctx) {
        GormEntity instance = (GormEntity) super.build(ctx)
        instance.save()
        instance
    }

    @Override
    Collection<String> findRequiredPropertyNames() {
        Collection<String> requiredPropertyNames = super.findRequiredPropertyNames()
        PersistentEntity persistentEntity = persistentEntity
        Collection<String> requiredPersistentFields = (Collection<String>)persistentEntity.getPersistentProperties().inject([]){acc,item->
            if(!item.nullable) acc.add(item.name)
            return acc
        }
        return requiredPropertyNames + requiredPersistentFields
    }

    @Override
    Map<String, ? extends Constrained> getConstraintsMap() {
        try{
            Validator validator = getValidator()
            
            if(validator instanceof PersistentEntityValidator){
                return ((PersistentEntityValidator)validator).constrainedProperties
            }
        }
        catch(Exception e){
            e.printStackTrace()
        }
        throw new RuntimeException("No constraints found for persistent entity ${target.name}. Make sure it's mocked and properly initialized.")
    }

    Validator getValidator(){
        mappingContext.getEntityValidator(persistentEntity)
    }
    
    MappingContext getMappingContext(){
        persistentEntity.getMappingContext()
    }
    
    PersistentEntity getPersistentEntity(){
        (PersistentEntity) ClassUtils.getStaticPropertyValue(target,'gormPersistentEntity')
    }

    @Override
    @CompileDynamic
    def buildLazy(BuildTestDataContext ctx) {
        (ctx.data? target.where(ctx.data):target.first()) ?:super.buildLazy(ctx)
    }
}
