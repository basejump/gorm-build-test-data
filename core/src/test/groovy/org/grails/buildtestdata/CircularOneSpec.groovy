package org.grails.buildtestdata

import grails.testing.gorm.DomainUnitTest
import org.grails.buildtestdata.domain.toone.CircularIndirect
import org.grails.buildtestdata.domain.toone.CircularIndirectLink
import org.grails.buildtestdata.domain.toone.CircularMany
import org.grails.buildtestdata.domain.toone.CircularOne
import spock.lang.Specification

class CircularOneSpec extends Specification implements DomainUnitTest<CircularOne>{
    @Override
    Class[] getDomainClassesToMock() {
        [CircularOne, CircularIndirect, CircularIndirectLink, CircularMany]
    }

    def 'circular one'(){
        given:
            CircularOne one = CircularOne.build()
        expect:
            one.id == 1
            one.one.id==1
    }
    def 'circular indirect'(){
        given:
            CircularIndirect circularIndirect = CircularIndirect.build()
        expect:
            circularIndirect.id==1
            circularIndirect.circularIndirectLink.id==1
            circularIndirect.circularIndirectLink.circularIndirect.id == 1
    }

    def 'circular many'(){
        given:
            CircularMany circularMany = CircularMany.build()
        expect:
            circularMany.id==1
            circularMany.childs.size()==5
            circularMany.childs.get(4).id == 1
    }
}
