package org.grails.buildtestdata.domain.toone

import grails.gorm.annotation.Entity

@Entity
class HasOne {

    static hasOne = [one: BelongsToHasOne]

    static graphql = true
}
