package au.com.anz.robot.command;

import au.com.anz.robot.model.Robot;

/**
 * User: agwibowo
 * Date: 28/02/12
 * Time: 9:56 PM
 */
public class ReportCommand extends AbstractCommand{
    public static final String COMMAND = "REPORT";

    public void execute(Robot robot) {
        System.out.println(robot.toString());
    }
}
