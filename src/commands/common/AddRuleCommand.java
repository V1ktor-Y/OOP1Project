package commands.common;

import commands.Command;
import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammatic.Grammar;
import grammatic.GrammarMap;
import grammatic.Rule;

public class AddRuleCommand implements Command {
    @Override
    public void performCommand(String context) throws Exception {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");

        String[] keyWords = context.split(" ", 2);
        if(keyWords.length < 2) throw new CommandContextException("Not enough context given");

        int id = Integer.parseInt(keyWords[0]);
        String rule = keyWords[1];

        Grammar grammar = GrammarMap.getInstance().getGrammarByID(id);
        if(grammar == null) throw new GrammarNotFoundException("Failed to find grammar with id: " + id);
        grammar.addRule(new Rule(rule));
    }

    @Override
    public String getDesc() {
        return "addrule <id> <rule>: \t\tAdds given rule to given grammar";
    }
}
