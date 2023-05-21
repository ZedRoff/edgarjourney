package pkg_utils;

import pkg_gameobjects.GameEngine;

 import javax.swing.JFrame;
 import javax.swing.JTextField;
 import javax.swing.JTextArea;
 import javax.swing.JLabel;
 import javax.swing.ImageIcon;
 import javax.swing.JPanel;
 import javax.swing.JScrollPane;
 import javax.swing.JButton;
 import java.awt.Dimension;
 import java.awt.BorderLayout;
 import java.awt.GridLayout;
 import java.awt.event.WindowAdapter;
 import java.awt.event.ActionListener;
 import java.awt.event.WindowEvent;
 import java.awt.event.ActionEvent;
 import java.net.URL;
 import javax.swing.Icon;
 import java.io.File;
 import javax.swing.Icon;
 
 /**
  * This class implements a simple graphical user interface with a text entry
  * area, a text output area and an optional image.
  * 
  * @author Michael Kolling + Denis Bureau + Aman GHAZANFAR
  
  * @version 2023.04.05
  */
 public class UserInterface implements ActionListener {
     // Attributes
   private GameEngine aEngine;
   private JFrame aMyFrame; // Handles the Frame generating methods
   private JTextField aEntryField; // Handles the user input methods
   private JTextArea aLog; // Handles the area the user can input text
   private JLabel aImage; // Handles the images of the game
   private JButton aButtonLook; // the look button that triggers the look command
   private JButton aButtonQuit; // the quit one
   private JButton aButtonEast; // to go to the east neighbor room
   private JButton aButtonWest; // to go to the west one
   private JButton aButtonNorth; // to go to the north one
   private JButton aButtonSouth; // to go to the south one
   private JButton aButtonEat; // to trigger the eat command
   private JButton aButtonBack; // triggers the back one
   private JButton aButtonInventory; // the inventory one
   public final static int ONE_SECOND = 1000; // a constant that sets 1 second to ms

   /**
    * Construct a UserInterface. As a parameter, a Game Engine
    * (an object processing and executing the game commands) is
    * needed.
    * 
    * @param pGameEngine  The GameEngine object implementing the game logic.
    */
   public UserInterface(final GameEngine pGameEngine) {
     this.aEngine = pGameEngine;
     this.createGUI();

    
   } // UserInterface(.)

   /**
    * Print out some text into the text area.
    * @param pText The text we want to print out
    */
   public void print(final String pText) {
     this.aLog.append(pText);
     this.aLog.setCaretPosition(this.aLog.getDocument().getLength());
   } // print(.)
public void removeAllText() {
    
    this.aLog.selectAll();
    this.aLog.replaceSelection("");
}
   /**
    * Print out some text into the text area, followed by a line break.
    * @param pText The text we want to print out
    */
   public void println(final String pText) {
     
     this.print(pText + "\n");
   } // println(.)

   /**
    * Show an image file in the interface.
    * @param pImageName The name of the image, its path
    */
   public void showImage(final String pImageName) {
     String vImagePath = "" + pImageName; // to change the directory
     URL vImageURL = this.getClass().getClassLoader().getResource(vImagePath);
     if (vImageURL == null)
       System.out.println("Image not found : " + vImagePath);
     else {
       ImageIcon vIcon = new ImageIcon(vImageURL);
       this.aImage.setIcon(vIcon);
       this.aMyFrame.pack();
     }
   } // showImage(.)

   /**
    * Enable or disable input in the input field.
    * @param pOnOff The boolean that states whether the game is still going on or not
    */
   public void enable(final boolean pOnOff) {
     this.aEntryField.setEditable(pOnOff); // enable/disable
     if (!pOnOff) { // disable
       this.aEntryField.getCaret().setBlinkRate(0); // cursor won't blink
       this.aEntryField.removeActionListener(this); // won't react to entry
     }
   } // enable(.)

   /**
    * Set up graphical user interface.
    */
   private void createGUI() {
    
    // Button setup
       
     this.aButtonLook = new JButton(new ImageIcon("./assets/loupe.png"));
     this.aButtonQuit = new JButton(new ImageIcon("./assets/quit.png"));

     this.aButtonEast = new JButton(new ImageIcon("./assets/square.png"));
     this.aButtonWest = new JButton(new ImageIcon("./assets/square.png"));
     this.aButtonSouth = new JButton(new ImageIcon("./assets/square.png"));
     this.aButtonNorth = new JButton(new ImageIcon("./assets/square.png"));

     this.aButtonEat = new JButton(new ImageIcon("./assets/eat.png"));
     this.aButtonInventory = new JButton(new ImageIcon("./assets/inventory.png"));
     this.aButtonBack = new JButton(new ImageIcon("./assets/back.png"));

     // end button setup
     
     this.aImage = new JLabel(); // image declaration (the main image of the game)
     this.aMyFrame = new JFrame("Edgar's Journey");
     this.aEntryField = new JTextField(34);

     this.aLog = new JTextArea();
    
     this.aLog.setEditable(false);
     JScrollPane vListScroller = new JScrollPane(this.aLog);
     vListScroller.setPreferredSize(new Dimension(200, 200));
     vListScroller.setMinimumSize(new Dimension(100, 100));
    
     // Placing all the buttons inside a Panel
    
     JPanel vNewPanel = new JPanel();
     vNewPanel.setLayout(new GridLayout(3, 3));
     vNewPanel.add(this.aButtonEat);
     vNewPanel.add(this.aButtonNorth);
     vNewPanel.add(this.aButtonLook);
     vNewPanel.add(this.aButtonWest);
     vNewPanel.add(this.aButtonBack);
     vNewPanel.add(this.aButtonEast);
     vNewPanel.add(this.aButtonInventory);
     vNewPanel.add(this.aButtonSouth);
     vNewPanel.add(this.aButtonQuit);
     
     
     // Placing all the layout into another panel
     
     JPanel vPanel = new JPanel();
     vPanel.setLayout(new BorderLayout()); 
     vPanel.add(this.aImage, BorderLayout.NORTH);
     vPanel.add(vListScroller, BorderLayout.CENTER);
     vPanel.add(this.aEntryField, BorderLayout.SOUTH);
     vPanel.add(vNewPanel, BorderLayout.EAST);
     this.aMyFrame.getContentPane().add(vPanel, BorderLayout.CENTER);
     
// stores the button list
     JButton[] vButtons = {
       this.aButtonLook,
       this.aButtonQuit,
       this.aButtonWest,
       this.aButtonNorth,
       this.aButtonSouth,
       this.aButtonEast,
       this.aButtonEat,
       this.aButtonInventory,
       this.aButtonBack
     }; 
     
     // add some event listeners to some components
     
     for (JButton vB: vButtons) {
       vB.addActionListener(this);
     }
    
     this.aEntryField.addActionListener(this);

     // to end program when window is closed
     this.aMyFrame.addWindowListener(new WindowAdapter() {
       public void windowClosing(WindowEvent e) {
         System.exit(0);
       }
     });
     
    // shows the window
     this.aMyFrame.pack();
     this.aMyFrame.setVisible(true);
     this.aEntryField.requestFocus();
   } // createGUI()

   /**
    * Actionlistener interface for entry textfield.
    */
   public void actionPerformed(final ActionEvent pE) {
     if (pE.getSource() == this.aButtonLook) {
       this.aEngine.interpretCommand("look");
     } else if (pE.getSource() == this.aButtonQuit) {

       this.aEngine.interpretCommand("quit");
     } else if (pE.getSource() == this.aButtonNorth) {
       this.aEngine.interpretCommand("go north");
     } else if (pE.getSource() == this.aButtonEast) {
       this.aEngine.interpretCommand("go east");
     } else if (pE.getSource() == this.aButtonWest) {
       this.aEngine.interpretCommand("go west");
     } else if (pE.getSource() == this.aButtonSouth) {
       this.aEngine.interpretCommand("go south");
     } else if (pE.getSource() == this.aButtonInventory) {
       this.aEngine.interpretCommand("inventory");
     } else if (pE.getSource() == this.aButtonBack) {
       this.aEngine.interpretCommand("back");
     } else if (pE.getSource() == this.aButtonEat) {
       this.aEngine.interpretCommand("eat cookie");
     } else {
       this.processCommand();
     }

   } // actionPerformed(.)

   /**
    * @return The actual Frame of the Game (used for quit command only)
    */
   public JFrame getFrame() {
     return this.aMyFrame;
   } // getFrame()
   /**
    * A command has been entered. Read the command and do whatever is 
    * necessary to process it.
    */
   private void processCommand() {
     String vInput = this.aEntryField.getText();
     this.aEntryField.setText("");

     this.aEngine.interpretCommand(vInput);
   } // processCommand()
 } // UserInterface 