package pkg_items;
import pkg_rooms.Room;
/**
 * Beamer Class - Handles the methods for a Beamer, Item for teleporting the player
 * @author Aman GHAZANFAR
 * @version 2023.04.02
 */
public class Beamer extends Item {
    // Attributes
    private Room aChargedRoom; // the room the beamer was charge in
 /**
     * Constructor, initialize the Beamer like a usual Item
     * @param pName The name of the beamer
     * @param pDescription The description of the beamer
     * @param pWeight The weight of the beamer
     */
    public Beamer(final String pName, final String pDescription, final double pWeight) {
        super(pName, pDescription, pWeight);
        
    } // Beamer(.)
    /**
     * Changes the Room the Beamer is charged in
     * @param pRoom The Room where the beamer is charged
     * @return The String that permits GameEngine to show the result of the request for the command
     */
    public String charge(final Room pRoom) {
            this.aChargedRoom = pRoom;
            return "You're Beamer has been charged successfuly";
        
        
        } // charge(.)
        /**
         * Changes the chargedRoom to null, and teleports the Player into the given room
         * @return The String that permits GameEngine to show the result of the request for the command
         */
    public String fire() {
        Room vStock = this.aChargedRoom;
        this.aChargedRoom = null;
        return "You fired the Beamer, you got teleported to : "+"\n"+vStock.getLongDescription();
    } // fire()
    /**
     * @return The Room the beamer was charged in
     */
    public Room getChargedRoom() {
        return this.aChargedRoom;
    } // getChargedRoom()
    /**
     * @return The state of the Beamer, whether it is charged or not
     */
    public boolean canFire() {
        return this.aChargedRoom != null;
    } // canFire()
   
} // Beamer