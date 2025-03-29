package parsing;

import exceptions.SerializationException;
import grammatic.Grammar;
import grammatic.Rule;
import util.readFromFile.ReadFromFile;

public class Parser {
    public static Grammar readGrammarFromFile(String path) throws SerializationException {
        String text = ReadFromFile.getAll(path);
        Grammar grammar = new Grammar(path);
        //Parse here

        return grammar;
    }

    public static String grammarToString(Grammar grammar, String path){
        StringBuilder sb = new StringBuilder();

        for(Character c : grammar.getNonterminalSymbols().getSymbols()){
            sb.append(c + " ");
        }

        for(Character c : grammar.getTerminalSymbols().getSymbols()){
            sb.append(c + " ");
        }
        sb.append(",\n");

        for(Rule rule : grammar.getRules()){
            sb.append(rule.getLeftSide())
                    .append(" -> ")
                    .append(rule.getRightSide())
                    .append(",\n");
        }
        return sb.toString();
    }
}
