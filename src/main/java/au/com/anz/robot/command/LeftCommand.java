package au.com.anz.robot.command;

import au.com.anz.robot.model.Robot;

/**
 * User: agwibowo
 * Date: 28/02/12
 * Time: 9:33 PM
 */
public class LeftCommand extends AbstractCommand {
    public static final String COMMAND = "LEFT";

    public LeftCommand(Robot robot) {
        super(robot);
    }

    public void execute() {
        robot.turnLeft();
    }
}
