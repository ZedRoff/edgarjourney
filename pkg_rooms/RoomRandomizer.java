package pkg_rooms;

import java.util.Random;

import java.util.Collection;
/**
 * RoomRandomizer Class - Permits to generate a random room, has only one method
 * @author Aman GHAZANFAR
 * @version 2023.04.05
 */
public class RoomRandomizer {
    // Attributes
    private Random aRandom; // Handles a Random object, so we can generate a random number from it
    /**
     * Constructor, instantiates the aRandom attribute
     */
    public RoomRandomizer() {
        this.aRandom = new Random();
        
    } // RoomRandomizer()
    /**
     * @param pRooms The Collection of visitableRooms
     * @return A random room among the visitable Rooms
     */
    public Room generateRandomRoom(final Collection<Room> pRooms) {

        int vRnd = this.aRandom.nextInt(pRooms.size());
       return (Room)pRooms.toArray()[vRnd];
       
    } // generateRandomRoom(.)
  
} // RoomRandomizer