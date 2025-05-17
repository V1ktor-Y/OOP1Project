package commands.common;

import commands.CLI;
import commands.Command;
import commands.CommandType;

import java.util.Map;

public class HelpCommand implements Command {
    @Override
    public void performCommand(String context) throws Exception {
        Map<CommandType, Command>  commandMap = CLI.getInstance().getCommandMap();
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<CommandType, Command> entry: commandMap.entrySet()){

            sb.append(entry.getValue().getDesc());
            sb.append("\n\n");

        }
        System.out.println(sb.toString());
    }

    @Override
    public String getDesc() {
        return "help - Prints the description of every command";
    }
}
