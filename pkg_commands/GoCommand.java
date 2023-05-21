package pkg_commands;
import pkg_gameobjects.Player;
import pkg_rooms.Room;
import pkg_gameobjects.Door;
import pkg_gameobjects.Character;
import pkg_gameobjects.MovingCharacter;
import pkg_rooms.TransporterRoom;
import pkg_items.Item;
/**
 * GoCommand Class - Permits the player to move around the map, directions : north, south, east, west, up and down
 * @author Aman GHAZANFAR
 * @version 2023/05/06
 */
public class GoCommand extends Command {
     /**
     * Execute function, common to every commands, overrides the abstract method of Command Class.
     * @param pPlayer A Player object
     */
    @Override
    public void execute(Player pPlayer) {
        
        // check if there is a second word
        if (!super.hasSecondWord()) {
      pPlayer.getGUI().println("Go where");
      return;
    }
    
    String vDirection = super.getSecondWord(); // get the direction input
  
    Room vNextRoom = pPlayer.getCurrentRoom().getExit(vDirection); // get the next room according to the direction

    // if vNextRoom returned the unknownroom, meaning we have an unknown direction
        if (vNextRoom == Room.UNKNOWN_ROOM) {
      pPlayer.getGUI().println("Unknown direction");
      return;
    }

  // if there is no room according to the direction
    if (vNextRoom == null) {
       pPlayer.getGUI().println("There is no door");
      return;
    }
 
    if(pPlayer.getCurrentRoom() instanceof TransporterRoom) {
        Item vI = new Item("pass", "A pass to open a chest", 0);
        pPlayer.getInventory().setItem(vI.getName(), vI);
        
        pPlayer.getGUI().println("You gone through the TransporterRoom and found a pass, gg ! It must be usefull use it ...");
        pPlayer.addAction("go", vNextRoom.getName());
    }
    
 Door vNextDoor = pPlayer.getCurrentRoom().getDoor(vDirection); // get the door to check if he is locked or he is a trap door
   
 
 // check if the door is locked
    if(vNextDoor.isLocked()) {
        
    if(vNextDoor.getKey().getName().equals("coin")) {
                 if(pPlayer.getCoins() >= 1) {
                     pPlayer.removeCoins();
                        pPlayer.incrementMoves(); // increments his move count, 25 maximum allowed
            pPlayer.getGameEngine().showGUI(vNextRoom); // shows all the infos about the room
             pPlayer.setLastDirection(vDirection); // for the case if the key has been dropped, and the user wants to go to a direction, we store the last direction
             // to then revert it if needed
           return;
                 } else {
                     pPlayer.getGUI().println("You need at least one coin to enter this Room");
                     return;
                 }
             
            }
        // check if the player has the key
     if(pPlayer.hasItem(vNextDoor.getKey().getName())) {
         pPlayer.incrementMoves(); // increments his move count, 25 maximum allowed
            pPlayer.getGameEngine().showGUI(vNextRoom); // shows all the infos about the room
             pPlayer.setLastDirection(vDirection); // for the case if the key has been dropped, and the user wants to go to a direction, we store the last direction
             // to then revert it if needed
           
               
        }else {
            String vItemName = vNextDoor.getKey().getName();
            if(vItemName.equals("shoes")) {
                pPlayer.getGUI().println("You don't wear your shoes, mom is going to be angry !");
                return;
            }
            if(vItemName.equals("cup")){
                pPlayer.getGUI().println("You don't have your coffee, Edgar can't work without his morning coffee");
                return;
            }
            if(vItemName.equals("cookie") && pPlayer.getMaxWeight() == 50) {
                pPlayer.getGUI().println("You can't open this door it's too heavy for you");
                return;
            }
           
            pPlayer.getGUI().println("You don't have the key, you can't go inside this Room"); // error message 
            
            
        }
      
    }else {
        if(pPlayer.getCurrentRoom().getName().equals("cabin") && vDirection.equals("south")) {
           if(!pPlayer.getAuthBasement()) {
                pPlayer.getGUI().println("You don't have the authorization to go inside this room, maybe there is a quest you still have to complete...");
                return;
            }
        }
        
        pPlayer.incrementMoves(); // increments his move count, 25 maximum allowed
        pPlayer.getGameEngine().showGUI(vNextRoom); // shows all the infos about the room
               pPlayer.setLastDirection(vDirection);// for the case if the key has been dropped, and the user wants to go to a direction, we store the last direction
             // to then revert it if needed
   
             //pPlayer.getCurrentRoom().getCharacter("Recupix").move(vNextRoom);
    }
       } // execute(.)
} // GoCommand