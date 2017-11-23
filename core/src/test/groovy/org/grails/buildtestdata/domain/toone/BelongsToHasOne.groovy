package org.grails.buildtestdata.domain.toone

import grails.gorm.annotation.Entity

@Entity
class BelongsToHasOne {

    static belongsTo = [one: HasOne]

    static graphql = true
}
