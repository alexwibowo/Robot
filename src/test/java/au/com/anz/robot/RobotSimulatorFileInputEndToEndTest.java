package au.com.anz.robot;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static au.com.anz.robot.RobotMatcher.isNotOnBoard;
import static au.com.anz.robot.RobotMatcher.isOnBoard;
import static au.com.anz.robot.model.Direction.EAST;
import static au.com.anz.robot.model.Direction.NORTH;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * User: agwibowo
 */
public class RobotSimulatorFileInputEndToEndTest {

    @Test
    public void should_be_able_to_handle_non_text_file() throws IOException, ParseException {

        InputFileCommander inputFileCommander = createInputFileCommander("non-text-file.pdf");

        RobotSimulator simulator = new RobotSimulator(inputFileCommander, new NullPrintStream() );
        simulator.run();

        inputFileCommander.cleanup();

        assertThat(simulator.getRobot(), isNotOnBoard());
    }

    @Test
    public void given_invalid_text_file__THEN__robot_should_stay_off_the_board() throws IOException {
        InputFileCommander inputFileCommander = createInputFileCommander("big_arbitrary_file.txt");

        RobotSimulator simulator = new RobotSimulator(inputFileCommander, new NullPrintStream() );
        simulator.run();

        inputFileCommander.cleanup();

        assertThat(simulator.getRobot(), isNotOnBoard());
    }

    @Test
    public void given_empty_file__THEN__robot_should_stay_off_the_board() throws IOException, ParseException {
        InputFileCommander inputFileCommander = createInputFileCommander("empty_file.txt");

        RobotSimulator simulator = new RobotSimulator(inputFileCommander, new NullPrintStream());
        simulator.run();

        inputFileCommander.cleanup();

        assertThat(simulator.getRobot(), isNotOnBoard());
    }

    @Test
    public void test_running_simulator_with_file_option() throws IOException, ParseException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("file_with_leading_non_place_command.txt");
        RobotSimulator.main(new String[]{"-file",url.getPath()});
    }

    @Test
    public void test_reading_file_with_leading_non_place_command() throws IOException {
        InputFileCommander inputFileCommander = createInputFileCommander("file_with_leading_non_place_command.txt");

        RobotSimulator simulator = new RobotSimulator(inputFileCommander, new NullPrintStream());
        simulator.run();

        inputFileCommander.cleanup();

        assertThat(simulator.getRobot(), isOnBoard(0, 1, EAST));
    }

    @Test
    public void test_reading_file_with_invalid_integer_coordinate() throws IOException {
        InputFileCommander inputFileCommander = createInputFileCommander("file_with_invalid_large_integer_coordinate.txt");

        RobotSimulator simulator = new RobotSimulator(inputFileCommander, new NullPrintStream());
        simulator.run();

        inputFileCommander.cleanup();

        assertThat(simulator.getRobot(), isNotOnBoard());
    }

    @Test
    public void test_reading_file_with_invalid_board_coordinate() throws IOException {
        InputFileCommander inputFileCommander = createInputFileCommander("file_with_invalid_board_coordinate.txt");

        RobotSimulator simulator = new RobotSimulator(inputFileCommander, new NullPrintStream());
        simulator.run();

        inputFileCommander.cleanup();

        assertThat(simulator.getRobot(), isNotOnBoard());
    }

    @Test
    public void test_reading_file_with_invalid_direction() throws IOException {
        InputFileCommander inputFileCommander = createInputFileCommander("file_with_invalid_direction.txt");

        RobotSimulator simulator = new RobotSimulator(inputFileCommander, new NullPrintStream());
        simulator.run();

        inputFileCommander.cleanup();

        assertThat(simulator.getRobot(), isNotOnBoard());
    }

    @Test
    public void test_reading_file_with_leading_empty_lines() throws IOException {
        InputFileCommander inputFileCommander = createInputFileCommander("file_with_leading_empty_lines.txt");

        RobotSimulator simulator = new RobotSimulator(inputFileCommander, new NullPrintStream());
        simulator.run();

        inputFileCommander.cleanup();

        assertThat(simulator.getRobot(), isOnBoard(0, 1, NORTH));
    }

    @Test
    public void test_reading_from_input_file() throws IOException {
        InputFileCommander inputFileCommander = createInputFileCommander("big_input_file.txt");

        RobotSimulator simulator = new RobotSimulator(inputFileCommander, new NullPrintStream());
        simulator.run();

        inputFileCommander.cleanup();
        assertTrue(simulator.getRobot().isOnBoard());
    }

    private InputFileCommander createInputFileCommander(String fileName) throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);
        return new InputFileCommander(url.getPath());
    }
}
