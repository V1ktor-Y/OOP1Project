package commands;

import commands.common.*;

public enum CommandType {
    EXIT(new ExitCommand()),
    OPEN(new OpenCommand()),
    CLOSE(new CloseCommand()),
    HELP(new HelpCommand()),
    SAVE(new SaveCommand()),
    SAVEAS(new SaveAsCommand()),
    ADDRULE(new AddRuleCommand()),
    CHOMSKIFY(new ChomskifyCommand()),
    CHOMSKY(new ChomskyCommand()),
    CONCAT(new ConcatCommand()),
    CYK(new CYKCommand()),
    EMPTY(new EmptyCommand()),
    ITER(new IterCommand()),
    LIST(new ListCommand()),
    PRINT(new PrintCommand()),
    REMOVERULE(new RemoveRuleCommand()),
    UNION(new UnionCommand());
    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
