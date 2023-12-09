package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day04 extends AocDay{
    public Day04(Integer day) {
        super(day);
    }

    @Override
    public String solvePart1() {
        Scanner scanner = getScanner();
        int sum = 0;
        
        while(scanner.hasNextLine()) {
            Card card = new Card(scanner.nextLine());
            sum += (int) Math.pow(2,card.winningHeldNumbers.size()-1);
        }
        
        return ""+sum;
    }

    @Override
    public String solvePart2() {
        Scanner scanner = getScanner();
        List<Card> cards = new ArrayList<>();
        
        while(scanner.hasNextLine()) {
            Card card = new Card(scanner.nextLine());
            cards.add(card);
        }

        int[] cardTotals = new int[cards.size()];
        
        for(Card card : cards) {
//            System.out.println(card);
            cardTotals[card.cardNumber-1] += 1;
            for(int i=0;i<card.winningHeldNumbers.size();i++){
//                System.out.println("Adding "+(cardTotals[card.cardNumber-1])+" to cardTotal["+(card.cardNumber+i)+"]");
                cardTotals[card.cardNumber+i] += cardTotals[card.cardNumber-1];
            }
        }
        
//        System.out.println(Arrays.toString(cardTotals));
        
        return ""+ IntStream.of(cardTotals).sum();
    }
    
    private class Card {
        Integer cardNumber;
        List<Integer> winningNumbers;
        List<Integer> heldNumbers;
        Set<Integer> winningHeldNumbers;
        
        Card(String cardVals) {
            String[] cardNumVals = cardVals.split("[:|]");
//            System.out.println(Arrays.toString(cardNumVals));
            cardNumber = Integer.parseInt(cardNumVals[0].substring(cardNumVals[0].length()-3).trim());
//            System.out.println(cardNumber);
            
            winningNumbers = splitNumberString(cardNumVals[1]," +");
            heldNumbers = splitNumberString(cardNumVals[2]," +");

            winningHeldNumbers = winningNumbers.stream()
                    .distinct()
                    .filter(heldNumbers::contains)
                    .collect(Collectors.toSet());
        }
        
        List<Integer> splitNumberString(String numberString, String regex) {
            ArrayList<Integer> retList = new ArrayList<>();
            String[] splitNumbers = numberString.trim().split(regex);
            for(String num : splitNumbers) {
                if(!num.isEmpty())
                    retList.add(Integer.parseInt(num));
            }
            
            return retList;
        }
        
        @Override
        public String toString() {
            return "{cardNumber: "+cardNumber+", winningHeldNumbers: "+winningHeldNumbers+"}";
        }
    }
}
