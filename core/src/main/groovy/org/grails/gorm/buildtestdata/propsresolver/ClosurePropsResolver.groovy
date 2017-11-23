package org.grails.gorm.buildtestdata.propsresolver

import org.grails.gorm.buildtestdata.InitialPropsResolver

class ClosurePropsResolver implements InitialPropsResolver{
    
    Map<Class,Closure> initialProps

    ClosurePropsResolver(Map<Class,Closure> initialProps){
        this.initialProps=initialProps
    }
    
    @Override
    Map<String, ?> getInitialProps(Class target) {
        Closure c = initialProps.get(target)
        
        return c? c() : null
    }
}
