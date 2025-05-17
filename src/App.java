import commands.CLI;
import exceptions.CommandNotFoundException;
import exceptions.InvalidCharacterException;
import grammar.Grammar;
import grammar.Rule;
import parsing.Parser;
import util.errorLog.ErrorLogger;
import util.writeToFile.WriteToFile;

public class App {
    public static void main(String[] args) throws InvalidCharacterException, CommandNotFoundException {
        /*Grammar g = new Grammar("./test.cfg");
        g.addSymbolToAlphabet('A');
        g.addSymbolToAlphabet('B');
        g.addSymbolToAlphabet('b');
        g.addSymbolToAlphabet('a');
        g.addRule(new Rule("A -> bBa"));
        g.addRule(new Rule("B -> abaa"));
        WriteToFile.write("./test.cfg", false,  Parser.grammarToString(g));
        */

        //CHECK IF INPUT IS EMPTY!
        try{
            CLI.getInstance().performCommand("open test.cfg");
            CLI.getInstance().performCommand("print 0");
            CLI.getInstance().performCommand("chomskify 0");
            CLI.getInstance().performCommand("print 1");

        } catch (Exception e) {
            ErrorLogger.log(e);
        }

    }
}
