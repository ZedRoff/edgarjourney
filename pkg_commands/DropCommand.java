package pkg_commands;
import pkg_gameobjects.Player;
import pkg_items.Item;
/**
 * DropCommand Class - Permits to drop an item inside a Room
 * @author Aman GHAZANFAR 
 * @version 2023/05/06
 */
public class DropCommand extends Command {
     /**
     * Execute function, common to every commands, overrides the abstract method of Command Class.
     * @param pPlayer A Player object
     */
    @Override
    public void execute(Player pPlayer) {
        
        // check if there is a second word
         if (!super.hasSecondWord()) pPlayer.getGUI().println("This command requires a second argument (item)");
      else {
          // check if the user has the item he wants to drop
   
       
       if(super.getSecondWord().equals("coin")) {
           if(pPlayer.getCoins() > 0) {
                    pPlayer.removeCoins();
         pPlayer.getGUI().println("You dropped a coin, you now have : "+pPlayer.getCoins()); // success message
          pPlayer.getCurrentRoom().addItem(new Item("coin", "A coins, let's see its purpose", 1)); // add the item into the current room
           }else {
               pPlayer.getGUI().println("You can't drop a coin, since you don't have one");
           }
   
         return;
    }
    if (!pPlayer.hasItem(super.getSecondWord())) {
       pPlayer.getGUI().println("You don't have this item");
       return;
    };
    
    Item vItem = pPlayer.getInventory().getItem(super.getSecondWord()); // get the item he wants to drop
   
      
    
    
    pPlayer.getCurrentRoom().addItem(vItem); // add the item into the current room
    pPlayer.setCurrentWeight(pPlayer.getCurrentWeight() - vItem.getWeight()); // change his weight
    
   
    pPlayer.getInventory().removeItem(vItem.getName()); // remove the item from his inventory
    pPlayer.getGUI().println("You drop the " + super.getSecondWord() + ". Your new total weight is : " + pPlayer.getCurrentWeight() + "kg"); // show him the success message
      }
 
   
    } // execute(.)
} // DropCommand