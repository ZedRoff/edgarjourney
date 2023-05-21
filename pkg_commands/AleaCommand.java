package pkg_commands;
import pkg_gameobjects.Player;
import pkg_rooms.TransporterRoom;
/**
 * AleaCommand Class - Command only usable while in test mode, be sure to change the option in GameEngine before trying it.
 * Permits to alter the TransporterRoom exit (making it non-random, for testing purpose).
 * @author Aman GHAZANFAR
 * @version 2023/05/06
 */
public class AleaCommand extends Command {
     /**
     * Execute function, common to every commands, overrides the abstract method of Command Class.
     * @param pPlayer A Player object
     */
    @Override
    public void execute(Player pPlayer) {
       // gets the transporter Room
        TransporterRoom vTransporter = (TransporterRoom)pPlayer.getGameEngine().getRooms().get("transporter");
        
        if(pPlayer.getTestMode()) { // check if the player is in test mode
            if(!super.hasSecondWord()) { // check if there is a second word
                vTransporter.setAlea(null); // sets the transporterRoom to default value
              pPlayer.getGUI().println("The default mode is actived, now you are going to be teleported to a random room once exiting the transporter room"); // success message
            }else { // if there is a second word
                if(pPlayer.getGameEngine().getRooms().get(super.getSecondWord()) == null) { // if the input Room doesn't exists
                    pPlayer.getGUI().println("This room wasn't recognized by the system");
                    return;
                } // if it exists
                vTransporter.setAlea(super.getSecondWord()); // changes the random room to the given one
                pPlayer.getGUI().println("Ok, now you will be teleported to "+super.getSecondWord()+" once exiting the transporter room"); // success message
            }
        } else { // if the player isn't in test mode
            pPlayer.getGUI().println("You aren't in test mode");
        }
    } // execute(.)
} // AleaCommand
