import pkg_utils.UserInterface;
import pkg_gameobjects.GameEngine;
import pkg_gameobjects.Player;


/**
 * Game Class - Shows the UserInterface (holds the game)
 * @author Aman GHAZANFAR
 * @version 2023.03.23
 */
public class Game  {
    
  // Attributes
  
  private UserInterface aGui;
  private GameEngine aEngine;

  /**
   * Create the game and initialise its internal map. Create the inerface and link to it.
   */
  public Game() {
    
      // Take the name of the user with a context dialog
      
    String vPrenom = "";
    while(vPrenom.length() == 0) {
        vPrenom = javax.swing.JOptionPane.showInputDialog("What's your nickname ?");
    }
   
    // Setup Engine
    
    this.aEngine = new GameEngine(vPrenom); // transfer of the name to the engine
    this.aGui = new UserInterface(this.aEngine);
    this.aEngine.setGUI(this.aGui);
  } // Game
}