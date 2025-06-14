package commands;

import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import exceptions.RuleNotFoundException;
import grammar.Grammar;
import grammar.GrammarMap;
import parsing.ContextParser;
import util.errorLog.ErrorLogger;

public class RemoveRuleCommand implements Command, ContextParser {
    /**
     * removerule id rule id - Removes given rule from given grammar
     * @param context
     * @throws Exception
     */
    @Override
    public void performCommand(String context) throws Exception {

        String[] keyWords = parseContext(context);
        int id = Integer.parseInt(keyWords[0]);
        int ruleId = Integer.parseInt(keyWords[1]);

        Grammar grammar = GrammarMap.getInstance().getGrammarByID(id);

        if(grammar == null) throw new GrammarNotFoundException("Failed to find grammar with id: " + id);
        try{
            grammar.removeRule(ruleId);
        }catch (RuleNotFoundException e){
            ErrorLogger.log(e);
        }
        System.out.println("Removed rule " + ruleId);
    }

    @Override
    public String getDesc() {
        return "removerule <id> <rule id> - Removes given rule from given grammar";
    }

    @Override
    public String[] parseContext(String context) throws CommandContextException {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");

        String[] keyWords = context.split(" ", 2);
        if(keyWords.length < 2) throw new CommandContextException("Not enough context given");
        return keyWords;
    }
}