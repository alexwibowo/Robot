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
        assertThat(CommandParser.fromString("MOVE"), instanceOf(MoveCommand.class));
        assertThat(CommandParser.fromString(" MOVE "), instanceOf(MoveCommand.class));
        assertThat(CommandParser.fromString("MOVE\n"), instanceOf(MoveCommand.class));
        assertThat(CommandParser.fromString("\tMOVE\t"), instanceOf(MoveCommand.class));
    }

    @Test
    public void should_recognize_LEFT_command() throws InvalidCommandException {
        assertThat(CommandParser.fromString("LEFT"), instanceOf(LeftCommand.class));
        assertThat(CommandParser.fromString(" LEFT "), instanceOf(LeftCommand.class));
        assertThat(CommandParser.fromString("LEFT\n"), instanceOf(LeftCommand.class));
        assertThat(CommandParser.fromString("\tLEFT\t"), instanceOf(LeftCommand.class));
    }

    @Test
    public void should_recognize_RIGHT_command() throws InvalidCommandException {
        assertThat(CommandParser.fromString("RIGHT"), instanceOf(RightCommand.class));
        assertThat(CommandParser.fromString(" RIGHT "), instanceOf(RightCommand.class));
        assertThat(CommandParser.fromString("RIGHT\n"), instanceOf(RightCommand.class));
        assertThat(CommandParser.fromString("\tRIGHT\t"), instanceOf(RightCommand.class));
    }

    @Test
    public void should_recognize_PLACE_command() throws InvalidCommandException {
        assertThat(CommandParser.fromString("PLACE 0,1,NORTH"), instanceOf(PlaceCommand.class));
        assertThat(CommandParser.fromString(" PLACE 0,1,NORTH "), instanceOf(PlaceCommand.class));
        assertThat(CommandParser.fromString(" PLACE 0,1,NORTH\n"), instanceOf(PlaceCommand.class));
        assertThat(CommandParser.fromString(" PLACE 0,1,NORTH\t"), instanceOf(PlaceCommand.class));
    }

    @Test
    public void should_recognize_REPORT_command() throws InvalidCommandException {
        assertThat(CommandParser.fromString("REPORT"), instanceOf(ReportCommand.class));
        assertThat(CommandParser.fromString(" REPORT "), instanceOf(ReportCommand.class));
        assertThat(CommandParser.fromString(" REPORT\n"), instanceOf(ReportCommand.class));
        assertThat(CommandParser.fromString(" REPORT\t"), instanceOf(ReportCommand.class));

    }

    @Test(expected = MalformedCommandException.class)
    public void should_throw_exception_when_given_wrong_command() throws InvalidCommandException{
        CommandParser.fromString("VOODOO_CHICKEN");
    }


}
