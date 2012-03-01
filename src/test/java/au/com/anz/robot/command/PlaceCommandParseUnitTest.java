package au.com.anz.robot.command;

import au.com.anz.robot.model.Direction;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;

/**
 * User: agwibowo
 */
public class PlaceCommandParseUnitTest {

    @Test
    public void should_be_able_to_parse_correctly_formatted_string() throws InvalidCommandException {
        PlaceCommand command = PlaceCommand.createFromString("PLACE 1,2,NORTH");
        assertThat(command.getX(), equalTo(1));
        assertThat(command.getY(), equalTo(2));
        assertThat(command.getFacingDirection(), equalTo(Direction.NORTH));
    }

    @Test
    public void should_be_able_to_handle_leading_zeroes_in_coordinate() throws InvalidCommandException {
        PlaceCommand command = PlaceCommand.createFromString("PLACE 01,0002,NORTH");
        assertThat(command.getX(), equalTo(1));
        assertThat(command.getY(), equalTo(2));
        assertThat(command.getFacingDirection(), equalTo(Direction.NORTH));
    }

    @Test
    public void should_be_able_to_handle_whitespaces() throws InvalidCommandException {
        PlaceCommand command = PlaceCommand.createFromString("  PLACE         1,\t 2,  NORTH   ");
        assertThat(command.getX(), equalTo(1));
        assertThat(command.getY(), equalTo(2));
        assertThat(command.getFacingDirection(), equalTo(Direction.NORTH));
    }

    @Test
    public void should_only_accept_all_uppercase_PLACE_command() throws InvalidCommandException {
        try {
            PlaceCommand.createFromString("place 1,2,NORTH");
            fail("Should have failed due to invalid command");
        } catch (MalformedCommandException e) {
            assertThat(e.getCommandString(), equalTo("place 1,2,NORTH"));
        }
    }

    @Test
    public void should_only_accept_all_uppercase_direction() throws InvalidCommandException {
        try {
            PlaceCommand.createFromString("PLACE 1,2,north");
            fail("Should have failed due to invalid direction");
        } catch (InvalidDirectionException e) {
            assertThat(e.getDirection(), equalTo("north"));
        }
    }

    @Test
    public void should_throw_exception_when_command_has_extra_arbitrary_string() throws InvalidCommandException {
        try {
            PlaceCommand.createFromString("PLACE 1,2,NORTH fofofooof");
            fail("Should have failed due to invalid command");
        } catch (MalformedCommandException e) {
            assertThat(e.getCommandString(), equalTo("PLACE 1,2,NORTH fofofooof"));
        }
    }

    @Test
    public void should_throw_exception_when_command_appears_in_middle_of_arbitrary_string() throws InvalidCommandException {
        try {
            PlaceCommand.createFromString("here is some arbitrary string PLACE 1,2,NORTH ");
            fail("Should have failed due to invalid command");
        } catch (MalformedCommandException e) {
            assertThat(e.getCommandString(), equalTo("here is some arbitrary string PLACE 1,2,NORTH"));
        }
    }

    @Test
    public void should_throw_exception_when_given_alphabetic_x_coordinate() throws InvalidCommandException {
        try {
            PlaceCommand.createFromString("PLACE a,2,NORTH");
            fail("Should have failed due to non integer x coordinate");
        } catch (MalformedCommandException e) {
            assertThat(e.getCommandString(), equalTo("PLACE a,2,NORTH"));
        }
    }

    @Test
    public void should_throw_exception_when_given_float_x_coordinate() throws InvalidCommandException {
        try {
            PlaceCommand.createFromString("PLACE 1.2,2,NORTH");
            fail("Should have failed due to non integer x coordinate");
        } catch (MalformedCommandException e) {
            assertThat(e.getCommandString(), equalTo("PLACE 1.2,2,NORTH"));
        }
    }

    @Test
    public void should_throw_exception_when_given_alphabetic_y_coordinate() throws InvalidCommandException {
        try {
            PlaceCommand.createFromString("PLACE 1,b,NORTH");
            fail("Should have failed due to non integer y coordinate");
        } catch (MalformedCommandException e) {
            assertThat(e.getCommandString(), equalTo("PLACE 1,b,NORTH"));
        }
    }

    @Test
    public void should_throw_exception_when_given_float_y_coordinate() throws InvalidCommandException {
        try {
            PlaceCommand.createFromString("PLACE 1,2.1,NORTH");
            fail("Should have failed due to non integer y coordinate");
        } catch (MalformedCommandException e) {
            assertThat(e.getCommandString(), equalTo("PLACE 1,2.1,NORTH"));
        }
    }

    @Test
    public void should_throw_exception_when_given_negative_x_coordinate() throws InvalidCommandException {
        try{
            PlaceCommand.createFromString("PLACE -1,2,NORTH");
            fail("Should have failed due to negative x coordinate");
        } catch (MalformedCommandException e) {
            assertThat(e.getCommandString(), equalTo("PLACE -1,2,NORTH"));
        }
    }

    @Test
    public void should_throw_exception_when_given_negative_y_coordinate() throws InvalidCommandException {
        try{
            PlaceCommand.createFromString("PLACE 1,-2,NORTH");
            fail("Should have failed due to negative y coordinate");
        } catch (MalformedCommandException e) {
            assertThat(e.getCommandString(), equalTo("PLACE 1,-2,NORTH"));
        }
    }

    @Test
    public void should_throw_exception_when_not_given_any_coordinate() throws InvalidCommandException {
        try {
            PlaceCommand.createFromString("PLACE NORTH");
            fail("Should have failed due to invalid coordinate");
        } catch (MalformedCommandException e) {
            assertThat(e.getCommandString(), equalTo("PLACE NORTH"));
        }
    }

    @Test
    public void should_throw_exception_when_given_more_than_two_integers_for_coordinate() throws InvalidCommandException {
        try {
            PlaceCommand.createFromString("PLACE 1,2,3 NORTH");
            fail("Should have failed due to invalid coordinate");
        } catch (MalformedCommandException e) {
            assertThat(e.getCommandString(), equalTo("PLACE 1,2,3 NORTH"));
        }
    }

    @Test
    public void should_throw_exception_when_given_only_one_integer_for_coordinate() throws InvalidCommandException {
        try {
            PlaceCommand.createFromString("PLACE 1 NORTH");
            fail("Should have failed due to invalid coordinate");
        } catch (MalformedCommandException e) {
            assertThat(e.getCommandString(), equalTo("PLACE 1 NORTH"));
        }
    }

    @Test
    public void should_throw_exception_when_not_given_direction() throws InvalidCommandException {
        try {
            PlaceCommand.createFromString("PLACE 1,2");
            fail("Should have failed due to missing direction");
        } catch (MalformedCommandException e) {
            assertThat(e.getCommandString(), equalTo("PLACE 1,2"));
        }

    }

    @Test
    public void should_throw_exception_when_given_invalid_direction() throws InvalidCommandException {
        try {
            PlaceCommand.createFromString("PLACE 1,2,CENTER");
            fail("Should have failed due to invalid direction");
        } catch (InvalidDirectionException e) {
            assertThat(e.getDirection(), equalTo("CENTER"));
        }
    }

    @Test
    public void should_throw_exception_when_command_does_not_start_with_PLACE_keyword() throws InvalidCommandException {
        try {
            PlaceCommand.createFromString("FOO 1,2,SOUTH");
            fail("Should have failed due to invalid direction");
        } catch (MalformedCommandException e) {
            assertThat(e.getCommandString(), equalTo("FOO 1,2,SOUTH"));
        }
    }

    @Test
    public void should_throw_exception_when_given_more_than_one_place_command() throws InvalidCommandException {
        try {
            PlaceCommand.createFromString("PLACE 1,2,NORTH PLACE 1,2,NORTH");
            fail("Should have failed due to invalid command");
        } catch (MalformedCommandException e) {
            assertThat(e.getCommandString(), equalTo("PLACE 1,2,NORTH PLACE 1,2,NORTH"));
        }
    }

    @Test
    public void should_throw_exception_when_given_large_integer() throws InvalidCommandException {
        int maxInt = Integer.MAX_VALUE;
        try {
            PlaceCommand.createFromString("PLACE "+maxInt+"1234,"+maxInt+"22222,NORTH");
            fail("Should have failed due to invalid coordinate");
        } catch (InvalidCoordinateException e) {
            assertThat(e.getMessage(), containsString("cannot be parsed into a valid board coordinate"));
        }
    }

    @Test
    public void should_throw_exception_when_given_small_integer() throws InvalidCommandException {
        int minInt = Integer.MIN_VALUE;
        try {
            PlaceCommand.createFromString("PLACE "+minInt+"1234,"+minInt+"22222,NORTH");
            fail("Should have failed, since coordinate should not be negative");
        } catch (MalformedCommandException e) {
            assertThat(e.getMessage(), containsString("Unknown command has been given"));
        }
    }
}
