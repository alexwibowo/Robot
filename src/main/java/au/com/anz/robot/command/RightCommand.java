package au.com.anz.robot.command;

import au.com.anz.robot.model.Robot;
import org.apache.commons.lang.StringUtils;

/**
 * A command to tell the robot to turn 90 degree to the right
 * <p/>
 * User: agwibowo
 * Date: 28/02/12
 * Time: 9:42 PM
 */
public class RightCommand extends AbstractCommand{
    public static final String COMMAND = "RIGHT";

    /**
     * @param commandString command string
     * @return <code>true</code> if the command can be supported by this class,
     * <code>false</code> otherwise
     */
    public static boolean hasSupportFor(String commandString) {
        return StringUtils.equals(RightCommand.COMMAND, commandString);
    }

    /**
     * Create instance of this class from the given string
     * <p/>
     * @param commandString command string
     * @return instance of {@link RightCommand} command
     */
    public static RightCommand createFromString(String commandString) {
        // there is no extra information contained in the RIGHT command.
        return new RightCommand();
    }

    /**
     * @see au.com.anz.robot.model.Robot#turnRight()
     */
    @Override
    public void execute(Robot robot) {
        robot.turnRight();
    }
}
