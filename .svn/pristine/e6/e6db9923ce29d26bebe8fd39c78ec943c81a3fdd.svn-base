package de.uniwue.jpp.fractions;

import java.util.Objects;

public class Term implements Element {

    private String term;

    public Term(){ }

    public Term(String term){
        if(term == null){
            throw new NullPointerException();
        }
        if(term.contains("\n")) {
            String[] temp = term.split("\n");
            this.term = temp[0];
        }else {
            this.term = term;
        }
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

    @Override
    public int width() {
        return this.term.length();
    }

    @Override
    public int lineCount() {
        return 1;
    }
}
