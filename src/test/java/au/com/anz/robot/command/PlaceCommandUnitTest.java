package au.com.anz.robot.command;

import au.com.anz.robot.model.Board;
import au.com.anz.robot.model.Direction;
import au.com.anz.robot.model.Robot;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * User: agwibowo
 */
public class PlaceCommandUnitTest {

    private Robot robot;

    @Before
    public void setup() {
        robot = new Robot(new Board(5, 5));
        robot.setX(3);
        robot.setY(3);
        robot.setFacingDirection(Direction.NORTH);
    }

    @Test
    public void should_ignore_command_when_given_negative_x_coordinate() {
        new PlaceCommand(-1, 3, Direction.WEST).execute(robot);
        assertThat(robot.getX(), equalTo(3));
        assertThat(robot.getY(), equalTo(3));
        assertThat(robot.getFacingDirection(), equalTo(Direction.NORTH));
    }

    @Test
    public void should_ignore_command_when_given_x_coordinate_beyond_board_width() {
        new PlaceCommand(5, 3, Direction.WEST).execute(robot);
        assertThat(robot.getX(), equalTo(3));
        assertThat(robot.getY(), equalTo(3));
        assertThat(robot.getFacingDirection(), equalTo(Direction.NORTH));
    }


    @Test
    public void should_ignore_command_when_given_negative_y_coordinate() {
        new PlaceCommand(3, -1, Direction.WEST).execute(robot);
        assertThat(robot.getX(), equalTo(3));
        assertThat(robot.getY(), equalTo(3));
        assertThat(robot.getFacingDirection(), equalTo(Direction.NORTH));

    }

    @Test
    public void should_ignore_command_when_given_y_coordinate_beyond_board_height() {
        new PlaceCommand(3, 5, Direction.WEST).execute(robot);
        assertThat(robot.getX(), equalTo(3));
        assertThat(robot.getY(), equalTo(3));
        assertThat(robot.getFacingDirection(), equalTo(Direction.NORTH));
    }

    @Test
    public void given_valid_coordinate__THEN__should_set_robot_to_the_correct_facing_direction() {
        new PlaceCommand(1, 1, Direction.WEST).execute(robot);
        assertThat(robot.getFacingDirection(), equalTo(Direction.WEST));
    }

    @Test
    public void given_valid_coordinate__THEN__should_set_robot_coordinate_to_the_given_coordinate() {
        new PlaceCommand(1, 1, Direction.WEST).execute(robot);
        assertThat(robot.getX(), equalTo(1));
        assertThat(robot.getY(), equalTo(1));
    }

    @Test
    public void given_valid_coordinate__THEN__should_set_robot_status_to_be_onBoard() {
        assertFalse(robot.isOnBoard());
        new PlaceCommand(1, 1, Direction.WEST).execute(robot);
        assertTrue(robot.isOnBoard());
    }

}
