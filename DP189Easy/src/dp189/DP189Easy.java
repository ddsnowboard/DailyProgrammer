/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp189;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ddsnowboard
 */
public class DP189Easy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String difficulty;
        ArrayList<String> easy = new ArrayList<>();
        ArrayList<String> medium = new ArrayList<>();
        ArrayList<String> hard = new ArrayList<>();
        File f = new File("words.txt");
        Scanner s = new Scanner(f);
        String next;
        while(s.hasNext())
        {
            next = s.nextLine();
            if(next.isEmpty())
            {}
            else if(next.length()<=5)
                
        }
        String word;
        while (true) {
            System.out.println("Do you want to play an EASY, MEDIUM, or HARD game?");
            difficulty = input.nextLine();
            if (difficulty.toLowerCase().equals("easy")) {
                
            }
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

}
