package au.com.anz.robot;

import au.com.anz.robot.command.Command;
import au.com.anz.robot.command.CommandParser;
import au.com.anz.robot.command.InvalidCommandException;
import au.com.anz.robot.model.Board;
import au.com.anz.robot.model.Robot;
import com.google.common.base.Preconditions;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintStream;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * User: agwibowo
 */
public class RobotSimulator {
    public static final Logger LOGGER = LoggerFactory.getLogger(RobotSimulator.class);

    private Robot robot;

    private Commander commandReader;

    private PrintStream printStream;

    public RobotSimulator(Commander commandReader, PrintStream printStream) {
        this.robot = new Robot(new Board(5, 5));
        this.robot.setOnBoard(false);  // initially robot is not on the table yet.
        this.commandReader = commandReader;
        this.printStream = printStream;
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
            Command command = new CommandParser(printStream).fromString(commandString);
            command.execute(robot);
        } catch (InvalidCommandException e) {
            LOGGER.debug("Invalid command [{}]", e.getMessage());
        }
    }

    public Robot getRobot() {
        return robot;
    }

    public static void main(String... args)
            throws IOException, ParseException {
        RobotSimulatorOptions options = new RobotSimulatorOptions();
        try {
            options.parseCommandLineArguments(args);
        } catch (ParseException e) {
            options.displayHelp();
            System.exit(1);
        }

        try {
            Commander commander = options.getCommander();
            RobotSimulator robotSimulator = new RobotSimulator(commander, System.out);
            robotSimulator.run();
        } catch (IOException e) {
            System.err.println("An error has occurred: " + e.getMessage());
            System.exit(1);
        }
    }


    /**
     * Helper class for {@link Options} in running the simulation
     */
    public static class RobotSimulatorOptions {
        /**
         * Options for running the simulator
         */
        private Options options;

        private CommandLine cli;

        public RobotSimulatorOptions() throws ParseException {
            options = createOptions();
        }

        private Options createOptions() {
            Options options = new Options();
            Option fileOption = OptionBuilder.withArgName("file")
                    .hasArg()
                    .withDescription("When this option is specified, the program will use the given file as input, instead of reading from standard input")
                    .create("file");
            options.addOption(fileOption);
            return options;
        }

        /**
         * @return <code>true</code> if the program is driven from input file, <code>false</code> otherwise
         */
        private boolean shouldReadFromInputFile() {
            checkNotNull(cli, "Command line argument has not been parsed");
            return cli.hasOption("file");
        }

        /**
         * @return input file name, should the program be driven from input file
         */
        private String getInputFilename() {
            checkNotNull(cli, "Command line argument has not been parsed");
            return cli.getOptionValue("file");
        }

        /**
         * Create {@link Commander} which will give commands to the robot
         * This method will return either 
         * <ul>
         *     <li>{@link InputFileCommander} if user has chosen to read input from file</li>
         *     <li>{@link InputStreamCommander} if user has chosen to read input from input stream (standard input)</li>
         * </ul>
         * <p/>
         * @return {@link Commander} instance, based on what user chooses
         * @throws IOException if user chooses to read command from file, and theres problem in reading the file, e.g.
         *         the file does not exist, or it is not readable
         * @see InputFileCommander
         * @see InputStreamCommander
         */
        public Commander getCommander()
                throws IOException {
            if (shouldReadFromInputFile()) {
                return new InputFileCommander(getInputFilename());
            }else{
                return new InputStreamCommander(System.in);
            }
        }

        /**
         * Parse the command line argument from the CLI
         * <p/>
         * @param args program arguments
         * @throws ParseException error in parsing the CLI arguments
         */
        public void parseCommandLineArguments(String... args) throws ParseException {
            CommandLineParser parser = new PosixParser();
            cli = parser.parse(options, args);
        }

        /**
         * Display help for the application
         */
        public void displayHelp() {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "Robot Simulator", options );
        }
    }
}
