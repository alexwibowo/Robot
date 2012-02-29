package au.com.anz.robot.command;

import au.com.anz.robot.model.Robot;

/**
 * A command to tell the robot to turn right
 * <p/>
 * User: agwibowo
 * Date: 28/02/12
 * Time: 9:42 PM
 */
public class RightCommand extends AbstractCommand{
    public static final String COMMAND = "RIGHT";

    /**
     * @see au.com.anz.robot.model.Robot#turnRight()
     */
    public void execute(Robot robot) {
        robot.turnRight();
    }
}
