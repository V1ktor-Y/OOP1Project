package commands;

import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammar.Alphabet;
import grammar.Grammar;
import grammar.GrammarMap;
import grammar.Rule;

public class IterCommand implements Command {
    @Override
    public void performCommand(String context) throws Exception {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");

        //context is grammar ID
        int id = Integer.parseInt(context);

        Grammar grammar = GrammarMap.getInstance().getGrammarByID(id);
        if (grammar == null) throw new GrammarNotFoundException("Could not find grammar with id " + id);

        Grammar newGrammar = new Grammar("");

        newGrammar.getNonTerminalSymbols().addAll(grammar.getNonTerminalSymbols().getSymbols());
        newGrammar.getNonTerminalSymbols().addAll(grammar.getNonTerminalSymbols().getSymbols());
        for (Rule rule : grammar.getRules()) {
            newGrammar.addRule(rule);
        }
        for (Rule r1 : newGrammar.getRules()) {
            for (Rule r2 : newGrammar.getRules()) {

            }
        }

    }

    @Override
    public String getDesc() {
        return "iter <id> - Iterates over given grammar and creates a new one";
    }
}
