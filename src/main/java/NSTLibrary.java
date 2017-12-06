import com.sun.jna.Library;

public interface NSTLibrary extends Library {
    void registerCallback(MyCallBack myc);
}