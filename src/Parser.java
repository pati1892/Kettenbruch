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
            Term neu = new Term(input);
            return neu;
        }


        return null;
    }

    private static Element parseInput(String input){
        input = input.trim();

        if (!input.contains(String.valueOf(FRACTION))) {
            Term neu = new Term(input);
            return neu;
        }
        input = removeBracket(input);

        String[] fraction = findFraction(input);
        if(fraction.length == 2){
            return new Fraction(parseInput(fraction[0]), parseInput(fraction[1]));
        }



        return null;
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
