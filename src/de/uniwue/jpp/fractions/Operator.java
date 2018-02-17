package de.uniwue.jpp.fractions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Operator implements Element {

    private final static char SPACE = '\u0020';

    private Element left;
    private Element right;
    private String operator;

    public Operator(Element left, String operator, Element right){
        if(left == null || operator == null || right == null){
            throw new NullPointerException();
        }
        if(operator.contains("\n")) {
            String[] temp = operator.split("\n");
            this.operator = temp[0];
        }else{
            this.operator = operator;
        }

        this.left = left;
        this.right = right;

    }

    @Override
    public String toString() {
        int height = this.left.lineCount() >= this.right.lineCount() ? this.left.lineCount() : this.right.lineCount();


        //System.out.println("links: " + this.left.toString());
        //System.out.println("rechts: " + this.right.toString() + ";");

        String[] left = this.left.toString().split("\n");
        String[] right = this.right.toString().split("\n");

        int diff = this.left.lineCount() - this.right.lineCount();

        if(diff < 0){
            left = fillOperator(left, diff, this.left.width());
        }else{
            right = fillOperator(right, diff, this.right.width());
        }

        int index = (height%2 == 0) ? height/2 : (height-1)/2;

        /*if(height % 2 == 0){
            index = height / 2;
        }else{
            index = (height - 1)/2;
        }*/
        StringBuilder str = new StringBuilder();

        //System.out.println(this.operator.length());

        for(int i = 0; i < height; i++){

            String operator = String.valueOf(SPACE);

            if(i == index){
                operator = this.operator;
            }

            str.append(left[i]);

            if(i != index) {
                for (int y = 0; y < this.operator.length(); y++)  str.append(operator);
            }else{
                str.append(operator);
            }

            str.append(right[i]);
            if(i<height) str.append("\n");
        }

        return str.toString();
    }

    private static String[] fillOperator(String[] operator, int diff, int elementWidth) {
        String filler = "";
        for(int i = 0; i < elementWidth; i++){
            filler += SPACE;
        }

        List<String> list = new ArrayList<>(Arrays.asList(operator));

        for(int i = 0; i < Math.abs(diff); i++){
            if(i % 2 == 0){
                list.add(0,filler);
            }else{
                list.add(filler);
            }
        }
        return list.toArray(new String[list.size()]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operator)) return false;
        Operator operator1 = (Operator) o;
        return Objects.equals(left, operator1.left) &&
                Objects.equals(right, operator1.right) &&
                Objects.equals(operator, operator1.operator);
    }

    @Override
    public int hashCode() {

        return Objects.hash(left, right, operator);
    }

    @Override
    public int width() {
        return this.left.width() + this.operator.length() + this.right.width();
    }

    @Override
    public int lineCount() {
        String[] l = this.left.toString().split("\n");

        String[] r = this.right.toString().split("\n");

        //System.out.println("links: \n" + l.toString());
        //System.out.println("rechtes: \n" + r.toString());

        if(l.length > r.length){
            return l.length;
        }else{
            return r.length;
        }
    }
}
