package au.com.anz.robot.model;

import au.com.anz.robot.command.InvalidDirectionException;

/**
 * User: agwibowo
 */
public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;


    /**
     * Parse the given string as a {@link Direction}
     * <p/>
     * @param directionString direction in string. Must correspond to {@link Direction}
     * @return instance of {@link Direction}
     * @see Direction
     * @throws au.com.anz.robot.command.InvalidDirectionException on failure to createFromString the string as direction
     */
    public static Direction parseSafely(String directionString)
            throws InvalidDirectionException {
        try {
            return valueOf(directionString);
        } catch (IllegalArgumentException e) {
            throw new InvalidDirectionException(directionString);
        }
    }

}
