package commands;

import java.util.HashMap;
import java.util.Map;

public class CLI {
    private final Map<String,Command> commandMap = new HashMap<>();
    private static CLI instance;
    private CLI() {
        commandMap.put("exit", new ExitCommand());
        //put all future commands here
    }

    public static CLI getInstance(){
        if (instance == null){
            instance = new CLI();
        }
        return instance;
    }
}
