package au.com.anz.robot.command;

import au.com.anz.robot.model.Robot;
import org.apache.commons.lang.StringUtils;

import java.io.PrintStream;

/**
 * A command to print the current location of the robot
 * <p/>
 * User: agwibowo
 */
public class ReportCommand implements Command{
    public static final String COMMAND = "REPORT";

    private PrintStream printStream;

    /**
     * @param printStream printstream to write output to
     */
    public ReportCommand(PrintStream printStream) {
        this.printStream = printStream;
    }

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
    public static ReportCommand createFromString(PrintStream printStream, String commandString) {
        // there is no extra information contained in the REPORT command.
        return new ReportCommand(printStream);
    }

    public void execute(Robot robot) {
        printStream.println(robot.toString());
    }
}
