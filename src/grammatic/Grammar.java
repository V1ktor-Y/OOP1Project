package grammatic;

import java.util.ArrayList;
import java.util.List;

public class Grammar {
    private List<Rule> rules = new ArrayList<>();
    private int id;

    public void addRule(String rule){
        rules.add(new Rule(rule));
    }
}
