package au.com.anz.robot.command;

/**
 * Exception to indicate that an unknown direction had been issued.
 * <p/>
 * User: agwibowo
 * Date: 29/02/12
 * Time: 12:31 AM
 */
public class InvalidDirectionException extends InvalidCommandException {
    private final String direction;

    /**
     * @param direction the direction
     */
    public InvalidDirectionException(String direction) {
        super(String.format("Invalid direction has been given [%s]",direction));
        this.direction = direction;
    }

    /**
     * @return the invalid direction
     */
    public String getDirection() {
        return direction;
    }
}
