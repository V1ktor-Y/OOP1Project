package commands.common;

import commands.Command;
import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammatic.Grammar;
import grammatic.GrammarMap;
import parsing.Parser;
import util.writeToFile.WriteToFile;

public class SaveCommand implements Command {
    @Override
    public void performCommand(String context) throws Exception {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");

        //context is grammar ID
        int id = Integer.parseInt(context);

        Grammar grammar = GrammarMap.getInstance().getGrammarByID(id);
        if(grammar == null) throw new GrammarNotFoundException("Could not find grammar with id " + id);

        String path = grammar.getOriginalFile();
        WriteToFile.write(path,false, Parser.grammarToString(grammar));

        System.out.println("Saved grammar with id " + id + "to file " + path);
    }

    @Override
    public String getDesc() {
        return "Saves grammar with the given id";
    }
}
