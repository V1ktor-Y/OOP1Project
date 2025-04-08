package grammatic;

import java.util.Arrays;
import java.util.Objects;

public class Rule {
    private String leftSide;
    private String rightSide;

    public Rule(String leftSide, String rightSide){
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public Rule(String string){
        String[] sides = string.split("->",2);
        leftSide = sides[0].trim();
        rightSide = sides[1].trim();
    }

    public String getLeftSide() {
        return leftSide;
    }

    public String getRightSide() {
        return rightSide;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return Objects.equals(leftSide, rule.leftSide) && Objects.equals(rightSide, rule.rightSide);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftSide, rightSide);
    }
}
