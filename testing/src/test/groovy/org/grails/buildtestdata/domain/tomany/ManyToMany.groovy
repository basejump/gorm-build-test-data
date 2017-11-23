package org.grails.buildtestdata.domain.tomany

import grails.gorm.annotation.Entity

@Entity
class ManyToMany {

    static hasMany = [toMany: ToMany]

    static belongsTo = [ToMany]
}
