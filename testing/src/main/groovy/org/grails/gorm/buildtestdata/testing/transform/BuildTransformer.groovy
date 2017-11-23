package org.grails.gorm.buildtestdata.testing.transform

import static org.codehaus.groovy.ast.ClassHelper.make
import static org.codehaus.groovy.ast.tools.GeneralUtils.block
import static org.codehaus.groovy.ast.tools.GeneralUtils.castX
import static org.codehaus.groovy.ast.tools.GeneralUtils.classX
import static org.codehaus.groovy.ast.tools.GeneralUtils.returnS
import static org.codehaus.groovy.ast.tools.GenericsUtils.makeClassSafeWithGenerics

import grails.testing.gorm.DomainUnitTest
import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.Parameter
import org.codehaus.groovy.ast.expr.ListExpression
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.tools.GenericsUtils
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.transform.GroovyASTTransformation
import org.grails.gorm.buildtestdata.ast.AbstractAnnotationMarkedTransformation
import org.grails.gorm.buildtestdata.testing.Build
import org.grails.gorm.buildtestdata.testing.BuildTestDataTest

@CompileStatic
@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class BuildTransformer extends AbstractAnnotationMarkedTransformation{
    BuildTransformer() {
        super(Build)
    }

    @Override
    void visit(ClassNode classNode, AnnotationNode anno) {
        List<ClassNode> classList = getClassList(anno,'value');
        
        addTrait(classNode,BuildTestDataTest,false)
        createMockSupportClasses("getClassesToBuildTestData",classNode,classList)
        
        boolean shouldExcludeDomainUnitTest = memberHasValue(anno,'excludeDomainUnitTest',true)
        
        if(!shouldExcludeDomainUnitTest){
            ClassNode domainUnitTestTrait = makeClassSafeWithGenerics(DomainUnitTest,classList.get(0).plainNodeReference)
            
            addInterface(classNode,domainUnitTestTrait)
            createMockSupportClasses("getDomainClassesToMock",classNode,classList)
        }
    }

    
    private void createMockSupportClasses(String methodName,ClassNode cNode, List<ClassNode> classNodes) {
        BlockStatement blockStatement = block(
            returnS(
                castX(
                    make(Class[],false),
                    new ListExpression(classNodes.collect{classX(it.plainNodeReference)} as List)
                )
            )
        )
        cNode.addMethod(
            methodName, 
            ACC_PUBLIC,
            GenericsUtils.nonGeneric(make(Class[],false)), 
            Parameter.EMPTY_ARRAY,
            ClassNode.EMPTY_ARRAY,
            blockStatement
        );
    }
    
}
