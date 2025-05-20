package commands.common;

import commands.Command;

public class ExitCommand implements Command {
    /**
     * exit - Exits the program
     * @param context
     */
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
