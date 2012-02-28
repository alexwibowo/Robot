package au.com.anz.robot.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static au.com.anz.robot.model.Direction.*;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: agwibowo
 * Date: 28/02/12
 * Time: 9:42 PM
 */
public class Robot {
    private static final Logger LOGGER = LoggerFactory.getLogger(Robot.class.getName());
    
    private Board board;

    private int x;

    private int y;

    private Direction facingDirection;

    public Robot(Board board) {
        x = 0;
        y = 0;
        facingDirection = Direction.NORTH;
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Direction facingDirection) {
        checkNotNull(facingDirection);
        this.facingDirection = facingDirection;
    }

    public void moveForward() {
        LOGGER.debug("Performing move forward");

        int newX = getX();
        
        int newY = getY();
        
        switch (facingDirection) {
            case NORTH:
                newY = getY() - 1;
                break;
            case EAST:
                newX = getX() + 1;
                break;
            case SOUTH:
                newY = getY() + 1;
                break;
            case WEST:
                newX = getX() - 1;
                break;
            default:
                throw new IllegalStateException("Robot is not facing anywhere!");
        }

        if (board.isValidPosition(newX, newY)) {
            LOGGER.debug("Robot is moving forward to new position [{},{}]", newX, newY);
            setX(newX);
            setY(newY);
        }else{
            LOGGER.debug("Robot is not moving forward, as new position is invalid [{},{}]",newX, newY);    
        }
    }

    /**
     * Turn the robot 90 degree to the left
     */
    public void turnLeft() {
        LOGGER.debug("Performing turn left");
        switch (facingDirection) {
            case NORTH:
                setFacingDirection(WEST);
                break;
            case WEST:
                setFacingDirection(SOUTH);
                break;
            case SOUTH:
                setFacingDirection(EAST);
                break;
            case EAST:
                setFacingDirection(NORTH);
                break;
            default:
                throw new IllegalStateException("Robot is not facing anywhere!");
        }
    }

    /**
     * Turn the robot 90 degree to the right
     */
    public void turnRight(){
        LOGGER.debug("Performing turn right");
        switch (facingDirection) {
            case NORTH:
                setFacingDirection(EAST);
                break;
            case EAST:
                setFacingDirection(SOUTH);
                break;
            case SOUTH:
                setFacingDirection(WEST);
                break;
            case WEST:
                setFacingDirection(NORTH);
                break;
            default:
                throw new IllegalStateException("Robot is not facing anywhere!");
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("x", x)
                .append("y", y)
                .append("facing", facingDirection)
                .toString();
    }
}
