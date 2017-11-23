package org.grails.buildtestdata

import org.grails.buildtestdata.domain.Basics
import org.grails.gorm.buildtestdata.testing.Build
import spock.lang.Specification

@Build(Basics)
class BasicsSpec extends Specification{
    
    def 'it will build'(){
        given:
            Basics basics = Basics.build()
        expect:
            basics.id==1            
    }
}
