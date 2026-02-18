
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class TimerPanel extends JPanel {

    private Timer timer;
    // in seconds
    private int breakTime;
    private int originalBreakTime;
    private int studyTime;
    private int originalStudyTime;
    private int studyPeriods;
    private int originalStudyPeriods;
    private boolean isStudying = true;
    private Clip clipWhite;
    private Clip clipGamma;
    private Clip clipRain;
    private int delay = 1000;

    public TimerPanel(int studyPeriods, int studyTime, int breakTime) {
        this.breakTime = breakTime;
        this.studyTime = studyTime;
        originalBreakTime = this.breakTime;
        originalStudyTime = this.studyTime;
        this.studyPeriods = studyPeriods;
        this.originalStudyPeriods = studyPeriods;
        int previousMinutes = TitlePanel.getSavedMinutes();
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
        amountOfStudyPeriods.setBounds(90, 60, 600, 60);
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
        //CHECKBOXES FOR MUSIC
        // White Noise Audio
        JCheckBox whiteNoisCheckBox = new JCheckBox();
        whiteNoisCheckBox.setText("White Noise");
        whiteNoisCheckBox.setFocusable(false);
        whiteNoisCheckBox.setBounds(0, 0, 200, 30);
        whiteNoisCheckBox.setOpaque(false);
        add(whiteNoisCheckBox);
        whiteNoisCheckBox.addActionListener(e -> {
            try {
                if (whiteNoisCheckBox.isSelected()) {
                    File whiteNoiseFile = new File("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\white_noise.wav");
                    AudioInputStream audioStreamWhite = AudioSystem.getAudioInputStream(whiteNoiseFile);
                    clipWhite = AudioSystem.getClip();
                    clipWhite.open(audioStreamWhite);
                    clipWhite.start();
                    clipWhite.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    if (clipWhite != null && clipWhite.isRunning()) {
                        clipWhite.stop();
                    }
                }
            } catch (Exception exc) {
            }
        });
        // Gamma Waves Audio
        JCheckBox gammaCheckBox = new JCheckBox();
        gammaCheckBox.setText("Gamma Waves");
        gammaCheckBox.setBounds(0, 20, 200, 30);
        gammaCheckBox.setFocusable(false);
        gammaCheckBox.setOpaque(false);
        add(gammaCheckBox);
        gammaCheckBox.addActionListener(e -> {
            try {
                if (gammaCheckBox.isSelected()) {
                    File gammaFile = new File("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\gamma.wav");
                    AudioInputStream audioStreamGamma = AudioSystem.getAudioInputStream(gammaFile);
                    clipGamma = AudioSystem.getClip();
                    clipGamma.open(audioStreamGamma);
                    clipGamma.start();
                    clipGamma.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    if (clipGamma != null && clipGamma.isRunning()) {
                        clipGamma.stop();
                    }
                }
            } catch (Exception exc) {
            }
        });
        // Rain Noise Audio
        JCheckBox rainCheckBox = new JCheckBox();
        rainCheckBox.setText("Rain Audio");
        rainCheckBox.setBounds(0, 40, 200, 30);
        rainCheckBox.setFocusable(false);
        rainCheckBox.setOpaque(false);
        add(rainCheckBox);
        rainCheckBox.addActionListener(e -> {
            try {
                if (rainCheckBox.isSelected()) {
                    File rainFile = new File("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\gammaWithRain.wav");
                    AudioInputStream audioStreamRain = AudioSystem.getAudioInputStream(rainFile);
                    clipRain = AudioSystem.getClip();
                    clipRain.open(audioStreamRain);
                    clipRain.start();
                    clipRain.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    if (clipRain != null && clipRain.isRunning()) {
                        clipRain.stop();
                    }
                }
            } catch (Exception exc) {
            }
        });
        /*
        // Clock ticking
        try {
            File clockTickingFile = new File("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\clock_ticking.wav");
            AudioInputStream audioStreamClock = AudioSystem.getAudioInputStream(clockTickingFile);
            clip = AudioSystem.getClip();
            clip.open(audioStreamClock);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception exc) {
        }
         */
        add(timerLabel);
        add(amountOfStudyPeriods);
        setBackground(new Color(255, 150, 150));
        add(studyingLabel);
        timer = new Timer(delay, e -> {
            // Is currently studying
            if (isStudying == true && this.studyPeriods > 0) {
                add(studyingLabel);
                remove(breakingLabel);
                setBackground(new Color(255, 150, 150));
                revalidate();
                repaint();
                this.studyTime--;
                // minutes : seconds
                timerLabel.setText(String.valueOf(this.studyTime / 60 + ":" + this.studyTime % 60));
                // When study time has ended
                if (this.studyTime <= 0) {
                    this.breakTime = originalBreakTime;
                    this.studyPeriods--;
                    amountOfStudyPeriods.setText("Number of Study Periods: " + String.valueOf(this.studyPeriods));
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
                // When break time has ended
                if (this.breakTime <= 0) {
                    this.studyTime = originalStudyTime;
                    isStudying = true;
                } else if (this.breakTime % 60 < 10) {
                    timerLabel.setText(this.breakTime / 60 + ":" + "0" + this.breakTime % 60);
                }
            }
            if (this.studyPeriods <= 0) {
                timer.stop();
                // Total minutes studied is saved
                int totalMinutes = originalStudyTime * originalStudyPeriods / 60;
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
                    writer.write(previousMinutes + totalMinutes + " minutes studied");
                    writer.close();
                    // variable e is already defined in constructor TimerPanel(int,int,int), so change to "ex"
                } catch (IOException ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
                }
                setBackground(Color.PINK);
                timerLabel.setVisible(false);
                breakingLabel.setVisible(false);
                studyingLabel.setVisible(false);
                amountOfStudyPeriods.setVisible(false);
                instructions.setVisible(false);
                revalidate();
                repaint();
            }
        });
        timer.start();
        // When reset timer button is hit:
        JButton resetButton = new JButton("Stop Timer");
        resetButton.setBounds(100, 600, 300, 40);
        add(resetButton);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                if (clipWhite != null && clipWhite.isRunning()) {
                        clipWhite.stop();
                }
                if (clipGamma != null && clipGamma.isRunning()) {
                        clipGamma.stop();
                }
                if (clipRain != null && clipRain.isRunning()) {
                        clipRain.stop();
                }
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(TimerPanel.this);
                frame.getContentPane().removeAll();
                frame.add(new OptionsPanel());
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}
