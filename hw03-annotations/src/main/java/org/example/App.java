package org.example;

import org.example.test.SomeTest;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        TestExecutor.execute(SomeTest.class);
    }
}
