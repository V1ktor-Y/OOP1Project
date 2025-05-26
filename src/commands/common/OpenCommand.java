package commands.common;

import commands.Command;
import exceptions.CommandContextException;
import exceptions.InvalidCharacterException;
import exceptions.SerializationException;
import grammar.GrammarMap;
import parsing.ContextParser;
import parsing.Parser;
import util.errorLog.ErrorLogger;


public class OpenCommand implements Command, ContextParser {
    /**
     * open filename - Reads a grammar from a .cfg file and adds it to the GrammarMap singleton's map
     * @param context
     * @throws CommandContextException
     */
    @Override
    public void performCommand(String context) throws CommandContextException, InvalidCharacterException, SerializationException {
        parseContext(context);
        GrammarMap.getInstance().addGrammar(Parser.readGrammarFromFile(context));
        System.out.println("Grammar loaded from " + context);
    }

    @Override
    public String getDesc() {
        return "open <filename> - Reads a grammar from a .cfg file and adds it to the GrammarMap singleton's map";
    }

    @Override
    public String[] parseContext(String context) throws CommandContextException {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");

        return null;
    }
}
