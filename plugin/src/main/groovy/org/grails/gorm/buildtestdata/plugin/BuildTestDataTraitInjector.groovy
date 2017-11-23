package org.grails.gorm.buildtestdata.plugin

import grails.compiler.traits.TraitInjector
import org.grails.gorm.buildtestdata.BuildTestData

class BuildTestDataTraitInjector implements TraitInjector{
    @Override
    Class getTrait() {
        return BuildTestData
    }

    @Override
    String[] getArtefactTypes() {
        ['Domain'] as String[] 
    }
}