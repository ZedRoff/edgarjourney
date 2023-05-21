package pkg_commands;
import pkg_gameobjects.Player;
/**
 * EatCommand Class - Permits player the eat for instance a cookie
 * @author Aman GHAZANFAR
 * @version 2023/05/06
 */
public class EatCommand extends Command {
    /**
     * Execute function, common to every commands, overrides the abstract method of Command Class.
     * @param pPlayer A Player object
     */
    @Override
    public void execute(Player pPlayer) {
       String[] vIngredients = {
      "cookie"
    };

    boolean verif = false;
    
    // checks if there is a second word
  
    if (!super.hasSecondWord()) {
       pPlayer.getGUI().println( "Eat what ?");
    } else {

        // checks if the choice of user is an actual ingrediant that is eatable
      StringBuilder parse = new StringBuilder();
      for (String ingredient: vIngredients) {
        if (ingredient.equals(super.getSecondWord())) {
          verif = true;

        }
        parse.append(" " + ingredient);

      }
      // if eatable
      if (verif) {
        if (pPlayer.hasItem("cookie")) { // if it's the magic cookie, and he has it
          pPlayer.setMaxWeight(pPlayer.getMaxWeight() + 10); // permit the player to carry on more weight
         pPlayer.getGUI().println("You ate a " + super.getSecondWord() + " you can now lift items up to " + pPlayer.getMaxWeight());

        } else { // if it's the magic cookie, but he doesn't have it
           pPlayer.getGUI().println("You don't have this item, but it exists");
        }

      } else { // if not
       pPlayer.getGUI().println("Wrong ingredient, you can only eat : " + parse.toString());
      }

    }
    } // execute(.)
} // EatCommand