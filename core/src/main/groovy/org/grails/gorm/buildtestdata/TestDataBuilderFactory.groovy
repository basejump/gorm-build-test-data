package org.grails.gorm.buildtestdata

interface TestDataBuilderFactory<T extends TestDataBuilder> extends Comparable<TestDataBuilderFactory> {
    T build(Class target)
    boolean supports(Class clazz)
}
