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

    public void addRule(Rule rule){
        RULES.add(rule);
    }

    public boolean addSymbolToAlphabet(Character c) throws InvalidCharacterException {
        if(Character.isLowerCase(c) || Character.isDigit(c)) return TERMINAL_SYMBOLS.addSymbol(c);
        else if(Character.isUpperCase(c)) return NONTERMINAL_SYMBOLS.addSymbol(c);
        else throw new InvalidCharacterException("Character is not a supported symbol");
    }

    public Alphabet getTerminalSymbols() {
        return TERMINAL_SYMBOLS;
    }

    public Alphabet getNonterminalSymbols() {
        return NONTERMINAL_SYMBOLS;
    }

    public Set<Rule> getRules(){
        return RULES;
    }

    /**
     * Removes the Nth rule in a grammar, indexes begin at 0
     * @param n Which rule should be removed
     * @return Whether the rule was successfully removed
     */
    public boolean removeRule(int n) throws RuleNotFoundException {
        Rule elem = null;
        int counter = 0;
        for(Rule rule : RULES){
            if(counter == n) {
                elem = rule;
                break;
            }
            counter++;
        }
        if(elem == null) throw new RuleNotFoundException("Rule index not found");
        return RULES.remove(elem);
    }

    public String getOriginalFile(){
        return ORIGINAL_FILE;
    }
}
