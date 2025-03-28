package parsing;

import exceptions.SerializationException;
import grammatic.Grammar;
import util.readFromFile.ReadFromFile;

public class Parser {
    public static Grammar readGrammarFromFile(String path) throws SerializationException {
        String text = ReadFromFile.getAll(path);
        Grammar grammar = new Grammar(path);
        //Parse here

        return grammar;
    }

    public static String serializeGrammar(Grammar grammar, String path){
        StringBuilder sb = new StringBuilder();
        //Do the magic here

        return sb.toString();
    }
}
