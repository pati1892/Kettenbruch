package de.uniwue.jpp.fractions;

import java.util.regex.Pattern;

public class Parser {

    private static final char FRACTION = '\u002f';
    private static final char OPEN = '\u0028';
    private static final char CLOSE = '\u0029';


    public static Element parse(String input){
        if(input.length() - input.replace(String.valueOf(OPEN),"").length() != input.length() - input.replace(String.valueOf(CLOSE),"").length()){
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
            //System.out.println(";zaehler:" + fraction[0] +";nenner:" + fraction[1]);
            //System.out.println(parse(fraction[0]) + "---"+ parse(fraction[1]));
            return new Fraction(parse(fraction[0]), parse(fraction[1]));
        }

       /* String[] operator = findOperator(input);*/

        //System.out.println("o:" + parse(operator[0]) + "\n o:-" + operator[1] + "- \no:" + parse(operator[2]));

        return findOperator(input);

    }

    private static Operator findOperator(String input) {
        int counter = 0;
        int inputLength = input.length();
        String[] op = new String[]{"", ""};
        int opIndex = 0;
        String operator = "";
        int i = 0;
        StringBuilder operatorBuilder = new StringBuilder();
        for(; i<inputLength; i++){
            char c = input.charAt(i);
            if(i == 0 && c != OPEN){
                opIndex = 1;
                operatorBuilder.append(c);
            }else if( c == OPEN){
                counter ++;
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                while(counter != 0){
                    i++;
                    c = input.charAt(i);
                    if(c == OPEN) counter++;
                    if(c == CLOSE) counter--;
                    sb.append(c);
                }
                op[opIndex] = sb.toString();
                opIndex++;
            }else{
                operatorBuilder.append(c);
            }

            if(opIndex > 1) break;
        }
        operator = operatorBuilder.toString();
        String remainInput = input.replace(input.substring(0, i+1), "");
        Operator operatorOne =  new Operator(parse(op[0]), operator, parse(op[1]));

        if(remainInput != null && remainInput.length()>0){
            return new Operator(operatorOne, "", findOperator(remainInput));
        }
        else{
            return operatorOne;
        }


    }

    private static String[] findFraction(String input){
        int counter = 0;
        int index = -1;
        for(int i = input.length() - 1; i > 0; i--) {
            if(input.charAt(i) == CLOSE) {
                counter++;
            }
            else if(input.charAt(i) == OPEN){
                counter --;
            }
            else if((input.charAt(i) == FRACTION) && (counter == 0)){
                index = i;
                break;
            }
        }
        if(index == -1) return null;
        String nenner = input.substring(0, index);
        String zaehler = input.substring(index+1, input.length());
        //System.out.println("zeahler:" + zaehler + "nenner:" + nenner);
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
                break;
            }
        }
        if(remove){
            return removeBracket(input.substring(1, input.length()-1));
        }
        else{
            return input;
        }
    }

    public static void main(String[] args) {
        String test ="1 / ((3 / x) * (y / 5) + ((2 / x) / y))";
        //String o1 = "(( log(y))/(1))";
        //String o2 = "(( z )/((5x)!))";
        //String op = "! + ";
        System.out.print(parse(test));
    }
}
