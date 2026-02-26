
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class LeaderboardPanel extends JPanel {

    // Center components
    private int centeredX(int componentWidth) {
        return (480 - componentWidth) / 2;
    }

    public LeaderboardPanel() {
        setBackground(Color.PINK);
        setLayout(null);
        setBounds(0, 0, 480, 720);

        // Stores an array of String that contain the username and total minutes studied
        ArrayList<String[]> leaderboard = new ArrayList<String[]>();

        // Loading existing leaderboard
        try {
            BufferedReader reader = new BufferedReader(new FileReader("leaderboard.txt"));
            String line = reader.readLine();
            // Checks if line is null or empty
            while (line != null && !line.isEmpty()) {
                // This will split it into an array of Strings
                String[] parts = line.split(" ");
                // Adding username and minutes
                leaderboard.add(new String[]{parts[0], parts[1]});
                // Read leaderboard again
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }

        // Loading username
        // Variable is not in try-catch so it can be used later
        String username = "";
        try {
            // Username is stored in username.txt
            BufferedReader reader = new BufferedReader(new FileReader("username.txt"));
            username = reader.readLine();
            reader.close();
        } catch (Exception e) {
        }

        // Loading minutes
        // Variable is not in try-catch so it can be used later
        int addMinutes = 0;
        try {
            // Minutes is stored in output.txt
            BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                addMinutes = Integer.parseInt(line);
            }
            reader.close();

            // Reset output.txt so minutes aren't added
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
                writer.write("0");
                writer.close();
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }

        // Only updates the leaderboard if the user studied
        if (addMinutes > 0) {
            //Updating leaderboard (accounting for if they do and don't have username on leaderboard)

            boolean hasUsername = false;
            for (int i = 0; i < leaderboard.size(); i++) {
                // Checking until username is found
                if (leaderboard.get(i)[0].equals(username)) {
                    // Add time to old time
                    leaderboard.get(i)[1] = String.valueOf(Integer.parseInt(leaderboard.get(i)[1]) + addMinutes);
                    // So now the program knows there is a username, no need to continue searching for it
                    hasUsername = true;
                    break;
                }
            }
            // If the code still doesn't find the username then it will add the username to the leaderboard
            if (hasUsername == false) {
                // Since the user is not on the leaderboard, the additional minutes will be the initial value added to the leaderboard
                leaderboard.add(new String[]{username, String.valueOf(addMinutes)});
            }
        }

        //Part of the java.util class. Collections.sort() is simillar to Arrays.sort(), but you can also use lists instead of only arrays
        Collections.sort(leaderboard, (a, b) -> Integer.parseInt(b[1]) - Integer.parseInt(a[1]));

        // Changing file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("leaderboard.txt"));
            // Will write every existing username and total study time
            for (int i = 0; i < leaderboard.size(); i++) {
                writer.write(leaderboard.get(i)[0] + " " + leaderboard.get(i)[1]);
                // Writes on a new line
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
        }

        // Displaying only Top 10 People on Leaderboard
        JLabel leaderboardTitleLabel = new JLabel("Leaderboard:");
        leaderboardTitleLabel.setBounds(60, 50, 200, 60);
        leaderboardTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        leaderboardTitleLabel.setForeground(Color.WHITE);
        add(leaderboardTitleLabel);
        for (int i = 0; i < leaderboard.size(); i++) {
            // Returns the username at the 'i'th index of the ArrayList and 0th index of the array
            String user = leaderboard.get(i)[0];
            // Returns the minutes studied at the 'i'th index of the ArrayList and 1st index of the array
            String minutes = leaderboard.get(i)[1];
            // The label of the leaderboard (actually putting information of Top 10 people from leaderboard.txt into the panel)
            JLabel leaderboardLabel = new JLabel((i + 1) + ". " + user + " - " + minutes + " Minutes");
            // Space after every ranking
            leaderboardLabel.setBounds(60, 100 + i * 20, 400, 30);
            leaderboardLabel.setFont(new Font("Arial", Font.BOLD, 14));
            leaderboardLabel.setForeground(Color.WHITE);
            add(leaderboardLabel);
            // Putting first place user in first.txt, so we can change the title text to gold
            if (i == 0) {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("first.txt"));
                    writer.write(user);
                    writer.close();
                } catch (Exception e) {
                }
            }
            //If we did a for loop for leaderboard.size(), it would crash if there were too many people.
            // But, since we cannot make the for loop stop at i = 10 when there isn't 10 people on the leaderboard, we do an if-statement here
            if (i == 9) {
                break;
            }
        }
        // Displaying user's own ranking (if they want to see their time but it is not on leaderboard)
        int ownRanking = -1;
        String ownMinutes = "0";
        for (int i = 0; i < leaderboard.size(); i++) {
            if (leaderboard.get(i)[0].equals(username)) {
                ownRanking = i+1;
                ownMinutes = leaderboard.get(i)[1];
                break;
            }
        }
        JLabel ownRankingLabel = new JLabel("Your Rank: " + ownRanking + ". " + username + " - " + ownMinutes + " Minutes");
        // Space after every ranking
        ownRankingLabel.setBounds(60, 500, 400, 30);
        ownRankingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ownRankingLabel.setForeground(Color.WHITE);
        add(ownRankingLabel);
        // Leaderboard System Complete

        // Back button to go to TitlePanel() (same back button as in OptionsPanel())
        ImageIcon backF1 = new ImageIcon("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\backF1.png");
        ImageIcon backF2 = new ImageIcon("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\backF2.png");
        JButton backButton = new JButton(backF1);
        backButton.setBounds(centeredX(110), 550, 110, 70);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backButton.setIcon(backF2);
                javax.swing.Timer t = new javax.swing.Timer(150, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(LeaderboardPanel.this);
                        frame.getContentPane().removeAll();
                        frame.add(new TitlePanel());
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
