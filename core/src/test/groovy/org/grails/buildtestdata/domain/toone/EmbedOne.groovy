package org.grails.buildtestdata.domain.toone

import grails.gorm.annotation.Entity

@Entity
class EmbedOne {

    Embed embed
    EmbedNonEntity embedNonEntity

    static embedded = ['embed', 'embedNonEntity']

    static graphql = true
}
