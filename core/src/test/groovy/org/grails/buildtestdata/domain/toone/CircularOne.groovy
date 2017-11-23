package org.grails.buildtestdata.domain.toone

import grails.gorm.annotation.Entity

@Entity
class CircularOne {

    CircularOne one
    
    static constraints = {
        one nullable: true
    }
}
