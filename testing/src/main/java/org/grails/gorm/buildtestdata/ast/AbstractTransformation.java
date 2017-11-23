package org.grails.gorm.buildtestdata.ast;

import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.GenericsType;
import org.codehaus.groovy.ast.tools.GenericsUtils;
import org.codehaus.groovy.transform.AbstractASTTransformation;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractTransformation extends AbstractASTTransformation{
    public static boolean addInterface(ClassNode classNode, ClassNode interfaceNode){
        boolean traitsAdded = false;
        boolean implementsTrait = false;
        boolean traitNotLoaded = false;
        try {
            implementsTrait = classNode.declaresInterface(interfaceNode);
        } catch (Throwable e) {
            // if we reach this point, the trait injector could not be loaded due to missing dependencies (for example missing servlet-api). This is ok, as we want to be able to compile against non-servlet environments.
            traitNotLoaded = true;
        }
        if (!implementsTrait && !traitNotLoaded) {
            classNode.addInterface(interfaceNode);
            traitsAdded=true;
        }
        return traitsAdded;
    }
    
    public static boolean addTrait(ClassNode classNode, Class trait,boolean includeGenerics) {
        ClassNode traitClassNode = ClassHelper.make(trait,includeGenerics);
        return addTrait(classNode,traitClassNode);
    }
    public static boolean addTrait(ClassNode classNode, ClassNode traitClassNode) {
        boolean traitsAdded = false;
        boolean implementsTrait = false;
        boolean traitNotLoaded = false;
        try {
            implementsTrait = classNode.declaresInterface(traitClassNode);
        } catch (Throwable e) {
            // if we reach this point, the trait injector could not be loaded due to missing dependencies (for example missing servlet-api). This is ok, as we want to be able to compile against non-servlet environments.
            traitNotLoaded = true;
        }
        if (!implementsTrait && !traitNotLoaded) {
            final GenericsType[] genericsTypes = traitClassNode.getGenericsTypes();
            final Map<String, ClassNode> parameterNameToParameterValue = new LinkedHashMap<String, ClassNode>();
            if(genericsTypes != null) {
                for(GenericsType gt : genericsTypes) {
                    parameterNameToParameterValue.put(gt.getName(), classNode);
                }
            }
            classNode.addInterface(replaceGenericsPlaceholders(traitClassNode, parameterNameToParameterValue, classNode));
            traitsAdded = true;
        }
        return traitsAdded;
    }
    protected static ClassNode replaceGenericsPlaceholders(ClassNode type, Map<String, ClassNode> genericsPlaceholders) {
        return replaceGenericsPlaceholders(type, genericsPlaceholders, null);
    }

    protected static ClassNode replaceGenericsPlaceholders(ClassNode type, Map<String, ClassNode> genericsPlaceholders, ClassNode defaultPlaceholder) {
        System.err.println("replaceGenericsPlaceholders "+type+"  ; "+type.isUsingGenerics());
        if (type.isArray()) {
            return replaceGenericsPlaceholders(type.getComponentType(), genericsPlaceholders).makeArray();
        }

        if (!type.isUsingGenerics() && !type.isRedirectNode()) {
            return type.getPlainNodeReference();
        }
        
        if(type.isGenericsPlaceHolder() && genericsPlaceholders != null) {
            final ClassNode placeHolderType;
            if(genericsPlaceholders.containsKey(type.getUnresolvedName())) {
                placeHolderType = genericsPlaceholders.get(type.getUnresolvedName());
            } else {
                placeHolderType = defaultPlaceholder;
            }
            if(placeHolderType != null) {
                return placeHolderType.getPlainNodeReference();
            } else {
                return ClassHelper.make(Object.class).getPlainNodeReference();
            }
        }

        final ClassNode nonGen = type.getPlainNodeReference();
        System.err.println("nonGen "+nonGen+"  ; "+type.isGenericsPlaceHolder());

        if("java.lang.Object".equals(type.getName())) {
            nonGen.setGenericsPlaceHolder(false);
            nonGen.setGenericsTypes(null);
            nonGen.setUsingGenerics(false);
        } else {
            if(type.isUsingGenerics()) {
                GenericsType[] parameterized = type.getGenericsTypes();
                if (parameterized != null && parameterized.length > 0) {
                    GenericsType[] copiedGenericsTypes = new GenericsType[parameterized.length];
                    for (int i = 0; i < parameterized.length; i++) {
                        GenericsType parameterizedType = parameterized[i];
                        GenericsType copiedGenericsType = null;
                        if (parameterizedType.isPlaceholder() && genericsPlaceholders != null) {
                            ClassNode placeHolderType = genericsPlaceholders.get(parameterizedType.getName());
                            if(placeHolderType != null) {
                                copiedGenericsType = new GenericsType(placeHolderType.getPlainNodeReference());
                            } else {
                                copiedGenericsType = new GenericsType(ClassHelper.make(Object.class).getPlainNodeReference());
                            }
                        } else {
                            copiedGenericsType = new GenericsType(replaceGenericsPlaceholders(parameterizedType.getType(), genericsPlaceholders));
                        }
                        copiedGenericsTypes[i] = copiedGenericsType;
                    }
                    nonGen.setGenericsTypes(copiedGenericsTypes);
                }
            }
        }

        return nonGen;
    }
}
