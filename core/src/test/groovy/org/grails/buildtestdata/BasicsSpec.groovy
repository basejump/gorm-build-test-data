package org.grails.buildtestdata

import grails.testing.gorm.DomainUnitTest
import org.grails.buildtestdata.domain.Basics
import spock.lang.Specification

class BasicsSpec extends Specification implements DomainUnitTest<Basics> {
    
    def 'it will build'(){
        given:
            Basics basics = Basics.build()
        expect:
            basics.id==1            
    }
}
