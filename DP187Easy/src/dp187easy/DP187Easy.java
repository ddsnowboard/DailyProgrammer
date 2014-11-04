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
            HashMap map = new HashMap(n);
            ArrayList<String> long_names = new ArrayList<>();
            String[] current;
            for (int i = 0; i < n; i++) {
                current = s.next().split(":");
                map.put(current[0], current[1]);
                long_names.add(current[1]);
            }
            s.nextLine();
            current = s.nextLine().split(" ");
            ArrayList<Character> chars = new ArrayList<>();
            for (String t : current) {
                if (t.charAt(0) == '-' && t.charAt(1) == '-') {
                    System.out.printf("flag: %s%n", t.substring(2));
                } else if (t.charAt(0) == '-' && t.length() > 2) {
                    chars.clear();
                    for (int p = 1; p < t.length(); p++) {
                        chars.add(t.charAt(p));
                    }
                    chars.trimToSize();
                    for (char c : chars) {
                        if (map.get(String.valueOf(c)) != null) {
                            System.out.printf("flag: %s%n", map.get(String.valueOf(c)));
                        } else {
                            System.out.println("Error: You gave an argument that's not defined!");
                            return;
                        }
                    }
                } else if (t.charAt(0) == '-') {
                    if (map.get(t.substring(1)) != null) {
                            System.out.printf("flag: %s%n", map.get(t.substring(1)));
                        } else {
                            System.out.println("Error: You gave an argument that's not defined!");
                            return;
                        }
                } else {
                    System.out.printf("parameter: %s%n", t);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DP187Easy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
