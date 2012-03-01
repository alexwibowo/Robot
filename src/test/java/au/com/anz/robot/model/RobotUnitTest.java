package au.com.anz.robot.model;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * User: agwibowo
 */
public class RobotUnitTest {
    
    @Test
    public void should_print_robot_location_if_on_board() {
        Robot robot = new Robot(new Board(5, 5));
        robot.setOnBoard(true);
        robot.setX(1);
        robot.setY(2);
        robot.setFacingDirection(Direction.EAST);

        assertThat(robot.toString(), equalTo("1,2,EAST"));
    }

    @Test
    public void should_indicate_if_robot_is_not_on_board() {
        Robot robot = new Robot(new Board(5, 5));
        robot.setOnBoard(false);

        assertThat(robot.toString(), equalTo("Robot is not on board yet"));
    }
}
