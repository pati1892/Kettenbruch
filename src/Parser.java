import java.util.regex.Pattern;

public class Parser {

    private static final char FRAKTION = '\u002f';
    private static final char OPEN = '\u0028';
    private static final char CLOSE = '\u0029';


    public static Element parse(String input){
        if(input.length() - input.replaceAll(String.valueOf(OPEN),"").length() != input.length() - input.replaceAll(String.valueOf(CLOSE),"").length()){
            throw new IllegalArgumentException();
        }
        if(Pattern.matches(FRAKTION + "{2,}", input)){
            throw new IllegalArgumentException("//");
        }
        input = input.trim();

        if (!input.contains(String.valueOf(FRAKTION))) {
            Term neu = new Term(input);
            return neu;
        }


        return null;
    }

}
