package org.grails.buildtestdata.domain.tomany

import grails.gorm.annotation.Entity
import org.grails.buildtestdata.domain.toone.ManyToOne
import org.grails.gorm.buildtestdata.BuildTestData


@Entity
class ToMany implements BuildTestData<ToMany>{

    static hasMany = [many: Many,
                      manyToOne: ManyToOne,
                      manyToMany: ManyToMany,
                      enums: Enum,
                      strings: String]

    static constraints = {
        many minSize: 5, nullable:false
    }
}
