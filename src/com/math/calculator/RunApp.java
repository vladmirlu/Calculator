package com.math.calculator;

public class RunApp {

    public static void main(String[] args) {
        try {
            Communicator communicator = new Communicator();
            communicator.openConsole("");
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }
}

