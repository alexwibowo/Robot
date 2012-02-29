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

@RunWith(MockitoJUnitRunner.class)
public class MoveCommandUnitTest {
    @Mock
    private Robot robot;

    private MoveCommand moveCommand;

    @Before
    public void setup() {
        moveCommand = new MoveCommand();
    }

    @Test
    public void should_only_invoke_move_forward_on_robot_once() throws InvalidRobotMovementException {
        moveCommand.execute(robot);

        verify(robot, times(1)).moveForward();
    }

    @Test
    public void should_not_invoke_anything_else_on_robot() throws InvalidRobotMovementException {
        moveCommand.execute(robot);

        verify(robot).moveForward();
        verifyNoMoreInteractions(robot);
    }
    
    @Test
    public void should_fail_when_robot_is_unable_to_move() throws InvalidRobotMovementException {
        InvalidRobotMovementException ex = new InvalidRobotMovementException("hey hey its saturday");
        doThrow(ex).when(robot).moveForward();
        try {
            moveCommand.execute(robot);
            fail("Should have failed, since robot is unable to move");
        } catch (InvalidRobotMovementException e) {
            assertThat(e, equalTo(ex));
        }
    }

    
}
