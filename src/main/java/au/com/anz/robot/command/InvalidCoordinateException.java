package au.com.anz.robot.command;

/**
 * Exception to indicate that the given coordinate is invalid and cannot be represented as
 * a coordinate in the board
 * <p/>
 * User: agwibowo
 */
public class InvalidCoordinateException extends InvalidCommandException {

    public InvalidCoordinateException(String coordinate) {
        super(String.format("Value [%s] cannot be parsed into a valid board coordinate.", coordinate));
    }
}
