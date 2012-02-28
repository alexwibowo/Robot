package au.com.anz.robot.model;

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
 * Date: 28/02/12
 * Time: 10:31 PM
 */
@RunWith(Parameterized.class)
public class RobotTurnRightUnitTest {

    private Direction initialDirection;

    private Direction expectedDirection;

    private Robot robot;

    public RobotTurnRightUnitTest(Direction initialDirection, Direction expectedDirection) {
        this.initialDirection = initialDirection;
        this.expectedDirection = expectedDirection;
    }

    @Before
    public void setUp() throws Exception {
        robot = new Robot(new Board(5,5));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {Direction.NORTH, Direction.EAST },
                {Direction.WEST, Direction.NORTH },
                {Direction.SOUTH, Direction.WEST },
                {Direction.EAST, Direction.SOUTH },
        };
        return asList(data);
    }

    @Test
    public void turn_right_test() {
        robot.setFacingDirection(initialDirection);
        robot.turnRight();
        assertThat(
                "When facing " + initialDirection + " then turning RIGHT should cause robot to face " + expectedDirection,
                robot.getFacingDirection(),
                equalTo(expectedDirection)
        );
    }
}
