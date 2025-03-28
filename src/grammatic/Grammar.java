package grammatic;

import exceptions.InvalidCharacterException;

import java.util.ArrayList;
import java.util.List;

public class Grammar {
    private final List<Rule> RULES;
    private final Alphabet TERMINAL_SYMBOLS;
    private final Alphabet NONTERMINAL_SYMBOLS;
    private final String ORIGINAL_FILE;

    public Grammar(String originalFilePath) {
        ORIGINAL_FILE = originalFilePath;
        RULES = new ArrayList<>();
        TERMINAL_SYMBOLS = new Alphabet();
        NONTERMINAL_SYMBOLS = new Alphabet();
    }

    public void addRule(String rule){
        RULES.add(new Rule(rule));
    }

    public boolean addSymbolToAlphabet(Character c) throws InvalidCharacterException {
        if(Character.isLowerCase(c)) return TERMINAL_SYMBOLS.addSymbol(c);
        else if(Character.isUpperCase(c)) return NONTERMINAL_SYMBOLS.addSymbol(c);
        else throw new InvalidCharacterException("Character is not a supported symbol");
    }
}
