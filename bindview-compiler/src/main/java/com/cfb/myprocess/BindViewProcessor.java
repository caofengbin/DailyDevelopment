package com.cfb.myprocess;

import com.cfb.myannotation.BindView;
import com.cfb.myannotation.OnClick;
import com.cfb.myprocess.model.AnnotatedClass;
import com.cfb.myprocess.model.BindViewField;
import com.cfb.myprocess.model.OnClickMethod;
import com.google.auto.service.AutoService;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;


/**
 * Created by fengbincao on 2017/1/5.
 */
@AutoService(Processor.class)
public class BindViewProcessor extends AbstractProcessor {

    // 文件相关辅助类
    private Filer mFiler;

    // 元素相关辅助类
    private Elements mElementUtils;

    // 日志相关辅助类
    private Messager mMessager;

    // 解析的目标注解集合
    private Map<String,AnnotatedClass> mAnnotatedClassMap = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        mFiler = processingEnv.getFiler();
    }

    // 返回该注解处理器支持的注解集合(比较重要的一个方法)
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        // 对于大部分class而言，getCanonicalName和getName这两个方法没有什么不同的，
        // 但是对于array或内部类等就显示出来了。
        types.add(BindView.class.getCanonicalName());
        types.add(OnClick.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        mAnnotatedClassMap.clear();
        try {
            processBindView(roundEnvironment);
            processOnClick(roundEnvironment);
        } catch (IllegalArgumentException e) {
            error(e.getMessage());
            return true;
        }

        try {
            for(AnnotatedClass annotatedClass : mAnnotatedClassMap.values()) {
                info("generating file for %s", annotatedClass.getFullClassName());
                annotatedClass.generateFinder().writeTo(mFiler);
            }
        } catch (Exception e) {
            e.printStackTrace();
            error("Generate file failed,reason:%s", e.getMessage());
        }
        return true;
    }

    // 处理@BindView相关的注解
    private void processBindView(RoundEnvironment roundEnvironment) {
        for(Element element : roundEnvironment.getElementsAnnotatedWith(BindView.class)) {
            AnnotatedClass annotatedClass = getAnnotatedClass(element);
            BindViewField field = new BindViewField(element);
            annotatedClass.addField(field);
            System.out.print("p_element=" + element.getSimpleName() + ",p_set=" + element.getModifiers());
        }
    }

    private AnnotatedClass getAnnotatedClass(Element element) {
        TypeElement encloseElement = (TypeElement) element.getEnclosingElement();
        String fullClassName = encloseElement.getQualifiedName().toString();
        // 检查是否有过缓存
        AnnotatedClass annotatedClass = mAnnotatedClassMap.get(fullClassName);
        if(annotatedClass == null) {
            annotatedClass = new AnnotatedClass(encloseElement,mElementUtils);
            mAnnotatedClassMap.put(fullClassName, annotatedClass);
        }
        return annotatedClass;
    }

    // 处理@OnClick相关的注解
    private void processOnClick(RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(OnClick.class)) {
            AnnotatedClass annotatedClass = getAnnotatedClass(element);
            OnClickMethod method = new OnClickMethod(element);
            annotatedClass.addMethod(method);
        }
    }

    // 写错误日志
    private void error(String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args));
    }

    // 打印日志
    private void info(String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, args));
    }
}
