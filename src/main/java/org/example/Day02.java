package org.example;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day02 extends AocDay{
    public Day02(Integer day) {
        super(day);
    }

    @Override
    public String solvePart1() {
        int maxRed = 12;
        int maxGreen = 13;
        int maxBlue = 14;
        int sum = 0;
        Scanner scanner;
        
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            
            String[] array = line.split(":");
            String gameNumber = array[0].substring(array[0].lastIndexOf(" ")+1);
            String[] turns = array[1].split("; ");
            boolean gamePossible = true;
            
            for(String turn : turns) {
//                System.out.println(turn.trim());
                String[] selections = turn.trim().split(",");
                for(String selection: selections) {
                    String[] qtyColor = selection.trim().split(" ");
                    String qty = qtyColor[0].trim(), color = qtyColor[1].trim();
//                    System.out.println(selection.trim());
//                    System.out.println("game: "+gameNumber+", color: "+color+", qty: "+qty);
                    if((color.trim().equals("green") && Integer.parseInt(qty.trim()) > maxGreen)
                    || (color.trim().equals("red")   && Integer.parseInt(qty.trim()) > maxRed)
                    || (color.trim().equals("blue")  && Integer.parseInt(qty.trim()) > maxBlue)) {
                        gamePossible = false;
//                        System.out.println("Game impossible: "+gameNumber);
                        break;
                    }
                }
                if(!gamePossible) break;
            }
            
            if(gamePossible) {
//                System.out.println("Game possible: "+gameNumber);
                sum += Integer.parseInt(gameNumber);
            }
        }
        
        return ""+sum;
    }
    
    

    @Override
    public String solvePart2() {
        int sum = 0;
        Scanner scanner;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            
            int maxRed=0, maxBlue=0, maxGreen=0;

            String[] array = line.split(":");
            String[] turns = array[1].split(";");

            for(String turn : turns) {
                String[] selections = turn.trim().split(",");
                for(String selection: selections) {
                    String[] qtyColor = selection.trim().split(" ");
                    int qty = Integer.parseInt(qtyColor[0].trim());
                    String color = qtyColor[1].trim();
                    
                    if(color.equals("red") && qty > maxRed) {
                        maxRed = qty;
                    } else if(color.equals("blue") && qty > maxBlue){
                        maxBlue = qty;
                    } else if(color.equals("green") && qty > maxGreen) {
                        maxGreen = qty;
                    }
                }
            }
            
            sum += maxRed * maxBlue * maxGreen;
        }

        return ""+sum;
    }
}
