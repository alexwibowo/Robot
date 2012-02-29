package au.com.anz.robot;

import au.com.anz.robot.model.Direction;
import au.com.anz.robot.model.Robot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

/**
 * User: Alex
 * Date: 29/02/12
 * Time: 5:36 PM
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotSimulatorEndToEndTest {

    @Mock
    private Commander commander;
    
    private RobotSimulator robotSimulator;

    private Robot robot;


    @Before
    public void setup() {
        robotSimulator = new RobotSimulator(commander);
        robot = robotSimulator.getRobot();
    }
    
    
    @Test
    public void test_straight_one_movement() throws IOException {
        when(commander.getNextCommand())
                .thenReturn("PLACE 0,0,NORTH")
                .thenReturn("MOVE")
                .thenReturn(null);
        robotSimulator.run();

        assertThat(robot.getX(), equalTo(0));
        assertThat(robot.getY(), equalTo(1));
        assertThat(robot.getFacingDirection(), equalTo(Direction.NORTH));
    }

    @Test
    public void test_move_and_turn_right_movement() throws IOException {
        when(commander.getNextCommand())
                .thenReturn("PLACE 0,0,NORTH")
                .thenReturn("MOVE")
                .thenReturn("RIGHT")
                .thenReturn(null);
        robotSimulator.run();

        assertThat(robot.getX(), equalTo(0));
        assertThat(robot.getY(), equalTo(1));
        assertThat(robot.getFacingDirection(), equalTo(Direction.EAST));
    }
    @Test
    public void test_move_and_turn_left_movement() throws IOException {
        when(commander.getNextCommand())
                .thenReturn("PLACE 0,0,NORTH")
                .thenReturn("MOVE")
                .thenReturn("LEFT")
                .thenReturn(null);
        robotSimulator.run();

        assertThat(robot.getX(), equalTo(0));
        assertThat(robot.getY(), equalTo(1));
        assertThat(robot.getFacingDirection(), equalTo(Direction.WEST));
    }

    @Test
    public void robot_should_not_fall_off_the_table() throws IOException {
        when(commander.getNextCommand())
                .thenReturn("PLACE 0,0,NORTH")
                .thenReturn("MOVE")
                .thenReturn("MOVE")
                .thenReturn("MOVE")
                .thenReturn("MOVE")
                .thenReturn("MOVE")
                .thenReturn("MOVE")
                .thenReturn(null);
        robotSimulator.run();

        assertThat(robot.getX(), equalTo(0));
        assertThat(robot.getY(), equalTo(4));
        assertThat(robot.getFacingDirection(), equalTo(Direction.NORTH));
    }

    @Test
    public void test_moving_around_board() throws IOException {
        when(commander.getNextCommand())
                .thenReturn("PLACE 0,0,NORTH")
                .thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE")
                .thenReturn("RIGHT")
                .thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE")
                .thenReturn("RIGHT")
                .thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE")
                .thenReturn("RIGHT")
                .thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE")
                .thenReturn(null);
        robotSimulator.run();

        assertThat(robot.getX(), equalTo(0));
        assertThat(robot.getY(), equalTo(0));
        assertThat(robot.getFacingDirection(), equalTo(Direction.WEST));
    }

    @Test
    public void test_multiple_place_command() throws IOException {
        when(commander.getNextCommand())
                .thenReturn("PLACE 0,0,NORTH")
                .thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE")
                .thenReturn("PLACE 3,3,SOUTH")
                .thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE")
                .thenReturn("LEFT")
                .thenReturn("MOVE")
                .thenReturn(null);
        robotSimulator.run();

        assertThat(robot.getX(), equalTo(4));
        assertThat(robot.getY(), equalTo(0));
        assertThat(robot.getFacingDirection(), equalTo(Direction.EAST));
    }
}
