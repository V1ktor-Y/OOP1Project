package grammatic;

import java.util.HashMap;
import java.util.Map;

public class GrammarMap {

    private Map<Integer,Grammar> grammarMap = new HashMap<>();

    private static GrammarMap instance;

    private GrammarMap(){}

    public void addGrammar(Grammar grammar){
        grammarMap.put(grammarMap.size(), grammar);
    }

    public static GrammarMap getInstance() {
        if(instance == null){
            instance = new GrammarMap();
        }
        return instance;
    }

    public String getAllIDs(){
        StringBuilder sb = new StringBuilder("");
        for (Map.Entry<Integer,Grammar> entry : grammarMap.entrySet()){
            sb.append(entry.getKey());
            sb.append(", ");
        }
        return sb.toString();
    }
}
