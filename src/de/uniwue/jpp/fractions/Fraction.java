package de.uniwue.jpp.fractions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fraction implements Element {

    private Element numerator;
    private Element denominator;
    private String fraction;

    public Fraction(Element numerator, Element denominator){
        if(numerator == null || denominator == null){
            throw new NullPointerException();
        }
        if(numerator.width() == 0 || denominator.width() == 0){
            throw new IllegalArgumentException("0");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        this.fraction = this.generateFraction();
    }

    private String generateFraction(){
        int width = (this.numerator.width() >= this.denominator.width() ? this.numerator.width() : this.denominator.width())+2;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < width; i++){
            sb.append('\u2014');
        }
        return sb.toString();
    }

    @Override
    public String toString() {

        int breiteBruch = this.width();
        int rightnu;
        int leftnu;
        int rightde;
        int leftde;


        //Zähler zentrieren
        if((breiteBruch - this.numerator.width()) % 2 > 0 ){
            rightnu = ((breiteBruch - this.numerator.width()) + 1)/2;
            leftnu = breiteBruch - this.numerator.width() - rightnu;
            //System.out.println("ungerade:" + rightnu + "," + leftnu + " " + this.numerator + " " + "Main:" + breiteBruch + ";");
        }else {
            rightnu = (breiteBruch - this.numerator.width()) / 2;
            leftnu = rightnu;
          //  System.out.println("gerade:" + rightnu + "," + leftnu + " " + this.numerator + " " + "Main:" + breiteBruch + ";");
        }

        //System.out.println("Zaehler: " + this.numerator + "," + breiteBruch + "," + rightnu + "," +leftnu + ";");

        //Nenner zentrieren
        if((breiteBruch - this.denominator.width()) % 2 > 0 ){
            rightde = ((breiteBruch - this.denominator.width()) + 1)/2;
            leftde = breiteBruch - this.denominator.width() - rightde;
        }else {
            rightde = (breiteBruch - this.denominator.width()) / 2;
            leftde = rightde;
        }
        //System.out.println("Nenner: " + this.denominator + "," + breiteBruch + "," + rightde + "," +leftde + ";" + "BREITE:" + this.denominator.width());

        StringBuilder Str = new StringBuilder();


        adjustFractionElement(this.numerator, rightnu, leftnu, Str);

        Str.append('\n');

        Str.append(this.fraction);

        Str.append('\n');

        adjustFractionElement(this.denominator, rightde, leftde, Str);

        //System.out.println("Zaehler:" + numerator.toString() + ";");
        //System.out.println("nenner:" +  denominator.toString() + ";");
        //System.out.println(Str.toString());
        return Str.toString();
    }

    private static void adjustFractionElement(Element element, int rightnu, int leftnu, StringBuilder str) {
        if(element.toString().contains("\n")){
            String[] temp = element.toString().split("\n");
            String re = "";
            String li = "";
            if(leftnu == rightnu){
                for(int i = 0; i < leftnu; i++){
                    li += '\u0020';
                }
                re = li;
            }else{
                for(int i = 0; i < leftnu; i++){
                    li += '\u0020';
                }
                for(int i = 0; i < rightnu; i++){
                    re += '\u0020';
                }
            }

            for(int i = 0; i < temp.length; i++){
                temp[i] = li + temp[i] + re;
                str.append(temp[i]);
                if(i < temp.length - 1){
                    str.append("\n");
                }
            }
        }
        else {
            for (int i = 0; i < leftnu; i++) {
                str.append('\u0020');
            }

            str.append(element);

            for (int i = 0; i < rightnu; i++) {
                str.append('\u0020');
            }
        }
    }

    @Override
    public int width() {
        return this.fraction.length();
    }

    @Override
    public int lineCount() {
        /**int lines = 0;
        for (Element i: this ) {
            if (i.toString().contains("\n")) {
                lines++;
            } else {
                lines++;
            }
        }

        String[] n = this.numerator.toString().split("\n");
        String[] d = this.denominator.toString().split("\n");



        if(n.length > d.length){
            System.out.println(n.length);
            return n.length;
        }else{
            System.out.println(d.length);
            return d.length;
        }*/
        //System.out.println(this.numerator.lineCount() + this.denominator.lineCount() + 1);
        return this.numerator.lineCount() + this.denominator.lineCount() + 1;
    }
}
