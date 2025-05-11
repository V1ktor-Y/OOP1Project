package commands;

import grammar.Grammar;
import grammar.GrammarMap;

import java.util.Map;

public class ListCommand implements Command {
    @Override
    public void performCommand(String context) throws Exception {
        Map<Integer, Grammar> grammarMap = GrammarMap.getInstance().getGrammarMap();
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<Integer,Grammar> entry : grammarMap.entrySet()){
            sb.append(entry.getKey());
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    @Override
    public String getDesc() {
        return "list - List every loaded grammar's id";
    }
}
