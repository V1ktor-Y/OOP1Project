package grammatic;

public class Rule {
    private String leftSide;
    private String rightSide;

    public Rule(String leftSide, String rightSide){
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public Rule(String string){
        String[] sides = string.split("",3);
        leftSide = sides[0];
        rightSide = sides[2];
    }

    public String getLeftSide() {
        return leftSide;
    }

    public String getRightSide() {
        return rightSide;
    }
}
