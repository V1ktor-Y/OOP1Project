package util;

import commands.CLI;
import commands.CommandType;
import exceptions.CommandNotFoundException;
import util.errorLog.ErrorLogger;

import java.util.Scanner;

public class ProgramManager {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Main program loop.
     * Gets user input and checks if the first word matches any command.
     */
    public static void run() {
        System.out.print(">>>");
        String[] input = SCANNER.nextLine().split(" ", 2);
        CommandType commandType = null;
        try {
            commandType = findCommandType(input[0]);
        } catch (CommandNotFoundException e) {
            ErrorLogger.log(e);
            return;
        }

        try {
            if (input.length == 1) {
                CLI.getInstance().performCommand(commandType, "");
            } else {
                CLI.getInstance().performCommand(commandType, input[1]);
            }
        } catch (Exception e) {
            ErrorLogger.log(e);
        }
    }

    private static CommandType findCommandType(String input) throws CommandNotFoundException {
        for (CommandType type : CommandType.values()) {
            if (input.toUpperCase().equals(type.name())) {
                return type;
            }
        }
        throw new CommandNotFoundException("Could not find command " + input);
    }
}