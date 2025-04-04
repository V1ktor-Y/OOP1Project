package commands;

import exceptions.CommandContextException;
import exceptions.CommandNotFoundException;
import util.errorLog.ErrorLogger;

import java.util.HashMap;
import java.util.Map;

public class CLI {
    private final Map<String,CommandType> commandMap = new HashMap<>();
    private static CLI instance;

    private CLI() {
        commandMap.put("exit", CommandType.EXIT);
        commandMap.put("open", CommandType.OPEN);
        commandMap.put("close", CommandType.CLOSE);
        //put all future commands here
    }

    public static CLI getInstance(){
        if (instance == null){
            instance = new CLI();
        }
        return instance;
    }

    public Map<String, CommandType> getCommandMap() {
        return commandMap;
    }

    public void performCommand(String input) throws CommandNotFoundException {
        String[] parsedInput = input.split(" ", 2);
        parsedInput[0] = parsedInput[0].toLowerCase();
        CommandType result = commandMap.get(parsedInput[0]);
        if(result == null) {
            throw new CommandNotFoundException("Command not found");
        }

        if(parsedInput.length < 2){
            try{
                result.getCommand().performCommand("");
            }catch (Exception e){
                ErrorLogger.log(e);
            }
        }

        try{
            result.getCommand().performCommand(parsedInput[1]);
        } catch (Exception e) {
            ErrorLogger.log(e);
        }

    }
}
