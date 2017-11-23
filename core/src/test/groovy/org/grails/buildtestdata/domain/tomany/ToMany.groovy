package org.grails.buildtestdata.domain.tomany

import grails.gorm.annotation.Entity
import org.grails.buildtestdata.domain.toone.ManyToOne


@Entity
class ToMany {

    static hasMany = [many: Many,
                      manyToOne: ManyToOne,
                      manyToMany: ManyToMany,
                      enums: Enum,
                      strings: String]

    static graphql = true
}
