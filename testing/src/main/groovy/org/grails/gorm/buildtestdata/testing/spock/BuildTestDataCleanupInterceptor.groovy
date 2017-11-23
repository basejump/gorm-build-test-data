package org.grails.gorm.buildtestdata.testing.spock

import org.grails.gorm.buildtestdata.testing.BuildTestDataTest
import org.spockframework.runtime.extension.IMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation

class BuildTestDataCleanupInterceptor implements IMethodInterceptor{
    @Override
    void intercept(IMethodInvocation invocation) throws Throwable {
        ((BuildTestDataTest)invocation.instance).cleanupBuildTestDataApi()
    }
}
