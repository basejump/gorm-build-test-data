package org.grails.gorm.buildtestdata.ast;

import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ClassNode;


public abstract class AbstractTraitApplyingASTTransformation extends AbstractAnnotationMarkedTransformation {
    private final Class[] traits;

    public AbstractTraitApplyingASTTransformation(Class annotation, Class[] traits) {
        super(annotation);
        this.traits = traits;
    }

    @Override
    public void visit(ClassNode classNode, AnnotationNode anno) {
        for (int i = 0; i < this.traits.length; i++) {
            addTrait(classNode, this.traits[i],false);
        }
    }
}
