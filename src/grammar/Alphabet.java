package grammar;

import java.util.HashSet;
import java.util.Set;

public class Alphabet {
    public static final char EPSILON = 'Є';
    private Set<Character> symbols = new HashSet<>();

    public boolean addSymbol(Character c){
        return symbols.add(c);
    }

    public Set<Character> getSymbols() {
        return symbols;
    }

    public int size(){
        return symbols.size();
    }

    public void addAll(Set<Character> characters){
        symbols.addAll(characters);
    }

    public boolean contains(Character c){
        return symbols.contains(c);
    }
}
