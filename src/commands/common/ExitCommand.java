package commands.common;

import commands.Command;

public class ExitCommand implements Command {
    @Override
    public void performCommand(String context) {
        System.out.println("Exiting");
        System.exit(0);
    }

    @Override
    public String getDesc() {
        return "exit - Exits the program";
    }
}
