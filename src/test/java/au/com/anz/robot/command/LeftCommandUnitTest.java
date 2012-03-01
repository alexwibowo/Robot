package au.com.anz.robot.command;

import au.com.anz.robot.model.Robot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * User: agwibowo
 */
@RunWith(MockitoJUnitRunner.class)
public class LeftCommandUnitTest {

    @Mock
    private Robot robot;

    private LeftCommand leftCommand;

    @Before
    public void setup() {
        leftCommand = new LeftCommand();
    }

    @Test
    public void should_only_invoke_turn_left_on_robot_once() throws InvalidRobotMovementException {
        leftCommand.execute(robot);

        verify(robot, times(1)).turnLeft();
    }

    @Test
    public void should_not_invoke_anything_else_on_robot() throws InvalidRobotMovementException {
        leftCommand.execute(robot);

        verify(robot).turnLeft();
        verifyNoMoreInteractions(robot);
    }

    @Test
    public void should_fail_when_robot_is_unable_to_turn() throws InvalidRobotMovementException {
        InvalidRobotMovementException ex = new InvalidRobotMovementException("hey hey its saturday");
        doThrow(ex).when(robot).turnLeft();
        try {
            leftCommand.execute(robot);
            fail("Should have failed, since robot is unable to turn");
        } catch (InvalidRobotMovementException e) {
            assertThat(e, equalTo(ex));
        }
    }
}
