/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Complex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ddsnowboard
 */
public class Complex {

    private static final Pattern pattern = Pattern.compile("(?<real>[+-]?[0-9]+)??(?<imaginary>[+-]?[0-9]+i)??");
    private double real;
    private double imaginary;

    public static Complex add(Complex a, Complex b) {
        return new Complex(a.real + b.real, a.imaginary + b.imaginary);
    }

    public static Complex subtract(Complex a, Complex b) {
        return new Complex(a.real - b.real, a.imaginary - b.imaginary);
    }

    public static Complex multiply(Complex a, Complex b) {
        return new Complex(a.real * b.real - (a.imaginary * b.imaginary), (a.real * b.imaginary) + (b.real * a.imaginary));
    }

    public static Complex divide(Complex top, Complex bottom) {
        double a = top.real;
        double b = top.imaginary;
        double c = bottom.real;
        double d = bottom.imaginary;
        return new Complex((a * c + b * d) / (c * c + d * d), (b * c - a * d) / (c * c + d * d));
    }

    public Complex(String number) {
        Matcher match = Complex.pattern.matcher(number);
        if (match.matches()) {
            if (match.group("real") != null) {
                real = Float.parseFloat(match.group("real"));
            } else {
                real = 0;
            }
            if (match.group("imaginary") != null) {
                imaginary = Float.parseFloat(match.group("imaginary").replace("i", ""));
            } else {
                imaginary = 0;
            }
        } else {
            throw new NumberFormatException("Wrong format; should be \"N+Ni\" or similar.");
        }
    }

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return this.real;
    }

    public double getImaginary() {
        return this.imaginary;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (real != 0) {
            sb.append(real);
        }
        if (imaginary > 0 && real != 0) {
            sb.append("+");
        }
        if (imaginary != 0) {
            sb.append(imaginary);
            sb.append("i");
        }
        return sb.toString();
    }

    public double getModulus() {
        return Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
    }

    public Complex getConjugate() {
        return new Complex(real, imaginary * -1);
    }

    public double getArgument() {
        return Math.atan2(imaginary, real);
    }
}
