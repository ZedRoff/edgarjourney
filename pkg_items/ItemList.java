package pkg_items;
import java.util.HashMap;
import java.util.Set;
/**
 * ItemList Class - Holds all the inventory functionalities
 * @author Aman GHAZANFAR
 * @version 27.03.2023
 */
public class ItemList {
  private HashMap < String, Item > aList; // hold items
  /**
   * Constructor of ItemList Class, creates the HashMap that holds all the items of Player's inventory or Room's item
   */
  public ItemList() {
    this.aList = new HashMap < String, Item > ();
  } // ItemList()
  /**
   * @param pName Item's name
   * @return Item's corresponding object
   */
  public Item getItem(final String pName) {
    return this.aList.get(pName.toLowerCase());
  } // getItem(.)

  /**
   * @return All the items (name and price) of Player's inventory
   */
  public String getItemsString() {
    StringBuilder vParse = new StringBuilder();
    Set < String > vKeys = this.aList.keySet();

    for (String vItem: vKeys) {
      vParse.append("[" + vItem + "]" + "(" + this.getItem(vItem).getWeight() + ") | ");
    }
    if (vParse.length() == 0) return "No item here";
    return vParse.toString();
  } // getItemsString()

  /**
   * Adds the item to Player's inventory
   * @param pS The name of the item
   * @param pI Item's object
   */
  public void setItem(final String pS, final Item pI) {
    this.aList.put(pS.toLowerCase(), pI);
  } // setItem(.)
  /**
   * Removes the item from Player's inventory
   * @param pName The name of the item
   */
  public void removeItem(final String pName) {
    this.aList.remove(pName);
  } // removeItem(.)

  @Override public String toString() {
      StringBuilder vParse = new StringBuilder();
    Set < String > vKeys = this.aList.keySet();

    for (String vItem: vKeys) {
        vParse.append(vItem + ",");
    }
    return String.join(",",vKeys);
  }
} // ItemList