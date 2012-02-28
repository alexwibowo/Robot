package au.com.anz.robot.command;

/**
 * User: agwibowo
 * Date: 29/02/12
 * Time: 12:17 AM
 */
public abstract class InvalidCommandException extends Exception {

    public InvalidCommandException(String message) {
        super(message);
    }
}
