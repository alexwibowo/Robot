package au.com.anz.robot.command;

import au.com.anz.robot.model.Robot;

/**
 * User: agwibowo
 * Date: 28/02/12
 * Time: 9:42 PM
 */
public class RightCommand extends AbstractCommand{
    public static final String COMMAND = "RIGHT";


    public RightCommand(Robot robot) {
        super(robot);
    }

    public void execute() {
        robot.turnRight();
    }
}
