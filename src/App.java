import exceptions.CommandNotFoundException;
import commands.CLI;
import util.errorLog.ErrorLogger;

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
