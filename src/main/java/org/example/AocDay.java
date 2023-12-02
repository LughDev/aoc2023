package org.example;

import java.io.File;

public abstract class AocDay {
    File file;
    public abstract String solvePart1();
    public abstract String solvePart2();
    
    public AocDay(Integer day) {
        file = AocProxy.getInput(day);
    }
}
