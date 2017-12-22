package com.tangshengbo.design.template.reflect;

import org.apache.commons.lang.exception.ExceptionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tangshengbo on 2017/12/22.
 */
public abstract class AbstractLoader {

    public final void dataInitialing() {
        Method[] methods = getClass().getMethods();
//        for (Method m : methods) {
//            invoke(m);
//        }
        List<Method> methodList = Arrays.asList(methods);
        methodList.stream()
                .filter(this::isInitDataMethod)
                .forEach(this::invoke);
    }

    private boolean isInitDataMethod(Method method) {
        return method.getName().startsWith("init")
                && Modifier.isPublic(method.getModifiers())
                && method.getReturnType().equals(Void.TYPE)
                && !method.isVarArgs()
                && !Modifier.isAbstract(method.getModifiers());
    }

    private void invoke(Method method) {
        try {
            method.invoke(this);
        } catch (ReflectiveOperationException e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
    }
}
