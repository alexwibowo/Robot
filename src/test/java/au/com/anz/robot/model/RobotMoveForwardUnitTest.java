package au.com.anz.robot.model;

import au.com.anz.robot.command.InvalidRobotMovementException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;

/**
 * User: agwibowo
 */
public class RobotMoveForwardUnitTest {

    private Robot robot;

    @Before
    public void setup() {
        robot = new Robot(new Board(5, 5));
        robot.setOnBoard(true);
        robot.setX(3);
        robot.setY(3);
    }

    @Test
    public void WHEN_facing_left_edge__THEN__robot_should_stay() throws InvalidRobotMovementException {
        //given
        robot.setX(0);
        robot.setFacingDirection(Direction.WEST);

        //when
        robot.moveForward();

        //then
        assertThat(robot.getX(), equalTo(0));
        assertThat(robot.getY(), equalTo(3));
        assertThat("Robot should not change facing direction when moving forward",
                robot.getFacingDirection(), equalTo(Direction.WEST));
    }

    @Test
    public void WHEN_facing_top_edge__THEN__robot_should_stay() throws InvalidRobotMovementException {
        //given
        robot.setY(4);
        robot.setFacingDirection(Direction.NORTH);

        //when
        robot.moveForward();

        // then
        assertThat(robot.getX(), equalTo(3));
        assertThat(robot.getY(), equalTo(4));
        assertThat("Robot should not change facing direction when moving forward",
                robot.getFacingDirection(), equalTo(Direction.NORTH));

    }

    @Test
    public void WHEN_facing_right_edge__THEN__robot_should_stay() throws InvalidRobotMovementException {
        //given
        robot.setX(4);
        robot.setFacingDirection(Direction.EAST);

        //when
        robot.moveForward();

        // then
        assertThat(robot.getX(), equalTo(4));
        assertThat(robot.getY(), equalTo(3));
        assertThat("Robot should not change facing direction when moving forward",
                robot.getFacingDirection(), equalTo(Direction.EAST));

    }

    @Test
    public void WHEN_facing_bottom_edge__THEN__robot_should_stay() throws InvalidRobotMovementException {
        //given
        robot.setY(0);
        robot.setFacingDirection(Direction.SOUTH);

        //when
        robot.moveForward();

        // then
        assertThat(robot.getX(), equalTo(3));
        assertThat(robot.getY(), equalTo(0));
        assertThat("Robot should not change facing direction when moving forward",
                robot.getFacingDirection(), equalTo(Direction.SOUTH));
    }

    @Test
    public void WHEN_facing_north__THEN__moving_forward_should_move_robot_up_by_one_unit() throws InvalidRobotMovementException {
        //given
        robot.setY(2);
        robot.setFacingDirection(Direction.NORTH);

        //when
        robot.moveForward();

        // then
        assertThat(robot.getX(), equalTo(3));
        assertThat(robot.getY(), equalTo(3));
        assertThat(robot.getFacingDirection(), equalTo(Direction.NORTH));

    }

    @Test
    public void WHEN_facing_south__THEN__moving_forward_should_move_robot_down_by_one_unit() throws InvalidRobotMovementException {
        //given
        robot.setY(2);
        robot.setFacingDirection(Direction.SOUTH);

        //when
        robot.moveForward();

        // then
        assertThat(robot.getX(), equalTo(3));
        assertThat(robot.getY(), equalTo(1));
        assertThat("Robot should not change facing direction when moving forward",
                robot.getFacingDirection(), equalTo(Direction.SOUTH));

    }

    @Test
    public void WHEN_facing_east__THEN__moving_forward_should_move_robot_to_right_by_one_unit() throws InvalidRobotMovementException {
       //given
        robot.setX(3);
        robot.setFacingDirection(Direction.EAST);

        //when
        robot.moveForward();

        // then
        assertThat(robot.getX(), equalTo(4));
        assertThat(robot.getY(), equalTo(3));
        assertThat("Robot should not change facing direction when moving forward",
                robot.getFacingDirection(), equalTo(Direction.EAST));

    }

    @Test
    public void WHEN_facing_west__THEN__moving_forward_should_move_robot_to_left_by_one_unit() throws InvalidRobotMovementException {
        //given
        robot.setX(3);
        robot.setFacingDirection(Direction.WEST);

        //when
        robot.moveForward();

        // then
        assertThat(robot.getX(), equalTo(2));
        assertThat(robot.getY(), equalTo(3));
        assertThat("Robot should not change facing direction when moving forward",
                robot.getFacingDirection(), equalTo(Direction.WEST));

    }


    @Test
    public void WHEN_robot_is_not_on_board__THEN__robot_should_not_be_able_to_move_forward() throws InvalidRobotMovementException {
        //given
        robot.setOnBoard(false);
        robot.setX(3);
        robot.setFacingDirection(Direction.WEST);

        //when
        try {
            robot.moveForward();
            fail("Should have failed, since robot is not on board");
        } catch (InvalidRobotMovementException e) {
        }

        // then
        assertThat(robot.getX(), equalTo(3));
        assertThat(robot.getY(), equalTo(3));
        assertThat("Robot should not change facing direction when moving forward",
                robot.getFacingDirection(), equalTo(Direction.WEST));

    }
}
