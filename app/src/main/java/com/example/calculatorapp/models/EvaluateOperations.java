package com.example.calculatorapp.models;

public class EvaluateOperations {

    public static long evaluate(String txtValue) { // e.g. 1 + 2 + 99
        String[] list = txtValue.split(" \\+ ");
        long result = 0;
        for(String s : list){
            result += Long.parseLong(s);
        }
        return result;
    }
}
