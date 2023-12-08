package org.example;

import java.util.*;

public class Day03 extends AocDay{
    public Day03(Integer day) {
        super(day);
    }

    @Override
    public String solvePart1() {
        Scanner scanner = getScanner();
        Schematic schematic;
        char[][] schema = new char[140][140];
        int counter = 0;
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            schema[counter] = line.toCharArray();
            counter++;
        }
        
        schematic = new Schematic(schema);
        
        int sum = 0;
        
        for(int y = 0; y<schema.length; y++) {
            for (int x = 0; x < schema[y].length; x++) {
//                System.out.println("y: "+y+",x: "+x+" "+schema[y][x]);
                if(isSymbol(schema[y][x])){
                    System.out.println("line "+y+": Found symbol: "+schema[y][x]);
                    for(int y2 = y-1; y2<=y+1; y2++){
                        for(int x2 = x-1; x2<=x+1; x2++){
                            if(isNumber(schema[y2][x2]))
                                System.out.println(schematic.contains(x2,y2) + " "+schematic.getNumber(x2,y2));
                            if(schematic.contains(x2,y2)){
                                System.out.println("\tAdding line "+y2+" '"+Integer.parseInt(schematic.getNumber(x2,y2))+"' '"+schematic.getNumber(x2,y2)+"'");
//                                System.out.println("Adding "+Integer.parseInt(schematic.getNumber(x2,y2)));
                                sum += Integer.parseInt(schematic.getNumber(x2,y2));
                                x2+=schematic.getNumber(x2,y2).length()-schematic.getNumber(x2,y2).indexOf(schema[y2][x2])-1;
                            }
                        }
                    }
                }
                
            }
//            System.out.println();
        }
        
        return "" + sum;
    }
    
    private boolean isSymbol(char c) {
        return (c < '0' || c > '9') && c != '.';
    }
    
    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    @Override
    public String solvePart2() {
        
        return null;
    }
    
    private class Schematic {
//        private char[][] schema;
        private List<Entry> symbols;
        private Map<String,Entry> numberMap;
        
        Schematic(char[][] schema) {
//            this.schema = schema.clone();
            numberMap = new HashMap<>();
            symbols = new ArrayList<>();
            for(int y = 0; y<schema.length; y++){
                for (int x = 0; x < schema[y].length; x++) {
                    if(isNumber(schema[y][x])){
                        String num = "";
                        int start = x;
                        while(x < schema[y].length && isNumber(schema[y][x])) {
                            num += schema[y][x];
                            x++;
                        }
                        Entry entry = new Entry(y,start,num);
//                        symbols.add(entry);
                        for(int i=start; i<x; i++) {
                            numberMap.put(createMapKey(i, y), entry);
                        }
                    } else if(isSymbol(schema[y][x])) {
                        Entry entry = new Entry(y,x,""+schema[y][x]);
                        symbols.add(entry);
//                        entryMap.put(createMapKey(x,y),entry);
                    }
                }
            }
            System.out.println("numberMapSize: "+numberMap.size());
        }
        
        public boolean contains(int x, int y) {
            return numberMap.containsKey(createMapKey(x,y));
        }
        
        public String getNumber(int x, int y) {
            return numberMap.get(createMapKey(x,y)).entry;
        }
        
        private String createMapKey(int x, int y){
            return "y"+y+"x"+x;
        }
        
        private class Entry{
            Integer x,y;
            String entry;
            
            public Entry(int y, int x, String entry) {
                this.entry = entry;
                this.y = y;
                this.x = x;
            }
            
            public String toString() {
                return String.format("{y: %d, x: %d, entry: %s}",y,x,entry);
            }
        }
    }
}
