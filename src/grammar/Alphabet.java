package grammar;

import java.util.HashSet;
import java.util.Set;

public class Alphabet {
    public static final char EPSILON = 'Є';
    private Set<String> symbols = new HashSet<>();

    public boolean addSymbol(String s){
        return symbols.add(s);
    }

    public Set<String> getSymbols() {
        return symbols;
    }

    public int size(){
        return symbols.size();
    }

    public void addAll(Set<String> strings){
        symbols.addAll(strings);
    }

    public boolean contains(String s){
        return symbols.contains(s);
    }
}
