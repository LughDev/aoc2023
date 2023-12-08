package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class AocDay {
    File file;
    public abstract String solvePart1();
    public abstract String solvePart2();
    
    public AocDay(Integer day) {
        file = AocProxy.getInput(day);
    }
    
    Scanner getScanner() {
        try {
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File "+ file.getPath()+" not found");
        }
        
        return new Scanner("");
    }
}
