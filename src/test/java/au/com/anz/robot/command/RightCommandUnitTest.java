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
public class RightCommandUnitTest {
    @Mock
    private Robot robot;

    private RightCommand rightCommand;

    @Before
    public void setup() {
        rightCommand = new RightCommand();
    }

    @Test
    public void should_only_invoke_turn_right_on_robot_once() {
        rightCommand.execute(robot);

        verify(robot, times(1)).turnRight();
    }

    @Test
    public void should_not_invoke_anything_else_on_robot() {
        rightCommand.execute(robot);

        verify(robot).turnRight();
        verifyNoMoreInteractions(robot);

    }


}
