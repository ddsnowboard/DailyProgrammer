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
    public static void main(String[] args) throws FileNotFoundException {
        String word = getWord();
        boolean finished = false;
        ArrayList<String> goodLetters = new ArrayList<>(word.length());
        int misses = 0;
        while (!finished)
        {
            System.out.println(getMan(misses));
            System.out.println(getLetters(word, goodLetters));
        }
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
        return word;
    }
    
    public static String getLetters(String word, ArrayList<String> letters)
    {
        StringBuilder output = new StringBuilder();
        for(int i = 0; i<word.length(); i++)
        {
            if(letters.contains(String.valueOf(word.charAt(i))))
            {
                output.append(String.valueOf(word.charAt(i)));
            }
            else
                output.append("_");
        }
        return output.toString();
    }
    }
