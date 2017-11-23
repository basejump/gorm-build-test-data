package org.grails.gorm.buildtestdata

interface InitialPropsResolver {
    Map<String,?> getInitialProps(Class target)
    
    static class EmptyInitialPropsResolver implements InitialPropsResolver{
        @Override
        Map<String, ?> getInitialProps(Class target) {
            return null
        }
    }
}