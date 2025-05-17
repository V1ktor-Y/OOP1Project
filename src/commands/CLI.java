package commands;

import commands.common.*;
import commands.*;
import exceptions.CommandNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class CLI {
    private static final Map<CommandType,Command> commandMap = new HashMap<>();
    private static CLI instance;

    private CLI() {
        //put all future commands here
        //Common
        commandMap.put(CommandType.CLOSE,new CloseCommand());
        commandMap.put(CommandType.EXIT,new ExitCommand());
        commandMap.put(CommandType.HELP,new HelpCommand());
        commandMap.put(CommandType.OPEN,new OpenCommand());
        commandMap.put(CommandType.SAVEAS,new SaveAsCommand());
        commandMap.put(CommandType.SAVE,new SaveCommand());

        commandMap.put(CommandType.ADDRULE,new AddRuleCommand());
        commandMap.put(CommandType.CHOMSKIFY,new ChomskifyCommand());
        commandMap.put(CommandType.CYK,new CYKCommand());
        commandMap.put(CommandType.CHOMSKY,new ChomskyCommand());
        commandMap.put(CommandType.CONCAT,new ConcatCommand());
        commandMap.put(CommandType.ITER,new IterCommand());
        commandMap.put(CommandType.LIST,new ListCommand());
        commandMap.put(CommandType.PRINT,new PrintCommand());
        commandMap.put(CommandType.REMOVERULE,new RemoveRuleCommand());
        commandMap.put(CommandType.UNION,new UnionCommand());

    }

    public static CLI getInstance(){
        if (instance == null){
            instance = new CLI();
        }
        return instance;
    }

    public Map<CommandType, Command> getCommandMap() {
        return commandMap;
    }

    public void performCommand(CommandType type, String context) throws Exception {
        Command result = commandMap.get(type);
        if(result == null) {
            throw new CommandNotFoundException("Command not found");
        }
        result.performCommand(context);
    }
}
