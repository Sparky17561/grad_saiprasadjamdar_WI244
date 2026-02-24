package com;

public class A implements I {

    private int counter = 0;

    @Override
    public void abc() {
        counter++;   // state change
        System.out.println("abc() executed");
    }

    public int getCounter() {
        return counter;
    }
}