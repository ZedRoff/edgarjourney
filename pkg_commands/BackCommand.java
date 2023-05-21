package pkg_commands;
import java.util.Stack;
import pkg_gameobjects.Player;
import pkg_gameobjects.Door;
import pkg_rooms.Room;
/**
 * BackCommand Class - Permits to back to the previous room the player was in
 * @author Aman GHAZANFAR
 * @version 2023/05/06
 */
public class BackCommand extends Command {
 /**
     * Execute function, common to every commands, overrides the abstract method of Command Class.
     * @param pPlayer A Player object
     */
    @Override
    public void execute(Player pPlayer) {
        // check if there is a second word
        if (super.hasSecondWord()) {
        pPlayer.getGUI().println("This command doesn't accept a second argument.");
        return;
      } else {
        // if the Player is right after a trap door, or after the teleportation process, or just at the starting of the game
       if (pPlayer.getVisitedRooms().empty()) {
       pPlayer.getGUI().println("You can't back here");

    }else { // if it's not the case
        
        Room newRoom = (Room)pPlayer.getVisitedRooms().peek();
       
        // revert the previous direction so we can know from which room he comes from (TODO : could improve on that point)
        String vNewDirection = "";
        String vLastDirection = pPlayer.getLastDirection();
        if(vLastDirection.equals("north")) {
            vNewDirection = "south";
        } else if(vLastDirection.equals("south")) {
            vNewDirection = "north";
        } else if(vLastDirection.equals("east")) {
            vNewDirection = "west";
        } else if(vLastDirection.equals("west")) {
            vNewDirection = "east";
        } else if(vLastDirection.equals("up")) {
            vNewDirection = "down";
        } else if(vLastDirection.equals("down") ){
            vNewDirection = "up";
        }
       
        // get the Door of the previous Room to check if it was a trap door
        Door vNextDoor = pPlayer.getCurrentRoom().getDoor(vNewDirection);
         
    
        if(!pPlayer.getCurrentRoom().isExit(newRoom)) { // if it's a trap door
         pPlayer.getGUI().println("You can't back, it's a trap door.");
        }
         else if(vNextDoor != null && vNextDoor.isLocked() && !pPlayer.hasItem(vNextDoor.getKey().getName())) { // if it isn't a trap door, but it's locked, and the player doesn't have the item
        pPlayer.getGUI().println("You can't back, you don't have the key");
    
    }else {
         
    pPlayer.getVisitedRooms().pop(); // unloads the last item of the visitedRooms stack
    pPlayer.setCurrentRoom(newRoom); // changes player's currentRoom
    pPlayer.getGUI().println("You're back at you're previous location."); // success message
    }
        
        pPlayer.getGameEngine().printLocationInfo(); // shows room's infos
      }
    }
} // execute(.)
} // BackCommand