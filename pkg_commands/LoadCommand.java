package pkg_commands;
import java.util.HashMap;
import java.io.File;

import pkg_gameobjects.Player;
import pkg_rooms.Room;
import java.util.Scanner;
/**
 * LoadCommand Class - Permits the player to load a savefile
 * @author Aman GHAZANFAR
 * @version 2023/05/06
 */
public class LoadCommand extends Command {
     /**
     * Execute function, common to every commands, overrides the abstract method of Command Class.
     * @param pPlayer A Player object
     */
    @Override
    public void execute(Player pPlayer) {
        
        // check if there is a second word
        if (!super.hasSecondWord()) {
      pPlayer.getGUI().println("Load what ?");
      return;
    }
    if(!pPlayer.getCurrentRoom().getName().equals("bedroom")){
        
        pPlayer.getGUI().println("This command can only be used inside the bedroom");
    return;
    }
    if(pPlayer.getVisitedRooms().size() != 0) {
        pPlayer.getGUI().println("This command can only be used inside a new game, no room entered previously");
        return;
    }
          
    File vFile = new File("./saves/"+super.getSecondWord()+".txt");
    Scanner vScan;
    try {
        
   vScan = new Scanner(vFile);
   if(vScan.hasNextLine() ) {
       String vLine = vScan.nextLine();
       if(vLine.length() == 0) {
           pPlayer.getGUI().println("This file is corrupted or empty");
       } else {
           

           String[] vCommands = vLine.split(",");
           
          
           pPlayer.resetVisitedRooms();
           pPlayer.resetItems();
           pPlayer.resetRoom();
      
           pPlayer.resetLastDirection();
           pPlayer.resetWeight();
           pPlayer.resetMovesCounter();
             
           pPlayer.resetName();
           pPlayer.resetActions();
           
           for(String vCommand: vCommands) {
               String[] vSplitter = vCommand.split("=");
               String vCommandWord = vSplitter[0].trim();
               String vSecondWord = vSplitter[1].trim();
             

                  if(vCommandWord.equals("go")) {
                     
                       if(this.verifDirection(vSecondWord)) {
                            
                           pPlayer.getGameEngine().interpretCommand("go "+vSecondWord);
                           
                            
                       } else {
                           Room vNextRoom = pPlayer.getGameEngine().getAllRooms().get(vSecondWord);
                            
                           pPlayer.teleport(vNextRoom);
                           
    pPlayer.setVisitedRoom();
      pPlayer.setCurrentRoom(vNextRoom);
      pPlayer.getGameEngine().printLocationInfo();
      
      
      
      
      
                       }
                    }else {
                  
                       if(vSecondWord.equals("null")) {
                           pPlayer.getGameEngine().interpretCommand(vCommandWord);
                       } else {
                           pPlayer.getGameEngine().interpretCommand(vCommandWord+" "+vSecondWord);
                       }
                       
                    }
               
           }
           pPlayer.getGUI().println("Save fully loaded.");
       }
   } else {
       pPlayer.getGUI().println("This file is corrupted or empty");
   }
 
   } catch (Exception FileNotFoundException) { 
        pPlayer.getGUI().println("This file doesn't exists");
    }
    } // execute(.)
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
} // LoadCommand