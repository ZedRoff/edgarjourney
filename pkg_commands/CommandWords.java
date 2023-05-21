package pkg_commands;
import java.util.HashMap;
 import java.util.Set;
/**

  * This class holds an enumeration table of all command words known to the game.
  * It is used to recognise commands as they are typed in.
  *
  * @author Aman GHAZANFAR
  * @version 2023.02.09
  */
 public class CommandWords {
   // Attributes
   
   private HashMap<String, CommandWord> aValidCommands; // a constant array that will hold all valid command words
  /**
   * Constructor, instantiates the aValidCommands attribute by searching inside the CommandWord Class' enums
   */
   public CommandWords() {
       aValidCommands = new HashMap<String, CommandWord>();
      
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                aValidCommands.put(command.toString(), command);
            }
        }
   } // CommandWords()
    
    /**
    * @param pCommandWord The first word of the command typed in
    * @return The command corresponding to the pCommandWord given
    */
    public CommandWord getCommandWord(String pCommandWord)
    {
        CommandWord command = aValidCommands.get(pCommandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    } // CommandWord(.)
    
    
   /**
    * @return The list of all commands inside a single String
    */
   public String getCommandList() {
     StringBuilder vCommands = new StringBuilder();
     Set<String> vKeys = aValidCommands.keySet();
     for (String command: vKeys) {
       vCommands.append(command + " ");

     }
     return vCommands.toString();
   } // getCommandList()

   /**
    * Check whether a given String is a valid command word. 
    * @param pString The command slitted with a space
    * @return true if a given string is a valid command, false if it isn't.
    */
   public boolean isCommand(final String pString) {
     return aValidCommands.containsKey(pString);
   } // isCommand(.)
   /**
    * @param pCommandWord The first word of any command
    * @return returns the actual function corresponding to the commandWord given
    */
   public Command getFunc(final String pCommandWord) {
       return this.getCommandWord(pCommandWord).toClass();
   } // getFunc(.)
 } // CommandWords