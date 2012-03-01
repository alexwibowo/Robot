package au.com.anz.robot;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assume.assumeTrue;

/**
 * User: agwibowo
 */
public class InputFileCommanderUnitTest {

    @Test(expected = FileNotFoundException.class)
    public void should_fail_when_file_does_not_exist() throws IOException {
        new InputFileCommander("/foo");
    }

    @Test
    public void should_fail_when_file_cannot_be_read() throws IOException {
        File file = File.createTempFile("file", ".txt");
        boolean result = file.setReadable(false);
        assumeTrue(result);
        try {
            new InputFileCommander(file.getAbsolutePath());
        } catch (Exception e) {
            assertThat(e.getMessage(), equalTo(String.format("File [%s] cannot be read.", file.getAbsolutePath())));
        }
    }

    @Test
    public void should_be_able_to_get_next_command() throws IOException {
        InputFileCommander inputFileCommander = fromFile("small_file.txt");
        assertThat(inputFileCommander.getNextCommand(), equalTo("PLACE 0,0,NORTH"));
        assertThat(inputFileCommander.getNextCommand(), equalTo("MOVE"));
        assertThat(inputFileCommander.getNextCommand(), equalTo("REPORT"));
        assertThat(inputFileCommander.getNextCommand(), nullValue());
        inputFileCommander.cleanup();
    }

    @Test
    public void should_be_able_to_read_empty_file() throws IOException {
        InputFileCommander inputFileCommander = fromFile("empty_file.txt");
        assertThat(inputFileCommander.getNextCommand(), nullValue());
        inputFileCommander.cleanup();
    }

    @Test
    public void should_be_able_to_handle_leading_empty_lines() throws IOException {
        InputFileCommander inputFileCommander = fromFile("file_with_leading_empty_lines.txt");
        assertThat(inputFileCommander.getNextCommand(), equalTo(""));
        assertThat(inputFileCommander.getNextCommand(), equalTo(""));
        assertThat(inputFileCommander.getNextCommand(), equalTo("PLACE 0,0,NORTH"));
        assertThat(inputFileCommander.getNextCommand(), equalTo("MOVE"));
        assertThat(inputFileCommander.getNextCommand(), equalTo("REPORT"));
        assertThat(inputFileCommander.getNextCommand(), nullValue());
        inputFileCommander.cleanup();
    }
    
    @Test
    public void should_be_able_to_handle_large_input_file() throws IOException {
        InputFileCommander inputFileCommander = fromFile("big_arbitrary_file.txt");
        String line = null;
        while ((line = inputFileCommander.getNextCommand())!=null) {
        }
        inputFileCommander.cleanup();
    }
    
    private InputFileCommander fromFile(String filename) throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(filename);
        return new InputFileCommander(url.getPath());
    }
}
