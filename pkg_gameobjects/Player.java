package pkg_gameobjects;
import java.util.HashMap;
import java.util.Stack;
import pkg_items.Item;
import pkg_items.ItemList;
import pkg_items.Beamer;
import pkg_rooms.Room;
import pkg_utils.UserInterface;
import java.util.ArrayList;
/**
 * Player Class - Holds all the informations about the Player (his current room, his inventory, the visited rooms and the weight he is carrying
 * @author Aman GHAZANFAR
 * @version 2023.03.27
 */
public class Player {
  private Room aCurrentRoom; //
  private double aMaxWeight;
  private String aName;
  private double aCurrentWeight;
  private Stack < Room > aVisitedRooms;
  private ItemList aInventory;
  private int aMovesCounter;
  private int aMaxMovesCount;
  private boolean aTestMode;
  private GameEngine aGameEngine;
  private String aLastDirection;
  private ArrayList<String> actions;
  private int aCoins;
  private boolean aBasementAllowed;
  /**
   * Player Class Constructor, instantiates all the attributes
   * @param pName The name of the Player
   * @param pRoom The Room in which the Player starts the game
   * @param pGameEngine The Player's GameEngine Object for further manipulation
   */
  public Player(final String pName, final Room pRoom, final GameEngine pGameEngine) {
    this.aCurrentRoom = pRoom;
    this.aMaxWeight = 50;
    this.aMovesCounter = 0;
    this.aMaxMovesCount = 25;
    this.aName = pName;
    this.aVisitedRooms = new Stack < Room > ();
    this.aCurrentWeight = 0;
    this.aInventory = new ItemList();
      this.aGameEngine = pGameEngine;
    this.aLastDirection = "";
    this.aTestMode = false;
    this.actions = new ArrayList<String>();
    this.aCoins = 0;
    this.aBasementAllowed = false;
  } // Player(.)
  /**
   * @return The state whether the user used 321 inside the cabin or not
   */
  public boolean getAuthBasement() {
      return this.aBasementAllowed;
  }
  /**
   * Grants the access to the basement
   */
  public void grantAccess() {
      if(this.aBasementAllowed) return;
      this.aBasementAllowed = !this.aBasementAllowed;
  }
  /**
   * @return User's actual coin amount
   */
  public int getCoins() {
      return this.aCoins;
  }
  /**
   * Adds one coin to player's inventory
   */
  public void addCoins() {
      this.aCoins++;
  }
  /**
   * Removes the coin from player's inventory
   */
  public void removeCoins() {
      this.aCoins--;
  }
  /**
   * Clear the visited rooms stack
   */
  public void resetVisitedRooms() {
      this.aVisitedRooms = new Stack<Room>();
  }
  /**
   * Sets the current room to be the first room of the game
   */
  public void resetRoom() {
      this.aCurrentRoom = aGameEngine.getAllRooms().get("bedroom");
  }
  /**
   * Reset player's item
   */
  public void resetItems() {
      this.aInventory = new ItemList();
  }
  /**
   * Changes its last direction to default
   */
  public void resetLastDirection() {
      this.aLastDirection = "";
  }
  /**
   * Sets back the weight and resets the current weight
   */
  public void resetWeight() {
      this.aCurrentWeight = 0;
      this.aMaxWeight = 50;
  }
  /**
   * Sets back the move counter to 0
   */
  public void resetMovesCounter() {
      this.aMovesCounter = 0;
  }
  /**
   * Resets its name
   */
  public void resetName() {
      this.aName = "";
  }
  /**
   * Empties the actions arraylist
   */
  public void resetActions() {
      this.actions = new ArrayList<String>();
  }
 
  public void teleport(final Room pRoom) {
      this.aCurrentRoom = pRoom;
  }
  /**
   * @return player's action during its current game
   */
  public ArrayList<String> getActions() {
      return this.actions;
  }
  /**
   * Changes the actions arraylist
   * @param pActions The users actions from the save that needs to be loaded
   */
  public void setActions(final ArrayList<String> pActions) {
      this.actions = pActions;
  }
  /**
   * @return Player last direction he wanted to go to
   */
  public String getLastDirection() {
      return this.aLastDirection;
  } // getLastDirection()
  /**
   * Adds an action the player's current actions
   * @param pCommandWord The first word of the command
   * @param pSecondWord The second word of the command
   */
  public void addAction(final String pCommandWord, final String pSecondWord) {
      this.actions.add(pCommandWord+"="+pSecondWord);
  }
  /**
   * Change Player's last direction he wants to go to
   * @param pValue The new value of the direction
   */
  public void setLastDirection(final String pValue) {
      this.aLastDirection = pValue;
  } // setLastDirection(.)
  /**
   * @return Whether the Player is in test mode, or not
   */
  public boolean getTestMode() {
      return this.aTestMode;
  } // getTestMode()
  /**
   * @return The GameEngine object
   */
  public GameEngine getGameEngine() {
      return this.aGameEngine;
  } // getGameEngine()
  /**
   * @return The current player's UserInterface
   */
  public UserInterface getGUI() {
      return this.aGameEngine.getGUI();
  } // getGUI()
  public void setName(final String pNewName) {
      this.aName = pNewName;
  }
  /**
   * Changes the test mode for the alea command
   * @param pBool The boolean that states whether the user is in test mode or not
   */
  public void setTestMode(final boolean pBool) {
      this.aTestMode = pBool;
  } // setTestMode(.)
  /**
   * @return Player's current room he is in
   */
  public Room getCurrentRoom() {
    return this.aCurrentRoom;
  } // getCurrentRoom()
  /**
   * @return Player's name
   */
  public String getName() {
    return this.aName;
  } // getName()
  /**
   * @return Player's current weight he is carrying
   */
  public double getCurrentWeight() {
    return this.aCurrentWeight;
  } // getCurrentWeight()
  /**
   * Change the current weight the player can carry on
   * @param pValue The new value of the current weight
   */
  public void setCurrentWeight(final double pValue) {
      this.aCurrentWeight = pValue;
  } // setCurrentWeight(.)
  /**
   * Changes Player's current room
   * @param pNewRoom The new room he is going to be inside
   */
  public void setCurrentRoom(final Room pNewRoom) {
    this.aCurrentRoom = pNewRoom;
  } // setCurrentRoom(.)
  /**
   * Adds the actual room to the Stack of visitedRooms (for the back command). We then change the room in the goRoom() method in GameEngine.
   */
  public void setVisitedRoom() {
    this.aVisitedRooms.push(this.getCurrentRoom());
  } // setVisitedRoom()
  /**
   * Checks whether the Player has an item or not
   * @param pItem The name of the item you want to check the existence in the inventory of
   * @return A boolean, player has/not the item
   */
  public boolean hasItem(final String pItem) {
    return this.aInventory.getItem(pItem) != null;
  } // hasItem(.)
  /**
   * @return The Stack of the visited rooms (for back command)
   */
  public Stack getVisitedRooms() {
    return this.aVisitedRooms;
  } // getVisitedRooms()
  /**
   * @return Player's inventory (ItemList Object)
   */
  public ItemList getInventory() {
    return this.aInventory;
  } // getInventory()
 
 
  /**
   * @return The max weight the player can carry on
   */
  public double getMaxWeight() {
      return this.aMaxWeight;
  } // getMaxWeight()
  /**
   * Change player's max weight capacity
   * @param pValue The new value of the max weight
   */
  public void setMaxWeight(final double pValue) {
      this.aMaxWeight = pValue;
  } // setMaxWeight(.)
 
  
  /**
   * @return The List of the Items inside Player's inventory (inside a single String)
   */
  public String getInventoryString() {
    return "Inventory : " + this.aInventory.getItemsString();
  } // getInventoryString()
 

  /**
   * Permits to increment the amout of moves the player has done, checks whether the limit is reached or not too
   */
  public void incrementMoves() {
      if(this.aMovesCounter == this.aMaxMovesCount) {
          
        javax.swing.JOptionPane.showMessageDialog(null, "You exceeded the "+this.aMaxMovesCount+" allowed moves, you lost.");
        System.exit(0);
    
      }
      this.aMovesCounter++;
  } // incrementMoves()
  /**
   * @return Player's current moves count
   */
  public int getMoves() {
      return this.aMovesCounter;
  }
    /**
     * @param pBeamer The beamer we want to get the state of
     * @return The state of the current beamer inside player's inventory
     */
  public boolean getBeamerState(final String pBeamer) {
      Item vBeamer = this.aInventory.getItem(pBeamer);
      
      if(vBeamer == null) return false;
      return ((Beamer)vBeamer).canFire();
  }
  /**
   * @param pBeamer The beamer we want to get the Room of
   * @return The current beamer's room he was charged in
   */
  public Room getBeamerRoom(final String pBeamer) {
      Item vBeamer = this.aInventory.getItem(pBeamer);
      
      if(vBeamer == null) return null;
      return ((Beamer)vBeamer).getChargedRoom();
  }
} // Player
