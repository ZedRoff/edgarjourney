package pkg_commands;
import pkg_gameobjects.Player;
import pkg_items.Item;
/**
 * TakeCommand Class - Permits player to take an item from a Room and to store it inside his inventory
 * @author Aman GHAZANFAR
 * @version 2023/05/06
 */
public class TakeCommand extends Command {
     /**
     * Execute function, common to every commands, overrides the abstract method of Command Class.
     * @param pPlayer A Player object
     */  
    @Override
    public void execute(Player pPlayer) {
        
        // check if there is a second word
         if (!super.hasSecondWord()) pPlayer.getGUI().println("This command requires a second argument (item)");
      else {
          
        Item vItem = pPlayer.getCurrentRoom().getItem(super.getSecondWord()); // get the item from the Room
        // if the item isn't inside the Room
    if (vItem == null) {
        pPlayer.getGUI().println("This item isn't in this room");
        return;
    }
       
    
    // if he can't carry out more weight
    if (pPlayer.getCurrentWeight() + vItem.getWeight() > pPlayer.getMaxWeight()) {
        pPlayer.getGUI().println("You can't carry this item, it would exceed the max weight limit, please drop items or upgrade");
         return;
    }
      if(vItem.getName().equals("toolbox")) {
        pPlayer.addCoins();
        pPlayer.getGUI().println("You looked inside the toolbox, there was a coin, you now have : "+pPlayer.getCoins()); // success message
   pPlayer.getCurrentRoom().removeItem(vItem); // removes the item from the room
    return;
    }
    if(vItem.getName().equals("toothpaste")) {
        if(!pPlayer.hasItem("brush")){
           pPlayer.getGUI().println("You need to take a brush first");
           return;
        }
    }
    if(vItem.getName().equals("cream")) {
        if(!pPlayer.hasItem("brush")) {
            pPlayer.getGUI().println("You need a brush to take cream");
            return;
        }else if(!pPlayer.hasItem("toothpaste")) {
            pPlayer.getGUI().println("You need a toothpaste to take cream");
            return;
        } 
    }
    if(vItem.getName().equals("key")) {
        if(!pPlayer.hasItem("cream")) {
            pPlayer.getGUI().println("You didn't finish your morning care");
            return;
        }
    }

    
    pPlayer.getInventory().setItem(vItem.getName(), vItem); // puts the item into player's inventory
    pPlayer.setCurrentWeight(pPlayer.getCurrentWeight()+vItem.getWeight()); // changes its global weight
    pPlayer.getCurrentRoom().removeItem(vItem); // removes the item from the room
    pPlayer.getGUI().println("You took the " + super.getSecondWord() + ". Your new total weight is : " + pPlayer.getCurrentWeight() + "kg"); // success message
      }
    } // execute(.)
} // TakeCommand