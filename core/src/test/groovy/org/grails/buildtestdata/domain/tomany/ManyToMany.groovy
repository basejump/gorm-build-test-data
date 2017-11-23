package org.grails.buildtestdata.domain.tomany

import grails.gorm.annotation.Entity
import org.grails.gorm.buildtestdata.BuildTestData

@Entity
class ManyToMany implements BuildTestData<ManyToMany>{

    static hasMany = [toMany: ToMany]

    static belongsTo = [ToMany]
    
    static constraints = {
        toMany minSize: 2,nullable: false
    }
}
