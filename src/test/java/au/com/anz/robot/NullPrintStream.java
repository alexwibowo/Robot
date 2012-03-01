package au.com.anz.robot;

import com.google.common.io.NullOutputStream;

import java.io.PrintStream;

/**
 * User: agwibowo
 */
public class NullPrintStream extends PrintStream{

    public NullPrintStream() {
        super(new NullOutputStream());
    }
}
