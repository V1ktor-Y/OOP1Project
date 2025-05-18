package commands;

import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammar.Grammar;
import grammar.GrammarMap;

public class ConcatCommand implements Command{

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

        //Add Alphabets from Grammar<id1>
        newGrammar.getTerminalSymbols().addAll(grammar1.getTerminalSymbols().getSymbols());
        newGrammar.getNonTerminalSymbols().addAll(grammar1.getNonTerminalSymbols().getSymbols());
        //Add Alphabets from Grammar<id2>
        newGrammar.getTerminalSymbols().addAll(grammar2.getTerminalSymbols().getSymbols());
        newGrammar.getNonTerminalSymbols().addAll(grammar2.getNonTerminalSymbols().getSymbols());
        //Add Rules from both grammars
        newGrammar.getRules().addAll(grammar1.getRules());
        newGrammar.getRules().addAll(grammar2.getRules());

        System.out.println("New grammar saved with id - " + GrammarMap.getInstance().addGrammar(newGrammar));

    }

    @Override
    public String getDesc() {
        return "concat <id1> <id2> - Gets the concatination between two grammars and saves it to the grammar map";
    }
}
