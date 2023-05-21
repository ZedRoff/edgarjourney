package pkg_commands;

import pkg_gameobjects.Player;
import pkg_rooms.Room;
import pkg_items.Item;
/**
 * InteractCommand Class - Permits player to interact with either Saro or inside the cabin
 * @author Aman GHAZANFAR
 * @version 2023/05/06
 */
public class InteractCommand extends Command {
     /**
     * Execute function, common to every commands, overrides the abstract method of Command Class.
     * @param pPlayer A Player object
     */
    @Override
    public void execute(Player pPlayer) {
        
        // check if there is a second word
        if (!super.hasSecondWord()) {
      pPlayer.getGUI().println("Talk to who ?");
      return;
    }
    if(pPlayer.getCurrentRoom().getName().equals("cabin")) {
        if(super.getSecondWord().equals("123")) {
            pPlayer.getGUI().println("Wrong number, maybe the cat was wrong ? Find another way to get the code...");
            return;
        }else if(super.getSecondWord().equals("321")) {
            pPlayer.grantAccess();
            pPlayer.getGUI().println("Right code, permission to enter basement granted");
            return;
        }
    } else if(pPlayer.getCurrentRoom().getName().equals("living")) {
        if(!super.getSecondWord().equals("watch")) {
            pPlayer.getGUI().println("Wrong answer, try again");
        }else {
            pPlayer.getGUI().println("Right answer, you now have access granted to go inside the laboratory");
            pPlayer.getInventory().setItem("code", new Item("code", "The code of the laboratory now Edgar can use it if you go to this direction", 0));
        }
    } else if(pPlayer.getCurrentRoom().getName().equals("laboratory")) {
        if(!super.getSecondWord().equals("helloworld")) {
            pPlayer.getGUI().println("Wrong password");
        } else {
            if(!pPlayer.hasItem("pass")){
                javax.swing.JOptionPane.showMessageDialog(null, "Well played ! Edgar can now code, the game has ended. However, there is an item you missed in this game, try to find it :D");
     return;
            }
            javax.swing.JOptionPane.showMessageDialog(null, "Well played ! Edgar can now code, the game has ended. Also, you find the pass, which completes the game.");
   
            pPlayer.getGUI().getFrame().dispose(); // disable the frame
        pPlayer.getGUI().enable(false); // disable the entry field in case 
        }
    
    }else {
        pPlayer.getGUI().println("This command can only be used inside the cabin & the living room");
    }
    } // execute(.)
} // InteractCommand