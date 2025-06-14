package commands;

import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammar.Alphabet;
import grammar.Grammar;
import grammar.GrammarMap;
import grammar.Rule;
import parsing.ContextParser;

public class IterCommand implements Command, ContextParser {
    /**
     * iter id - Iterates over given grammar and creates a new one
     * @param context
     * @throws Exception
     */
    @Override
    public void performCommand(String context) throws Exception {
        parseContext(context);
        //context is grammar ID
        int id = Integer.parseInt(context);

        Grammar grammar = GrammarMap.getInstance().getGrammarByID(id);
        if(grammar == null) throw new GrammarNotFoundException("Could not find grammar with id " + id);

        Grammar newGrammar = new Grammar("");

        addOld(newGrammar, grammar);

        iterateSymbols(grammar, newGrammar);

        GrammarMap.getInstance().addGrammar(newGrammar);
        System.out.println("Grammar iterated. Created new grammar with id " + (GrammarMap.getInstance().getIdCounter() - 1));
    }

    private void iterateSymbols(Grammar grammar, Grammar newGrammar) {
        for(String c1 : grammar.getTerminalSymbols().getSymbols()){
            for(String c2 : grammar.getTerminalSymbols().getSymbols()){
                newGrammar.getTerminalSymbols().addSymbol(c1 + c2);
            }
        }
        for(String c1 : grammar.getNonTerminalSymbols().getSymbols()){
            for(String c2 : grammar.getNonTerminalSymbols().getSymbols()){
                newGrammar.getNonTerminalSymbols().addSymbol(c1 + c2);
            }
        }
    }

    private void addOld(Grammar newGrammar, Grammar grammar) {
        newGrammar.getNonTerminalSymbols().addAll(grammar.getNonTerminalSymbols().getSymbols());
        newGrammar.getNonTerminalSymbols().addAll(grammar.getNonTerminalSymbols().getSymbols());
        newGrammar.getTerminalSymbols().addAll(grammar.getTerminalSymbols().getSymbols());
        newGrammar.getTerminalSymbols().addAll(grammar.getTerminalSymbols().getSymbols());
        for(Rule rule : grammar.getRules()){
            newGrammar.addRule(rule);
        }

        newGrammar.getNonTerminalSymbols().addSymbol(String.valueOf(Alphabet.EPSILON));
        newGrammar.getTerminalSymbols().addSymbol(String.valueOf(Alphabet.EPSILON));
    }

    @Override
    public String getDesc() {
        return "iter <id> - Iterates over given grammar and creates a new one";
    }

    @Override
    public String[] parseContext(String context) throws CommandContextException {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");
        return null;
    }
}
