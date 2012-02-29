package au.com.anz.robot.command;

import au.com.anz.robot.model.Robot;
import org.apache.commons.lang.StringUtils;

/**
 * A command to tell the robot to move one unit forward in the direction it is currently facing.
 * <p/>
 * User: agwibowo
 * Date: 28/02/12
 * Time: 9:33 PM
 */
public class MoveCommand extends AbstractCommand {
    public static final String COMMAND = "MOVE";

    /**
     * @param commandString command string
     * @return <code>true</code> if the command can be supported by this class,
     * <code>false</code> otherwise
     */
    public static boolean hasSupportFor(String commandString) {
        return StringUtils.equals(MoveCommand.COMMAND, commandString);
    }

    /**
     * Create instance of this class from the given string
     * <p/>
     * @param commandString command string
     * @return instance of {@link MoveCommand} command
     */
    public static MoveCommand createFromString(String commandString) {
        // there is no extra information contained in the MOVE command.
        return new MoveCommand();
    }

    @Override
    public void execute(Robot robot) {
        robot.moveForward();
    }
}
