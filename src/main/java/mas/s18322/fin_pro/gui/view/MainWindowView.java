package mas.s18322.fin_pro.gui.view;

import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;

@Component
public class MainWindowView extends JFrame {
    /***
     * Class contractor setting the window title to 'Terminal',
     * size to 500X600 and default close operation to EXIT_ON_CLOSE
     * @throws HeadlessException Thrown when code that is dependent on a keyboard, display,
     * or mouse is called in an environment that does not support a keyboard, display, or mouse.
     */
    public MainWindowView() throws HeadlessException{
        setTitle("Terminal");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    /***
     * Open message dialog with specified content
     * @param s content of the message
     */
    public void msgBox(String s){
        JOptionPane.showMessageDialog(this, s);
    }
}
