/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp195easy;

/**
 *
 * @author ddsnowboard
 */
public class Redirect {
    String input;
    String output;
    public Redirect(String s)
    {
        this.input = s.split(":")[0];
        this.output = s.split(":")[1];
        if(this.input.charAt(this.input.length()-1) != '/')
            this.input += "/";
        if(this.output.charAt(this.output.length()-1) != '/')
            this.output += "/";
    }
}
