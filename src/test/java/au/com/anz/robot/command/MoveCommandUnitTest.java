package au.com.anz.robot.command;

import au.com.anz.robot.model.Robot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
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
    public void should_only_invoke_move_forward_on_robot_once() {
        moveCommand.execute(robot);

        verify(robot, times(1)).moveForward();
    }

    @Test
    public void should_not_invoke_anything_else_on_robot() {
        moveCommand.execute(robot);

        verify(robot).moveForward();
        verifyNoMoreInteractions(robot);
    }
}
