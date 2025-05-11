import commands.CLI;
import exceptions.CommandNotFoundException;
import exceptions.InvalidCharacterException;
import grammar.Grammar;
import grammar.Rule;
import parsing.Parser;
import util.writeToFile.WriteToFile;

public class App {
    public static void main(String[] args) throws InvalidCharacterException, CommandNotFoundException {
        /*Grammar g = new Grammar("./test.cfg");
        g.addSymbolToAlphabet('A');
        g.addSymbolToAlphabet('B');
        g.addSymbolToAlphabet('b');
        g.addSymbolToAlphabet('a');
        g.addRule(new Rule("A -> bB"));
        g.addRule(new Rule("B -> ab"));
        WriteToFile.write("./test.cfg", false,  Parser.grammarToString(g));
        */

        //CHECK IF INPUT IS EMPTY!

        CLI.getInstance().performCommand("open test.cfg");
        CLI.getInstance().performCommand("print 0");
    }
}
