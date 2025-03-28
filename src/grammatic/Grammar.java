package grammatic;

import java.util.ArrayList;
import java.util.List;

public class Grammar {
    private List<Rule> rules = new ArrayList<>();

    public void addRule(String rule){
        rules.add(new Rule(rule));
    }
}
