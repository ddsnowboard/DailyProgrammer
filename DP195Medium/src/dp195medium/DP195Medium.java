/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp195medium;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
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
        
    }
    
}
