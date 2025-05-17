package commands;

import exceptions.ChomskyException;
import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammar.Alphabet;
import grammar.Grammar;
import grammar.GrammarMap;
import grammar.Rule;

import java.util.Objects;

public class ChomskifyCommand implements Command {
    @Override
    public void performCommand(String context) throws Exception {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");

        //context is grammar ID
        int id = Integer.parseInt(context);

        Grammar grammar = GrammarMap.getInstance().getGrammarByID(id);
        if (grammar == null) throw new GrammarNotFoundException("Could not find grammar with id " + id);
        Grammar newGrammar = new Grammar("");

        newGrammar.getNonterminalSymbols().addAll(grammar.getNonterminalSymbols().getSymbols());
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

            //terminal char pass
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

            //non-terminal char pass
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
            newGrammar.addRule(new Rule(left.toString(),right.toString()));
        }
        GrammarMap.getInstance().addGrammar(newGrammar);
        System.out.println("Chomskyfied. New Grammar id: " + (GrammarMap.getInstance().getIdCounter() - 1));
    }

    private char getNewNonTerminalChar(Alphabet terminals) throws ChomskyException {
        for (char c = 'A'; c <= 'Z'; c++) {
            if (!terminals.contains(c)) {
                terminals.addSymbol(c);
                return c;
            }
        }
        throw new ChomskyException("Could not add a new non-terminal symbol");
    }

    @Override
    public String getDesc() {
        return "chomskify <id> - Converts given grammar into Chomsky Normal Form and saves it to the grammar map";
    }
}
