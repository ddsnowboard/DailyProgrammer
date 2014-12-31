/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp195easy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ddsnowboard
 */
public class DP195Easy {

    /**
     * @param args the command line arguments
     */
//    Make it go over and over, and also make it handle slashy shenanigans better. 
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("input.txt");
        Scanner s = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<Redirect> redirects = new ArrayList<>();
        String path;
        s.nextLine();
        while (s.hasNextLine()) {
            lines.add(s.nextLine());
        }
        path = lines.get(lines.size() - 1);
        lines.remove(lines.size() - 1);
        for (String i : lines) {
            redirects.add(new Redirect(i));
        }
        for (Redirect r : redirects) {
            if(path.contains(r.input))
            {
                path = path.replace(r.input, r.output);
            }
        }
        System.out.println(path);
    }

}
