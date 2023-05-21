package pkg_gameobjects;
import pkg_items.Item;

/**
 * Door Class - Creates doors, so we can lock them
 * @author Aman GHAZANFAR
 * @version 2023.04.02
 */
 public class Door {
    // Attributes
    private Item aKey; // The key that is necessary to open the Door
    private boolean aState; // says whether the door is opened or not
    /**
     * Constructor, instantiates the state of the door (whether it's locked or not).
     */
    public Door() {
        this.aState = false;
    } // Door()
    /**
     * Handles the lock of a Door
     * @param pKey The Key item in order to open the door
     */
    public void lock(final Item pKey) {
        this.aState = true;
        this.aKey = pKey;
    } // lock(.)
    /**
     * @return A boolean that contains the state of the Door (opened or closed)
     */
    public boolean isLocked() {
        return this.aState;
    } // isLocked()
    /**
     * @return The Key that is required to open the Door
     */
    public Item getKey() {
        return this.aKey;
    } // getKey()
} // Door