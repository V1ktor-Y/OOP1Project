package commands;

public class ExitCommand implements Command{
    @Override
    public void performCommand(String context) {
        System.out.println("Exiting");
        System.exit(0);
    }
}
