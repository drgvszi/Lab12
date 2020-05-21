package org.example;

import java.lang.reflect.Method;

public class AnnotationController {
    public void initializeButton(Object object) throws Exception {
        Class clazz = object.getClass();
        if (clazz.isAnnotationPresent(Width.class)) {
            Width text = (Width) clazz.getAnnotation(Width.class);
            Method methodToFind = clazz.getMethod("setPrefWidth", double.class);
            if (methodToFind != null) {
                methodToFind.invoke(object, text.value());
            }
        }
        if (clazz.isAnnotationPresent(Height.class)) {
            Height text = (Height) clazz.getAnnotation(Height.class);
            Method methodToFind = clazz.getMethod("setPrefHeight", double.class);
            if (methodToFind != null) {
                methodToFind.invoke(object, text.value());
            }
        }
        if (clazz.isAnnotationPresent(Text.class)) {
            Text text = (Text) clazz.getAnnotation(Text.class);
            Method methodToFind = clazz.getMethod("setText", String.class);
            if (methodToFind != null) {
                methodToFind.invoke(object, text.value());
            }
        }
    }

}
