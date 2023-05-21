package pkg_commands;
import pkg_gameobjects.Player;
import pkg_items.Beamer;
import pkg_items.Item;
/**
 * FireCommand Class - Permits to fire the Beamer so it teleports you to a Room
 * @author Aman GHAZANFAR
 * @version 2023/05/06
 */
public class FireCommand extends Command {
 /**
     * Execute function, common to every commands, overrides the abstract method of Command Class.
     * @param pPlayer A Player object
     */
    @Override
    public void execute(Player pPlayer) {
        
        // check if there is a second word
         if(!super.hasSecondWord()) {
            pPlayer.getGUI().println("Fire what ?");
        }else {
    
        Item vItem = pPlayer.getInventory().getItem(super.getSecondWord()); // get the beamer from player's inventory
      if(pPlayer.hasItem(super.getSecondWord())) { // check if he has it, so vItem can't be null
          Beamer vBeamer = (Beamer)vItem; // casts the Item object into a Beamer object so we can use it
          if(!vBeamer.canFire()) { // if we can't fire the beamer
             pPlayer.getGUI().println("The Beamer can't fire, please charge it first."); // error message
          } else { // if we can fire it
              pPlayer.setCurrentRoom(vBeamer.getChargedRoom()); // teleport the player to the room where the Beamer was initially charged in (include printLocationInfo())
            pPlayer.getGUI().println(vBeamer.fire()); // shows the teleportation success message
          }
      } else {
           pPlayer.getGUI().println("You don't have the Beamer, find it first."); // error message
      }  
if( pPlayer.getCurrentRoom().getImageName() != null )
            pPlayer.getGUI().showImage( pPlayer.getCurrentRoom().getImageName() );      // shows room's image 
        }
    } // execute(.)
} // FireCommand