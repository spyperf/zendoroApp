
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class TitlePanel extends JPanel {

    private static int savedMinutes = 0;

    // Center components
    private int centeredX(int componentWidth) {
        return (480 - componentWidth) / 2;
    }

    public static int getSavedMinutes() {
        return savedMinutes;
    }

    public TitlePanel() {
        setBackground(Color.PINK);
        setLayout(null);
        setBounds(0, 0, 480, 720);
        // Saving File Locally:
        try {
            BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
            String line = reader.readLine();
            reader.close();
            // Checks if line read is null or if its empty
            if (line != null && !line.isEmpty()) {
                // Since output.txt contains invalid data, we trim any spaces or other abnormal characters, and catch the exception
                line = line.trim();
                try {
                    savedMinutes = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    savedMinutes = 0;
                }
            } else {
                savedMinutes = 0;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            // File not exisiting?
            savedMinutes = 0;
            e.printStackTrace();
        }

        // zendoro text
        ImageIcon zendoroText = new ImageIcon("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\zendoroText.png");
        JLabel titleText = new JLabel(zendoroText);
        titleText.setBounds(0, 200, 480, 100);
        titleText.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleText);

        try {
            BufferedReader firstReader = new BufferedReader(new FileReader("first.txt"));
            String first = firstReader.readLine();
            BufferedReader usernameReader = new BufferedReader(new FileReader("username.txt"));
            String username = usernameReader.readLine();
            if (first != null && username != null && first.trim().equals(username)) {
                // gold zendoro text (if they're number one)
                ImageIcon zendoroTextGold = new ImageIcon("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\zendoroTextGold.png");
                titleText.setIcon(zendoroTextGold);
                titleText.revalidate();
                titleText.repaint();
            }
        } catch (Exception e) {
        }

        // Start Button
        ImageIcon startF1 = new ImageIcon("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\startF1.png");
        ImageIcon startF2 = new ImageIcon("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\startF2.png");
        JButton startButton = new JButton(startF1);
        startButton.setBounds(centeredX(110), 400, 110, 70);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);
        add(startButton);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startButton.setIcon(startF2);
                javax.swing.Timer t = new javax.swing.Timer(150, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(TitlePanel.this);
                        frame.getContentPane().removeAll();
                        frame.add(new OptionsPanel());
                        frame.revalidate();
                        frame.repaint();
                        ((javax.swing.Timer) evt.getSource()).stop();
                    }
                });
                t.setRepeats(false);
                t.start();
            }
        });

        // Leaderboard Button
        ImageIcon rankF1 = new ImageIcon("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\rankF1.png");
        ImageIcon rankF2 = new ImageIcon("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\rankF2.png");
        JButton leaderboardbutton = new JButton(rankF1);
        leaderboardbutton.setBounds(centeredX(110), 480, 110, 70);
        leaderboardbutton.setBorderPainted(false);
        leaderboardbutton.setFocusPainted(false);
        leaderboardbutton.setContentAreaFilled(false);
        add(leaderboardbutton);
        try {
            // Checks if output.txt and username.txt have information and data
            BufferedReader outputReader = new BufferedReader(new FileReader("output.txt"));
            String output = outputReader.readLine();
            BufferedReader usernameReader = new BufferedReader(new FileReader("username.txt"));
            String username = usernameReader.readLine();
            outputReader.close();
            usernameReader.close();
            if (output != null && !output.isEmpty() && username != null && !username.isEmpty()) {
                leaderboardbutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        leaderboardbutton.setIcon(rankF2);
                        javax.swing.Timer t = new javax.swing.Timer(150, new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                leaderboardbutton.setIcon(rankF1);
                                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(TitlePanel.this);
                                frame.getContentPane().removeAll();
                                frame.add(new LeaderboardPanel());
                                frame.revalidate();
                                frame.repaint();
                                ((javax.swing.Timer) evt.getSource()).stop();
                            }
                        });
                        t.setRepeats(false);
                        t.start();
                    }
                });
            }
        } catch (Exception e) {
        }
    }
}
