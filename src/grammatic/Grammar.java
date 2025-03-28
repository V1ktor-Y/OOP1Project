package grammatic;

import Exceptions.InvalidCharacterError;

import java.util.ArrayList;
import java.util.List;

public class Grammar {
    private final List<Rule> rules;
    private final Alphabet terminalSymbols;
    private final Alphabet nonterminalSymbols;

    public Grammar() {
        rules = new ArrayList<>();
        terminalSymbols = new Alphabet();
        nonterminalSymbols = new Alphabet();
    }

    public void addRule(String rule){
        rules.add(new Rule(rule));
    }

    public boolean addSymbolToAlphabet(Character c) throws InvalidCharacterError {
        if(Character.isLowerCase(c)) return terminalSymbols.addSymbol(c);
        else if(Character.isUpperCase(c)) return nonterminalSymbols.addSymbol(c);
        else throw new InvalidCharacterError("Character is not a supported symbol");
    }
}
