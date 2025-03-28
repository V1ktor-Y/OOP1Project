package commands;

import Exceptions.CommandNotFoundException;

import java.util.Arrays;
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

    public void performCommand(String input) throws CommandNotFoundException {
        String[] parsedInput = input.split(" ", 2);
        parsedInput[0] = parsedInput[0].toLowerCase();
        CommandType result = commandMap.get(parsedInput[0]);
        if(result == null) {
            throw new CommandNotFoundException("Command not found");
        }
        if(parsedInput.length < 2){
            result.getCommand().performCommand("");
        }
        result.getCommand().performCommand(parsedInput[1]);

    }
}
