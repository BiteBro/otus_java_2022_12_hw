package org.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class Ioc {
    private Ioc(){}
    static TestLoggingInterface createInstance(){
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging(), Log.class);
        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                    new Class<?>[]{TestLoggingInterface.class}, handler);
    }
    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface testInter;
        private final Set<String> methods;

        DemoInvocationHandler(TestLoggingInterface someInter, Class<? extends Annotation> annotation){
            this.testInter = someInter;
            this.methods = getAnnotatedMethod(someInter.getClass(), annotation);
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (methods.contains(method.getName() + Arrays.toString(method.getParameters()))){
                System.out.println("\nExecuted method: " + method.getName() + ", param:" + Arrays.toString(args));
            }
            return method.invoke(testInter, args);
        }
        private Set<String> getAnnotatedMethod(Class<?> clazz, Class<? extends Annotation> annotation){
            Set<String> methods = new HashSet<>();
            for(Method m : clazz.getDeclaredMethods()){
                if (m.getAnnotation(annotation)!=null){
                    methods.add(m.getName() + Arrays.toString(m.getParameters()));
                }
            }
            return methods;
        }
    }
}
