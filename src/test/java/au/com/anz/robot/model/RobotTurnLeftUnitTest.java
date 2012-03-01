package au.com.anz.robot.model;

import au.com.anz.robot.command.InvalidRobotMovementException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * User: agwibowo
 */
@RunWith(Parameterized.class)
public class RobotTurnLeftUnitTest {

    private Direction initialDirection;

    private Direction expectedDirection;

    private Robot robot;

    public RobotTurnLeftUnitTest(Direction initialDirection, Direction expectedDirection) {
        this.initialDirection = initialDirection;
        this.expectedDirection = expectedDirection;
    }

    @Before
    public void setUp() throws Exception {
        robot = new Robot(new Board(5,5));
        robot.setOnBoard(true);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {Direction.NORTH, Direction.WEST },
                {Direction.WEST, Direction.SOUTH },
                {Direction.SOUTH, Direction.EAST },
                {Direction.EAST, Direction.NORTH },
        };
        return asList(data);
    }

    @Test
    public void turn_left_test() throws InvalidRobotMovementException {
        robot.setFacingDirection(initialDirection);
        robot.turnLeft();
        assertThat(
                "When facing " + initialDirection + " then turning LEFT should cause robot to face " + expectedDirection,
                robot.getFacingDirection(),
                equalTo(expectedDirection)
        );
    }
}
