package au.com.anz.robot.command;

import org.apache.commons.lang.StringUtils;
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

    public static Command parse(String commandString)
            throws InvalidCommandException {
        String trimmedCommand = trim(commandString);
        if (StringUtils.equals(LeftCommand.COMMAND, trimmedCommand)) {
            return new LeftCommand();
        } else if (StringUtils.equals(RightCommand.COMMAND, trimmedCommand)) {
            return new RightCommand();
        } else if (StringUtils.equals(MoveCommand.COMMAND, trimmedCommand)) {
            return new MoveCommand();
        } else if (StringUtils.equals(ReportCommand.COMMAND, trimmedCommand)) {
            return new ReportCommand();
        } else if (StringUtils.startsWith(trimmedCommand, PlaceCommand.COMMAND)) {
            return PlaceCommand.parse(commandString);
        } else{
            LOGGER.warn("Unknown command had been given [{}]", commandString);
            throw new UnsupportedOperationException(commandString);
        }
    }


}
