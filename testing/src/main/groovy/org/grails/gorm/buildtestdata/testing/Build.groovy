package org.grails.gorm.buildtestdata.testing

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.RUNTIME)
@GroovyASTTransformationClass("org.grails.buildtestdata.testing.transform.BuildTransformer")
@interface Build {
    Class[] value()
    boolean excludeDomainUnitTest() default false
}