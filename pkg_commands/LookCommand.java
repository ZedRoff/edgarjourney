 package pkg_commands;
import pkg_gameobjects.Player;
import pkg_items.Item;
/**
 * LookCommand Class - Permits to either look inside the room, or look into a specific item (whether you give it an argument or not)
 * @author Aman GHAZANFAR
 * @version 2023/05/06
 */
public class LookCommand extends Command {
     /**
     * Execute function, common to every commands, overrides the abstract method of Command Class.
     * @param pPlayer A Player object
     */
    @Override
    public void execute(Player pPlayer) {
     // check if there is a second word
       if (super.hasSecondWord()) {
      Item vItem = pPlayer.getCurrentRoom().getItem(super.getSecondWord());
      
      // check if the item he wants to look into is inside the room or not
      if (vItem == null) {
        pPlayer.getGUI().println("This item doesn't exists");
        return;
      }
      
      // show item's caracteristics
     pPlayer.getGUI().println("Item's name : " + vItem.getName());
      pPlayer.getGUI().println("Item's description : " + vItem.getDescription());
      pPlayer.getGUI().println("Item's weight : " + vItem.getWeight());
    } else {
      // show room's caracteristics
     pPlayer.getGUI().println(pPlayer.getCurrentRoom().getLongDescription());
    }
    } // execute(.)
} // LookCommand