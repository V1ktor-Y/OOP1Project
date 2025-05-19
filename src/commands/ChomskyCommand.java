package commands;

import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammar.Grammar;
import grammar.GrammarMap;
import grammar.Rule;

public class ChomskyCommand implements Command
{
    @Override
    public void performCommand(String context) throws Exception {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");

        //context is grammar ID
        int id = Integer.parseInt(context);

        Grammar grammar = GrammarMap.getInstance().getGrammarByID(id);
        if(grammar == null) throw new GrammarNotFoundException("Could not find grammar with id " + id);

        for (Rule r : grammar.getRules()) {
            String rightSide = r.getRightSide();
            if (rightSide.length() == 1 && grammar.getTerminalSymbols().contains(rightSide)) {
                continue;
            }
            if (rightSide.length() == 2 && grammar.getNonTerminalSymbols().contains(String.valueOf(rightSide.charAt(0))) && grammar.getNonTerminalSymbols().getSymbols().contains(String.valueOf(rightSide.charAt(1)))) {
                continue;
            }
            System.out.println("Grammar is not in Chomsky Normal Form");
            return;
        }
    }
    @Override
    public String getDesc() {
        return "chomsky <id> - Checks if given grammar is in Chomsky Normal Form";
    }

}
