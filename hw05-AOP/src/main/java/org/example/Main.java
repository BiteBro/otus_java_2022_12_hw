package org.example;

public class Main {
    public static void main(String[] args) {
        TestLoggingInterface tli = Ioc.createInstance();
        System.out.println("\nWork result:");
        System.out.println("**********************************************");
        tli.addition(1);
        tli.additionA(2,4);
        tli.additionB(3,5, 7);
        tli.additionS("Hello");
        System.out.println("\n**********************************************\n");
    }
}