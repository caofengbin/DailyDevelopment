package com.cfb.myprocess.model;

import com.cfb.myannotation.BindView;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * Created by fengbincao on 2017/1/5.
 * AnnotatedClass的成员变量
 *
 * 被BindView注解标记的字段的模型类
 */

public class BindViewField {

    private VariableElement mFieldElement;

    private int mResId;

    public BindViewField(Element element) throws IllegalArgumentException {
        if(element.getKind() != ElementKind.FIELD) {
            // 判断是否是类成员
            throw new IllegalArgumentException(String.format("Only field can be annotated with @%s",
                    BindView.class.getSimpleName()));
        }
        mFieldElement = (VariableElement) element;
        // 获取注解和值
        BindView bindView = mFieldElement.getAnnotation(BindView.class);
        mResId = bindView.value();
        if(mResId < 0) {
            throw new IllegalArgumentException(String.format("value() in %s for field % is not valid",
                    BindView.class.getSimpleName(), mFieldElement.getSimpleName()));
        }
    }

    public Name getFieldName() {
        return mFieldElement.getSimpleName();
    }

    public int getResId() {
        return mResId;
    }

    public TypeMirror getFieldType() {
        return mFieldElement.asType();
    }
}
