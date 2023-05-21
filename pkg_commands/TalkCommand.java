package pkg_commands;


 import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import pkg_gameobjects.Player;
import pkg_gameobjects.Character;
import pkg_gameobjects.MovingCharacter;
/**
 * TalkCommand Class - Permits the player to talk to an NPC
 * @author Aman GHAZANFAR
 * @version 2023/05/06
 */
public class TalkCommand extends Command {
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
    Character vCharacter = pPlayer.getCurrentRoom().getCharacter(super.getSecondWord()); // get the NPC by its name
    if(vCharacter instanceof MovingCharacter) {
        JFrame vFrame = new JFrame("The Map");
    ImageIcon vIcon = new ImageIcon("./assets/map.png");
    JLabel vLabel = new JLabel(vIcon);
    vFrame.add(vLabel);
    vFrame.pack();
    vFrame.setVisible(true);
    }
    if(vCharacter == null) { // if the NPC isn't inside the Room
        pPlayer.getGUI().println("There is no such NPC inside this Room");
        return;
    }
    pPlayer.getGUI().println(vCharacter.getName() + " said : "+vCharacter.getDialog());
    
   
    
    } // execute(.)
} // GoCommand