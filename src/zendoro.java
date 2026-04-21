
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class zendoro {

    public static void main(String[] args) {
        // Creates a new frame with size x: 480, y: 720
        JFrame frame = new JFrame("zendoro");
        frame.setSize(480, 720);
        // The program will stop once the user closes the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // No need because I used coordinates
        frame.setLocationRelativeTo(null);
        // prevents user from resizing the frame
        frame.setResizable(false);
        try {
            // Create a reader that reads from username.txt
            BufferedReader reader = new BufferedReader(new FileReader("username.txt"));
            // Line currently being read
            String line = reader.readLine();
            reader.close();
            // Checks if line read is null or if its empty
            if (line != null && !line.isEmpty()) {
                // This will direct the user to the title panel if they have a username
                frame.add(new TitlePanel());
            }
        } catch (IOException e) {
            // Prints Exception
            e.printStackTrace();
            // If the username doesn't exist, it will direct the user to create a username
            frame.add(new UsernamePanel());
        }
        // Setting the frame to be visible
        frame.setVisible(true);
    }
}
