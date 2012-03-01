package au.com.anz.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.apache.commons.io.IOUtils.closeQuietly;

/**
 * Implementation of {@link Commander} which reads the commands from  {@link InputStream}
 * of input.
 * <p/>
 * User: agwibowo
 */
public class InputStreamCommander implements Commander {

    private BufferedReader reader;

    /**
     * Input stream to read command from
     * @param inputStream the input stream
     */
    public InputStreamCommander(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public String getNextCommand()
            throws IOException {
        return reader.readLine();
    }
    
    public void cleanup() {
        closeQuietly(reader);
    }
}
