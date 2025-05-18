package grammar;

import exceptions.InvalidCharacterException;
import exceptions.RuleNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class Grammar {
    private final Set<Rule> RULES;
    private final Alphabet TERMINAL_SYMBOLS;
    private final Alphabet NONTERMINAL_SYMBOLS;
    private final String ORIGINAL_FILE;

    public Grammar(String originalFilePath) {
        ORIGINAL_FILE = originalFilePath;
        RULES = new HashSet<>();
        TERMINAL_SYMBOLS = new Alphabet();
        NONTERMINAL_SYMBOLS = new Alphabet();
    }

    public void addRule(Rule rule) {
        RULES.add(rule);
    }

    public boolean addSymbolToAlphabet(String s) throws InvalidCharacterException {
        boolean isNotMixed = true;

        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) {
                throw new InvalidCharacterException("Unsupported character " + c);
            }
        }

        //check if all are terminals
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                isNotMixed = false;
                break;
            }
        }
        // check if all are nonterminals
        if (isNotMixed) {
            return TERMINAL_SYMBOLS.addSymbol(s);
        } else {
            isNotMixed = true;
            for (char c : s.toCharArray())
                if (Character.isLowerCase(c) || Character.isDigit(c)) {
                    isNotMixed = false;
                    break;
                }
        }

        if (isNotMixed) {
            return NONTERMINAL_SYMBOLS.addSymbol(s);
        } else
            throw new InvalidCharacterException("All characters in string need to be either terminal(lower case and digits) or non-terminal(upper case)");
    }

    public Alphabet getNonTerminalSymbols() {
        return NONTERMINAL_SYMBOLS;
    }

    public Alphabet getTerminalSymbols() {
        return TERMINAL_SYMBOLS;
    }

    public Set<Rule> getRules() {
        return RULES;
    }

    /**
     * Removes the Nth rule in a grammar, indexes begin at 0
     *
     * @param n Which rule should be removed
     * @return Whether the rule was successfully removed
     */
    public boolean removeRule(int n) throws RuleNotFoundException {
        Rule elem = null;
        int counter = 0;
        for (Rule rule : RULES) {
            if (counter == n) {
                elem = rule;
                break;
            }
            counter++;
        }
        if (elem == null) throw new RuleNotFoundException("Rule index not found");
        return RULES.remove(elem);
    }

    public String getOriginalFile() {
        return ORIGINAL_FILE;
    }
}
