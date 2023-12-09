package org.example;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int[] days = {4};
        for(int day : days) {
            String zDay = (day < 10 ? "0" : "") + day;
            
//            System.out.println("Generating Day"+zDay);
            Class cls = null;
            try {
                cls = Class.forName("org.example.Day" + zDay);
            } catch (ClassNotFoundException e) {
                System.out.println("Day"+zDay+" doesn't exist");
                continue;
            }
            AocDay aocDay = (AocDay) cls.getConstructor(Integer.class).newInstance(day);
            System.out.println("Day"+zDay+".1: " + aocDay.solvePart1());
            System.out.println("Day"+zDay+".2: " + aocDay.solvePart2());
            System.out.println();
        }
    }
    
    public final static String inputsDirectory = "src/main/inputs/";
}