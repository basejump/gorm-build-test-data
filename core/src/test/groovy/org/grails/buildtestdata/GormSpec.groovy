package org.grails.buildtestdata

import grails.testing.gorm.DomainUnitTest
import org.grails.buildtestdata.domain.Basics
import org.grails.buildtestdata.domain.tomany.Many
import org.grails.buildtestdata.domain.tomany.ManyToMany
import org.grails.buildtestdata.domain.tomany.ToMany
import org.grails.buildtestdata.domain.toone.ManyToOne
import org.grails.buildtestdata.domain.toone.One
import spock.lang.Specification

class GormSpec extends Specification implements DomainUnitTest<Basics>{
    @Override
    Class[] getDomainClassesToMock() {
        [Basics, ToMany, Many, ManyToOne, ManyToMany, One]
    }

    def 'builds all basic types'(){
        expect: 
            Basics.build().id == 1
    }
    def 'toMany'(){
        given:
            def instance = ToMany.build()
        expect:
            instance.id == 1
            instance.many.size() == 5
    }
    def 'ManyToMany'(){
        given:
            ManyToMany instance = ManyToMany.build()
        expect:
            instance.id == 1
            instance.toMany.size() == 2
    }
}
