package commands;

public class ExitCommand implements Command{
    @Override
    public void performCommand(String context) {
        System.exit(0);
    }
}
