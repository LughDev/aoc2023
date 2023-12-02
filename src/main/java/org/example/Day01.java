package org.example;

import java.io.FileNotFoundException;
import java.util.*;

public class Day01 extends AocDay{
    public Day01(Integer day) {
        super(day);
    }

    @Override
    public String solvePart1() {
        Scanner scanner;
        System.out.println("Day 1 Part 1");
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        int sum = 0;
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int first = getFirstInt(line,false);
            int last = getLastInt(line,false);
            sum += Integer.parseInt(first+""+last);
            
        }
        return ""+sum;
    }

    @Override
    public String solvePart2() {
        Scanner scanner;
        System.out.println("Day 1 Part 2");
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int sum = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int first = getFirstInt(line,true);
            int last = getLastInt(line,true);
            sum += Integer.parseInt(first+""+last);

        }

        return ""+sum;
    }
    
    private int getFirstInt(String line, boolean isPart2) {
        Map<String,Integer> indexes = new HashMap<>();
        for(int i=1;i<10;i++){
            int digitIdx = line.indexOf(""+i);
            int wordIdx = line.indexOf(convertDigitToWord(""+i));
            if(digitIdx >= 0)
                indexes.put(""+i,digitIdx);
            if(isPart2 && wordIdx >= 0)
                indexes.put(convertDigitToWord(""+i),wordIdx);
        }
        
        int min = Integer.MAX_VALUE;
        int minDigit = -1;
        for(Map.Entry<String,Integer> idx : indexes.entrySet()){
            if(idx.getValue() < min) {
                min = idx.getValue();
                minDigit = convertWordToDigit(idx.getKey());
            }
        }
        return minDigit;
    }

    private int getLastInt(String line, boolean isPart2) {
        Map<String,Integer> indexes = new HashMap<>();
        for(int i=1;i<10;i++){
            int digitIdx = line.lastIndexOf(""+i);
            int wordIdx = line.lastIndexOf(convertDigitToWord(""+i));
            if(digitIdx >= 0)
                indexes.put(""+i,digitIdx);
            if(isPart2 && wordIdx >= 0)
                indexes.put(convertDigitToWord(""+i),wordIdx);
        }

        int max = Integer.MIN_VALUE;
        int maxDigit = 10;
        for(Map.Entry<String,Integer> idx : indexes.entrySet()){
            if(idx.getValue() > max) {
                max = idx.getValue();
                maxDigit = convertWordToDigit(idx.getKey());
            }
        }
        return maxDigit;
    }

    private String convertDigitToWord(String digit) {
        switch (digit) {
            case "0":
                return "zero";
            case "1":
                return "one";
            case "2":
                return "two";
            case "3":
                return "three";
            case "4":
                return "four";
            case "5":
                return "five";
            case "6":
                return "six";
            case "7":
                return "seven";
            case "8":
                return "eight";
            case "9":
                return "nine";
        }
        System.out.println("invalid digit: "+digit);
        return "";
    }

    private int convertWordToDigit(String digit) {
        if(digit.length() == 1) 
            return Integer.parseInt(digit);
        
        switch (digit) {
            case "zero":
                return 0;
            case "one":
                return 1;
            case "two":
                return 2;
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            case "seven":
                return 7;
            case "eight":
                return 8;
            case "nine":
                return 9;
        }
        System.out.println("invalid word: "+digit);
        return -1;
    }
}
