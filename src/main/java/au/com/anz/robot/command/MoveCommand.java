package au.com.anz.robot.command;

import au.com.anz.robot.model.Robot;

/**
 * MOVE will move the toy robot one unit forward in the direction
 * it is currently facing.
 * <p/>
 * User: agwibowo
 * Date: 28/02/12
 * Time: 9:33 PM
 */
public class MoveCommand extends AbstractCommand {
    public static final String COMMAND = "MOVE";

    public MoveCommand(Robot robot) {
        super(robot);
    }

    public void execute() {
        robot.moveForward();
    }
}
