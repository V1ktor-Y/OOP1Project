package grammatic;

import java.util.HashSet;
import java.util.Set;

public class Alphabet {
    Set<Character> symbols = new HashSet<>();

    public boolean addSymbol(Character c){
        return symbols.add(c);
    }
}
