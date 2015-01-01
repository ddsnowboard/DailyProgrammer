/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp195medium;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ddsnowboard
 */
public class DP195Medium {

    public static void main(String[] args) throws FileNotFoundException {
        File inFile = new File("input.txt");
        Pattern parser = Pattern.compile("(?<number>[0-9]+)d(?<sides>[0-9]+)");
        Scanner s = new Scanner(inFile);
        String input = s.nextLine();
        boolean found = false;
        Matcher matcher = parser.matcher(input.split(" ")[0]);
        matcher.matches();
        Die[] target = new Die[Integer.parseInt(matcher.group("number"))];
        for (int i = 0; i < target.length; i++) {
            target[i] = new Die(Integer.parseInt(matcher.group("sides")));
        }
        matcher.reset(input.split(" ")[1]);
        matcher.matches();
        Die[] game = new Die[Integer.parseInt(matcher.group("number"))];
        for (int i = 0; i < game.length; i++) {
            game[i] = new Die(Integer.parseInt(matcher.group("sides")));
        }
        int targetNumber = 0;
        for (Die d : target) {
            targetNumber += d.roll();
        }
        System.out.printf("%d, ", targetNumber);
        Integer[] numbers = new Integer[game.length];
        for (int i = 0; i < game.length; i++) {
            numbers[i] = game[i].roll();
            System.out.printf("%d ", numbers[i]);
        }
        System.out.println();
        Arrays.sort(numbers, Collections.reverseOrder());
        int curr;
        StringBuilder currString;
        for (int i = 0; i < Math.pow(2, numbers.length); i++) {
            curr = 0;
            currString = new StringBuilder();
            for (int j = 0; j < numbers.length; j++) {
                if ((i & ((int) Math.pow(2, j))) > 0) {
                    currString.append("+");
                    curr += numbers[j];
                } else {
                    currString.append("-");
                    curr -= numbers[j];
                }
                currString.append(numbers[j]);
            }
            if (curr == targetNumber) {
                System.out.println(currString.toString().replaceAll("^\\+", ""));
                found = true;
                break;
            } else if (curr == -1 * targetNumber) {
//                You might not want to look at this line...
                System.out.println(currString.toString().replace("-", "b").replace("+", "a").replace("b", "+").replace("a", "-").replaceAll("^\\+", ""));
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("I didn't find anything.");
        }
    }

}
