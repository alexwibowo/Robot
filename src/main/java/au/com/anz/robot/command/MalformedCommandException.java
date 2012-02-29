package au.com.anz.robot.command;

import static org.apache.commons.lang.StringUtils.trim;

/**
 * Exception to indicate that an unknown command had been issued.
 * <p/>
 * User: agwibowo
 * Date: 29/02/12
 * Time: 12:33 AM
 */
public class MalformedCommandException extends InvalidCommandException{

    private final String commandString;

    public MalformedCommandException(String commandString) {
        super(String.format("Unknown command has been given [%s]", commandString));
        this.commandString = trim(commandString);
    }

    /**
     * @return the invalid command
     */
    public String getCommandString() {
        return commandString;
    }
}
