package commands;

import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammar.Grammar;
import grammar.GrammarMap;
import grammar.Rule;
import parsing.ContextParser;

public class ChomskyCommand implements Command, ContextParser
{
    /**
     * chomsky id - Checks if given grammar is in Chomsky Normal Form
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
        System.out.println("Grammar is in Chomsky Normal Form");

    }
    @Override
    public String getDesc() {
        return "chomsky <id> - Checks if given grammar is in Chomsky Normal Form";
    }

    @Override
    public String[] parseContext(String context) throws CommandContextException {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");
        return  null;
    }
}
