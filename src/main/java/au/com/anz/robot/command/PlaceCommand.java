package au.com.anz.robot.command;

import au.com.anz.robot.model.Direction;
import au.com.anz.robot.model.Robot;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static au.com.anz.robot.model.Direction.valueOf;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.Integer.parseInt;
import static java.util.regex.Pattern.compile;

/**
 * A command to place the robot on the table, and move it to a given coordinate, facing a given direction.
 * The command must be in the following pattern:
 *
 * <pre>
 *      <PLACE_TOKEN><WHITESPACE>+<COORDINATE><WHITESPACE>+
 *
 * where:
 *  1) <PLACE_TOKEN> is the "PLACE" keyword
 *  2) <WHITESPACE> is one or more whitespace character
 *  3) <COORDINATE> is in the form of
 *
 *      <DIGIT><WHITESPACE>*,<WHITESPACE>*<DIGIT><WHITESPACE>*,<DIRECTION>
 *  where
 *  1) DIGIT is [0-9]+
 *  2) DIRECTION is [NORTH|SOUTH|EAST|WEST]
 *
 * </pre>
 * <p/>
 *
 * Example
 * <pre>
 *     PLACE 0,0,NORTH
 *     PLACE 0,1,EAST
 * </pre>
 * <p/>
 * User: agwibowo
 */
public class PlaceCommand implements Command{
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceCommand.class.getName());

    public static final String COMMAND = "PLACE";
    
    private static final Pattern SUPPORTED_PATTERN = compile("^\\s*PLACE\\s+(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\w+)\\s*");

    private final int x;

    private final int y;

    private final Direction facingDirection;

    /**
     * @param x x-coordinate where the robot will be placed
     * @param y y-coordinate where the robot will be placed
     * @param facingDirection direction where the robot will face
     */
    public PlaceCommand(int x, int y, Direction facingDirection) {
        checkNotNull(facingDirection);
        this.x=x;
        this.y=y;
        this.facingDirection = facingDirection;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    /**
     * @param commandString command string
     * @return <code>true</code> if the command can be supported by this class,
     * <code>false</code> otherwise
     */
    public static boolean hasSupportFor(String commandString) {
        return StringUtils.startsWith(commandString, PlaceCommand.COMMAND);
    }

    public void execute(Robot robot) {
        if (robot.getBoard().isValidPosition(x, y)) {
            robot.setX(x);
            robot.setY(y);
            robot.setFacingDirection(facingDirection);
            robot.setOnBoard(true);
        }else{
            LOGGER.warn("Coordinate [{},{}] is not valid with respect to the board. Ignoring the command", x, y);
        }
    }

    /**
     * Parse a command string as a <code>PLACE</code> command.
     * <p/>
     * @param commandString command string to be parsed
     * @return instance of {@link PlaceCommand} command object
     * @throws InvalidCommandException on failure to createFromString the command string
     */
    public static PlaceCommand createFromString(String commandString)
            throws InvalidCommandException {
        Matcher matcher = SUPPORTED_PATTERN.matcher(commandString);

        if (matcher.find()
                && matcher.hitEnd()) { // we dont want any extras. We want to be strict.
            int x = parseCoordinate(matcher.group(1));
            int y = parseCoordinate(matcher.group(2));
            String direction = matcher.group(3);
            return new PlaceCommand(x, y, Direction.parseSafely(direction));
        }else {
            throw new MalformedCommandException(commandString);
        }
    }

    private static int parseCoordinate(String coordinate)
            throws InvalidCoordinateException {
        try {
            return Integer.parseInt(coordinate);
        } catch (NumberFormatException e) {
            throw new InvalidCoordinateException(coordinate);
        }
    }
}
