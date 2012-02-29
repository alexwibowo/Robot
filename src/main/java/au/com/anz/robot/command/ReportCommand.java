package au.com.anz.robot.command;

import au.com.anz.robot.model.Robot;
import org.apache.commons.lang.StringUtils;

/**
 * A command to print the current location of the robot
 * <p/>
 * User: agwibowo
 * Date: 28/02/12
 * Time: 9:56 PM
 */
public class ReportCommand extends AbstractCommand{
    public static final String COMMAND = "REPORT";

    /**
     * @param commandString command string
     * @return <code>true</code> if the command can be supported by this class,
     * <code>false</code> otherwise
     */
    public static boolean hasSupportFor(String commandString) {
        return StringUtils.equals(ReportCommand.COMMAND, commandString);
    }

    /**
     * Create instance of this class from the given string
     * <p/>
     * @param commandString command string
     * @return instance of {@link ReportCommand} command
     */
    public static ReportCommand createFromString(String commandString) {
        // there is no extra information contained in the REPORT command.
        return new ReportCommand();
    }

    @Override
    public void execute(Robot robot) {
        System.out.println(robot.toString());
    }
}
