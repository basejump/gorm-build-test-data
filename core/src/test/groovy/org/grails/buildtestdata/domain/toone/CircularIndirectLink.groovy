package org.grails.buildtestdata.domain.toone

import grails.gorm.annotation.Entity

@Entity
class CircularIndirectLink {
    CircularIndirect circularIndirect
}
