package commands;

import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammatic.Grammar;
import grammatic.GrammarMap;

public class EmptyCommand implements Command{
    @Override
    public void performCommand(String context) throws Exception {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");

        //context is grammar ID
        int id = Integer.parseInt(context);

        Grammar grammar = GrammarMap.getInstance().getGrammarByID(id);
        if(grammar == null) throw new GrammarNotFoundException("Could not find grammar with id " + id);

        if(grammar.getNonterminalSymbols().size() == 0 && grammar.getTerminalSymbols().size() == 0){
            System.out.println("Grammar is empty");
        }else{
            System.out.println("Grammar is not empty");
        }
    }

    @Override
    public String getDesc() {
        return "empty <id> - Checks if the alphabet of given command is empty";
    }
}
