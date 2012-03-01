package au.com.anz.robot.command;

import au.com.anz.robot.NullPrintStream;
import au.com.anz.robot.model.Board;
import au.com.anz.robot.model.Direction;
import au.com.anz.robot.model.Robot;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * User: agwibowo
 */
public class ReportCommandUnitTest {

    private Robot robot;
    
    
    @Before
    public void setup() {
        robot = new Robot(new Board(5, 5));
        robot.setX(3);
        robot.setY(4);
        robot.setFacingDirection(Direction.NORTH);
        robot.setOnBoard(true);
    }

    @Test
    public void robot_should_stay_at_the_current_position() {
        new ReportCommand(new NullPrintStream()).execute(robot);

        assertThat(robot.getX(), equalTo(3));
        assertThat(robot.getY(), equalTo(4));
        assertThat(robot.getFacingDirection(), equalTo(Direction.NORTH));
    }
}
