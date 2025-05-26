package parsing;

import exceptions.CommandContextException;

public interface ContextParser {
    String[] parseContext(String context) throws CommandContextException;
}
