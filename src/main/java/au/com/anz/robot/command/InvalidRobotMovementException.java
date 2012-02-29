package au.com.anz.robot.command;

/**
 * Exception to indicate that a movement cannot be applied to the robot. Generally
 * it is because the robot is not on the board yet.
 * <p/>
 * User: agwibowo
 * Date: 29/02/12
 * Time: 9:57 PM
 */
public class InvalidRobotMovementException extends InvalidCommandException{

    public InvalidRobotMovementException(String reason) {
        super(reason);
    }
}
