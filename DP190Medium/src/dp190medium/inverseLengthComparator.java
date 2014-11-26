/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp190medium;

import java.util.Comparator;

/**
 *
 * @author ddsnowboard
 */
public class inverseLengthComparator implements Comparator<String> {

    public int compare(String s1, String s2) {
        return s2.length() - s1.length();
    }
}
