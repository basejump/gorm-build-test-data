package org.grails.gorm.buildtestdata.testing.spock

import org.grails.gorm.buildtestdata.testing.BuildTestDataTest
import org.spockframework.runtime.extension.AbstractGlobalExtension
import org.spockframework.runtime.model.SpecInfo

class SpockExtension extends AbstractGlobalExtension {
    BuildTestDataSetupInterceptor buildTestDataSetupInterceptor = new BuildTestDataSetupInterceptor()
    BuildTestDataCleanupInterceptor buildTestDataCleanupInterceptor = new BuildTestDataCleanupInterceptor()
    
    @Override
    void visitSpec(SpecInfo spec) {
        if(BuildTestDataTest.isAssignableFrom(spec.reflection)){
            spec.addSetupInterceptor(buildTestDataSetupInterceptor)
            spec.addCleanupInterceptor(buildTestDataCleanupInterceptor)
        }
        super.visitSpec(spec)
    }
}
