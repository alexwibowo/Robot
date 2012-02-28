package au.com.anz.robot.command;

import static org.apache.commons.lang.StringUtils.trim;

/**
 * User: agwibowo
 * Date: 29/02/12
 * Time: 12:33 AM
 */
public class MalformedCommandException extends InvalidCommandException{

    private String commandString;

    public MalformedCommandException(String commandString) {
        super(String.format("Invalid command has been given [%s]", commandString));
        this.commandString = trim(commandString);
    }

    public String getCommandString() {
        return commandString;
    }
}
