package pkg_commands;

import pkg_gameobjects.Player;
/**
 * QuitCommand Class - Permits to quit the game, shows a dialog right before, action depending on the choice
 * @author Aman GHAZANFAR
 * @version 2023/05/06
 */
public class QuitCommand extends Command {
   
    /**
     * Execute function, common to every commands, overrides the abstract method of Command Class.
     * @param pPlayer A Player object
     */
    @Override
    public void execute(Player pPlayer) {
        
        // checks whether a second word was input
          if (super.hasSecondWord()) {
      pPlayer.getGUI().println("Quit what ?");

    } else {
     
        // takes user's name
    int input = javax.swing.JOptionPane.showConfirmDialog(null, "Are you sure you want to quit ? :/");
  
    if (input == 1) { // if he doesn't want to quit
      return;
    } else if(input == 0) { // if he wants to quit
      javax.swing.JOptionPane.showMessageDialog(null, "Thank you for playing. Good bye.");
      pPlayer.getGUI().getFrame().dispose(); // disable the frame
        pPlayer.getGUI().enable(false); // disable the entry field in case 
    }
    }
} // execute(.)
} // QuitCommand