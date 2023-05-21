package pkg_commands;
import pkg_gameobjects.Player;

/**
 * InventoryCommand Class - Shows user's inventory
 * @author Aman GHAZANFAR
 * @version 2023/05/06
 */
public class InventoryCommand extends Command {
  
     /**
     * Execute function, common to every commands, overrides the abstract method of Command Class.
     * @param pPlayer A Player object
     */
    @Override
    public void execute(Player pPlayer) {
        
          pPlayer.getGUI().println(pPlayer.getInventoryString()); // pretty explicit
          pPlayer.getGUI().println("You can carry on more : "+(pPlayer.getMaxWeight()-pPlayer.getCurrentWeight())+"kg worth of items");
          pPlayer.getGUI().println("You also have : "+pPlayer.getCoins()+" coins");
          
    } // execute(.)
} // InventoryCommand