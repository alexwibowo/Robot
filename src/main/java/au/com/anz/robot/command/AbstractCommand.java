package au.com.anz.robot.command;

import au.com.anz.robot.model.Robot;

/**
 * User: agwibowo
 * Date: 28/02/12
 * Time: 9:54 PM
 */
public abstract class AbstractCommand implements Command{

    protected Robot robot;

    public AbstractCommand(Robot robot) {
        this.robot = robot;
    }

    public Robot getRobot() {
        return robot;
    }
}
