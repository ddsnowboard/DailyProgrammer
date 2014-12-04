/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp191easy;

import java.util.Comparator;

/**
 *
 * @author ddsnowboard
 */
public class PairAmountComparator implements Comparator<Pair>{
    public PairAmountComparator()
    {}

    @Override
    public int compare(Pair o1, Pair o2) {
        return o1.timesUsed - o2.timesUsed;
    }
}
