package com.cfb.myprocess.model;

import com.cfb.myprocess.TypeUtil;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by fengbincao on 2017/1/5.
 * AnnotatedClass的作用，
 * 我们在解析XML，解析Json的时候数据解析完之后需要以对象的形式表示出来，
 * 这里也一样，@BindView用来标记类成员，一个类下可以有多个成员，
 * 好比一个Activity中可以有多个控件，一个容器下有多个控件等。如下：
 */

public class AnnotatedClass {

    // 类名
    public TypeElement mClassElement;

    // 成员变量集合，被@BindView注解标记的变量集合
    public List<BindViewField> mField;

    // 成员变量集合，被@OnClick注解标记的方法集合
    public List<OnClickMethod> mMethod;

    // 元素辅助类
    public Elements mElementsUtils;

    public AnnotatedClass(TypeElement classElement, Elements elementUtils) {
        this.mClassElement = classElement;
        this.mElementsUtils = elementUtils;
        this.mField = new ArrayList<>();
        this.mMethod = new ArrayList<>();
    }

    // 获取当前类的全名
    public String getFullClassName() {
        return mClassElement.getQualifiedName().toString();
    }

    // 添加一个成员变量
    public void addField(BindViewField field) {
        mField.add(field);
    }

    // 添加一个方法变量
    public void addMethod(OnClickMethod method) {
        mMethod.add(method);
    }

    // 输出java
    // 生成代码的关键部分
    public JavaFile generateFinder() {
        //构建方法
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("inject")
                .addModifiers(Modifier.PUBLIC)      //添加描述
                .addAnnotation(Override.class)      //添加注解
                .addParameter(TypeName.get(mClassElement.asType()), "host", Modifier.FINAL)  //添加参数
                .addParameter(TypeName.OBJECT, "source")                                     //添加参数
                .addParameter(TypeUtil.FINDER, "finder");                                    //添加参数

        // 添加类成员
        for(BindViewField field : mField) {
            // 添加一行
            methodBuilder.addStatement("host.$N=($T)finder.findView(source,$L)", field.getFieldName()
                    , ClassName.get(field.getFieldType()), field.getResId());
        }

        // 添加声明的Listener
        if(mMethod.size() > 0) {
            methodBuilder.addStatement("$T listener", TypeUtil.ONCLICK_LISTENER);
        }

        for(OnClickMethod method : mMethod) {
            TypeSpec listener = TypeSpec.anonymousClassBuilder("")
                    .addSuperinterface(TypeUtil.ONCLICK_LISTENER)
                    .addMethod(MethodSpec.methodBuilder("onClick")
                        .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC)
                        .returns(TypeName.VOID)
                        .addParameter(TypeUtil.ANDROID_VIEW, "view")
                        .addStatement("host.$N()", method.getMethodName())
                        .build())
                    .build();
            methodBuilder.addStatement("listener = $L ", listener);
            for (int id : method.ids) {
                methodBuilder.addStatement("finder.findView(source,$L).setOnClickListener(listener)", id);
            }
        }

        String packageName = getPackageName(mClassElement);
        String className = getClassName(mClassElement,packageName);
        ClassName bindClassName = ClassName.get(packageName,className);

        // 构建类
        TypeSpec finderClass = TypeSpec.classBuilder(bindClassName.simpleName() + "$$Injector")
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(TypeUtil.INJECTOR, TypeName.get(mClassElement.asType())))
                .addMethod(methodBuilder.build())
                .build();
        return JavaFile.builder(packageName, finderClass).build();
    }

    // 包名
    public String getPackageName(TypeElement type) {
        return mElementsUtils.getPackageOf(type).getQualifiedName().toString();
    }

    // 类名
    public String getClassName(TypeElement type,String packageName) {
        int packageLen = packageName.length() + 1;
        return type.getQualifiedName().toString().substring(packageLen).replace('.', '$');
    }
}
