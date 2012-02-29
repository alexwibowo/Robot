package au.com.anz.robot.command;

/**
 * Abstraction of all exceptions that indicate invalid command had been given by the user
 * <p/>
 * User: agwibowo
 * Date: 29/02/12
 * Time: 12:17 AM
 */
public abstract class InvalidCommandException extends Exception {

    public InvalidCommandException(String message) {
        super(message);
    }
}
