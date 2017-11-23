package org.grails.buildtestdata.domain.toone

import grails.gorm.annotation.Entity
import org.grails.buildtestdata.domain.tomany.Many

@Entity
class Embed {

    One one

    static hasMany = [many: Many]
}
