
import java.io.*;
import javax.swing.*;

public class zendoro {
    public static int savedMinutes = 0;
    public static void main(String[] args) {
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
                }
                else {
                    savedMinutes = Integer.parseInt(line.substring(0,line.indexOf(" ")));
                }
            }
            else {
                savedMinutes = 0;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            // File not exisiting?
            savedMinutes = 0;
            e.printStackTrace();
        }
        minutesStudied.setText(savedMinutes + " minutes studied");
        minutesStudied.setBounds(0, 100, 480, 100);
        minutesStudied.setHorizontalAlignment(SwingConstants.CENTER);
        JFrame frame = new JFrame("zendoro");
        frame.setSize(480, 720);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(minutesStudied);
        TitlePanel titleScreen = new TitlePanel();
        frame.add(titleScreen);
        frame.setVisible(true);
    }
}
