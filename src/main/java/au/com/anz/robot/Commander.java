package au.com.anz.robot;

import java.io.IOException;

/**
 * User: Alex
 * Date: 29/02/12
 * Time: 5:29 PM
 */
public interface Commander {

    /**
     * @return the next command that can be executed by the robot
     * @throws IOException
     */
    String getNextCommand() throws IOException;

    /**
     *
     */
    void cleanup();
}
