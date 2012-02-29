package au.com.anz.robot.command;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

/**
 * User: agwibowo
 * Date: 29/02/12
 * Time: 12:20 PM
 */
public class CommandParserUnitTest {
    @Test
    public void should_recognize_MOVE_command() throws InvalidCommandException {
        assertThat(CommandParser.parse("MOVE"), instanceOf(MoveCommand.class));
        assertThat(CommandParser.parse(" MOVE "), instanceOf(MoveCommand.class));
        assertThat(CommandParser.parse("MOVE\n"), instanceOf(MoveCommand.class));
        assertThat(CommandParser.parse("\tMOVE\t"), instanceOf(MoveCommand.class));
    }

    @Test
    public void should_recognize_LEFT_command() throws InvalidCommandException {
        assertThat(CommandParser.parse("LEFT"), instanceOf(LeftCommand.class));
        assertThat(CommandParser.parse(" LEFT "), instanceOf(LeftCommand.class));
        assertThat(CommandParser.parse("LEFT\n"), instanceOf(LeftCommand.class));
        assertThat(CommandParser.parse("\tLEFT\t"), instanceOf(LeftCommand.class));
    }

    @Test
    public void should_recognize_RIGHT_command() throws InvalidCommandException {
        assertThat(CommandParser.parse("RIGHT"), instanceOf(RightCommand.class));
        assertThat(CommandParser.parse(" RIGHT "), instanceOf(RightCommand.class));
        assertThat(CommandParser.parse("RIGHT\n"), instanceOf(RightCommand.class));
        assertThat(CommandParser.parse("\tRIGHT\t"), instanceOf(RightCommand.class));
    }

    @Test
    public void should_recognize_PLACE_command() throws InvalidCommandException {
        assertThat(CommandParser.parse("PLACE 0,1,NORTH"), instanceOf(PlaceCommand.class));
        assertThat(CommandParser.parse(" PLACE 0,1,NORTH "), instanceOf(PlaceCommand.class));
        assertThat(CommandParser.parse(" PLACE 0,1,NORTH\n"), instanceOf(PlaceCommand.class));
        assertThat(CommandParser.parse(" PLACE 0,1,NORTH\t"), instanceOf(PlaceCommand.class));
    }

    @Test(expected = MalformedCommandException.class)
    public void should_throw_exception_when_given_wrong_command() throws InvalidCommandException{
        CommandParser.parse("VOODOO_CHICKEN");
    }


}
