package org.example;

public class Main {
    public static void main(String[] args) {
        TestLoggingInterface tli = Ioc.createInstance();
        System.out.println("\nWork result:");
        System.out.println("**********************************************");
        tli.addition(1);
        tli.additionA(1);
        tli.addition(2,4);
        tli.addition(3,5, 7);
        tli.addition("Hello");
        System.out.println("\n**********************************************\n");
    }
}