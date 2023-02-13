package org.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ioc {
    private Ioc(){}
    static TestLoggingInterface createInstance(){
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging(), Log.class);
        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                    new Class<?>[]{TestLoggingInterface.class}, handler);
    }
    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface testInter;
        private final List<Method> methods;
        DemoInvocationHandler(TestLoggingInterface someInter, Class<? extends Annotation> annotation){
            this.testInter = someInter;
            this.methods = getAnnotatedMethod(someInter.getClass(), annotation);
        }

        private List<Method> getAnnotatedMethod(Class<?> clazz, Class<? extends Annotation> annotation){
            List<Method> methods = new ArrayList<>();
            for(Method m : clazz.getDeclaredMethods()){
                if (m.getAnnotation(annotation)!=null){
                    methods.add(m);
                }
            }
            return methods;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for(Method m : methods){
                if (m.getName().equals(method.getName())){
                    if (Arrays.equals(m.getParameterTypes(), method.getParameterTypes())){
                        System.out.println("\nExecuted method: " + m.getName() + ", param:" + Arrays.toString(args));
                    }
                }
            }
            return method.invoke(testInter, args);
        }
    }
}
