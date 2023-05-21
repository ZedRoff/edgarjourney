package pkg_commands;
import java.io.File;
import java.util.Scanner;
import pkg_gameobjects.Player;
/**
 * TestCommand Class - Permits to test the game thanks to a txt file that has a specified path to follow upon commands
 * @author Aman GHAZANFAR
 * @version 2023/05/06
 */
public class TestCommand extends Command {
 /**
     * Execute function, common to every commands, overrides the abstract method of Command Class.
     * @param pPlayer A Player object
     */
    @Override
    public void execute(Player pPlayer) {
        
        // check if there is a second word
        if(!super.hasSecondWord()) {
          pPlayer.getGUI().println("Test what ?");
          return;
        }
         if(pPlayer.getVisitedRooms().size() != 0) {
        pPlayer.getGUI().println("This command can only be used inside a new game, no room entered previously");
        return;
    }
      File vFile = new File("./tests/" + super.getSecondWord() + ".txt"); // get the file which user wants to try the game with
    Scanner vScan; // Scanner object thanks to it, we can read files line by line (or sentences word by word)
    try {
      vScan = new Scanner(vFile);
      while (vScan.hasNextLine()) { // while there is a new line, continue reading it
            String vLine = vScan.nextLine(); // get the next line
            if(vLine.length() == 0) return; // it there is no carac on that line, no need to run it
           pPlayer.getGameEngine().interpretCommand(vLine); // run the command on that line
      }
      vScan.close(); // close the scanner
    } catch (Exception FileNotFoundException) { // if the file wasn't found
      pPlayer.getGUI().println("File doesn't exists");
    }
    } // execute(.)
} // TestCommand