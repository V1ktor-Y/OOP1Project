package commands;

import exceptions.ChomskyException;
import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammar.Alphabet;
import grammar.Grammar;
import grammar.GrammarMap;
import grammar.Rule;
import parsing.ContextParser;

import java.util.Objects;

public class ChomskifyCommand implements Command, ContextParser {
    /**
     * chomskify id - Converts given grammar into Chomsky Normal Form and saves it to the grammar map
     * @param context
     * @throws Exception
     */
    @Override
    public void performCommand(String context) throws Exception {
        parseContext(context);
        //context is grammar ID
        int id = Integer.parseInt(context);

        Grammar grammar = GrammarMap.getInstance().getGrammarByID(id);
        if (grammar == null) throw new GrammarNotFoundException("Could not find grammar with id " + id);
        Grammar newGrammar = new Grammar("");

        newGrammar.getNonTerminalSymbols().addAll(grammar.getNonTerminalSymbols().getSymbols());
        newGrammar.getTerminalSymbols().addAll(grammar.getTerminalSymbols().getSymbols());

        for (Rule r : grammar.getRules()) {

            //if left or right side doesnt have any symbols
            if (r.getRightSide().isBlank() || r.getLeftSide().isBlank()) {
                continue;
            }
            //if right side == epsilon
            if (r.getRightSide().charAt(0) == Alphabet.EPSILON) {
                continue;
            }

            StringBuilder right = new StringBuilder(r.getRightSide());
            StringBuilder left = new StringBuilder(r.getLeftSide());

            terminalCharPass(right, newGrammar);
            nonTerminalCharPass(right, newGrammar);

            newGrammar.addRule(new Rule(left.toString(),right.toString()));
        }
        GrammarMap.getInstance().addGrammar(newGrammar);
        System.out.println("Chomskyfied. New Grammar id: " + (GrammarMap.getInstance().getIdCounter() - 1));
    }

    private void nonTerminalCharPass(StringBuilder right, Grammar newGrammar) throws ChomskyException {
        while (right.length() > 2) {
            for (int i = 0; i < right.length() - 2; i++) {
                char currChar = right.charAt(i);
                char nextChar = right.charAt(i+1);
                char newNonTerminal = getNewNonTerminalChar(newGrammar.getNonTerminalSymbols());
                Rule newRule = new Rule(String.valueOf(newNonTerminal), String.valueOf(currChar) + nextChar);

                newGrammar.addRule(newRule);

                right.setCharAt(i,newNonTerminal);
                right.deleteCharAt(i+1);
            }
        }
    }

    private void terminalCharPass(StringBuilder right, Grammar newGrammar) throws ChomskyException {
        for (int i = 0; i < right.length(); i++) {
            char currChar = right.charAt(i);

            if (currChar >= 'a' && currChar <= 'z') {

                //Check if rule already exists
                char newNonTerminal = ' ';
                for(Rule rule : newGrammar.getRules()){
                    if(rule.getRightSide().equals(String.valueOf(currChar))){
                        newNonTerminal = rule.getLeftSide().charAt(0);
                    }
                }
                if(newNonTerminal == ' '){
                    newNonTerminal = getNewNonTerminalChar(newGrammar.getNonTerminalSymbols());
                    newGrammar.addRule(new Rule(String.valueOf(newNonTerminal), String.valueOf(currChar)));
                }

                right.setCharAt(i, newNonTerminal);
            }
        }
    }

    private char getNewNonTerminalChar(Alphabet terminals) throws ChomskyException {
        for (char c = 'A'; c <= 'Z'; c++) {
            if (!terminals.contains(String.valueOf(c))) {
                terminals.addSymbol(String.valueOf(c));
                return c;
            }
        }
        throw new ChomskyException("Could not add a new non-terminal symbol");
    }

    @Override
    public String getDesc() {
        return "chomskify <id> - Converts given grammar into Chomsky Normal Form and saves it to the grammar map";
    }

    @Override
    public String[] parseContext(String context) throws CommandContextException {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");
        return null;
    }
}
