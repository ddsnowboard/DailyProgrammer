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

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        File words = new File("enable1.txt.");
//        File words = new File("short.txt");
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
//        String beingSearched = "ethylenediaminetetraacetates";
            thisCount = 0;
            thisList = new ArrayList<>();
            thisLength = beingSearched.length();
            for (int start = 0; start <= thisLength - 2; start++) {
                for (int end = 2; end <= thisLength - start; end++) {
//                    System.out.printf("Word: %s, Start: %d, End: %d, Substring: %s%n", beingSearched, start, end, beingSearched.subSequence(start, start + end));
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
        for (int i = 0; i < 30; i++) {
            toFind.add(new ArrayList<>());
        }

        Scanner sc = new Scanner(file);
        String next;
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
