package commands.common;

import commands.Command;
import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammar.GrammarMap;
import parsing.ContextParser;

public class CloseCommand implements Command, ContextParser {
    /**
     * close id - Removes the grammar with the given key(id) from the grammar map
     * @param context
     * @throws GrammarNotFoundException
     * @throws CommandContextException
     */
    @Override
    public void performCommand(String context) throws GrammarNotFoundException, CommandContextException {
        parseContext(context);
        //context is grammar ID
        int id = Integer.parseInt(context);

        if (GrammarMap.getInstance().removeGrammarByID(id)){
            System.out.println("Removed grammar with id " + id);
        }else{
            throw new GrammarNotFoundException("Could not find grammar with id " + id);
        }
    }

    @Override
    public String getDesc() {
        return "close <id> - Removes the grammar with the given key(id) from the grammar map";
    }

    @Override
    public String[] parseContext(String context) throws CommandContextException{
        if (context.isEmpty()) throw new CommandContextException("Empty command context");

        return null;
    }
}
