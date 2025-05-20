package commands.common;

import commands.Command;
import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import exceptions.PathException;
import grammar.Grammar;
import grammar.GrammarMap;
import parsing.Parser;
import util.writeToFile.WriteToFile;

public class SaveCommand implements Command {
    /**
     * save id - Saves grammar with the given id to its original file
     * @param context
     * @throws Exception
     */
    @Override
    public void performCommand(String context) throws Exception {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");

        //context is grammar ID
        int id = Integer.parseInt(context);

        Grammar grammar = GrammarMap.getInstance().getGrammarByID(id);
        if(grammar == null) throw new GrammarNotFoundException("Could not find grammar with id " + id);

        String path = grammar.getOriginalFile();

        if(path.isEmpty()) throw new PathException("This grammar does not have a default path");
        WriteToFile.write(path,false, Parser.grammarToString(grammar));

        System.out.println("Saved grammar with id " + id + " to file " + path);
    }

    @Override
    public String getDesc() {
        return "save <id> - Saves grammar with the given id to its original file";
    }
}
