package au.com.anz.robot.command;

import au.com.anz.robot.model.Board;
import au.com.anz.robot.model.Direction;
import au.com.anz.robot.model.Robot;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * User: agwibowo
 * Date: 28/02/12
 * Time: 11:25 PM
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
        new PlaceCommand(robot, -1, 3, Direction.WEST).execute();
        assertThat(robot.getX(), equalTo(3));
        assertThat(robot.getY(), equalTo(3));
        assertThat(robot.getFacingDirection(), equalTo(Direction.NORTH));
    }

    @Test
    public void should_ignore_command_when_given_x_coordinate_beyond_board_width() {
        new PlaceCommand(robot, 5, 3, Direction.WEST).execute();
        assertThat(robot.getX(), equalTo(3));
        assertThat(robot.getY(), equalTo(3));
        assertThat(robot.getFacingDirection(), equalTo(Direction.NORTH));
    }


    @Test
    public void should_ignore_command_when_given_negative_y_coordinate() {
        new PlaceCommand(robot, 3, -1, Direction.WEST).execute();
        assertThat(robot.getX(), equalTo(3));
        assertThat(robot.getY(), equalTo(3));
        assertThat(robot.getFacingDirection(), equalTo(Direction.NORTH));

    }

    @Test
    public void should_ignore_command_when_given_y_coordinate_beyond_board_height() {
        new PlaceCommand(robot, 3, 5, Direction.WEST).execute();
        assertThat(robot.getX(), equalTo(3));
        assertThat(robot.getY(), equalTo(3));
        assertThat(robot.getFacingDirection(), equalTo(Direction.NORTH));
    }

    @Test
    public void given_valid_coordinate__THEN__should_set_robot_to_the_correct_facing_direction() {
        new PlaceCommand(robot, 1, 1, Direction.WEST).execute();
        assertThat(robot.getFacingDirection(), equalTo(Direction.WEST));
    }

    @Test
    public void given_valid_coordinate__THEN__should_set_robot_coordinate_to_the_given_coordinate() {
        new PlaceCommand(robot, 1, 1, Direction.WEST).execute();
        assertThat(robot.getX(), equalTo(1));
        assertThat(robot.getY(), equalTo(1));
    }

}
