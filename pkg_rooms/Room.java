package pkg_rooms;
import pkg_items.ItemList;
import java.util.HashMap;
import java.util.Iterator;
import pkg_items.Item;
import pkg_gameobjects.Door;
import pkg_gameobjects.Character;

import java.util.Set;


/**
 * Room Class - Permits to define a room
 * 
 * @author Aman GHAZANFAR
 * @version 2023.02.09
 */
public class Room {
  // Attributes
  private String aDescription;
  private HashMap < String, Room > exits;
  private String aImageName;
  public static final Room UNKNOWN_ROOM = new Room("none", "no room", "no image"); // Specific public attribute, that is needed to check whether a direction exists or not. 
  private ItemList aItems;
  private HashMap<String, Door> aDoors;
  private HashMap<String, Character> aCharacters;
  private String aName;
  /**
   * Creates a new room with the given description. Also, creates the hashmap to store all the exits.
   * @param pName Room's name to reference it in other commands for instance
   * @param pDescription Room's description that will be displayed
   * @param pImage Room's image file's path
   */
  public Room(final String pName, final String pDescription, final String pImage) {
    this.aDescription = pDescription;
    this.exits = new HashMap < String, Room > ();
    this.aDoors = new HashMap<String,Door>();
    this.aImageName = pImage;
    this.aItems = new ItemList();
    this.aCharacters = new HashMap<String,Character>();
    this.aName = pName;
  } // Room()
  /**
   * @return Room's name
   */
  public String getName() {
      return this.aName;
  }
  /**
   * Permits to get the current room description. (short one).
   * @return The description of the current room.
   */
  public String getShortDescription() {
    return this.aDescription;
  } // getShortDescription()
  /**
   * Permits to get the long description of the current room. (long one).
   * @return The long description of the current room.
   */
  public String getLongDescription() {
    return "\n=========================\n=========================You are in : " + this.aDescription + ".\n=========================\n" + "The Exits available are : " + this.getExitString() + "\n=========================\n" + "The items available here are : " + this.getItemsString()+"\n=========================\n" + "The NPC you can talk to : "+this.getCharactersString();
  } // getLongDescription()
  /**
   * Sets the exit of a room to the HashMap.
   * @param pDirection The direction of the exit
   * @param pNeighbor The Room we want to set the exit to
   */
  public void setExit(final String pDirection, final Room pNeighbor) {
    this.exits.put(pDirection, pNeighbor);
    this.aDoors.put(pDirection, new Door());
  } // setExit()
  /**
   * @param pDirection A cardinal direction north, south etc..
   * @return The door the direction points to inside a room
   */
  public Door getDoor(final String pDirection) {
      return this.aDoors.get(pDirection);
  }
  /**
   * @param pDirection A cardinal direction north, south etc...
   * @param pItem The item object that is needed to open this door
   * Permits to lock a specific door inside a room with a key needed to open it
   */
  public void lockDoor(final String pDirection, final Item pItem) {
      this.aDoors.get(pDirection).lock(pItem);
  }
  /**
   * @return Whether the direction is a room's exit or not (usefull for back command essentially)
   */
 public boolean isExit(final Room pRoom){
        return this.exits.containsValue(pRoom);
    }
   
  /**
   * Permits to get the exit of the given room.
   * @param pDirection The direction you want to go further.
   * @return The Room corresponding to the direction OR if the direction is not one of the 5 possible directions, it returns the UNKNOWN_ROOM.
   */
  public Room getExit(String pDirection) {
    if (!this.verifDirection(pDirection)) {
      return UNKNOWN_ROOM;
    } else {
      return this.exits.get(pDirection);
    }

  } // getExit(.)
  /**
   * Permits to verify whether a direction is one of the 5 possible ones or not.
   * @param pDirection The direction you want to verify.
   * @return A boolean value telling if it's a right direction.
   */
  public boolean verifDirection(String pDirection) {
    String[] vDirections = {
      "north",
      "south",
      "west",
      "east",
      "up",
      "down"
    };
    for (String direction: vDirections) {
      if (direction.equals(pDirection)) {
        return true;
      }
    }
    return false;
  } // verifDirection(.)
  /**
   * Getting all the possible exits from a Room.
   * @return A String with all the possible exits splitted with a space.
   */
  public String getExitString() {
    StringBuilder vPath = new StringBuilder("Exits: ");
    Set < String > keys = exits.keySet();
    for (String exit: keys) {
      vPath.append(exit + " | ");

    }
    return vPath.toString();
  } // getExitString()
  /**
   * @return The list of the items inside the Room
   */
  public String getItemsString() {
    return this.aItems.getItemsString();
  } // getItemsString()
  /**
   * @param pItem The name of the item you want to get
   * @return The Item you got from that name
   */
  public Item getItem(final String pItem) {
    return this.aItems.getItem(pItem);
  } // getItem(.)
  /**
   * Returns the items that are available inside a Rpom
   * @return The inventory of the Player (the hashmap)
   */
  public ItemList getItems() {
    return this.aItems;
  } // getItems()
  /**
   * @return Room's image name
   */
  public String getImageName() {
    return this.aImageName;
  } // getImageName()
  /**
   * Permits to add an item inside a Room
   * @param pItem Item Object of the Item you want to add inside the Room
   */
  public void addItem(final Item pItem) {
    this.aItems.setItem(pItem.getName(), pItem);
  } // addItem(.)
  /**
   * Permits to remove an item from a Room
   * @param pItem Item Object of the Item you want to remove from the Room
   */
  public void removeItem(final Item pItem) {
    this.aItems.removeItem(pItem.getName());
  } // removeItem(.)
  
  /**
   * Permits to add a character into a Room
   * @param pCharacter The character you want to add inside the Room
   */
  public void addCharacter(final Character pCharacter) {
      this.aCharacters.put(pCharacter.getName(), pCharacter);
  } // addCharacter(.)
  /**
   * Permits to remove a character into a Room
   * @param pCharacter The character you want to remove inside the Room
   */
  public void removeCharacter(final Character pCharacter) {
      this.aCharacters.remove(pCharacter.getName());
  } // removeCharacter(.)
  /**
   * @return The list of NPCS available inside a Room
   */
  public String getCharactersString() {
      StringBuilder vPath = new StringBuilder("");
    Set < String > keys = this.aCharacters.keySet();
    for (String vCharacter: keys) {
      vPath.append(vCharacter + " | ");
    }
    return vPath.toString();
  } // getCharactersString()
  /**
   * @param pCharacterName The character's name
   * @return The NPC corresponding to the given name
   */
  public Character getCharacter(final String pCharacterName) {
      return this.aCharacters.get(pCharacterName);
  }
  
} // Room