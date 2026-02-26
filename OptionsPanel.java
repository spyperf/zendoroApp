
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;

public class OptionsPanel extends JPanel {

    // Center components
    private int centeredX(int componentWidth) {
        return (480 - componentWidth) / 2;
    }

    public OptionsPanel() {
        setBackground(Color.PINK);
        setLayout(null);
        setBounds(0, 0, 480, 720);
        // Options Title
        JLabel optionsText = new JLabel("Options: ");
        optionsText.setBounds(centeredX(200), 50, 200, 100);
        optionsText.setHorizontalAlignment(SwingConstants.CENTER);
        optionsText.setFont(new Font("Arial", Font.BOLD, 30));
        optionsText.setForeground(Color.white);
        add(optionsText);
        // 1 to 6 study periods
        JLabel studyPeriodsText = new JLabel("# of Study Periods");
        studyPeriodsText.setBounds(0, 125, 480, 100);
        studyPeriodsText.setHorizontalAlignment(SwingConstants.CENTER);
        studyPeriodsText.setFont(new Font("Arial", Font.BOLD, 20));
        studyPeriodsText.setForeground(Color.white);
        JSlider amountOfStudyPeriods = new JSlider(1, 6, 4);
        amountOfStudyPeriods.setBounds(90, 200, 300, 50);
        // removes bg
        amountOfStudyPeriods.setOpaque(false);
        amountOfStudyPeriods.setPaintTicks(true);
        amountOfStudyPeriods.setPaintTrack(true);
        amountOfStudyPeriods.setPaintLabels(true);
        amountOfStudyPeriods.setMajorTickSpacing(1);
        amountOfStudyPeriods.setUI(new BasicSliderUI(amountOfStudyPeriods) {
            public void paintTrack(Graphics g) {
                g.setColor(Color.white);
                // Centering track
                g.fillRect(trackRect.x, trackRect.y + trackRect.height / 2 - 2, trackRect.width, 4);
            }

            public void paintThumb(Graphics g) {
                g.setColor(Color.white);
                // Centering thumb
                g.fillOval(thumbRect.x - 5, thumbRect.y, 20, 20);
                // Prevent thumb from copying itself
                revalidate();
                repaint();
            }
        });
        add(studyPeriodsText);
        add(amountOfStudyPeriods);

        // 20-60 minute study periods 
        JLabel timeForStudy = new JLabel("Study Time (mins)");
        timeForStudy.setBounds(0, 260, 480, 100);
        timeForStudy.setHorizontalAlignment(SwingConstants.CENTER);
        timeForStudy.setFont(new Font("Arial", Font.BOLD, 20));
        timeForStudy.setForeground(Color.white);
        JSlider studyTime = new JSlider(20, 60, 25);
        studyTime.setBounds(90, 335, 300, 50);
        // removes bg
        studyTime.setOpaque(false);
        studyTime.setPaintTicks(true);
        studyTime.setPaintTrack(true);
        studyTime.setPaintLabels(true);
        studyTime.setMajorTickSpacing(5);
        studyTime.setSnapToTicks(true);
        studyTime.setUI(new BasicSliderUI(studyTime) {
            public void paintTrack(Graphics g) {
                g.setColor(Color.white);
                // Centering track
                g.fillRect(trackRect.x, trackRect.y + trackRect.height / 2 - 2, trackRect.width, 4);
            }

            public void paintThumb(Graphics g) {
                g.setColor(Color.white);
                // Centering thumb
                g.fillOval(thumbRect.x - 5, thumbRect.y, 20, 20);
                // Prevent thumb from copying itself
                revalidate();
                repaint();
            }
        });
        add(studyTime);
        add(timeForStudy);

        // 5 to 10 minute breaks
        JLabel timeForBreak = new JLabel("Break Time (mins)");
        timeForBreak.setBounds(0, 395, 480, 100);
        timeForBreak.setHorizontalAlignment(SwingConstants.CENTER);
        timeForBreak.setFont(new Font("Arial", Font.BOLD, 20));
        timeForBreak.setForeground(Color.white);
        JSlider breakTime = new JSlider(5, 10, 5);
        breakTime.setBounds(90, 470, 300, 50);
        // removes bg
        breakTime.setOpaque(false);
        breakTime.setPaintTicks(true);
        breakTime.setPaintTrack(true);
        breakTime.setPaintLabels(true);
        breakTime.setMajorTickSpacing(1);
        breakTime.setUI(new BasicSliderUI(breakTime) {
            public void paintTrack(Graphics g) {
                g.setColor(Color.white);
                // Centering track
                g.fillRect(trackRect.x, trackRect.y + trackRect.height / 2 - 2, trackRect.width, 4);
            }

            public void paintThumb(Graphics g) {
                g.setColor(Color.white);
                // Centering thumb
                g.fillOval(thumbRect.x - 5, thumbRect.y, 20, 20);
                // Prevent thumb from copying itself
                revalidate();
                repaint();
            }
        });
        add(breakTime);
        add(timeForBreak);

        // Next button
        ImageIcon nextF1 = new ImageIcon("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\nextF1.png");
        ImageIcon nextF2 = new ImageIcon("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\nextF2.png");
        JButton nextButton = new JButton(nextF1);
        nextButton.setBounds(centeredX(110) + 100, 550, 110, 70);
        nextButton.setBorderPainted(false);
        nextButton.setFocusPainted(false);
        nextButton.setContentAreaFilled(false);
        add(nextButton);
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextButton.setIcon(nextF2);
                javax.swing.Timer t = new javax.swing.Timer(150, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        // Putting values into TimerPanel class
                        int periods = amountOfStudyPeriods.getValue();
                        int study = studyTime.getValue() * 60;
                        // cannot use "break" keyword to name a variable
                        int breaks = breakTime.getValue() * 60;
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(OptionsPanel.this);
                        frame.getContentPane().removeAll();
                        frame.add(new TimerPanel(periods, study, breaks));
                        frame.revalidate();
                        frame.repaint();
                    }
                });
                t.setRepeats(false);
                t.start();
            }
        });

        // Back button
        ImageIcon backF1 = new ImageIcon("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\backF1.png");
        ImageIcon backF2 = new ImageIcon("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\backF2.png");
        JButton backButton = new JButton(backF1);
        backButton.setBounds(centeredX(110) - 100, 550, 110, 70);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backButton.setIcon(backF2);
                javax.swing.Timer t = new javax.swing.Timer(150, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(OptionsPanel.this);
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
