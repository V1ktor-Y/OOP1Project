package commands;

import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammar.Grammar;
import grammar.GrammarMap;
import grammar.Rule;
import parsing.ContextParser;

public class AddRuleCommand implements Command, ContextParser {
    /**
     * addrule id rule - Adds given rule to given grammar
     * @param context
     * @throws Exception
     */
    @Override
    public void performCommand(String context) throws Exception {

        String[] keyWords = parseContext(context);
        int id = Integer.parseInt(keyWords[0]);
        String rule = keyWords[1];

        Grammar grammar = GrammarMap.getInstance().getGrammarByID(id);
        if(grammar == null) throw new GrammarNotFoundException("Failed to find grammar with id: " + id);
        grammar.addRule(new Rule(rule));
        System.out.println("Added rule " + rule);
    }

    @Override
    public String getDesc() {
        return "addrule <id> <rule> - Adds given rule to given grammar";
    }

    @Override
    public String[] parseContext(String context) throws CommandContextException {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");

        String[] keyWords = context.split(" ", 2);
        if(keyWords.length < 2) throw new CommandContextException("Not enough context given");
        return  keyWords;
    }
}
