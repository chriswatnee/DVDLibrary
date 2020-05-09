/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import java.util.Scanner;

/**
 *
 * @author christopherwatnee
 */
public class UserIOConsoleImpl implements UserIO {
    private Scanner userInput = new Scanner(System.in);
    
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt) {
        double input;
        String inputString;
        System.out.println(prompt);
        inputString = userInput.nextLine();
        input = Double.parseDouble(inputString);
        return input;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double input;
        String inputString;
        
        do {
            System.out.println(prompt);
            inputString = userInput.nextLine();
            input = Double.parseDouble(inputString);
        } while (input < min || input > max);

        return input;
    }

    @Override
    public float readFloat(String prompt) {
        float input;
        String inputString;
        System.out.println(prompt);
        inputString = userInput.nextLine();
        input = Float.parseFloat(inputString);
        return input;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float input;
        String inputString;
        
        do {
            System.out.println(prompt);
            inputString = userInput.nextLine();
            input = Float.parseFloat(inputString);
        } while (input < min || input > max);

        return input;
    }

    @Override
    public int readInt(String prompt) {
        int input;
        String inputString;
        System.out.println(prompt);
        inputString = userInput.nextLine();
        input = Integer.parseInt(inputString);
        return input;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int input;
        String inputString;
        
        do {
            System.out.println(prompt);
            inputString = userInput.nextLine();
            input = Integer.parseInt(inputString);
        } while (input < min || input > max);

        return input;
    }

    @Override
    public long readLong(String prompt) {
        long input;
        String inputString;
        System.out.println(prompt);
        inputString = userInput.nextLine();
        input = Long.parseLong(inputString);
        return input;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long input;
        String inputString;
        
        do {
            System.out.println(prompt);
            inputString = userInput.nextLine();
            input = Long.parseLong(inputString);
        } while (input < min || input > max);

        return input;
    }

    @Override
    public String readString(String prompt) {
        String inputString;
        System.out.println(prompt);
        inputString = userInput.nextLine();
        return inputString;
    }
}
