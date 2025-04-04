package commands.common;

import commands.Command;
import exceptions.CommandContextException;
import grammatic.GrammarMap;
import parsing.Parser;
import util.errorLog.ErrorLogger;


public class OpenCommand implements Command {
    @Override
    public void performCommand(String context) throws CommandContextException {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");
        try{
            GrammarMap.getInstance().addGrammar(Parser.readGrammarFromFile(context));
            System.out.println("Grammar loaded from " + context);
        } catch (Exception e){
            ErrorLogger.log(e);
        }
    }

    @Override
    public String getDesc() {
        return "open <filename>: \t\tReads a grammar from a .cfg file and adds it to the GrammarMap singleton's map";
    }
}
