package org.example;

import org.example.annotations.After;
import org.example.annotations.Before;
import org.example.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

public class TestExecutor {
    //Создает объект переданного класса.
    private static <T> T getObjectClass(Class<T> typeClass){
        try {
            return typeClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Формирование списка методов с определенной аннотацией.
    @SuppressWarnings("unchecked")
    private static List<Method> getAnnotatedMethod(Method[] methods, Class<?> annotation, Class<?> clazz) {
        List<Method> methodsVsAnnotation = new ArrayList<>();

        for(Method m : methods) {
            try {
                if (clazz.getMethod(m.getName()).isAnnotationPresent((Class<? extends Annotation>) annotation)) {
                    methodsVsAnnotation.add(m);
                }
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return methodsVsAnnotation;
    }

    //Запуск списка методов с определенной аннотацией. Возвращает колличество успешно пройденых тестов.
    private static int executeAnnotatedMethods(List<Method> method, Class<?> testedClass){
        int count = 0;
        System.out.println();
        for (Method m : method) {
            try {
                m.invoke(getObjectClass(testedClass));
                count++;
                System.out.println(m.getName() + " PASSED! ");
            } catch (Exception e) {
                System.out.println(m.getName() + " FAILED! ");
            }
        }

        return count;
    }

    private static void executionStatistic(int numberOfMethods, int testPassedCount){
        int testFailedCount = numberOfMethods - testPassedCount;
        System.out.println("\nTest execution statistics");
        System.out.println("*******************************************");
        System.out.println("Tests were performed: " + numberOfMethods + "!");
        System.out.println("Of them successfully: " + testPassedCount + "!");
        System.out.println("Of them unsuccessful: " + testFailedCount + "!");
        System.out.println("*******************************************\n");
    }

    //Запуск исполнения тестовых процедур
    public static void execute(Class<?> testedClass) throws ClassNotFoundException {

        Class<?> clazz = Class.forName(testedClass.getName());
        List<Method> beforeMethods = getAnnotatedMethod(clazz.getDeclaredMethods(), Before.class, clazz);
        List<Method> testMethods = getAnnotatedMethod(clazz.getDeclaredMethods(), Test.class, clazz);
        List<Method> afterMethods = getAnnotatedMethod(clazz.getDeclaredMethods(), After.class, clazz);

        executeAnnotatedMethods(beforeMethods, clazz);
        int testPassedCount = executeAnnotatedMethods(testMethods, clazz);
        executeAnnotatedMethods(afterMethods, clazz);

        executionStatistic(testMethods.size(), testPassedCount);
    }
}
