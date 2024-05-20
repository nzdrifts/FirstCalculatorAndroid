package com.example.calculatorapp.models;

import java.util.Arrays;

public class EvaluateOperations {

    public static double evaluate(String txtValue) { // e.g. 1 + 2 - 99 (+ and -)
        if (txtValue.isEmpty()) return 0;

        String[] list = txtValue.split(" ");
        double result = Double.parseDouble(list[0]);

        for (int i = 1; i < list.length-1; i++) {
            if (list[i].equals("+")){
                result += Double.parseDouble(list[i+1]);
            }
            if (list[i].equals("-")){
                result -= Double.parseDouble(list[i+1]);
            }
        }
        return result;
    }

    public static double evaluatePriority(String txtValue) { //return a string with no / or x
        String[] list = txtValue.split(" ");
        for (int i = 1; i < list.length-1; i++) {
            if (list[i].equals("/")){
                String result = joinList(list,0,i-1) + " " + (Double.parseDouble(list[i-1])/Double.parseDouble(list[i+1])) + " " + joinList(list,i+1,list.length-1);
                if(result.startsWith(" ")) result = result.substring(1);
                if(result.endsWith(" ")) result = result.substring(0, result.length()-1);
                return evaluatePriority(result);
            }
            if (list[i].equals("x")){
                String result = joinList(list,0,i-1) + " " + (Double.parseDouble(list[i-1])*Double.parseDouble(list[i+1])) + " " + joinList(list,i+1,list.length-1);
                if(result.startsWith(" ")) result = result.substring(1);
                if(result.endsWith(" ")) result = result.substring(0, result.length()-1);
                return evaluatePriority(result);
            }
        }
        return evaluate(txtValue);
    }

    private static String joinList(String[] list, int start, int end){
        return String.join(" ", Arrays.asList(list).subList(start,end));
    }
}
