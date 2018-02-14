public class Operator {

    private Element left;
    private Element right;
    private String operator;

    public Operator(Element left, String operator, Element right){
        if(left == null || operator == null || right == null){
            throw new NullPointerException();
        }

        this.left = left;
        this.right = right;
        this.operator = operator;

    }

    @Override
    public String toString() {
        int height;
        if(this.left.lineCount() > this.right.lineCount()){
            height = this.left.lineCount();
        }
        height = this.right.lineCount();

    }
}
