package au.com.anz.robot.model;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Representation of the board where the robot will be roam around.
 * <p/>
 * User: agwibowo
 */
public class Board {
    /**
     * Board width
     */
    private final int width;

    /**
     * Board height
     */
    private final int height;

    /**
     * Construct board with the given width & height
     * <p/>
     * @param width board width
     * @param height board height
     */
    public Board(int width, int height) {
        checkArgument(width > 0, "Width must be greater than zero");
        checkArgument(height > 0, "Height must be greater than zero");
        this.width = width;
        this.height = height;
    }

    /**
     * @param x x position, zero based
     * @param y y position, zero based
     * @return <code>true</code> if the given coordinate is a valid coordinate with respect to the
     * current board, <code>false</code> otherwise
     */
    public boolean isValidPosition(int x, int y) {
        return isValidXCoordinate(x) && isValidYCoordinate(y);
    }

    private boolean isValidYCoordinate(int y) {
        return y >= 0 && y < height;
    }

    private boolean isValidXCoordinate(int x) {
        return x >= 0 && x < width;
    }

}
