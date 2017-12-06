import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyTouchBarTest {

    @Test
    public void testTouch() throws InterruptedException {
        final AtomicBoolean done = new AtomicBoolean(false);

        SwingUtilities.invokeLater(() -> {
            Foundation.init();
            NSTLibrary lib = NST.getMyNSTLibrary();
            lib.registerCallback(new MyCallBack());
            ID nsapp = Foundation.invoke("NSApplication", "sharedApplication");
            Foundation.invoke(nsapp, "setAutomaticCustomizeTouchBarMenuItemEnabled:", true);
            ID tb = Foundation.invoke(Foundation.invoke("NSTouchBar", "alloc"), "init");
            ID tbd = Foundation.invoke(Foundation.invoke("NSTDelegate", "alloc"), "init");
            Foundation.invoke(tb, "setDelegate:", tbd);
            final ID items = Foundation.invoke("NSArray", "arrayWithObjects:",
                    Foundation.convertTypes(new String[]{
                            "label", "button",
                            "NSTouchBarItemIdentifierOtherItemsProxy", null}));

            Foundation.invoke(tb, "setDefaultItemIdentifiers:", items);
            Foundation.invoke(nsapp, "setTouchBar:", tb);

            Frame f = new Frame();
            f.setBounds(0, 0, 500, 110);
            f.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent ev) {
                    done.set(true);
                    synchronized (done) {
                        done.notify();
                    }
                }
            });
            f.setVisible(true);
        });

        synchronized (done) {
            while (!done.get()) {
                done.wait();
            }
        }
    }
}
