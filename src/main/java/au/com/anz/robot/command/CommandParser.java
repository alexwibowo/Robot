package au.com.anz.robot.command;

import au.com.anz.robot.model.Robot;
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

    public static Command parse(Robot robot, String commandString)
            throws InvalidCommandException {
        String trimmedCommand = trim(commandString);
        if (StringUtils.equals(LeftCommand.COMMAND, trimmedCommand)) {
            return new LeftCommand(robot);
        } else if (StringUtils.equals(RightCommand.COMMAND, trimmedCommand)) {
            return new RightCommand(robot);
        } else if (StringUtils.equals(MoveCommand.COMMAND, trimmedCommand)) {
            return new MoveCommand(robot);
        } else if (StringUtils.equals(ReportCommand.COMMAND, trimmedCommand)) {
            return new ReportCommand(robot);
        } else if (StringUtils.startsWith(trimmedCommand, PlaceCommand.COMMAND)) {
            return PlaceCommand.parse(robot, commandString);
        } else{
            throw new UnsupportedOperationException(commandString);
        }
    }


}
