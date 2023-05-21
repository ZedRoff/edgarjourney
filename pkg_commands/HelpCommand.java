package pkg_commands;
import pkg_gameobjects.Player;
/**
 * HelpCommand Class - Shows the help manual to the player
 */
public class HelpCommand extends Command {
   /**
     * Execute function, common to every commands, overrides the abstract method of Command Class.
     * @param pPlayer A Player object
     */
    @Override
    public void execute(Player pPlayer) {
         pPlayer.getGUI().println("How could you be lost inside/near your own house..., anyway, here is the help page\n.You command word are: ");
    pPlayer.getGUI().println(pPlayer.getGameEngine().getParser().getCommandString()); // the help page is here
    } // execute(.)
} // HelpCommand