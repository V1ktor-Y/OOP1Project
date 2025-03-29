import exceptions.InvalidCharacterException;
import grammatic.Grammar;
import grammatic.Rule;
import parsing.Parser;
import util.writeToFile.WriteToFile;

public class App {
    public static void main(String[] args) throws InvalidCharacterException {
        Grammar g = new Grammar(".\\test.cfg");
        g.addSymbolToAlphabet('A');
        g.addSymbolToAlphabet('B');
        g.addSymbolToAlphabet('b');
        g.addSymbolToAlphabet('a');
        g.addRule(new Rule("A -> bB"));
        g.addRule(new Rule("B -> ab"));
        WriteToFile.write(".\\test.cfg", false,  Parser.grammarToString(g));
        //CHECK IF INPUT IS EMPTY!
    }
}
