package org.grails.buildtestdata.domain.toone

import grails.gorm.annotation.Entity
import org.grails.datastore.mapping.model.types.ToMany

@Entity
class ManyToOne {

    One one

    static belongsTo = [toMany: ToMany]

    static graphql = true
}
