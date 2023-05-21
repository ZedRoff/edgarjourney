package pkg_items;
/**
 * Item Class - Holds all the functionalities of a single item that is inside any Room
 * @author Aman GHAZANFAR
 * @version 2023.03.27
 */
public class Item {
  private String aName; // the name of the item
  private String aDescription; // his description
  private double aWeight; // and his weight
  /**
   * Item Class Constructor, instantiates the attributes of an item
   * @param pName The name of the item
   * @param pDescription The description of the item
   * @param pWeight The weight of the item
   */
  public Item(final String pName, final String pDescription, final double pWeight) {
    this.aName = pName;
    this.aDescription = pDescription;
    this.aWeight = pWeight;
  } // Item(.)
  /**
   * @return Item's name
   */
  public String getName() {
    return this.aName;
  } // getName()

  /**
   * @return Item's description
   */
  public String getDescription() {
    return this.aDescription;
  } // getDescription()
  /**
   * @return Item's weight
   */
  public double getWeight() {
    return this.aWeight;
  } // getWeight()

} // Item