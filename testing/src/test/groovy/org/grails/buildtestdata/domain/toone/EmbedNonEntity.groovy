package org.grails.buildtestdata.domain.toone

import grails.gorm.dirty.checking.DirtyCheck

@DirtyCheck
class EmbedNonEntity {
    String name
}
