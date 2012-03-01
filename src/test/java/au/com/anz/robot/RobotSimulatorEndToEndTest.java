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
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

/**
 * User: agwibowo
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotSimulatorEndToEndTest {

    @Mock
    private Commander commander;
    
    private RobotSimulator robotSimulator;

    private Robot robot;


    @Before
    public void setup() {
        robotSimulator = new RobotSimulator(commander, new NullPrintStream());
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
    public void should_be_able_to_handle_empty_initial_lines() throws IOException {
        when(commander.getNextCommand())
                .thenReturn("")
                .thenReturn("  ")
                .thenReturn("\t")
                .thenReturn("PLACE 0,0,NORTH")
                .thenReturn("MOVE")
                .thenReturn(null);
        robotSimulator.run();

        assertThat(robot.getX(), equalTo(0));
        assertThat(robot.getY(), equalTo(1));
        assertThat(robot.getFacingDirection(), equalTo(Direction.NORTH));
    }

    @Test
    public void should_ignore_commands_until_first_place_command() throws IOException {
        when(commander.getNextCommand())
                .thenReturn("")
                .thenReturn("MOVE")
                .thenReturn("MOVE")
                .thenReturn("LEFT")
                .thenReturn("MOVE")
                .thenReturn(null);
        robotSimulator.run();

        assertThat(robot.getX(), equalTo(0));
        assertThat(robot.getY(), equalTo(0));
        assertThat(robot.getFacingDirection(), nullValue());
        assertFalse(robot.isOnBoard());
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
    public void test_moving_around_board_2() throws IOException {
        when(commander.getNextCommand())
                .thenReturn("PLACE 0,0,NORTH")
                .thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE")
                .thenReturn("RIGHT").thenReturn("MOVE").thenReturn("RIGHT")                      //0
                .thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE")
                .thenReturn("LEFT").thenReturn("MOVE").thenReturn("LEFT")                       // 1
                .thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE")
                .thenReturn("RIGHT").thenReturn("MOVE").thenReturn("RIGHT")                     // 2
                .thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE")
                .thenReturn("LEFT").thenReturn("MOVE").thenReturn("LEFT")                       // 3
                .thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE")
                .thenReturn("RIGHT").thenReturn("MOVE").thenReturn("RIGHT")                     // 4
                .thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE").thenReturn("MOVE")
                .thenReturn(null);
        robotSimulator.run();

        assertThat(robot.getX(), equalTo(4));
        assertThat(robot.getY(), equalTo(0));
        assertThat(robot.getFacingDirection(), equalTo(Direction.SOUTH));
         
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
