package dp190medium;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author ddsnowboard
 */
public class DP190Medium {

    public static void main(String[] args) throws FileNotFoundException {
        File words = new File("huge.txt.");
//        File words = new File("enable1.txt");
        ArrayList<ArrayList<String>> wordsToFind = new ArrayList<>();
        ArrayList<String> wordsToSearch = new ArrayList<>();
        initArrays(wordsToSearch, wordsToFind, words);
        String currentHighestWord = "";
        int currentHighestNumber = 0;
        ArrayList<String> currentHighestList = new ArrayList<>();
        int thisCount;
        int thisLength;
        ArrayList<String> thisList;
        for (String beingSearched : wordsToSearch) {
            thisCount = 0;
            thisList = new ArrayList<>();
            thisLength = beingSearched.length();
            for (int start = 0; start <= thisLength - 2; start++) {
                for (int end = 2; end <= thisLength - start; end++) {
                    try {
                        if (Collections.binarySearch(wordsToFind.get(end), beingSearched.substring(start, start + end)) >= 0 && !thisList.contains(beingSearched.substring(start, start + end)) && !beingSearched.equals(beingSearched.substring(start, start + end))) {
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

        System.out.printf("%s is the word with the most, with %d%n", currentHighestWord, currentHighestNumber);
        for (String s : currentHighestList) {
            System.out.println(s);
        }
    }

    public static void initArrays(ArrayList<String> toSearch, ArrayList<ArrayList<String>> toFind, File file) throws FileNotFoundException {
        toFind.clear();
        Scanner sc = new Scanner(file);
        String next;
        int longestWordLength = 0;
        while (sc.hasNextLine()) {
            next = sc.nextLine();
            if (next.length() > longestWordLength) {
                longestWordLength = next.length();
            }
        }
        for (int i = 0; i < longestWordLength + 1; i++) {
            toFind.add(new ArrayList<>());
        }
        sc = new Scanner(file);
        while (sc.hasNextLine()) {
            next = sc.nextLine();
            if (next.length() >= 2) {
                toSearch.add(next);
                toFind.get(next.length()).add(next);
            }
        }
        for (ArrayList<String> a : toFind) {
            Collections.sort(a);
        }
    }

}
