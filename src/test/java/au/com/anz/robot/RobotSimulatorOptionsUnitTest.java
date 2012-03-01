package au.com.anz.robot;

import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * User: agwibowo
 */
public class RobotSimulatorOptionsUnitTest {

    private RobotSimulator.RobotSimulatorOptions options;

    @Before
    public void setup() throws ParseException {
        options = new RobotSimulator.RobotSimulatorOptions();
    }
    
    
    @Test
    public void when_user_didnt_specify_file_option__THEN__should_read_from_standard_input()
            throws ParseException, IOException {
        options.parseCommandLineArguments();  // no arguments specified
        assertThat(options.getCommander(), instanceOf(InputStreamCommander.class));
    }

    @Test
    public void when_user_specified_file_option__THEN__should_read_from_file()
            throws ParseException, IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("small_file.txt");
        options.parseCommandLineArguments("-file",url.getPath());
        assertThat(options.getCommander(), instanceOf(InputFileCommander.class));
    }
    
    @Test(expected = FileNotFoundException.class)
    public void should_fail_when_specified_file_does_not_exists() throws ParseException, IOException {
        options.parseCommandLineArguments("-file", System.currentTimeMillis()+"foo.txt");
        options.getCommander();
    }

    @Test
    public void should_fail_when_file_option_was_chosen_and_filename_wasnt_specified() throws ParseException {
        try {
            options.parseCommandLineArguments("-file");
            fail("Should have failed, since filename was not specified");
        } catch (MissingArgumentException e) {
            assertThat(e.getMessage(), equalTo("Missing argument for option: file"));
        }
    }
}
