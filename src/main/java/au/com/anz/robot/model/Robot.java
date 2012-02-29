package au.com.anz.robot.model;

import au.com.anz.robot.command.InvalidRobotMovementException;
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

    /**
     * The board where the robot will be on
     */
    private Board board;

    private int x;

    private int y;

    private Direction facingDirection;

    private boolean onBoard;

    public Robot(Board board) {
        x = 0;
        y = 0;
        checkNotNull(board);
        onBoard = false;
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

    public boolean isOnBoard() {
        return onBoard;
    }

    public void setOnBoard(boolean onBoard) {
        this.onBoard = onBoard;
    }

    /**
     * Move the robot one unit forward, in the direction where it is currently facing ({@link #facingDirection}
     * If the new location will mean the robot falling off from the board {@link Board}, then the robot
     * will stay in the current location, still facing the same direction.
     */
    public void moveForward()
            throws InvalidRobotMovementException {
        LOGGER.debug("Performing move forward");

        validateRobotIsOnBoard();
        int newX = getX();
        int newY = getY();
        switch (facingDirection) {
            case NORTH:
                newY = getY() + 1;
                break;
            case EAST:
                newX = getX() + 1;
                break;
            case SOUTH:
                newY = getY() - 1;
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
            // new position is invalid - robot will fall off the table.
            LOGGER.debug("Robot is not moving forward, as new position is invalid [{},{}]",newX, newY);
        }
    }

    private void validateRobotIsOnBoard() throws InvalidRobotMovementException {
        if (!isOnBoard()) {
            throw new InvalidRobotMovementException("Robot is not on board");
        }
    }

    /**
     * Turn the robot 90 degree to the left. Robot will stay in the current location.
     */
    public void turnLeft() throws InvalidRobotMovementException {
        LOGGER.debug("Performing turn left");
        validateRobotIsOnBoard();
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
     * Turn the robot 90 degree to the right. Robot will stay in the current location.
     */
    public void turnRight() throws InvalidRobotMovementException {
        LOGGER.debug("Performing turn right");
        validateRobotIsOnBoard();
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
        if (isOnBoard()) {
            return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                    .append("x", x)
                    .append("y", y)
                    .append("facing", facingDirection)
                    .toString();
        }else {
            return "Robot is not on board yet";
        }
    }
}
