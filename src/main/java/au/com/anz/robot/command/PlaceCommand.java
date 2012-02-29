package au.com.anz.robot.command;

import au.com.anz.robot.model.Direction;
import au.com.anz.robot.model.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static au.com.anz.robot.model.Direction.valueOf;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.Integer.parseInt;
import static java.util.regex.Pattern.compile;

/**
 * User: agwibowo
 * Date: 28/02/12
 * Time: 11:19 PM
 */
public class PlaceCommand extends AbstractCommand{
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceCommand.class.getName());
    
    public static final String COMMAND = "PLACE";
 
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

    public void execute(Robot robot) {
        if (robot.getBoard().isValidPosition(x, y)) {
            robot.setX(x);
            robot.setY(y);
            robot.setFacingDirection(facingDirection);
        }else{
            LOGGER.warn("Coordinate [{},{}] is not valid with respect to the board. Ignoring the command", x, y);
        }
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
     * Parse a command string as a <code>PLACE</code> command.
     * <p/>
     * @param commandString command string to be parsed
     * @return instance of {@link PlaceCommand} command object
     * @throws InvalidCommandException on failure to parse the command string
     */
    public static PlaceCommand parse(String commandString)
            throws InvalidCommandException {
        Pattern pattern = compile("^\\s*PLACE\\s+(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\w+)\\s*");
        
        Matcher matcher = pattern.matcher(commandString);
        
        if (matcher.find() 
                && matcher.hitEnd()) { // we dont want any extras. We want to be strict.
            int x = parseInt(matcher.group(1));
            int y = parseInt(matcher.group(2));
            String direction = matcher.group(3);
            return new PlaceCommand(x, y, parseDirection(direction));
        }else {
            throw new MalformedCommandException(commandString);
        }
    }

    /**
     * Parse the given string as a {@link Direction}
     * <p/>
     * @param directionString direction in string. Must correspond to {@link Direction}
     * @return instance of {@link Direction}
     * @see Direction
     * @throws InvalidDirectionException on failure to parse the string as direction
     */
    private static Direction parseDirection(String directionString)
            throws InvalidDirectionException {
        try {
            return valueOf(directionString);
        } catch (IllegalArgumentException e) {
            throw new InvalidDirectionException(directionString);
        }
    }

}
