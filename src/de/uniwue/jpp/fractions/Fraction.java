package de.uniwue.jpp.fractions;

import java.util.Objects;

public class Fraction implements Element {

    private Element numerator;
    private Element denominator;
    private String fraction;
    private final static char SPACE = '\u0020';

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
        }else {
            rightnu = (breiteBruch - this.numerator.width()) / 2;
            leftnu = rightnu;
        }

        //Nenner zentrieren
        if((breiteBruch - this.denominator.width()) % 2 > 0 ){
            rightde = ((breiteBruch - this.denominator.width()) + 1)/2;
            leftde = breiteBruch - this.denominator.width() - rightde;
        }else {
            rightde = (breiteBruch - this.denominator.width()) / 2;
            leftde = rightde;
        }

        StringBuilder Str = new StringBuilder();


        adjustFractionElement(this.numerator, rightnu, leftnu, Str);

        Str.append('\n');

        Str.append(this.fraction);

        Str.append('\n');

        adjustFractionElement(this.denominator, rightde, leftde, Str);

        return Str.toString();
    }

    private static void adjustFractionElement(Element element, int rightnu, int leftnu, StringBuilder str) {
        if(element.toString().contains("\n")){
            String[] temp = element.toString().split("\n");
            String re = "";
            String li = "";
            if(leftnu == rightnu){
                for(int i = 0; i < leftnu; i++){
                    li += SPACE;
                }
                re = li;
            }else{
                for(int i = 0; i < leftnu; i++){
                    li += SPACE;
                }
                for(int i = 0; i < rightnu; i++){
                    re += SPACE;
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
                str.append(SPACE);
            }

            str.append(element);

            for (int i = 0; i < rightnu; i++) {
                str.append(SPACE);
            }
        }
    }

    @Override
    public int width() {
        return this.fraction.length();
    }

    @Override
    public int lineCount() {
        return this.numerator.lineCount() + this.denominator.lineCount() + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fraction)) return false;
        Fraction fraction = (Fraction) o;
        return Objects.equals(numerator, fraction.numerator) &&
                Objects.equals(denominator, fraction.denominator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }
}
