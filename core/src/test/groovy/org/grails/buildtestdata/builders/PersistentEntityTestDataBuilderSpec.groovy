package org.grails.buildtestdata.builders

import org.grails.buildtestdata.domain.toone.CircularOne
import org.grails.buildtestdata.domain.toone.One
import org.grails.buildtestdata.domain.toone.ToOne
import org.grails.buildtestdata.testing.Build
import spock.lang.Specification

@Build(value=[ToOne,One,CircularOne])
class PersistentEntityTestDataBuilderSpec extends Specification{

    def 'toOne'(){
        expect:
            ToOne.build()
    }
}
