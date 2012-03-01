package au.com.anz.robot.command;

import au.com.anz.robot.model.Robot;

/**
 * Interface to all commands that can be executed on the robot
 * <p/>
 * User: agwibowo
 */
public interface Command {

    /**
     * Execute the command on the robot
     * <p/>
     * @param robot {@link Robot}
     * @throws InvalidCommandException when the command cannot be executed with the current robot's state
     */
    void execute(Robot robot)
            throws InvalidCommandException;
}
