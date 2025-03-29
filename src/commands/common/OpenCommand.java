package commands.common;

import commands.Command;
import exceptions.CommandContextException;
import exceptions.InvalidCharacterException;
import exceptions.SerializationException;
import grammatic.GrammarMap;
import parsing.Parser;
import util.errorLog.ErrorLogger;

import java.util.Objects;

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
        return "Reads a grammar from a .cfg file and adds it to the GrammarMap singleton's map";
    }
}
