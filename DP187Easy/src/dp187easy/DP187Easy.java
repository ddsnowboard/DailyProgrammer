package dp187easy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DP187Easy {

    public static void main(String[] args) {
        try {
            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            int number = s.nextInt();
            boolean expecting_argument = false;
            FlagGroup expecting_flag = null;
            HashMap<String, FlagGroup> map = new HashMap<>(number);
            String[] current;
            // This is whatever we're looking at, stripped of dashes, turned into
            // a string, whatever else needs to be done to make it easier to use. 
            String working_copy;
            // Makes the list of available commands. 
            for (int i = 0; i < number; i++) {
                current = s.next().split(":");
                map.put(current[0].replace("*", ""), new FlagGroup(current[1], current[0].contains("*")));
            }
            // Skips the rest of the line, whatever newline characters et 
            // alia are on there. 
            s.nextLine();
            current = s.nextLine().split(" ");
            // If it's a short flag with more than one character, I use this to
            // be able to iterate through them. 
            ArrayList<Character> chars = new ArrayList<>();
            for (String current_flag : current) {
                // If the first two characters are dashes, it's a long flag; 
                // just print it. 
                if (current_flag.substring(0, 2).equals("--")) {
                    working_copy = current_flag.substring(2);
                    if (working_copy.contains("=")) {
                        System.out.printf("flag: %s (value: %s)%n", working_copy.split("=")[0], working_copy.split("=")[1]);
                    } else {
                        System.out.printf("flag: %s%n", working_copy);
                    }
                // If the first character is a dash and it's longer than 2, then it's 
                // a multi-short flag; look for it's long brothers and print them. 
                } else if (current_flag.charAt(0) == '-' && current_flag.length() > 2) {
                    for (int p = 1; p < current_flag.length(); p++) {
                        working_copy = String.valueOf(current_flag.charAt(p));
                        if (map.get(working_copy) != null) {
                            if (!(p == current_flag.length() - 1 && map.get(working_copy).argument)) {
                                System.out.printf("flag: %s%n", map.get(working_copy).long_name);
                            } else {
                                expecting_argument = true;
                                expecting_flag = map.get(working_copy);
                            }
                        } else {
                            System.out.println("Error: You gave an argument that's not defined!");
                            return;
                        }
                    }
                    // If it starts with a dash and isn't longer than 2, than it's a single
                    // short flag. Find it's long brother and print it. 
                } else if (current_flag.charAt(0) == '-' && current_flag.length() == 2) {
                    working_copy = current_flag.replace("-", "");
                    if (map.get(working_copy) != null) {
                        if (!(map.get(working_copy).argument)) {
                            System.out.printf("flag: %s%n", map.get(working_copy).long_name);
                        } else {
                            expecting_argument = true;
                            expecting_flag = map.get(working_copy);
                        }
                    } else {
                        System.out.println("Error: You gave an argument that's not defined!");
                        return;
                    }
                } else {
                    if (expecting_argument) {
                        System.out.printf("flag: %s (value: %s)%n", expecting_flag.long_name, current_flag);
                        expecting_argument = false;
                    } else {
                        System.out.printf("parameter: %s%n", current_flag);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DP187Easy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
