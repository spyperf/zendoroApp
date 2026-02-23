
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class zendoro {
    public static void main(String[] args) {
        JFrame frame = new JFrame("zendoro");
        frame.setSize(480, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        try {
            BufferedReader reader = new BufferedReader(new FileReader("username.txt"));
            String line = reader.readLine();
            reader.close();
            // Checks if line read is null or if its empty
            if (line != null && !line.isEmpty()) {
                frame.add(new TitlePanel());
            }
            else {
                frame.add(new UsernamePanel());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            // File not exisiting?
            frame.add(new UsernamePanel());
            e.printStackTrace();
        }
        frame.setVisible(true);
    }
}
