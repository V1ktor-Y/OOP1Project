package grammatic;

import java.util.Arrays;

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
        System.out.println(Arrays.toString(sides));
    }

    public String getLeftSide() {
        return leftSide;
    }

    public String getRightSide() {
        return rightSide;
    }
}
