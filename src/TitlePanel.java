
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class TitlePanel extends JPanel {

    private static int savedMinutes = 0;

    // Center components
    private int centeredX(int componentWidth) {
        return (480 - componentWidth) / 2;
    }

    // This method will return the saved minutes
    public static int getSavedMinutes() {
        return savedMinutes;
    }

    public TitlePanel() {
        // Pink background
        setBackground(Color.PINK);
        // No layout
        setLayout(null);
        // Panel at (0,0), width of 480, height 720
        setBounds(0, 0, 480, 720);
        // Saving File Locally:
        try {
            // Reading information from output.txt
            BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
            String line = reader.readLine();
            reader.close();
            // Checks if line read is null or if its empty
            if (line != null && !line.isEmpty()) {
                // Since output.txt contains invalid data, we trim any spaces or other abnormal characters, and catch the exception
                line = line.trim();
                try {
                    // String line becomes an Integer
                    savedMinutes = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    // Prints Exception
                    e.printStackTrace();
                    // If there is invalid data, then we set savedMinutes to 0
                    savedMinutes = 0;
                }
            } else {
                // If it is null or empty, we set it to 0
                savedMinutes = 0;
            }
        } catch (IOException e) {
            // Prints Exception
            e.printStackTrace();
            // File not exisiting? Automatically assume it is 0 (user has not ever studied)
            savedMinutes = 0;
        }

        // zendoro text (this will be stored in the images folder in the src, so users can be able to see the images)
        ImageIcon zendoroText = new ImageIcon("src/images/zendoroText.png");
        // New JLabel at (0,200) with width 480 and height 100
        JLabel titleText = new JLabel(zendoroText);
        titleText.setBounds(0, 200, 480, 100);
        // Centering JLabel
        titleText.setHorizontalAlignment(SwingConstants.CENTER);
        // Appear on panel
        add(titleText);

        try {
            // The first reader will read from first.txt (the user who is first on the leaderboard)
            BufferedReader firstReader = new BufferedReader(new FileReader("first.txt"));
            String first = firstReader.readLine();
            // The second reader will read from username.txt (to compare the usernames)
            BufferedReader usernameReader = new BufferedReader(new FileReader("username.txt"));
            String username = usernameReader.readLine();
            // If first.txt does contain a username, and first and username are not null, and they are both equal:
            if (!first.isEmpty() && first != null && username != null && first.trim().equals(username)) {
                // gold zendoro text (if they're number one)
                ImageIcon zendoroTextGold = new ImageIcon("src/images/zendoroTextGold.png");
                // Sets the Title Logo to the new Gold Icon Logo
                titleText.setIcon(zendoroTextGold);
                // Refresh and update changes
                titleText.revalidate();
                titleText.repaint();
            }
        } catch (Exception e) {
            // Prints Exception
            e.printStackTrace();
        }

        // Start Button
        ImageIcon startF1 = new ImageIcon("src/images/startF1.png");
        // Start button animation 2 (pressing down animation)
        ImageIcon startF2 = new ImageIcon("src/images/startF2.png");
        JButton startButton = new JButton(startF1);
        startButton.setBounds(centeredX(110), 400, 110, 70);
        // Set its border, and every component to transparent
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);
        add(startButton);

        // Start button has been pressed --> action happens
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switches to animation 2
                startButton.setIcon(startF2);
                // Timer for 150 ms until it will switch to next panel
                javax.swing.Timer t = new javax.swing.Timer(150, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        // Returns window
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(TitlePanel.this);
                        // Removes all instances
                        frame.getContentPane().removeAll();
                        // Adds the OptionsPanel
                        frame.add(new OptionsPanel());
                        // Updates and refreshes the frame to show OptionsPanel
                        frame.revalidate();
                        frame.repaint();
                        // Stops the Timer (cast to Timer since getSource() outputs an Object)
                        ((javax.swing.Timer) evt.getSource()).stop();
                    }
                });
                // The timer will not repeat (one-time animation)
                t.setRepeats(false);
                t.start();
            }
        });

        // Leaderboard Button
        ImageIcon rankF1 = new ImageIcon("src/images/rankF1.png");
        // Same concept as start button (animation 2 for when pressed down)
        ImageIcon rankF2 = new ImageIcon("src/images/rankF2.png");
        JButton leaderboardbutton = new JButton(rankF1);
        leaderboardbutton.setBounds(centeredX(110), 480, 110, 70);
        // All components of the button are transparent except for the two ImageIcons
        leaderboardbutton.setBorderPainted(false);
        leaderboardbutton.setFocusPainted(false);
        leaderboardbutton.setContentAreaFilled(false);
        // Adding to frame
        add(leaderboardbutton);
        try {
            // Checks if output.txt and username.txt have information and data
            BufferedReader outputReader = new BufferedReader(new FileReader("output.txt"));
            String output = outputReader.readLine();
            BufferedReader usernameReader = new BufferedReader(new FileReader("username.txt"));
            String username = usernameReader.readLine();
            outputReader.close();
            usernameReader.close();
            // If both output and username are not null and are not empty...
            if (output != null && !output.isEmpty() && username != null && !username.isEmpty()) {
                // Then the action will happen
                leaderboardbutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Setting icon to animation 2
                        leaderboardbutton.setIcon(rankF2);
                        // 150ms Timer
                        javax.swing.Timer t = new javax.swing.Timer(150, new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                // Returns the window
                                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(TitlePanel.this);
                                // Removes all components and objects on the frame
                                frame.getContentPane().removeAll();
                                // Loads in LeaderboardPanel()
                                frame.add(new LeaderboardPanel());
                                // Refreshes and updates to save changes made
                                frame.revalidate();
                                frame.repaint();
                                // 150 ms timer will finish after one time
                                ((javax.swing.Timer) evt.getSource()).stop();
                            }
                        });
                        // One-time timer
                        t.setRepeats(false);
                        t.start();
                    }
                });
            }
            // Or else, the user cannot enter the LeaderboardPanel() (without a information in username.txt and output.txt)
        } catch (Exception e) {
            // Prints Exception
            e.printStackTrace();
        }
    }
}
