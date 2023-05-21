package pkg_commands;
import pkg_commands.GoCommand;

/**
 * Representations for all the valid command words for the game.
 * @author GHAZANFAR Aman
 * @version 2023.04.05
 */
public enum CommandWord
{
    // A value for each command word, with a function corresponding, plus one for unrecognised
    // commands.
    GO("go", new GoCommand()),
    QUIT("quit", new QuitCommand()),
    HELP("help", new HelpCommand()),
    UNKNOWN("?", new UnknownCommand()),
    TAKE("take", new TakeCommand()),
    DROP("drop", new DropCommand()),
    INVENTORY("inventory", new InventoryCommand()),
    LOOK("look", new LookCommand()),
    EAT("eat", new EatCommand()),
    BACK("back", new BackCommand()),
    TEST("test", new TestCommand()),
    FIRE("fire", new FireCommand()),
    CHARGE("charge", new ChargeCommand()),
    ALEA("alea", new AleaCommand()),
    TALK("talk", new TalkCommand()),
    SAVE("save", new SaveCommand()),
    LOAD("load", new LoadCommand()),
    INTERACT("interact", new InteractCommand());

private String commandString;
private Command aCommand;
    
    /**
     * Initialise with the corresponding command word.
     * @param commandString The command string.
     * @param pCommand The Command Object including the Command to make it correspond to the CommandWord ENUMS
     */
    CommandWord(String commandString, final Command pCommand)
    {
        this.commandString = commandString;
        this.aCommand = pCommand;
    } // CommandWord(.)
    
    /**
     * @return The command word as a string.
     */
    @Override public String toString()
    {
        return commandString;
    } // toString(.)
    /**
     * @return The Class pointer to the command
     */
    public Command toClass(){
        return this.aCommand;
    } // toClass()
} // CommandWord