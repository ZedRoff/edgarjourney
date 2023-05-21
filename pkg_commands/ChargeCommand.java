package pkg_commands;
import pkg_gameobjects.Player;
import pkg_items.Beamer;
import pkg_items.Item;
/**
 * ChargeCommand Class - Permits to charge the Beamer inside a Room
 * @author Aman GHAZANFAR
 * @version 2023/05/06
 */
public class ChargeCommand extends Command {
    /**
     * Execute function, common to every commands, overrides the abstract method of Command Class.
     * @param pPlayer A Player object
     */
    @Override
    public void execute(Player pPlayer) {
        // checks if there is a second word
       if(!super.hasSecondWord()) {
            pPlayer.getGUI().println("Charge what ?");
        }else {
       Item vItem = pPlayer.getInventory().getItem(super.getSecondWord()); // get the item from his inventory
      if(pPlayer.hasItem(super.getSecondWord())) { // if the player has the beamer
          Beamer vBeamer = (Beamer)vItem;
          if(vBeamer.getChargedRoom() != null) { // if the beamer is charged inside a room
              pPlayer.getGUI().println("You're Beamer is already charged.");
          } else { // if it isn't charged inside a room
              pPlayer.getGUI().println(vBeamer.charge(pPlayer.getCurrentRoom())); // shows the success message of the Beamer that is fully charged
          }
      } else { // if he doens't have the Beamer
          pPlayer.getGUI().println("You don't have the Beamer, find it first.");
      }
    }
} // execute(.)
} // ChargeCommand