
import java.awt.*;
import java.io.*;
import javax.swing.*;

public class UsernamePanel extends JPanel {

    // Center components
    private int centeredX(int componentWidth) {
        return (480 - componentWidth) / 2;
    }

    public UsernamePanel() {
        // Pink background
        setBackground(Color.PINK);
        // No layout
        setLayout(null);
        // Panel of the same size as the frame at (0,0)
        setBounds(0, 0, 480, 720);
        // Instructions:
        JLabel usernameLabel = new JLabel("Enter a username (this action is permanent):");
        usernameLabel.setBounds(centeredX(200), 20, 300, 20);
        usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        add(usernameLabel);
        // JTextField - allows user to input value
        JTextField usernameTextField = new JTextField();
        usernameTextField.setBounds(centeredX(200), 60, 200, 20);
        add(usernameTextField);
        // When an event is triggered when the user types enter...
        usernameTextField.addActionListener(e -> {
            // Can't allow username that is empty, has a space, or is too long
            if (usernameTextField.getText().indexOf(" ") >= 0 || usernameTextField.getText().equals("") || usernameTextField.getText().length() > 18) {
                return;
            }
            // Check for username duplicates
            try (BufferedReader reader = new BufferedReader(new FileReader("leaderboard.txt"))) {
                // Going through each line in loop, so initalize line outside of loop
                String line;
                // Must initialize line outside of while loop while checking everytime if the line is empty or not
                while (((line = reader.readLine()) != null)) {
                    // splits the String into seperate smaller Strings that are seperated by spaces
                    String[] parts = line.split(" ");
                    // Gets the username from the leaderboard and trism it to prevent trailing spaces
                    String leaderboardUsername = parts[0].trim();
                    // If the username from the leaderboard is the same as the user's input...
                    if (leaderboardUsername.equals(usernameTextField.getText())) {
                        reader.close();
                        // Will not let user choose this username
                        return;
                    }
                }
            } catch (IOException ex) {
                // Prints Exception
                ex.printStackTrace();
            }
            // Writing new username to username.txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("username.txt"))) {
                // Writes whatever is in the TextField to the file of username.txt
                writer.write(usernameTextField.getText());
                // Returns the window/frame
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(UsernamePanel.this);
                // Removes all instances
                frame.getContentPane().removeAll();
                // TitlePanel is created
                frame.add(new TitlePanel());
                // Update and refresh data
                frame.revalidate();
                frame.repaint();
            } catch (IOException ex) {
                // Prints Exception
                ex.printStackTrace();
            }
        }
        );
    }
}
