package au.com.anz.robot.command;

import au.com.anz.robot.model.Robot;
import org.apache.commons.lang.StringUtils;

/**
 * A command to tell the robot to turn 90 degrees to the left
 * <p/>
 * User: agwibowo
 */
public class LeftCommand implements Command {
    public static final String COMMAND = "LEFT";

    /**
     * @param commandString command string
     * @return <code>true</code> if the command can be supported by this class,
     * <code>false</code> otherwise
     */
    public static boolean hasSupportFor(String commandString) {
        return StringUtils.equals(LeftCommand.COMMAND, commandString);
    }

    /**
     * Create instance of this class from the given string
     * <p/>
     * @param commandString command string
     * @return instance of {@link LeftCommand} command
     */
    public static LeftCommand createFromString(String commandString) {
        // there is no extra information contained in the LEFT command.
        return new LeftCommand();
    }

    /**
     * @see au.com.anz.robot.model.Robot#turnLeft()
     */
    public void execute(Robot robot) throws InvalidRobotMovementException {
        robot.turnLeft();
    }
}
