package commands.common;

import commands.Command;
import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammatic.Grammar;
import grammatic.GrammarMap;
import parsing.Parser;
import util.writeToFile.WriteToFile;

public class SaveAsCommand implements Command {
    @Override
    public void performCommand(String context) throws Exception {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");
        String[] keyWords = context.split(" ", 2);
        if(keyWords.length < 2) throw new CommandContextException("Not enough context given");
        //context is grammar ID
        int id = Integer.parseInt(keyWords[0]);

        Grammar grammar = GrammarMap.getInstance().getGrammarByID(id);
        if(grammar == null) throw new GrammarNotFoundException("Could not find grammar with id " + id);

        String path = keyWords[1];

        WriteToFile.write(path,false, Parser.grammarToString(grammar));
        System.out.println("Saved grammar with id " + id + "to file " + path);


    }

    @Override
    public String getDesc() {
        return "Saves grammar with he given id to the given path";
    }
}
