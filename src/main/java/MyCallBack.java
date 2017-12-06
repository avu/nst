import com.sun.jna.Callback;

public class MyCallBack implements Callback {
    public void callback (String message) {
        System.out.println(message);
    }
}