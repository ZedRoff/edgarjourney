package pkg_gameobjects;

import pkg_utils.UserInterface;
import pkg_utils.Parser;
import pkg_rooms.Room;
import pkg_rooms.TransporterRoom;
import pkg_items.Item;
import pkg_items.Beamer;
import pkg_commands.Command;
import pkg_commands.CommandWord;
import pkg_gameobjects.Character;
import pkg_gameobjects.MovingCharacter;

import java.util.Stack;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * GameEngine Class - The main class that creates the Player Object and contains all the printX() commands, creates the rooms, sets their items, also holds the commands and their correspondings
 *
 * @author Aman GHAZANFAR
 * @version 2023.03.23
 */
public class GameEngine {
  // Attributes

  private Parser aParser; // the parser permits to read the input field
  private UserInterface aGui; // the user interface that holds all the components
  private HashMap < String, Room > aRooms; // holds all the visitable rooms of the game (that doesn't require keys or spoils the game)
  private Player aPlayer; // the Player object
  private Timer aTimer; // holds a timer's methods
  private HashMap<String, Room> allRooms;
  
  /**
   * The constructor of Game Class, calls the createRooms method, makes an instance of Parser Class, creates the Player Object, and instantiates the room hashmap
   * @param pName The name of the Player
    */
  public GameEngine(final String pName) {
    this.aRooms = new HashMap < String, Room > ();
     this.allRooms = new HashMap< String, Room > ();

    this.createRooms();
    this.aParser = new Parser();
    this.aPlayer = new Player(pName, this.aRooms.get("bedroom"), this);
   
    this.aTimer = new Timer(120000, new ActionListener() {
   
        // the timer that asks player whether he is sleeping or not
    public void actionPerformed(ActionEvent evt) {
    javax.swing.JOptionPane.showMessageDialog(null, "Are you afk ?");
    
     ((Timer)evt.getSource()).stop();
    }    
});
  } // GameEngine(.)
  /**
   * @return the visitable Rooms
   */
  public HashMap<String, Room> getRooms() {
      return aRooms;
  } // getRooms()
  public Parser getParser() {
      return this.aParser;
  }
  
  /**
   * Sets the user interface
   * @param pUserInterface A UserInterface type object
   */
  public void setGUI(final UserInterface pUserInterface) {
    this.aGui = pUserInterface;
    this.printWelcome();
  } // setGUI(.)
  /**
   * @return the GameEngine objet
   */
  public UserInterface getGUI() {
      return this.aGui;
  } // getGUI()
  /**
   * Permits to create all the rooms and assign them their exits, the items and puts the values inside aRooms.
   * Remark: The first room is Edgar's bedroom.
   */
  private void createRooms() {

    Room vBedroom = new Room("bedroom", "Edgar's bedroom.", "./assets/bedroom.png");
    Room vKitchen = new Room("kitchen", "Edgar's kitchen.", "./assets/kitchen.png");
    Room vGarage = new Room("garage", "inside the garage", "./assets/garage.png");
    Room vBathroom = new Room("bathroom", "inside a bathroom", "./assets/bathroom.png");
    Room vCabin = new Room("cabin", "inside the phone booth, use interact command inside it...", "./assets/phone.png");
    Room vOutside = new Room("outside", "outside the house", "./assets/outside.png");
    Room vEntry = new Room("entry", "inside the intrance hall (underground)", "./assets/intrance.png");
    Room vPantry = new Room("pantry", "inside the pantry, miam", "./assets/pantry.png");
    Room vLiving = new Room("living", "inside the living room", "./assets/living.jpg");
    Room vLaboratory = new Room("laboratory", "inside the LABOORATORYYY", "./assets/laboratory.png");
    HashMap<String,Room> aVisitableRooms;
  
    Room vTransporter = new TransporterRoom("transporter","You are inside the transporter room. You can't back now. When you exit this room, you are going to get teleported to a random room", "./assets/transporter.jpg", aRooms);
    
    
    Item vCup = new Item("cup", "A cup of coffee", 10);
    Item vShoes = new Item("shoes", "Edgar's Shoes", 20);
    Item vCoin = new Item("coin", "A coin, let's see what its purpose", 5);
    Item vBeamer = new Beamer("beamer", "Permits to teleport to a room. Look for charge and fire commands", 10);
    Item vKey = new Item("key", "A key to open a door", 20);
    Item vToolbox = new Item("toolbox", "A very suspicious toolbox", 10);
    Item vCream = new Item("cream", "Nivea Cream for the face", 10);
    Item vToothpaste = new Item("toothpaste", "Toothpaste for some good white teeths", 10);
    Item vBrush = new Item("brush", "TeethBrush, apply the toothpaste on it", 10);
    // Bedroom part
     vBedroom.setExit("east", vKitchen);
    vBedroom.setExit("south", vBathroom);
    
    vBedroom.addCharacter(new Character("Mom", "Edgar's mom, she is very angry that you are late", "Hey ! Wake up you are late ! As always..."));
    vBedroom.addCharacter(new MovingCharacter("Recupix", "Edgar's robot, he can do many fancy things", "Hey ! My name is Recupix, I can show you the map and give you hints whenever you need it ! And I will follow you everywhere as well !"));
    
    vBedroom.addItem(vShoes);
  
    
    vBedroom.lockDoor("east", vShoes);

    vBedroom.lockDoor("south", vCup);
    
    // end bedroom part
    
    // kitchen part
    vKitchen.setExit("west", vBedroom);
    vKitchen.setExit("south", vGarage);
    vKitchen.setExit("east", vTransporter);
  
    vKitchen.addItem(vBeamer);
    vKitchen.addItem(vCup);
    
    vKitchen.lockDoor("south",vCup);
    vKitchen.lockDoor("east", vCoin);
    
    vKitchen.addCharacter(new Character("Brother","Edgar's brother", "Hey bro ! Ready for this first coding day ? Oh and, I changed your computer's password, it's helloworld now..."));
    // end kitchen part

    // garage part
    
    // vGarage.setExit("north", vKitchen); -> commented for trap doors system
    vGarage.setExit("west", vBathroom);
    vGarage.setExit("south", vOutside);
    
    vGarage.addItem(vToolbox);
    
    vGarage.lockDoor("south", vKey);
    // end garage part
    
    // bathroom part
    
    vBathroom.setExit("east", vGarage);
    vBathroom.setExit("north", vBedroom);
    
    vBathroom.addItem(vKey);
    vBathroom.addItem(vCream);
    vBathroom.addItem(vToothpaste);
    vBathroom.addItem(vBrush);
    
    // end bathroom part
   
    vOutside.addCharacter(new Character("cat", "A cat you can examinate", "The code of the cabin is 123"));


    vPantry.addItem(new Item("cookie", "With these cookies, you can carry out more weight", 10));

    vLaboratory.addItem(new Item("computer", "Edgar's Computer where you have to write the program", 0));
    vLaboratory.addItem(new Item("keyboard", "Edgar's computer's keyboard", 0));
    vLaboratory.addItem(new Item("mouse", "Edgar's computer's mouse", 0));

    
    
    vOutside.setExit("west", vCabin);
    vOutside.setExit("north", vGarage);
    
    vCabin.setExit("east", vOutside);
    vCabin.setExit("down", vEntry);
    vCabin.addItem(new Item("manual", "WARNING ! The cat gave you a wrong information..., the real code is 321 !!", 0));
    
    vPantry.setExit("east", vEntry);
       vEntry.setExit("east", vLiving);
    vEntry.setExit("west", vPantry);
    vEntry.setExit("up", vCabin);
 vEntry.lockDoor("east", new Item("cookie", "n", 0));
   
    vLiving.setExit("east", vLaboratory);
    vLiving.setExit("west", vEntry);
   
    vLiving.addCharacter(new Character("Saro", "A mage is inside your living room", "Who has two needles, but does not prick? Use interact command to reply..."));
 
 vTransporter.setExit("east", Room.UNKNOWN_ROOM);
 
   
    vLaboratory.setExit("west", vLiving);
    vLaboratory.lockDoor("west", new Item("code", "n", 0));
    vLiving.lockDoor("east", new Item("code", "n", 0));
    

    vOutside.lockDoor("north", vKey);
   
   // only put the rooms where you can't get softlock / doesn't gets you ahead of scenario, and thanks to that I can exclude the transporter room 
    aRooms.put("bedroom", vBedroom);
    aRooms.put("kitchen", vKitchen);
    aRooms.put("garage", vGarage);
    aRooms.put("bathroom", vGarage);
    aRooms.put("transporter", vTransporter);
    
// all rooms out there

    allRooms.put("pantry", vPantry);
    allRooms.put("living", vLiving);
    allRooms.put("laboratory", vLaboratory);
    allRooms.put("bedroom", vBedroom);
    allRooms.put("kitchen", vKitchen);
    allRooms.put("garage", vGarage);
    allRooms.put("bathroom", vGarage);
    allRooms.put("transporter", vTransporter);
    allRooms.put("cabin", vCabin);
    allRooms.put("outside", vOutside);
    allRooms.put("entry", vEntry);
    
  } // createRooms()
  /**
   * @return All rooms of the game
   */
     public HashMap<String, Room> getAllRooms() {
         return this.allRooms;
     }
  /**
   * Shows the GUI, avoids redondant code
   * @param pNextRoom The next room the player wants to go in
   */
  public void showGUI(final Room pNextRoom) {
      
 
      
      this.aPlayer.setVisitedRoom();
    
      pNextRoom.addCharacter(this.aPlayer.getCurrentRoom().getCharacter("Recupix"));
   
      this.aPlayer.getCurrentRoom().removeCharacter(this.aPlayer.getCurrentRoom().getCharacter("Recupix"));
   
    this.aPlayer.setCurrentRoom(pNextRoom);

    this.printLocationInfo();
        
    this.aTimer.start();
  } // showGUI(.)
  /**
   * Prints the starting game's message (welcome message, exits available and shows the start image).
   */
  private void printWelcome() {
    this.aGui.println("Welcome "+this.aPlayer.getName()+" to Edgar's Journey!\nCome and discover a typical developer's life !\nType "+CommandWord.HELP+" if you need help.");
    this.aGui.println("\n");
  this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
   
this.aTimer.start();
    if (this.aPlayer.getCurrentRoom().getImageName() != null) this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
  } // printWelcome()
  /**
   * The help page of the game, prints out all the available commands.
   */
  private void printHelp() {
    this.aGui.println("How could you be lost inside/near your own house..., anyway, here is the help page\n.You command word are: ");
    this.aGui.println(this.aParser.getCommandString());

  } // printHelp()
  

  /**
   * Handles all the commands side, to all the aValidCommands corresponds a method thanks to this method. 
   * @param pCommandLine A String composed of two words
   */
  public void interpretCommand(final String pCommandLine) {
    
    Command vCommand = aParser.getCommand(pCommandLine);
      
    if(vCommand == null) {
        this.aGui.println("I don't understand...");
    } else {
        if(!(aPlayer.getCurrentRoom() instanceof TransporterRoom)) {
            this.aPlayer.addAction(vCommand.getCommandWord(), vCommand.getSecondWord());
        }
      
        vCommand.execute(aPlayer);
    }

  } // interpretCommand(.)
 
  /**
   * Prints out on the GUI the details about a room (items, exits and description). And shows the image.
   */
  public void printLocationInfo() {
    this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
    if (this.aPlayer.getCurrentRoom().getImageName() != null) this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
  } // printLocationInfo().

} // GameEngine