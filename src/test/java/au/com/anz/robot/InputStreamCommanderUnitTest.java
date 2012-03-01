package au.com.anz.robot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;

import static org.apache.commons.io.IOUtils.toInputStream;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * User: agwibowo
 */
@RunWith(MockitoJUnitRunner.class)
public class InputStreamCommanderUnitTest {

    @Mock
    private InputStream mockInputStream;

    @Test
    public void getNextCommand_should_read_the_command_from_inputStream()
            throws IOException {
        InputStream is = toInputStream("hello\r\nworld\r\n");
        InputStreamCommander commander = new InputStreamCommander(is);
        assertThat(commander.getNextCommand(), equalTo("hello"));
        assertThat(commander.getNextCommand(), equalTo("world"));
        assertThat(commander.getNextCommand(), nullValue());
    }
    
    @Test
    public void cleanup_should_close_the_input_stream()
            throws IOException {
        InputStreamCommander commander = new InputStreamCommander(mockInputStream);
        commander.cleanup();
        verify(mockInputStream, times(1)).close();
    }
}
