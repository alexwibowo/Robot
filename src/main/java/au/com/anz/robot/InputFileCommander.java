package au.com.anz.robot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.String.format;

/**
 * Implementation of {@link Commander} which read content of a file to read all the
 * commands to be given to the robot.
 * <p/>
 * User: agwibowo
 */
public class InputFileCommander implements Commander{

    private Scanner scanner;

    /**
     * @param filepath path to the file
     * @throws IOException if the file does not exist, or cannot be read
     */
    public InputFileCommander(String filepath) throws IOException {
        init(filepath);
    }

    private void init(String filepath)
            throws IOException {
        File file = new File(filepath);
        if (!file.exists()) {
            throw new FileNotFoundException(format("File [%s] does not exists", filepath));
        }
        if (!file.canRead()) {
            throw new IOException(format("File [%s] cannot be read.", filepath));
        }
        scanner = new Scanner(file);
    }

    public String getNextCommand() throws IOException {
        return scanner.hasNextLine()? scanner.nextLine() : null;
    }

    public void cleanup() {
        scanner.close();
    }
}
