/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp195medium;

import java.util.Random;

/**
 *
 * @author ddsnowboard
 */
public class Die {

    private int sides;
    private static final Random rand = new Random();

    public Die(int sides) {
        this.sides = sides;
    }

    public int roll() {
        return rand.nextInt(sides - 1) + 1;
    }
}
