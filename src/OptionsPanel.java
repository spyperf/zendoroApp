
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OptionsPanel extends JPanel {

    public OptionsPanel() {
        setBackground(Color.PINK);
        setLayout(null);
        setBounds(0, 0, 480, 720);

        // 1 to 6 study periods
        JLabel studyPeriodsText = new JLabel("# of Study Periods");
        studyPeriodsText.setBounds(0, 125, 480, 100);
        studyPeriodsText.setHorizontalAlignment(SwingConstants.CENTER);
        studyPeriodsText.setFont(new Font("Arial", Font.BOLD, 20));
        JSlider amountOfStudyPeriods = new JSlider(1, 6, 4);
        amountOfStudyPeriods.setBounds(90, 200, 300, 50);
        // removes bg
        amountOfStudyPeriods.setOpaque(false);
        amountOfStudyPeriods.setPaintTicks(true);
        amountOfStudyPeriods.setPaintTrack(true);
        amountOfStudyPeriods.setPaintLabels(true);
        amountOfStudyPeriods.setMajorTickSpacing(1);
        add(studyPeriodsText);
        add(amountOfStudyPeriods);

        // 20-60 minute study periods 
        JLabel timeForStudy = new JLabel("Study Time (mins)");
        timeForStudy.setBounds(0, 260, 480, 100);
        timeForStudy.setHorizontalAlignment(SwingConstants.CENTER);
        timeForStudy.setFont(new Font("Arial", Font.BOLD, 20));
        JSlider studyTime = new JSlider(20, 60, 25);
        studyTime.setBounds(90, 335, 300, 50);
        // removes bg
        studyTime.setOpaque(false);
        studyTime.setPaintTicks(true);
        studyTime.setPaintTrack(true);
        studyTime.setPaintLabels(true);
        studyTime.setMajorTickSpacing(5);
        studyTime.setSnapToTicks(true);
        add(studyTime);
        add(timeForStudy);

        // 5 to 10 minute breaks
        JLabel timeForBreak = new JLabel("Break Time (mins)");
        timeForBreak.setBounds(0, 395, 480, 100);
        timeForBreak.setHorizontalAlignment(SwingConstants.CENTER);
        timeForBreak.setFont(new Font("Arial", Font.BOLD, 20));
        JSlider breakTime = new JSlider(5, 10, 5);
        breakTime.setBounds(90, 470, 300, 50);
        // removes bg
        breakTime.setOpaque(false);
        breakTime.setPaintTicks(true);
        breakTime.setPaintTrack(true);
        breakTime.setPaintLabels(true);
        breakTime.setMajorTickSpacing(1);
        add(breakTime);
        add(timeForBreak);

        // Next button
        JButton nextButton = new JButton("Next");
        nextButton.setBounds(300, 600, 80, 40);
        add(nextButton);
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 600, 80, 40);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(OptionsPanel.this);
                frame.getContentPane().removeAll();
                frame.add(new TitlePanel());
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}
