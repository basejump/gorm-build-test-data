package org.grails.buildtestdata.domain.toone

import grails.gorm.annotation.Entity
import org.grails.gorm.buildtestdata.BuildTestData

@Entity
class CircularOne implements BuildTestData<CircularOne>{

    CircularOne one
    
}
