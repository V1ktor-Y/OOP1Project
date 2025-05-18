package commands;

import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammar.Grammar;
import grammar.GrammarMap;
import grammar.Rule;

public class UnionCommand implements Command {
    @Override
    public void performCommand(String context) throws Exception {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");

        String[] keyWords = context.split(" ", 2);
        if(keyWords.length < 2) throw new CommandContextException("Not enough context given");

        //context is grammar ID
        int id1 = Integer.parseInt(keyWords[0]);
        int id2 = Integer.parseInt(keyWords[1]);

        Grammar grammar1 = GrammarMap.getInstance().getGrammarByID(id1);
        if(grammar1 == null) throw new GrammarNotFoundException("Could not find grammar with id " + id1);

        Grammar grammar2 = GrammarMap.getInstance().getGrammarByID(id2);
        if(grammar2 == null) throw new GrammarNotFoundException("Could not find grammar with id " + id2);

        Grammar newGrammar = new Grammar("");

        for(String c : grammar1.getTerminalSymbols().getSymbols()){
            if(grammar2.getTerminalSymbols().contains(c)){
                newGrammar.getTerminalSymbols().addSymbol(c);
            }
        }
        for(String c : grammar1.getNonTerminalSymbols().getSymbols()){
            if(grammar2.getNonTerminalSymbols().contains(c)){
                newGrammar.getNonTerminalSymbols().addSymbol(c);
            }
        }

        for(Rule r : grammar1.getRules()){
            if(grammar2.getRules().contains(r)){
                newGrammar.addRule(r);
            }
        }


        System.out.println("New grammar saved with id - " + GrammarMap.getInstance().addGrammar(newGrammar));

    }

    @Override
    public String getDesc() {
        return "union <id1> <id2> - Gets the union between two grammars and saves it to the grammar map";
    }
}
