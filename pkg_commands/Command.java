 package pkg_commands;
 import pkg_gameobjects.Player;
/**
  * Command Class - Handles all the command side, a command is composed of two words (the command word and the argument word).
  *
  * @author Aman GHAZANFAR
  * @version 2023.02.09
  */
 public abstract class Command {
   // Attributes
   private String aSecondWord; // the second word of this command typed in
 private String aCommandWord;


   /**
    * @return A String with the argument word. (second word).
    */
   public String getSecondWord() {
     return this.aSecondWord;
   } // getSecondWord()
    public boolean isUnknown(){
        return this.getCommandWord() == null;
    }
   /**
    * @return The command word of the line user gave
    */
       public String getCommandWord(){
        return this.aCommandWord;
    } // getCommandWord()
   /**
    * @return A boolean : true if there is a second word. false : if there is no second word.
    */
   public boolean hasSecondWord() {
     return this.aSecondWord != null;
   } // hasSecondWord()
    /**
     * Define the second word of this command (the word
     * entered after the command word). Null indicates that 
     * there was no second word.
     * @param pSecondWord The second word of any Command
     */
    public void setSecondWord(String pSecondWord)
    {
        this.aSecondWord = pSecondWord;
    } // setSecondWord(.)
    /**
     * Sets the command word on the command (the word entered before the second word). 
     * Null indicates that there was no first word.
     * @param pCommandWord The first word of any command
     */
    public void setCommandWord(String pCommandWord) {
        this.aCommandWord = pCommandWord;
    } // setCommandWord(.)
  /**
   * Abstract method, has to be re-written inside every command, permits to avoid using all the switch inside interpretCommand, heritage 
   * @param pPlayer The Player Object (current Player so it can handle more than 1 player)
   */
    public abstract void execute(final Player pPlayer);
    //execute(.)
 
 } // Command