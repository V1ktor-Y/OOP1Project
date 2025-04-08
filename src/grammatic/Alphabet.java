package grammatic;

import java.util.HashSet;
import java.util.Set;

public class Alphabet {
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
}
