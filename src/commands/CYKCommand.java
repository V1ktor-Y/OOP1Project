package commands;

import exceptions.CYKException;
import exceptions.CommandContextException;
import exceptions.GrammarNotFoundException;
import grammar.Grammar;
import grammar.GrammarMap;
import grammar.Rule;
import parsing.ContextParser;

import java.util.*;

public class CYKCommand implements Command, ContextParser {
    /**
     * CYK id word - Checks if given word is in given grammar using CYK algorithm
     * @param context
     * @throws Exception
     */
    @Override
    public void performCommand(String context) throws Exception {

        String[] keyWords = parseContext(context);
        int id = Integer.parseInt(keyWords[0]);
        String w = keyWords[1];

        Grammar grammar = GrammarMap.getInstance().getGrammarByID(id);
        if (grammar == null) throw new GrammarNotFoundException("Could not find grammar with id " + id);

        //Check if Chomsky
        chomskyCheck(grammar);

        int n = w.length();
        // Initialize the table
        Map<Integer, Map<Integer, Set<Character>>> T = new HashMap<>();

        for (int j = 0; j < n; j++) {

            // Iterate over the rules
            ruleIter(grammar, w, j, T);
            for (int i = j; i >= 0; i--) {

                // Iterate over the range from i to j
                for (int k = i; k <= j; k++) {

                    // Iterate over the rules
                    for (Rule x : grammar.getRules()) {
                        String lhs = x.getLeftSide();
                        String rhs = x.getRightSide();

                        // If a terminal is found
                        foundTerminal(rhs, T, i, k, j, lhs);
                    }
                }
            }
        }

        // If word can be formed by rules of given grammar
        if (T.get(0) != null && T.get(0).get(n - 1) != null
                && T.get(0).get(n - 1).size() != 0)
            System.out.println("True");
        else
            System.out.println("False");
    }

    private void foundTerminal(String rhs, Map<Integer, Map<Integer, Set<Character>>> T, int i, int k, int j, String lhs) {
        if (rhs.length() == 2
                && T.get(i) != null
                && T.get(i).get(k) != null
                && T.get(i).get(k).contains(
                rhs.charAt(0))
                && T.get(k + 1) != null
                && T.get(k + 1).get(j)
                != null
                && T.get(k + 1)
                .get(j)
                .contains(
                        rhs.charAt(1))) {
            if (T.get(i) == null)
                T.put(i,
                        new HashMap<>());
            if (T.get(i).get(j) == null)
                T.get(i).put(
                        j, new HashSet<>());
            T.get(i).get(j).add(lhs.charAt(0));
        }
    }

    private void ruleIter(Grammar grammar, String w, int j, Map<Integer, Map<Integer, Set<Character>>> T) {
        for (Rule x : grammar.getRules()) {
            String lhs = x.getLeftSide();
            String rhs = x.getRightSide();

            // If a terminal is found
            if (rhs.length() == 1
                    && rhs.charAt(0) == w.charAt(j)) {
                if (T.get(j) == null)
                    T.put(j, new HashMap<>());
                T.get(j)
                        .computeIfAbsent(
                                j, k -> new HashSet<>())
                        .add(lhs.charAt(0));
            }

        }
    }

    private void chomskyCheck(Grammar grammar) throws CYKException {
        for (Rule r : grammar.getRules()) {
            String rightSide = r.getRightSide();
            if (rightSide.length() == 1 && grammar.getTerminalSymbols().contains(rightSide)) {
                continue;
            }
            if (rightSide.length() == 2 && grammar.getNonTerminalSymbols().contains(String.valueOf(rightSide.charAt(0))) && grammar.getNonTerminalSymbols().getSymbols().contains(String.valueOf(rightSide.charAt(1)))) {
                continue;
            }
            throw new CYKException("Given grammar is not in CNF");
        }
    }


    @Override
    public String getDesc() {
        return "CYK <id> <word> - Checks if given word is in given grammar using CYK algorithm";
    }

    @Override
    public String[] parseContext(String context) throws CommandContextException {
        if (context.isEmpty()) throw new CommandContextException("Empty command context");

        String[] keyWords = context.split(" ", 2);
        if (keyWords.length < 2) throw new CommandContextException("Not enough context given");
        return keyWords;
    }
}


