package dp187easy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DP187Easy {

    public static void main(String[] args) {
        try {
            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            int n = s.nextInt();
            boolean expecting_argument = false;
            FlagGroup expecting_flag = null;
            HashMap<String, FlagGroup> map = new HashMap<>(n);
            String[] current;
            // Makes the list of available commands. 
            for (int i = 0; i < n; i++) {
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
            for (String t : current) {
                // If the first two characters are dashes, it's a long flag; 
                // just print it. 
                if (t.charAt(0) == '-' && t.charAt(1) == '-') {
                    if (t.contains("=")) {
                        System.out.printf("flag: %s (value: %s)%n", t.split("=")[0].substring(2), t.split("=")[1]);
                    } else {
                        System.out.printf("flag: %s%n", t.substring(2));
                    }
                    // If the first character is a dash and it's longer than 2, then it's 
                    // a multi-short flag; look for it's long brothers and print them. 
                } else if (t.charAt(0) == '-' && t.length() > 2) {
                    for (int p = 1; p < t.length(); p++) {
                        if (map.get(String.valueOf(t.charAt(p))) != null) {
                            if (!(p == t.length() - 1 && map.get(String.valueOf(t.charAt(p))).argument)) {
                                System.out.printf("flag: %s%n", map.get(String.valueOf(t.charAt(p))).long_name);
                            } else {
                                expecting_argument = true;
                                expecting_flag = map.get(String.valueOf(t.charAt(p)));
                            }
                        } else {
                            System.out.println("Error: You gave an argument that's not defined!");
                            return;
                        }
                    }
                    // If it starts with a dash and isn't longer than 2, than it's a single
                    // short flag. Find it's long brother and print it. 
                } else if (t.charAt(0) == '-' && t.length() == 2) {
                    if (map.get(t.substring(1)) != null) {
                        if (!(map.get(t.substring(1)).argument)) {
                            System.out.printf("flag: %s%n", map.get(t.substring(1)).long_name);
                        } else {
                            expecting_argument = true;
                            expecting_flag = map.get(t.substring(1));
                        }
                    } else {
                        System.out.println("Error: You gave an argument that's not defined!");
                        expecting_argument = false;
                        return;
                    }
                } else {
                    if (expecting_argument) {
                        System.out.printf("flag: %s (value: %s)%n", expecting_flag.long_name, t);
                        expecting_argument = false;
                    } else {
                        System.out.printf("parameter: %s%n", t);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DP187Easy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
