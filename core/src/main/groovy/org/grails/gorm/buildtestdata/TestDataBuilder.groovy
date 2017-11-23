package org.grails.gorm.buildtestdata

interface TestDataBuilder {
    def build(BuildTestDataContext ctx)
    def buildLazy(BuildTestDataContext ctx)
}