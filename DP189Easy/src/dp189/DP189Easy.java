/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp189;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author ddsnowboard
 */
public class DP189Easy {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException if it can't find the words file.
     */
    private final static long DELAY = 1000l;

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        String word = getWord();
        Scanner s = new Scanner(System.in);
        boolean finished = false;
        ArrayList<String> goodLetters = new ArrayList<>(word.length());
        ArrayList<String> badLetters = new ArrayList<>(25);
        int misses = 0;
        String currentGuess;
        while (!finished) {
            System.out.println(getMan(misses));
            System.out.println(getLetters(word, goodLetters));
            System.out.println("What letter do you want to guess?");
            currentGuess = s.nextLine().toLowerCase();
            if (currentGuess.length() > 1)
                System.out.println("You can only guess one letter.");
            else if (word.toLowerCase().contains(currentGuess)) {
                System.out.println("You already guessed that letter!");
            } else if (badLetters.contains(currentGuess) || goodLetters.contains(currentGuess)) {
                goodLetters.add(currentGuess);
                System.out.println("That's right!");
            } else {
                System.out.println("Nope!");
                badLetters.add(currentGuess);
                misses++;
            }
            Thread.sleep(DELAY);
            if(goodLetters.size() == word.length() || misses == 7)
                finished = true;
        }
        if(misses == 7)
            System.out.println("You lose! The answer was" + word);
        else
            System.out.println("You win!");
    }

    public static String getMan(int i) {
        String[] man = {
            "            |---|\n"
            + "            |   |\n"
            + "                |\n"
            + "                |\n"
            + "                |\n"
            + "                |\n"
            + "                |\n"
            + "                |\n"
            + "        ________|___",
            "                    |---|\n"
            + "                    |   |\n"
            + "                    O   |\n"
            + "                        |\n"
            + "                        |\n"
            + "                        |\n"
            + "                        |\n"
            + "                        |\n"
            + "                ________|___",
            "                |---|\n"
            + "                |   |\n"
            + "                O   |\n"
            + "                |   |\n"
            + "                |   |\n"
            + "                    |\n"
            + "                    |\n"
            + "                    |\n"
            + "            ________|___",
            "                |---|\n"
            + "                |   |\n"
            + "              \\ O   |\n"
            + "               \\|   |\n"
            + "                |   |\n"
            + "                    |\n"
            + "                    |\n"
            + "                    |\n"
            + "                    |\n"
            + "            ________|___",
            "                |---|\n"
            + "                |   |\n"
            + "              \\ O / |\n"
            + "               \\|/  |\n"
            + "                |   |\n"
            + "                    |\n"
            + "                    |\n"
            + "                    |\n"
            + "                    |\n"
            + "            ________|___",
            "                    |---|\n"
            + "                    |   |\n"
            + "                  \\ O / |\n"
            + "                   \\|/  |\n"
            + "                    |   |\n"
            + "                   /    |\n"
            + "                  /     |\n"
            + "                        |\n"
            + "                        |\n"
            + "                ________|___",
            "                |---|\n"
            + "                |   |\n"
            + "              \\ O / |\n"
            + "               \\|/  |\n"
            + "                |   |\n"
            + "               / \\  |\n"
            + "              /   \\ |\n"
            + "                    |\n"
            + "                    |\n"
            + "            ________|___"};
        return man[i];
    }

    public static String getWord() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        String difficulty;
        ArrayList<String> easy = new ArrayList<>(15000);
        ArrayList<String> medium = new ArrayList<>(30000);
        ArrayList<String> hard = new ArrayList<>(75000);
        File f = new File("words.txt");
        Scanner s = new Scanner(f);
        String next;
        while (s.hasNext()) {
            next = s.nextLine();
            if (next.isEmpty()) {
            } else if (next.length() <= 5) {
                easy.add(next);
            } else if (next.length() <= 7) {
                medium.add(next);
            } else {
                hard.add(next);
            }

        }
        String word;
        Random random = new Random();
        OUTER:
        while (true) {
            System.out.println("Do you want to play an EASY, MEDIUM, or HARD game?");
            difficulty = input.nextLine();
            switch (difficulty.toLowerCase()) {
                case "easy":
                    word = easy.get(random.nextInt(easy.size()));
                    break OUTER;
                case "medium":
                    word = medium.get(random.nextInt(medium.size()));
                    break OUTER;
                case "hard":
                    word = hard.get(random.nextInt(hard.size()));
                    break OUTER;
                default:
                    System.out.println("That wasn't an available option");
                    break;
            }
        }
        s.close();
        return word.toLowerCase();
    }

    public static String getLetters(String word, ArrayList<String> letters) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (letters.contains(String.valueOf(word.charAt(i)))) {
                output.append(String.valueOf(word.charAt(i)));
            } else {
                output.append("_");
            }
            output.append(" ");
        }
        return output.toString();
    }
}
