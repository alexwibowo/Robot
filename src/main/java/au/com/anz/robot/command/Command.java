package au.com.anz.robot.command;

import au.com.anz.robot.model.Robot;

/**
 * User: agwibowo
 * Date: 28/02/12
 * Time: 9:32 PM
 */
public interface Command {

    /**
     * Execute the command on the robot
     * <p/>
     * @param robot {@link Robot}
     */
    void execute(Robot robot);
}
