public class Fraction implements Element {

    private Element numerator;
    private Element denominator;

    public Fraction(Element numerator, Element denominator){
        if(numerator == null || denominator == null){
            throw new NullPointerException();
        }
        if(numerator.width() == 0 || denominator.width() == 0){
            throw new IllegalArgumentException("0");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public String toString() {

        int main;
        int leftn;
        int rightn;
        int leftd;
        int rightd;

        if(this.numerator.width() > this.denominator.width()){
            main = this.numerator.width() + 2;
        }else{
            main = this.denominator.width() + 2;
        }
        //Zähler zentrieren
        if(((main - numerator.width())/2)%1 > 0 ){
            leftn = ((main - numerator.width()) + 1)/2;
            rightn = main - numerator.width() - leftn;
        }else {
            leftn = (main - numerator.width()) / 2;
            rightn = leftn;
        }

        //Nenner zentrieren
        if(((main - denominator.width())/2)%1 > 0 ){
            leftd = ((main - denominator.width()) + 1)/2;
            rightd = main - denominator.width() - leftd;
        }else {
            leftd = (main - denominator.width()) / 2;
            rightd = leftd;
        }

        StringBuilder Str = new StringBuilder();

        for(int i = 0; i < leftn ; i++){
            Str.append('\u0020');
        }

        Str.append(numerator);

        for(int i = 0; i < rightn; i++){
            Str.append('\u0020');
        }

        Str.append('\n');

        for(int i = 0; i < main; i++){
            Str.append('\u2014');
        }

        Str.append('\n');

        for(int i = 0; i < leftd ; i++){
            Str.append('\u0020');
        }

        Str.append(denominator);

        for(int i = 0; i < rightd; i++){
            Str.append('\u0020');
        }

        return Str.toString();
    }

    @Override
    public int width() {
        if(this.numerator.width() > this.denominator.width()){
            return this.numerator.width() + 2;
        }
        return this.denominator.width() + 2;
    }

    @Override
    public int lineCount() {
        if(this.numerator.lineCount() > this.denominator.lineCount()){
            return this.numerator.lineCount();
        }
        return this.denominator.lineCount();
    }
}
