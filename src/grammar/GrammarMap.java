package grammar;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton that holds every currently loaded Grammar
 */
public class GrammarMap {

    private Map<Integer,Grammar> grammarMap = new HashMap<>();
    private int idCounter;
    private static GrammarMap instance;

    public int getIdCounter() {
        return idCounter;
    }

    private GrammarMap(){}

    /**
     * Adds grammar to the map
     * @param grammar
     * @return id of the added grammar
     */
    public int addGrammar(Grammar grammar){
        grammarMap.put(idCounter++, grammar);
        return idCounter - 1;
    }

    public boolean removeGrammarByID(int id){
        //remove returns the corresponding value if id is found
        return grammarMap.remove(id) != null;
    }

    public static GrammarMap getInstance() {
        if(instance == null){
            instance = new GrammarMap();
        }
        return instance;
    }

    public String getAllIDs(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer,Grammar> entry : grammarMap.entrySet()){
            sb.append(entry.getKey());
            sb.append(", ");
        }
        return sb.toString();
    }

    /**
     * Get the grammar with given id
     * @param id
     * @return grammar
     */
    public Grammar getGrammarByID(int id){
        return grammarMap.get(id);
    }

    public Map<Integer, Grammar> getGrammarMap() {
        return grammarMap;
    }
}
