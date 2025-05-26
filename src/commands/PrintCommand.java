package commands;

import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammar.Grammar;
import grammar.GrammarMap;
import grammar.Rule;
import parsing.ContextParser;

public class PrintCommand implements Command, ContextParser {
    /**
     * print id - Prints given command to console
     * @param context
     * @throws Exception
     */
    @Override
    public void performCommand(String context) throws Exception {

        int id = Integer.parseInt(context);
        Grammar grammar;

        if ((grammar = GrammarMap.getInstance().getGrammarByID(id)) == null)
            throw new GrammarNotFoundException("Couldn't find grammar with given id");

        StringBuilder sb = getGrammarString(id, grammar);
        System.out.println(sb.toString());
    }

    private StringBuilder getGrammarString(int id, Grammar grammar) {
        StringBuilder sb = new StringBuilder("Grammar with id ").append(id).append("\n\n");

        sb.append("\tTerminal Symbols: {");
        for (String c : grammar.getTerminalSymbols().getSymbols()){
            sb.append(c).append(", ");
        }
        sb.append("}\n\tNon-terminal Symbols: {");

        for (String c : grammar.getNonTerminalSymbols().getSymbols()){
            sb.append(c).append(", ");
        }
        sb.append("}\n\n");

        sb.append("\tRules:\n");
        int counter = 0;
        for(Rule rule : grammar.getRules()){
            sb.append("\t\t").append("(").append(counter++).append(") ").append(rule.getLeftSide()).append(" -> ").append(rule.getRightSide()).append("\n");
        }
        sb.append("\n");

        sb.append("\tOriginal file: ").append(grammar.getOriginalFile().isBlank() ? "N/A" : grammar.getOriginalFile());
        return sb;
    }

    @Override
    public String getDesc() {
        return "print <id> - Prints given command to console";
    }

    @Override
    public String[] parseContext(String context) throws CommandContextException {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");
        return null;
    }
}
