package pkg_utils; 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import pkg_commands.CommandWords;
import pkg_commands.Command;
/**
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kolling and David J. Barnes + D.Bureau + GHAZANFAR Aman

 * @version 2008.03.30/2027.03.2023
 */
public class Parser 
{
    // Attributes
    
    private CommandWords aValidCommands;  // look at CommandWords Class
      /**
     * Constructeur par defaut qui cree les 2 objets prevus pour les attributs
     */
    public Parser() 
    {
        this.aValidCommands = new CommandWords();
         } // Parser()
    /**
     * @return A String showing all the commands.
     */
    public String getCommandString() {
        return this.aValidCommands.getCommandList();
    } // getCommand()
    /**
     * @param pInputLine A String with the command (spaced with a space in between)
     * @return The next command from the user.
     */
   public Command getCommand(final String pInputLine) 
    {
       String vWord1;
        String vWord2;

        StringTokenizer tokenizer = new StringTokenizer( pInputLine );

        if ( tokenizer.hasMoreTokens() )
            vWord1 = tokenizer.nextToken();      // get first word
        else
            vWord1 = null;

        if ( tokenizer.hasMoreTokens() )
            vWord2 = tokenizer.nextToken();      // get second word
        else
            vWord2 = null;
        
            Command vCommand = (Command)aValidCommands.getFunc(vWord1);
           
            vCommand.setSecondWord(vWord2);
            vCommand.setCommandWord(vWord1);
            return vCommand;
       
}
} // Parser