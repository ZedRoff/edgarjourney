package pkg_rooms;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Collection;
/**
 * TransporterRoom Class - Handle the transporter Room methods
 * @author Aman GHAZANFAR
 * @version 2023.04.05
 */
public class TransporterRoom extends Room { 
   // Attributes
    private Collection<Room> aArrayRooms; // holds the values of the visitable rooms hashmap
    private HashMap<String, Room> aRooms; // holds the visitable rooms hashmap
    private RoomRandomizer aRandomGenerator; // holds a RoomRandomizer object
    private String aAleaString; // the value of the randomRoom if test mode is activated
    
    /**
     * Constructor, instantiates all the attributes
     * @param pName Room's name
     * @param pDescription Room's description
     * @param pImage Room's image file's path
     * @param pRooms The visitage rooms HashMap
     */
    public TransporterRoom(final String pName, final String pDescription, final String pImage, final HashMap<String, Room> pRooms) {
     super(pName, pDescription, pImage);
       aAleaString = "";
       this.aRooms = pRooms;
     this.aRandomGenerator = new RoomRandomizer();
    } // TransporterRoom(.)
    /**
     * Finds a random room thanks to the aRandomGenerator method
     * @return A random room among the visitable rooms
     */
    private Room findRandomRoom() {
        aArrayRooms = this.aRooms.values();
        
        if(aAleaString.length() != 0) {
            return this.aRooms.get(aAleaString);
        }
       return aRandomGenerator.generateRandomRoom(aArrayRooms);
       
    } // findRandomRoom()
    /**
     * As TransporterRoom is a sort of Room, we Override its exit so we don't have to change our initial conditions inside goRoom method
     * @param pDirection The given direction we want to get the exit of
     * @return The exit that was found from the given pDirection
     */
    @Override public Room getExit(final String pDirection) {
        
        if(!super.verifDirection(pDirection)) {
            return UNKNOWN_ROOM;
        }
      if(super.getExit(pDirection) == null) {
          return null;
      }
      //System.out.println(findRandomRoom());
        return findRandomRoom();
    } // getExit(.)
    /**
     * For the test mode, we change the random room into a given room so we can know where the transporter room is going to lead us
     * @param pString The Rooms's name you want to be teleported to
     */
    public void setAlea(final String pString) {
        aAleaString = pString;
    } // setAlea(.)
} // TransporterRoom