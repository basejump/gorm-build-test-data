package org.grails.gorm.buildtestdata.builders

import org.grails.gorm.buildtestdata.TestDataBuilder
import org.grails.gorm.buildtestdata.TestDataBuilderFactory

abstract class AbstractTestDataBuilderFactory<T extends TestDataBuilder> implements TestDataBuilderFactory<T> {
    int importance
    AbstractTestDataBuilderFactory(int importance){        
        this.importance=importance
    }
    @Override
    int compareTo(TestDataBuilderFactory other) {
        if(other==this) return 0
        if(other instanceof AbstractTestDataBuilderFactory){
            return other.importance == this.importance?0:
                other.importance < this.importance? -1:1
        }
        return 0
    }

    @Override
    boolean supports(Class clazz) {
        return true
    }
}
