import Exceptions.CommandNotFoundException;
import commands.CLI;
import util.ErrorLog.ErrorLogger;

import java.util.Calendar;

public class App {
    public static void main(String[] args) {
        try {

        CLI.getInstance().performCommand("ExIt");
        } catch (CommandNotFoundException e) {
            ErrorLogger.log(e);
        }

        //CHECK IF INPUT IS EMPTY!
    }
}
