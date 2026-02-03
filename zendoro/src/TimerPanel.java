
import java.awt.*;
import javax.swing.*;

public class TimerPanel extends JPanel {

    private Timer timer;
    // in seconds
    private int breakTime;
    private int originalBreakTime;
    private int studyTime;
    private int originalStudyTime;
    private int studyPeriods;
    private boolean isStudying = true;

    public TimerPanel(int studyPeriods, int studyTime, int breakTime) {
        this.breakTime = breakTime;
        this.studyTime = studyTime;
        originalBreakTime = this.breakTime;
        originalStudyTime = this.studyTime;
        this.studyPeriods = studyPeriods;
        setBackground(Color.PINK);
        setLayout(null);
        setBounds(0, 0, 480, 720);
        JTextArea instructions = new JTextArea("Pressing \"Stop Timer\" will reset the session, and any progress from the current period will be lost.");
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setOpaque(false);
        instructions.setBounds(0, 500, 480, 100);
        instructions.setFont(new Font("Arial", Font.BOLD, 20));
        add(instructions);
        JLabel amountOfStudyPeriods = new JLabel("Number of Study Periods: " + String.valueOf(studyPeriods));
        amountOfStudyPeriods.setFont(new Font("Arial", Font.BOLD, 20));
        amountOfStudyPeriods.setBounds(90, 0, 600, 60);
        JLabel studyingLabel = new JLabel("Study Period");
        studyingLabel.setFont(new Font("Arial", Font.BOLD, 40));
        studyingLabel.setBounds(90, 100, 300, 60);
        JLabel breakingLabel = new JLabel("Break Period");
        breakingLabel.setFont(new Font("Arial", Font.BOLD, 40));
        breakingLabel.setBounds(90, 100, 300, 60);
        JLabel timerLabel = new JLabel(String.valueOf(studyTime / 60 + ":" + "0" + studyTime % 60));
        timerLabel.setFont(new Font("Arial", Font.BOLD, 40));
        timerLabel.setBounds(80, 300, 320, 80);
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(timerLabel);
        add(amountOfStudyPeriods);
        setBackground(new Color(255, 150, 150));
        add(studyingLabel);
        timer = new Timer(10, e -> {
            // Is currently studying
            if (isStudying == true) {
                add(studyingLabel);
                remove(breakingLabel);
                setBackground(new Color(255, 150, 150));
                revalidate();
                repaint();
                this.studyTime--;
                // minutes : seconds
                timerLabel.setText(String.valueOf(this.studyTime / 60 + ":" + this.studyTime % 60));
                if (this.studyTime <= 0) {
                    this.breakTime = originalBreakTime;
                    this.studyPeriods--;
                    amountOfStudyPeriods.setText("Number of Study Periods: " + String.valueOf(studyPeriods));
                    isStudying = false;
                } else if (this.studyTime % 60 < 10) {
                    timerLabel.setText(this.studyTime / 60 + ":" + "0" + this.studyTime % 60);
                } // Is not currently studying
            }
            if (isStudying == false) {
                add(breakingLabel);
                remove(studyingLabel);
                setBackground(new Color(150, 255, 150));
                revalidate();
                repaint();
                this.breakTime--;
                timerLabel.setText(String.valueOf(this.breakTime / 60 + ":" + this.breakTime % 60));
                if (breakTime <= 0) {
                    this.studyTime = originalStudyTime;
                    isStudying = true;
                } else if (this.breakTime % 60 < 10) {
                    timerLabel.setText(this.breakTime / 60 + ":" + "0" + this.breakTime % 60);
                }
            }
            if (this.studyPeriods <= 0) {
                timer.stop();
                timerLabel.setText("0:00");
                return;
            }
        });
        timer.start();

        // When reset timer button is hit:
    }
}

// FIX BREAK TIME ISSUE IT IS NOT SOTOPPING
