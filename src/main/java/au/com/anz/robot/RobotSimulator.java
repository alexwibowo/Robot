package au.com.anz.robot;

import au.com.anz.robot.command.Command;
import au.com.anz.robot.command.InvalidCommandException;
import au.com.anz.robot.model.Board;
import au.com.anz.robot.model.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static au.com.anz.robot.command.CommandParser.parse;

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
        this.commandReader = commandReader;
    }

    public void run() throws IOException {
        try {
            String commandString = null;
            while ((commandString = commandReader.getNextCommand()) != null) {
                doExecuteCommand(commandString);
            }
        } finally {
            commandReader.cleanup();
        }
    }

    private void doExecuteCommand(String commandString) {
        try {
            Command command = parse(commandString);
            command.execute(robot);
        } catch (InvalidCommandException e) {
            LOGGER.debug("Invalid command [{}]", e.getMessage());
        }
    }

    public Robot getRobot() {
        return robot;
    }

    public static void main(String... args) throws IOException {
        Commander commandReader = new SystemInCommander();
        RobotSimulator robotSimulator = new RobotSimulator(commandReader);
        robotSimulator.run();
    }
}
