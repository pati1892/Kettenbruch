package de.uniwue.jpp.fractions;

import java.util.regex.Pattern;

public class Parser {

    private static final char FRACTION = '\u002f';
    private static final char OPEN = '\u0028';
    private static final char CLOSE = '\u0029';



    public static Element parse(String input){
        if(input.length() - input.replaceAll(String.valueOf(OPEN),"").length() != input.length() - input.replaceAll(String.valueOf(CLOSE),"").length()){
            throw new IllegalArgumentException();
        }
        if(Pattern.matches(FRACTION + "{2,}", input)){
            throw new IllegalArgumentException("//");
        }


        input = input.trim();

        if (!input.contains(String.valueOf(FRACTION))) {
            return new Term(input);
        }

        input = removeBracket(input);

        String[] fraction = findFraction(input);
        if(fraction != null && fraction.length == 2){
            return new Fraction(parse(fraction[0]), parse(fraction[1]));
        }

        String[] operator = findOperator(input);

        return new Operator(parse(operator[0]), operator[1], parse(operator[2]));

    }

    private static String[] findOperator(String input) {
        int counter = 0;
        String operant_1 = "";
        String operant_2 = "";
        String operator = "";

        int index = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == OPEN) {
                index = i;
            } else if (input.charAt(i) == CLOSE) {
                if (operant_1.equals(""))
                    operant_1 = input.substring(index, i);
                else
                    operant_2 = input.substring(index, i);

            }
            operator = input.replaceAll(operant_1, "");
            operator = operator.replaceAll(operant_2, "");
            if (operant_2.equals("")) {
                operant_2 = operant_1;
                operant_1 = "";
            }
        }
        return new String[]{operant_1, operator, operant_2};
    }

    private static String[] findFraction(String input){
        int counter = 0;
        int index = -1;
        for(int i = 0; i<input.length(); i++) {
            if(input.charAt(i) == OPEN) counter++;
            else if(input.charAt(i) == CLOSE) counter --;
            else if((input.charAt(i) == FRACTION) && (counter == 0)){
                index = i;
                break;
            }
        }
        if(index == -1) return null;
        String nenner = input.substring(0, index-1);
        String zaehler = input.substring(index+1, input.length()-1);
        return new String[]{nenner, zaehler};

    }

    private static String removeBracket(String input){
        int counter = 0;
        boolean remove = false;
        for(int i = 0; i<input.length(); i++){
            if(input.charAt(i) == OPEN) counter++;
            else if(input.charAt(i) == CLOSE) counter --;
            if(counter == 0){
                if(i == input.length()-1){
                    remove = true;
                }
            }
        }
        if(remove){
            return removeBracket(input.substring(1, input.length()-2));
        }
        else{
            return input;
        }
    }
}
