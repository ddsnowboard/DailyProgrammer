/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp190medium;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ddsnowboard
 */
public class DP190Medium {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        File words = new File("enable1.txt.");
//        File words = new File("short.txt");
        Scanner sc = new Scanner(words);
        ArrayList<ArrayList<String>> wordsToFind = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            wordsToFind.add(new ArrayList<>());
        }
        ArrayList<String> wordsToSearch = new ArrayList<>();
        String currentHighestWord = "";
        int counter = 0;
        int currentHighestNumber = 0;
        ArrayList<String> currentHighestList = new ArrayList<>();
        String next;
        while (sc.hasNext()) {
            next = sc.nextLine();
            wordsToFind.get(next.length()).add(next);
            if (next.length() > 2) {
                wordsToSearch.add(next);
            }
        }
        for (ArrayList<String> a : wordsToFind) {
            Collections.sort(a);
        }
//        Collections.sort(wordsToSearch, new lengthComparator());
        int thisCount;
        int thisLength;
        ArrayList<String> thisList;
        for (String beingSearched : wordsToSearch) {
            counter++;
            if (counter == 1000) {
                System.out.println(beingSearched);
                System.out.println();
                System.out.println(currentHighestNumber);
                System.out.println(currentHighestWord);
                System.out.println(currentHighestList);
                counter = 0;
            }
            if (beingSearched.length() > 11) {
                thisCount = 0;
                thisList = new ArrayList<>();
                thisLength = beingSearched.length();
                for (int start = 0; start < thisLength - 2; start++) {
                    for (int end = start + 2; end < thisLength - start; end++) {
                        try {
//                            Replace contains() with a binary search, seeing as
//                            you can sort this list pretty easily. 
                            if (Collections.binarySearch(wordsToFind.get(end - start), beingSearched.substring(start, start + end)) != -1 && (Collections.binarySearch(thisList, beingSearched.substring(start, start+end))) != -1) {
                                thisCount++;
                                thisList.add(beingSearched.substring(start, start + end));
                            }
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.printf("%d, %d, %s%n", start, end, beingSearched);
                        }
                    }
                }
                if (thisCount > currentHighestNumber) {
                    currentHighestNumber = thisCount;
                    currentHighestWord = beingSearched;
                    currentHighestList = thisList;
                }
            }
        }
        System.out.printf("%s is the word with the most, with %d%n", currentHighestWord, currentHighestNumber);
        for (String s : currentHighestList) {
            System.out.println(s);
        }
    }

}
