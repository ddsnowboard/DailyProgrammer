/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp191easy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author ddsnowboard
 */
public class DP191Easy {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("input.txt");
        Scanner sc = new Scanner(f);
        Counter words = new Counter();
        boolean inBook = false;
        String currentLine;
        String[] currentWords;
        ArrayList<Pair> pairs = new ArrayList<>();
        Pattern startStop = Pattern.compile("^[*]{3}[^*]+?[*]{3}$");
        Pattern notLetters = Pattern.compile("[^A-Za-z]");
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            if (startStop.matcher(currentLine).matches() && !inBook) {
                inBook = true;
            } else if (startStop.matcher(currentLine).matches() && inBook) {
                inBook = false;
            } else if (inBook && !currentLine.equals("")) {
                currentWords = currentLine.split(" ");
                for (String s : currentWords) {
                    if (!s.equals("")) {
                        words.count(notLetters.matcher(s).replaceAll("").toLowerCase());
                    }
                }
            }
        }
        for (String s : words.keySet()) {
            pairs.add(new Pair(s, words.get(s)));
        }
        Collections.sort(pairs, new PairAmountComparator());
        Collections.reverse(pairs);
        for (int i = 0;i<10;i++) {
            System.out.printf("%s : %d%n", pairs.get(i).word, pairs.get(i).timesUsed);
        }
    }
    
}
