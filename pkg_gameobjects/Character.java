package pkg_gameobjects;

/**
 * Character Class - Handles all the NPC of the Game
 * @author Aman GHAZANFAR 
 * @version 2023/05/21  
 */
public class Character {
    // Attributes
    
    private String aName; // NPC's name
    private String aDescription; // its description
    private String aDialog; // and the dialog he handles with the Player
    /**
     * Character Class Constructor, instantiates all the attributes
     * @param pName NPCs name
     * @param pDescription NPCs description
     * @param pDialog NPCs dialog
     */
    public Character(final String pName, final String pDescription, final String pDialog) {
        this.aName = pName;
        this.aDescription = pDescription;
        this.aDialog = pDialog;
    } // Character(.)
    /*
     * @return NPC's name
     */
    public String getName() {
        return aName;
    } // getName()
    /**
     * @return NPC's description (role)
     */
    public String getDescription() {
        return aDescription;
    } // getDescription()
    /**
     * @return NPC's dialog
     */
    public String getDialog() {
        return aDialog;
    } // getDialog()
} // Character(.)