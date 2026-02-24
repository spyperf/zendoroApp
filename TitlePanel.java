
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
        return (480-componentWidth) / 2;
    }

    public static int getSavedMinutes() {
        return savedMinutes;
    }

    public TitlePanel() {
        setBackground(Color.PINK);
        setLayout(null);
        setBounds(0, 0, 480, 720);
        // Saving File Locally:
        JLabel minutesStudied = new JLabel();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
            String line = reader.readLine();
            reader.close();
            // Checks if line read is null or if its empty
            if (line != null && !line.isEmpty()) {
                // checks for space
                line = line.trim();
                if (line.indexOf(" ") == -1) {
                    savedMinutes = Integer.parseInt(line);
                } else {
                    savedMinutes = Integer.parseInt(line.substring(0, line.indexOf(" ")));
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
        minutesStudied.setText(savedMinutes + " Minutes Studied");
        minutesStudied.setFont(new Font("SansSerif", Font.BOLD, 18));
        minutesStudied.setForeground(Color.WHITE);
        minutesStudied.setBounds(centeredX(480), 245, 480, 100);
        minutesStudied.setHorizontalAlignment(SwingConstants.CENTER);
        add(minutesStudied);

        // zendoro text
        ImageIcon zendoroText = new ImageIcon("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\zendoroText.png");
        JLabel titleText = new JLabel(zendoroText);
        titleText.setBounds(centeredX(480), 200, 480, 100);
        titleText.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleText);

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
        JButton leaderboardbutton = new JButton("Leaderboard");
        leaderboardbutton.setBounds(centeredX(110), 500, 110, 70);
        /*leaderboardbutton.setBorderPainted(false);
        leaderboardbutton.setFocusPainted(false);
        leaderboardbutton.setContentAreaFilled(false);*/
        add(leaderboardbutton);

        leaderboardbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                javax.swing.Timer t = new javax.swing.Timer(150, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
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
}
