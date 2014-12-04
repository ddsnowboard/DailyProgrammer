/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp191easy;

import java.util.HashMap;

/**
 *
 * @author ddsnowboard
 */
public class Counter extends HashMap<String, Integer> {

    public Counter() {
        super();
    }

    public void count(String s) {
        if (this.get(s) == null) {
            this.put(s, 1);
        } else {
            this.put(s, this.get(s)+1);
        }
    }
    
}
