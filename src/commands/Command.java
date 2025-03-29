package commands;

import exceptions.CommandContextException;

public interface Command {
    void performCommand(String context) throws Exception;
    String getDesc();
}
