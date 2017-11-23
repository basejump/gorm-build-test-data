package org.grails.buildtestdata

import org.grails.gorm.buildtestdata.BuildTestData
import spock.lang.Specification

class PogoSpec extends Specification{
    def 'nesting'(){
        expect:
            DoesImplement.build().doesNotImplement==null
            DoesImplement.build([
                doesNotImplement:[name:'works']
            ]).doesNotImplement.name == 'works'        
    }
}

class DoesImplement implements BuildTestData<DoesImplement>{
    DoesNotImplement doesNotImplement
}
class DoesNotImplement{
    String name
    Date date
}