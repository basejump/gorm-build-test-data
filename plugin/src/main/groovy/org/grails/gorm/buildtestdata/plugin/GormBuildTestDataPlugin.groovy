package org.grails.gorm.buildtestdata.plugin

import grails.plugins.Plugin

class GormBuildTestDataPlugin extends Plugin {

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "3.3.0 > *"


    // TODO Fill in these fields
    def title = "Gorm Build Test Data" // Headline display name of the plugin
    def author = "Dennie de Lange"
    def authorEmail = "dennie@tkvw.nl"
    def description = '''\
The new build-test-data plugin
'''
    def profiles = ['web']

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/gorm-build-test-data"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]


    Closure doWithSpring() {{ ->

    }}
}
