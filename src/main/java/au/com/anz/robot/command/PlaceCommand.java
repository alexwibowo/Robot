package au.com.anz.robot.command;

import au.com.anz.robot.model.Direction;
import au.com.anz.robot.model.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: agwibowo
 * Date: 28/02/12
 * Time: 11:19 PM
 */
public class PlaceCommand extends AbstractCommand{
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceCommand.class.getName());

    private final int x;
    
    private final int y;
    
    private final Direction facingDirection;
    
    public PlaceCommand(Robot robot, int x, int y, Direction facingDirection) {
        super(robot);
        checkNotNull(facingDirection);
        this.x=x;
        this.y=y;
        this.facingDirection = facingDirection;
    }

    public void execute() {
        if (robot.getBoard().isValidPosition(x, y)) {
            robot.setX(x);
            robot.setY(y);
            robot.setFacingDirection(facingDirection);
        }else{
            LOGGER.warn("Coordinate [{},{}] is not valid with respect to the board. Ignoring the command", x, y);
        }
    }
}
