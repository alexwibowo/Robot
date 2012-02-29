package au.com.anz.robot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.commons.lang.StringUtils.trim;

/**
 * User: agwibowo
 * Date: 28/02/12
 * Time: 9:50 PM
 */
public class CommandParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandParser.class.getName());

    private CommandParser(){}

    /**
     * Parse a string as a {@link Command}
     * <p/>
     * @param commandString string representation of the command
     * @return {@link Command} object that represents the passed in string
     * @throws InvalidCommandException
     */
    public static Command parse(String commandString)
            throws InvalidCommandException {
        String trimmedCommand = trim(commandString);

        if (LeftCommand.hasSupportFor(trimmedCommand)) {
            return LeftCommand.createFromString(trimmedCommand);
            
        } else if (RightCommand.hasSupportFor(trimmedCommand)) {
            return RightCommand.createFromString(trimmedCommand);
            
        } else if (MoveCommand.hasSupportFor(trimmedCommand)) {
            return MoveCommand.createFromString(trimmedCommand);
            
        } else if (ReportCommand.hasSupportFor(trimmedCommand)) {
            return ReportCommand.createFromString(trimmedCommand);
            
        } else if (PlaceCommand.hasSupportFor(trimmedCommand)) {
            return PlaceCommand.createFromString(trimmedCommand);
            
        } else{
            LOGGER.warn("Unknown command had been given [{}]", commandString);
            throw new MalformedCommandException(commandString);
        }
    }


}
