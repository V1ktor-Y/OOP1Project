package commands.common;

import commands.Command;
import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammatic.GrammarMap;

public class CloseCommand implements Command {
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
        return "close <id>: \t\tRemoves the grammar with the given key(id) from the grammar map";
    }
}
