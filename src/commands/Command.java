package commands;

public interface Command {
    void performCommand(String context);
    String getDesc();
}
