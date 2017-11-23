package org.grails.gorm.buildtestdata.propsresolver

import org.grails.gorm.buildtestdata.InitialPropsResolver

class MapPropsResolver implements InitialPropsResolver{
    Map data
    MapPropsResolver(Map data){
        this.data=data
    }
    @Override
    Map<String, ?> getInitialProps(Class target) {
        return data.get(target)
    }
}
