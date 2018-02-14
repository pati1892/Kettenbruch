import java.util.Objects;

public class Term {

    private String term;

    public Term(){ }

    public Term(String term){
        if(term == null){
            throw new NullPointerException();
        }
        String[] temp = term.split("\n");
        this.term = temp[0];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Term)) return false;
        Term term1 = (Term) o;
        return Objects.equals(term, term1.term);
    }

    @Override
    public int hashCode() {

        return Objects.hash(term);
    }

    @Override
    public String toString() {
        return this.term;
    }
}
