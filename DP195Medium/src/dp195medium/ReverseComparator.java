/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp195medium;

import java.util.Comparator;

/**
 *
 * @author ddsnowboard
 */
class ReverseComparator implements Comparator<Integer> {

    public ReverseComparator() {
    }

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }

}
