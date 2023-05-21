package pkg_gameobjects;
import pkg_rooms.Room;
/**
 * MovingCharacter Class - Handles all the moving NPCs of the Game
 */
public class MovingCharacter extends Character {
    // Attributes
    private Room aCurrentRoom;
   
    /**
     * Character Class Constructor, instantiates all the attributes
     * @param pName NPC's name
     * @param pDescription NPC's description
     * @param pDialog NPC's dialog
     */
    public MovingCharacter(final String pName, final String pDescription, final String pDialog) {
        super(pName, pDescription, pDialog);
    } // MovingCharacter(.)
    /**
     * Allowed a MovingCharacter type object to move around the map
     * @param pRoom The new Room he is going to be in
     */
    public void move(final Room pRoom) {
        aCurrentRoom.removeCharacter(this);
        pRoom.addCharacter(this);
        aCurrentRoom = pRoom;
    } // move(.)
    
} // MovingCharacter(.)