package au.com.anz.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.apache.commons.io.IOUtils.closeQuietly;

/**
 * Implementation of {@link Commander} which uses {@link System#in} as the source
 * of input.
 * <p/>
 * User: agwibowo
 * Date: 29/02/12
 * Time: 5:15 PM
 */
public class SystemInCommander implements Commander {

    private BufferedReader reader;

    public SystemInCommander() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String getNextCommand()
            throws IOException {
        return reader.readLine();
    }
    
    public void cleanup() {
        closeQuietly(reader);
    }
}
