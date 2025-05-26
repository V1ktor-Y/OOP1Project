package commands;

import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammar.Grammar;
import grammar.GrammarMap;
import parsing.ContextParser;

public class EmptyCommand implements Command, ContextParser {
    /**
     * empty id - Checks if the alphabet of given command is empty
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

        if(grammar.getNonTerminalSymbols().size() == 0 && grammar.getTerminalSymbols().size() == 0){
            System.out.println("Grammar is empty");
        }else{
            System.out.println("Grammar is not empty");
        }
    }

    @Override
    public String getDesc() {
        return "empty <id> - Checks if the alphabet of given command is empty";
    }

    @Override
    public String[] parseContext(String context) throws CommandContextException {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");
        return null;
    }
}
