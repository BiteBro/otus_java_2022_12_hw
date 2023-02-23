package org.example;

public class TestLogging implements TestLoggingInterface{

    @Override
    public void addition(int i) {
    }
    @Log
    @Override
    public void additionA(int i) {
    }
    @Override
    public void addition(int i, int a) {

    }
    @Log
    @Override
    public void addition(int i, int a, int b) {

    }
    @Log
    @Override
    public void addition(String s) {

    }

}
