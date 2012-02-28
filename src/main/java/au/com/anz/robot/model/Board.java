package au.com.anz.robot.model;

/**
 * User: agwibowo
 * Date: 28/02/12
 * Time: 9:48 PM
 */
public class Board {
    private final int width;

    private final int height;

    /**
     * Construct board with the given width & height
     * <p/>
     * @param width board width
     * @param height board height
     */
    public Board(int width, int height) {
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
        return x >= 0 && x < width && y >= 0 && y < height;
    }


}
