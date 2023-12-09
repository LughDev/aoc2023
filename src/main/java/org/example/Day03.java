package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Day03 extends AocDay{
    public Day03(Integer day) {
        super(day);
    }

    @Override
    public String solvePart1() {
        Scanner scanner = getScanner();
        ArrayList<String> list = new ArrayList<>();
        
        while(scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        
        char[][] schema = to2dArray(list);
        Schematic schematic = new Schematic(schema);
        
        int sum = 0;
        
        for(List<Schematic.Entry> numbers : schematic.symbolNumberMap.values()) {
            for(Schematic.Entry entry : numbers) {
                sum += Integer.parseInt(entry.entry);
            }
        }
        
        List<Schematic.Entry> symbolNumberMap = schematic.getSymbolNumberMap().values().stream().flatMap(List::stream).sorted().collect(Collectors.toList());

//        System.out.println(symbolNumberMap);
        
        
        //Visualize
//        int y = 0;
//        int x = 0;
//        for(Schematic.Entry entry : symbolNumberMap) {
//            printUpTo(schema,entry,x,y);
//            y=entry.y;
//            x=entry.x+entry.entry.length();
//        }
//        System.out.println();
        
        return "" + sum;
    }

    @Override
    public String solvePart2() {
        Scanner scanner = getScanner();
        ArrayList<String> list = new ArrayList<>();

        while(scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }

        char[][] schema = to2dArray(list);
        Schematic schematic = new Schematic(schema);

        int sum = 0;
        
        for(Map.Entry<Schematic.Entry, List<Schematic.Entry>> entry: schematic.symbolNumberMap.entrySet()){
            if(entry.getKey().entry.equals("*") && entry.getValue().size() == 2){
                sum += Integer.parseInt(entry.getValue().get(0).entry) * Integer.parseInt(entry.getValue().get(1).entry);
            }
        }
        
        return ""+sum;
    }

    private void printUpTo(char[][] schema, Schematic.Entry entry, int x, int y) {
        for(;y<=entry.y;y++) {
            for(;x<(y==entry.y?entry.x+entry.entry.length():schema[y].length);x++) {
                System.out.print(schema[y][x]);
            }
            if(x>=schema[y].length)
                System.out.println();
            x=0;
        }
    }

    private char[][] to2dArray(ArrayList<String> list) {
        char[][] array = new char[list.size()][];
        for(int i=0; i<list.size();i++){
            array[i] = list.get(i).toCharArray();
        }
        return array;
    }

    private boolean isSymbol(char c) {
        return (c < '0' || c > '9') && c != '.';
    }
    
    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }
    
    private class Schematic {
        private List<Entry> symbols;
        private Map<String,Entry> numberMap;
        private Map<Entry,List<Entry>> symbolNumberMap;
        
        Schematic(char[][] schema) {
            numberMap = new TreeMap<>();
            symbols = new ArrayList<>();
            for(int y = 0; y<schema.length; y++){
                for (int x = 0; x < schema[y].length; x++) {
                    if(isNumber(schema[y][x])){
                        StringBuilder num = new StringBuilder();
                        int start = x;
                        while(x < schema[y].length && isNumber(schema[y][x])) {
                            num.append(schema[y][x]);
                            x++;
                        }
                        Entry entry = new Entry(y,start,num.toString());
                        for(int i=start; i<x; i++) {
                            numberMap.put(createMapKey(i, y), entry);
                        }
                        x--;
                    } else if(isSymbol(schema[y][x])) {
                        Entry entry = new Entry(y,x,""+schema[y][x]);
                        symbols.add(entry);
                    }
                }
            }
            
            symbolNumberMap = new TreeMap<>();
            
            for(Entry entry : symbols) {
                symbolNumberMap.put(entry, getSurroundingNumbers(entry));
            }
            
//            System.out.println("symbols: "+symbols.toString());
//            System.out.println("numberMapSize: "+numberMap);
//            System.out.println(symbolNumberMap);
        }

        private List<Entry> getSurroundingNumbers(Entry entry) {
            Set<Entry> entries = new HashSet<>();
            for (int dy = -1; dy <= 1; dy++){
                for (int dx = -1; dx <= 1; dx++) {
                    if(containsNumber(entry.x + dx, entry.y+dy)) {
                        entries.add(numberMap.get(createMapKey(entry.x + dx, entry.y+dy)));
                    }
                }
            }
            
            return new ArrayList<>(entries);
        }

        public boolean containsNumber(int x, int y) {
            return numberMap.containsKey(createMapKey(x,y));
        }
        
        public String getNumber(int x, int y) {
            return numberMap.get(createMapKey(x,y)).entry;
        }
        
        private String createMapKey(int x, int y){
            return "y"+zeroPad(y)+"x"+zeroPad(x);
        }
        
        private String zeroPad(int num) {
            if(num < 10){
                return "00"+num;
            } else if(num<100){
                return "0"+num;
            }
            
            return ""+num;
        }

        public Map<Entry, List<Entry>> getSymbolNumberMap() {
            return symbolNumberMap;
        }

        private class Entry implements Comparable<Entry>{
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

            @Override
            public int compareTo(Entry o) {
                return y - o.y == 0 ? x - o.x : y - o.y;
            }
        }
    }
}
