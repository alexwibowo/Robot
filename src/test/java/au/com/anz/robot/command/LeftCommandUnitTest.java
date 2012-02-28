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

/**
 * User: agwibowo
 * Date: 28/02/12
 * Time: 9:40 PM
 */
@RunWith(MockitoJUnitRunner.class)
public class LeftCommandUnitTest {

    @Mock
    private Robot robot;

    private LeftCommand leftCommand;

    @Before
    public void setup() {
        leftCommand = new LeftCommand(robot);
    }

    @Test
    public void should_only_invoke_turn_left_on_robot_once() {
        leftCommand.execute();

        verify(robot, times(1)).turnLeft();
    }

    @Test
    public void should_not_invoke_anything_else_on_robot() {
        leftCommand.execute();

        verify(robot).turnLeft();
        verifyNoMoreInteractions(robot);
    }
}
