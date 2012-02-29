package au.com.anz.robot.command;

import au.com.anz.robot.model.Robot;

/**
 * A command to tell the robot to turn left
 * <p/>
 * User: agwibowo
 * Date: 28/02/12
 * Time: 9:33 PM
 */
public class LeftCommand extends AbstractCommand {
    public static final String COMMAND = "LEFT";

    /**
     * @see au.com.anz.robot.model.Robot#turnLeft()
     */
    public void execute(Robot robot) {
        robot.turnLeft();
    }
}
