
import java.awt.*;
import java.io.*;
import javax.swing.*;

public class UsernamePanel extends JPanel {

    // Center components
    private int centeredX(int componentWidth) {
        return (480 - componentWidth) / 2;
    }

    public UsernamePanel() {
        setBackground(Color.PINK);
        setLayout(null);
        setBounds(0, 0, 480, 720);
        JLabel usernameLabel = new JLabel("Enter a username (this action is permanent):");
        usernameLabel.setBounds(centeredX(200), 20, 200, 20);
        usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        add(usernameLabel);
        JTextField usernameTextField = new JTextField();
        usernameTextField.setBounds(centeredX(200), 60, 200, 20);
        add(usernameTextField);
        usernameTextField.addActionListener(e -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("username.txt"))) { 
                writer.write(usernameTextField.getText());
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(UsernamePanel.this);
                frame.getContentPane().removeAll();
                frame.add(new TitlePanel());
                frame.revalidate();
                frame.repaint();
            } catch (IOException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }
        }
        );
    }
}
