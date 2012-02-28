package au.com.anz.robot.command;

/**
 * User: agwibowo
 * Date: 29/02/12
 * Time: 12:31 AM
 */
public class InvalidDirectionException extends InvalidCommandException {
    private String direction;

    public InvalidDirectionException(String direction) {
        super(String.format("Invalid direction has been given [%s]",direction));
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
