package parsing;

import exceptions.InvalidCharacterException;
import exceptions.SerializationException;
import grammar.Grammar;
import grammar.Rule;
import util.readFromFile.ReadFromFile;

public class Parser {
    /**
     * Input needs to contain at least an alphabet
     * @param path
     * @return
     * @throws SerializationException
     * @throws InvalidCharacterException
     */
    public static Grammar readGrammarFromFile(String path) throws SerializationException, InvalidCharacterException {
        String text = ReadFromFile.getAll(path);
        Grammar grammar = new Grammar(path);

        //0 removes trailing empty strings
        String[] lines = text.split(",\n",0);

        if(lines.length < 1) throw new SerializationException("Could not find grammar");

        //Add every character from first line to grammar alphabet
        for(String character : lines[0].split(" ",0)){
            // if lines[0] is instead a rule, addSymbolToAlphabet will catch the -> and throw an error
            grammar.addSymbolToAlphabet(character);
        }

        //It's a lawless land out here
        if(lines.length == 1) return grammar;

        for(int i = 1; i < lines.length; i++){

            grammar.addRule(new Rule(lines[i].trim()));
        }

        return grammar;
    }

    public static String grammarToString(Grammar grammar){
        StringBuilder sb = new StringBuilder();

        for(String c : grammar.getNonTerminalSymbols().getSymbols()){
            sb.append(c + " ");
        }

        for(String c : grammar.getTerminalSymbols().getSymbols()){
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
