import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TitlePanel extends JPanel {

    public TitlePanel() {
        setBackground(Color.PINK);
        setLayout(null);
        setBounds(0, 0, 480, 720);
        JLabel titleText = new JLabel("zendoro");
        titleText.setBounds(0, 200, 480, 100);
        titleText.setHorizontalAlignment(SwingConstants.CENTER);
        titleText.setFont(new Font("Arial", Font.BOLD, 40));
        JButton startButton = new JButton("Start");
        startButton.setBounds(80, 600, 300, 40);
        add(startButton);
        add(titleText);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(TitlePanel.this);
                frame.getContentPane().removeAll();
                frame.add(new OptionsPanel());
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}