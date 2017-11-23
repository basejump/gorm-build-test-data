package org.grails.buildtestdata.domain.tomany

import grails.gorm.annotation.Entity
import org.grails.buildtestdata.domain.toone.One

@Entity
class Many {
    One one
}
