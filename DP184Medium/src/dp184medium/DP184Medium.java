package dp184medium;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ddsnowboard
 */
public class DP184Medium {

    public static void main(String[] args) {
        File f = new File("input.txt");
        Scanner s;
        try {
            s = new Scanner(f);
            int number = s.nextInt();
            String next;
            String[] split;
            ArrayList<String> sequence = new ArrayList<>();
            sequence.addAll(Arrays.asList(s.next().split("->")));
            String[] nuclei = new String[10000];
            for (int i = 0; i < nuclei.length; i++) {
                nuclei[i] = sequence.get(0);
            }
            HashMap<String, Double> map = new HashMap<>();
            while (s.hasNext()) {
                next = s.nextLine();
                if (!next.equals("")) {
                    split = next.split(": ");
                    map.put(split[0], new Double(split[1]));
                }
            }
            for (int i = 0; i < number; i++) {
                nuclei = decay(nuclei, sequence, map);
            }
            int amt;
            for (String p : sequence) {
                amt = 0;
                for (String q : nuclei) {
                    if (q.equals(p)) {
                        amt++;
                    }
                }
                System.out.printf("%s: %f%%\n", p, 100 * ((float) amt / (float) nuclei.length));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DP184Medium.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String[] decay(String[] nuclei, ArrayList<String> seq, HashMap<String, Double> map) {
        int NUCLEI_SIZE = nuclei.length;
        Random random = new Random();
        for (int i = 0; i < NUCLEI_SIZE; i++) {
            if (random.nextDouble() <= map.get(nuclei[i])) {
                nuclei[i] = seq.get(seq.indexOf(nuclei[i]) + 1);
            }
        }
        return nuclei;
    }

}
