package org.grails.buildtestdata.domain.toone

import grails.gorm.annotation.Entity
import org.grails.gorm.buildtestdata.BuildTestData

@Entity
class CircularMany implements BuildTestData<CircularMany>{
    List<CircularMany> childs
    static hasMany = [childs:CircularMany]
    
    static constraints = {
        childs nullable: false,minSize: 5
    }
}
