
import javax.swing.*;

public class zendoro {
    public static void main(String[] args) {
        JFrame frame = new JFrame("zendoro");
        frame.setSize(480, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(new TitlePanel());
        frame.setVisible(true);
    }
}
