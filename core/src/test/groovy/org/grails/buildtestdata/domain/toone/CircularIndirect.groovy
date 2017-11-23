package org.grails.buildtestdata.domain.toone

import grails.gorm.annotation.Entity
import org.grails.gorm.buildtestdata.BuildTestData

@Entity
class CircularIndirect implements BuildTestData<CircularIndirect>{
    CircularIndirectLink circularIndirectLink
}
