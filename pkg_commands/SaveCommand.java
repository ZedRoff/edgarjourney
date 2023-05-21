package pkg_commands;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;
import pkg_gameobjects.Player;
import pkg_rooms.Room;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * SaveCommand Class - Permits the player to save the game
 * @author Aman GHAZANFAR
 * @version 2023/05/06
 */
public class SaveCommand extends Command {
     /**
     * Execute function, common to every commands, overrides the abstract method of Command Class.
     * @param pPlayer A Player object
     */
    @Override
    public void execute(Player pPlayer) {
        
        // check if there is a second word
        if (!super.hasSecondWord()) {
      pPlayer.getGUI().println("Save where ?");
      return;
    }
    try {
       FileWriter vFile = new FileWriter("./saves/"+super.getSecondWord()+".txt"); 
       vFile.write(pPlayer.getActions().toString().replace("[", "").replace("]", ""));
       vFile.close();
       pPlayer.getGUI().println("You're game was save successfully");
    } catch(IOException e) {
        pPlayer.getGUI().println("The file wasn't found");
         e.printStackTrace();
    }
    
    
    
    } // execute(.)
} // SaveCommand