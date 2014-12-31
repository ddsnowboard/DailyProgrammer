/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp195medium;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ddsnowboard
 */
public class DP195Medium {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        File inFile = new File("input.txt");
        Pattern parser = Pattern.compile("(?<number>[0-9]+)d(?<sides>[0-9]+)");
        Scanner s = new Scanner(inFile);
        String input = s.nextLine();
        Matcher m = parser.matcher(input.split(" ")[0]);
        m.matches();
        Die[] target = new Die[Integer.parseInt(m.group("number"))];
        for (int i = 0; i < target.length; i++) {
            target[i] = new Die(Integer.parseInt(m.group("sides")));
        }
        m.reset(input.split(" ")[1]);
        m.matches();
        Die[] game = new Die[Integer.parseInt(m.group("number"))];
        for (int i = 0; i < game.length; i++) {
            game[i] = new Die(Integer.parseInt(m.group("sides")));
        }
        int targetNumber = 0;
        for (Die d : target) {
            targetNumber += d.roll();
        }
        System.out.println(targetNumber);
        Integer[] numbers = new Integer[game.length];
        for (int i = 0; i < game.length; i++) {
            numbers[i] = game[i].roll();
        }
        Arrays.sort(numbers, new ReverseComparator());
        System.out.println(Arrays.toString(numbers));
        // Incoming grossness; beware
        int curr;
        StringBuilder currString;
        for (int i = 0; i < Math.pow(2, numbers.length); i++) {
            curr = 0;
            currString = new StringBuilder();
            for (int j = 0; j < numbers.length; j++) {
                if ((i & ((int) Math.pow(2, j))) > 0) {
                    if (j != 0) {
                        currString.append("+");
                    }
                    curr += numbers[j];
                } else {
                    currString.append("-");
                    curr -= numbers[j];
                }
                currString.append(numbers[j]);

            }
            // Figure out how absolute values factor into this. (HEYO! pun.)
            if (curr == targetNumber || curr == -1*targetNumber) {
                System.out.println("Found it!");
                System.out.println(currString.toString());
                break;
            }
        }
    }

}
