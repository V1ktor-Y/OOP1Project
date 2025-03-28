package commands;

public enum CommandType {
    EXIT(new ExitCommand()),
    OPEN(new OpenCommand()),
    CLOSE(new CloseCommand()),
    ;
    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
