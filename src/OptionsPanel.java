
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
        // Pink background
        setBackground(Color.PINK);
        // No layout
        setLayout(null);
        // Panl at 0,0 size 480, 720 (same as others)
        setBounds(0, 0, 480, 720);
        // Options Title
        JLabel optionsText = new JLabel("Options: ");
        optionsText.setBounds(centeredX(200), 50, 200, 100);
        // Centering component
        optionsText.setHorizontalAlignment(SwingConstants.CENTER);
        optionsText.setFont(new Font("Arial", Font.BOLD, 30));
        // Whtie text colour
        optionsText.setForeground(Color.white);
        // Add to frame
        add(optionsText);
        // 1 to 6 study periods
        JLabel studyPeriodsText = new JLabel("# of Study Periods");
        // All JLabel components will be the same except for their dimension and their location
        studyPeriodsText.setBounds(0, 125, 480, 100);
        studyPeriodsText.setHorizontalAlignment(SwingConstants.CENTER);
        studyPeriodsText.setFont(new Font("Arial", Font.BOLD, 20));
        studyPeriodsText.setForeground(Color.white);
        // SLIDER (Consists of a base and a knob) from 1-6 value(s)
        JSlider amountOfStudyPeriods = new JSlider(1, 6, 4);
        amountOfStudyPeriods.setBounds(90, 200, 300, 50);
        // removes bg
        amountOfStudyPeriods.setOpaque(false);
        // remove highlight/border
        amountOfStudyPeriods.setFocusable(false);
        // Display the ticks, marks, and the labels
        amountOfStudyPeriods.setPaintTicks(true);
        amountOfStudyPeriods.setPaintTrack(true);
        amountOfStudyPeriods.setPaintLabels(true);
        // Going up or down by 1
        amountOfStudyPeriods.setMajorTickSpacing(1);
        // Custom JSlider (overriding the methods)
        amountOfStudyPeriods.setUI(new BasicSliderUI(amountOfStudyPeriods) {
            public void paintTrack(Graphics g) {
                // Track will be completely white
                g.setColor(Color.white);
                // Centering the track (y position --> the average of its y coordinate + its height - 2)
                g.fillRect(trackRect.x, trackRect.y + trackRect.height / 2 - 2, trackRect.width, 4);
            }

            public void paintThumb(Graphics g) {
                // Thumb will also be compeltely white
                g.setColor(Color.white);
                // Centering thumb (x coordinate shifts 5 units)
                g.fillOval(thumbRect.x - 5, thumbRect.y, 20, 20);
                // Prevent thumb from copying itself (if the user slides the JSlider)
                revalidate();
                repaint();
            }
        });
        // Adds JLabel and JSlider
        add(studyPeriodsText);
        add(amountOfStudyPeriods);

        // 20-60 minute study periods 
        // Text: "Study Time (mins)"
        JLabel timeForStudy = new JLabel("Study Time (mins)");
        timeForStudy.setBounds(0, 260, 480, 100);
        timeForStudy.setHorizontalAlignment(SwingConstants.CENTER);
        timeForStudy.setFont(new Font("Arial", Font.BOLD, 20));
        timeForStudy.setForeground(Color.white);
        // JSlider fromv alues of 20-60 mins
        JSlider studyTime = new JSlider(20, 60, 25);
        studyTime.setBounds(90, 335, 300, 50);
        // removes bg
        studyTime.setOpaque(false);
        // remove highlight/border
        studyTime.setFocusable(false);
        // Showing track, ticks, and labels
        studyTime.setPaintTicks(true);
        studyTime.setPaintTrack(true);
        studyTime.setPaintLabels(true);
        // Now, sets the intervals to 5 (increase and decrease by 5 mins)
        studyTime.setMajorTickSpacing(5);
        // Forces to snap between 5 integers
        studyTime.setSnapToTicks(true);
        // Custom JSlider Component (same as before)
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
        // Adding the JLabel and JSlider
        add(studyTime);
        add(timeForStudy);

        // 5 to 10 minute breaks
        // JLabel: Break time (mins)
        JLabel timeForBreak = new JLabel("Break Time (mins)");
        timeForBreak.setBounds(0, 395, 480, 100);
        timeForBreak.setHorizontalAlignment(SwingConstants.CENTER);
        timeForBreak.setFont(new Font("Arial", Font.BOLD, 20));
        timeForBreak.setForeground(Color.white);
        // JSLider for the break time at min 5 mins and max 10 mins
        JSlider breakTime = new JSlider(5, 10, 5);
        breakTime.setBounds(90, 470, 300, 50);
        // removes bg
        breakTime.setOpaque(false);
        // remove highlight/border
        breakTime.setFocusable(false);
        breakTime.setPaintTicks(true);
        breakTime.setPaintTrack(true);
        breakTime.setPaintLabels(true);
        // Go up by 1 mins
        breakTime.setMajorTickSpacing(1);
        // Same custom JSlider as before
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
        // Adding JLabel and JSlider to the frame
        add(breakTime);
        add(timeForBreak);

        // Next button
        ImageIcon nextF1 = new ImageIcon("src/images/nextF1.png");
        // animation 2
        ImageIcon nextF2 = new ImageIcon("src/images/nextF2.png");
        JButton nextButton = new JButton(nextF1);
        nextButton.setBounds(centeredX(110) + 100, 550, 110, 70);
        // Transparent components of JButton
        nextButton.setBorderPainted(false);
        nextButton.setFocusPainted(false);
        nextButton.setContentAreaFilled(false);
        // Adding it to the frame
        add(nextButton);
        // Actions happens once nextButton has been pressed:
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to animation 2 (held down animation)
                nextButton.setIcon(nextF2);
                javax.swing.Timer t = new javax.swing.Timer(150, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        // Putting values into TimerPanel class
                        int periods = amountOfStudyPeriods.getValue();
                        int study = studyTime.getValue() * 60;
                        // cannot use "break" keyword to name a variable
                        int breaks = breakTime.getValue() * 60;
                        // Returns the window
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(OptionsPanel.this);
                        // Removes all instances
                        frame.getContentPane().removeAll();
                        // These three parameters are carried information from the OptionsPanel()
                        frame.add(new TimerPanel(periods, study, breaks));
                        // Update and refresh
                        frame.revalidate();
                        frame.repaint();
                    }
                });
                // Timer runs once
                t.setRepeats(false);
                t.start();
            }
        });

        // Back button
        ImageIcon backF1 = new ImageIcon("src/images/backF1.png");
        ImageIcon backF2 = new ImageIcon("src/images/backF2.png");
        JButton backButton = new JButton(backF1);
        backButton.setBounds(centeredX(110) - 100, 550, 110, 70);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        add(backButton);

        // Same logic as next button
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
                        // Stopping 150 ms timer
                        ((javax.swing.Timer) evt.getSource()).stop();
                    }
                });
                t.setRepeats(false);
                t.start();
            }
        });
    }
}
