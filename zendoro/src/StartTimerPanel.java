//THIS CLASS IS CURRENTLY NOT BEING USED

/*

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartTimerPanel extends JPanel {

    private int periods;
    private int study;
    private int breaks;

    public StartTimerPanel(int periods, int study, int brk) {
        this.periods = periods;
        this.study = study;
        this.breaks = breaks;
        setBackground(Color.PINK);
        setLayout(null);
        setBounds(0, 0, 480, 720);
        JTextArea instructions = new JTextArea("Your study session begins when you press \"Start Timer\". Pressing \"Stop Timer\" will reset the session, and any progress from the current period will be lost.");
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setOpaque(false);
        instructions.setBounds(0, 50, 480, 100);
        instructions.setFont(new Font("Arial", Font.BOLD, 20));
        add(instructions);

        JButton startTimer = new JButton("Start Timer");
        startTimer.setBounds(80, 600, 300, 40);
        add(startTimer);
        startTimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(StartTimerPanel.this);
                frame.getContentPane().removeAll();
                frame.add(new TimerPanel(periods, study, breaks));
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}

*/