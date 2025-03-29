package grammatic;

import exceptions.InvalidCharacterException;

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
}
