import com.sun.jna.Native;

import java.util.HashMap;
import java.util.Map;

public class NST {
    private static final NSTLibrary myNSTLibrary;

    public static NSTLibrary getMyNSTLibrary() {
        return myNSTLibrary;
    }

    static {
        System.loadLibrary("nst");
        // Set JNA to convert java.lang.String to char* using UTF-8, and match that with
        // the way we tell CF to interpret our char*
        // May be removed if we use toStringViaUTF16
        System.setProperty("jna.encoding", "UTF8");

        Map<String, Object> nstOptions = new HashMap<>();

        myNSTLibrary = Native.loadLibrary("nst", NSTLibrary.class, nstOptions);
    }
}
