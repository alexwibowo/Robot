package au.com.anz.robot.command;

/**
 * Abstraction of all exceptions that indicate invalid command had been given by the user
 * <p/>
 * User: agwibowo
 */
public abstract class InvalidCommandException extends Exception {

    public InvalidCommandException(String message) {
        super(message);
    }
}
