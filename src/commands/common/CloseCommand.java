package commands.common;

import commands.Command;
import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammar.GrammarMap;

public class CloseCommand implements Command {
    /**
     * close id - Removes the grammar with the given key(id) from the grammar map
     * @param context
     * @throws GrammarNotFoundException
     * @throws CommandContextException
     */
    @Override
    public void performCommand(String context) throws GrammarNotFoundException, CommandContextException {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");

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
}
