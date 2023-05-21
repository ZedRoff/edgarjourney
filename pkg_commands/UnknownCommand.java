package pkg_commands;
import pkg_gameobjects.Player;
/**
 * UnknownCommand Class - In case an unknown command has been entered by the user
 * @author Aman GHAZANFAR
 * @version 2023/05/06
 */
public class UnknownCommand extends Command {
 
     /**
     * Execute function, common to every commands, overrides the abstract method of Command Class.
     * @param pPlayer A Player object
     */
    @Override
    public void execute(Player pPlayer) {
        // if the command triggers the unknown flag
    
      pPlayer.getGUI().println("I don't know what you mean..."); // tell him it doesn't exists
    
    
    } // execute(.)
} // UnknownCommand