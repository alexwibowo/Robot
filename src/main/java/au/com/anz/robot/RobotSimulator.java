package au.com.anz.robot;

import au.com.anz.robot.command.Command;
import au.com.anz.robot.command.InvalidCommandException;
import au.com.anz.robot.model.Board;
import au.com.anz.robot.model.Robot;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static au.com.anz.robot.command.CommandParser.fromString;
import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * User: agwibowo
 * Date: 28/02/12
 * Time: 9:51 PM
 */
public class RobotSimulator {
    public static final Logger LOGGER = LoggerFactory.getLogger(RobotSimulator.class);

    private Robot robot;

    private Commander commandReader;

    public RobotSimulator(Commander commandReader) {
        this.robot = new Robot(new Board(5, 5));
        this.robot.setOnBoard(false);  // initially robot is not on the table yet.
        this.commandReader = commandReader;
    }

    /**
     * Run the simulation
     */
    public void run() throws IOException {
        try {
            String commandString = null;
            while ((commandString = commandReader.getNextCommand()) != null) {
                if (isNotBlank(commandString)) {
                    doExecuteCommand(commandString);
                }
            }
        } finally {
            commandReader.cleanup();
        }
    }

    /**
     * Execute single command string on the robot
     * @param commandString command string
     */
    private void doExecuteCommand(String commandString) {
        try {
            Command command = fromString(commandString);
            command.execute(robot);
        } catch (InvalidCommandException e) {
            LOGGER.debug("Invalid command [{}]", e.getMessage());
        }
    }

    public Robot getRobot() {
        return robot;
    }

    public static void main(String... args) throws IOException, ParseException {
        Commander commander = getCommander(args);
        RobotSimulator robotSimulator = new RobotSimulator(commander);
        robotSimulator.run();
    }

    private static Commander getCommander(String[] args) throws IOException {
        Commander commandReader = null;
        Options options = new Options();
        Option fileOption = OptionBuilder.withArgName("file")
                .hasArg()
                .withDescription("When this option is specified, the program will use the given file as input, instead of reading from standard input")
                .create("file");

        options.addOption(fileOption);
        CommandLineParser parser = new PosixParser();
        try {
            CommandLine cli = parser.parse(options, args);
            if (cli.hasOption("file")) {
                commandReader = new InputFileCommander(cli.getOptionValue("file"));
            }else{
                commandReader = new SystemInCommander();
            }
            return commandReader;
        } catch (ParseException e) {
            displayHelp(options);
            System.exit(1);
            return null;
        }
    }

    private static void displayHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "Robot Simulator", options );
    }
}
