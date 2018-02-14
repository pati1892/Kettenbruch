import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        }else {
            height = this.right.lineCount();
        }

        String[] left = this.left.toString().split("\n");
        String[] right = this.right.toString().split("\n");
        int diff = this.left.lineCount() - this.right.lineCount();
        String[] diffarr = new String[Math.abs(diff)];
        if(diff < 0){
            String temp = "";
            for(int i = 0; i < left.length; i++){
                temp += '\u0020';
            }
            for(int i = 0; i < diffarr.length; i++){
                diffarr[i] = temp;
            }
            List<String> list = new ArrayList<>(Arrays.asList(left));

            int i=0;

            while(i < temp.length()){
                if(i % 2 == 0){
                    list.add(0,diffarr[i]);
                }else{
                    list.add(diffarr[i]);
                }
                i++;
            }
            left = list.toArray(new String[list.size()]);

        }

        if(diff > 0){
            String temp = "";
            for(int i = 0; i < right.length; i++){
                temp += '\u0020';
            }
            for(int i = 0; i < diffarr.length; i++){
                diffarr[i] = temp;
            }

            List<String> list = new ArrayList<>(Arrays.asList(right));

            int i=0;

            while(i < temp.length()){
                if(i % 2 == 0){
                    list.add(0,diffarr[i]);
                }else{
                    list.add(diffarr[i]);
                }
                i++;
            }
            right = list.toArray(new String[list.size()]);
        }

        int index;

        if(height % 2 == 0){
            index = height / 2;
        }else{
            index = (height - 1)/2;
        }
        StringBuilder str = new StringBuilder();

        for(int i = 0; i < height; i++){
            String operator = "\u0020";
            if(i == index){
                operator = this.operator;
            }
            str.append(left[i]);
            str.append(operator);
            str.append(right[i]);
            str.append("\n");
        }

        return str.toString();
    }
}
