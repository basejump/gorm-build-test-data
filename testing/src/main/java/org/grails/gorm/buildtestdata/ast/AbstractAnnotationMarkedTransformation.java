package org.grails.gorm.buildtestdata.ast;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.SourceUnit;

public abstract class AbstractAnnotationMarkedTransformation extends AbstractTransformation{
    private final ClassNode annotationType;
    private final String annotationTypeName;

    public AbstractAnnotationMarkedTransformation(Class annotation){
        this.annotationType = ClassHelper.make(annotation);
        this.annotationTypeName = '@' + annotationType.getNameWithoutPackage();
    }

    @Override
    public void visit(ASTNode[] nodes, SourceUnit source) {
        init(nodes, source);

        AnnotatedNode parent = (AnnotatedNode) nodes[1];
        AnnotationNode anno = (AnnotationNode) nodes[0];

        if (!annotationType.equals(anno.getClassNode())) return;

        if (parent instanceof ClassNode) {
            ClassNode cNode = (ClassNode) parent;
            if (!checkNotInterface(cNode, annotationTypeName)) return;

            visit(cNode,anno);
        }        
    }
    public abstract void visit(ClassNode classNode,AnnotationNode anno);
}
