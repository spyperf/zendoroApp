
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

    // Center components
    private int centeredX(int componentWidth) {
        return (480 - componentWidth) / 2;
    }

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

        // Instructions
        JTextArea instructions = new JTextArea("Pressing \"Stop\" will reset the session, and any progress from the current period will be lost.");
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setOpaque(false);
        instructions.setForeground(Color.white);
        instructions.setBounds(centeredX(480), 500, 480, 100);
        instructions.setFont(new Font("Arial", Font.BOLD, 20));
        add(instructions);

        // Amount of study periods
        JLabel amountOfStudyPeriods = new JLabel("Number of Study Periods: " + String.valueOf(studyPeriods));
        amountOfStudyPeriods.setFont(new Font("Arial", Font.BOLD, 20));
        amountOfStudyPeriods.setForeground(Color.white);
        amountOfStudyPeriods.setBounds(centeredX(300), 150, 300, 60);
        add(amountOfStudyPeriods);

        // Currently studying...
        JLabel studyingLabel = new JLabel("Study Period");
        studyingLabel.setFont(new Font("Arial", Font.BOLD, 40));
        studyingLabel.setForeground(Color.white);
        studyingLabel.setBounds(centeredX(300), 190, 300, 60);
        add(studyingLabel);
        
        // Currently taking a break...
        JLabel breakingLabel = new JLabel("Break Period");
        breakingLabel.setFont(new Font("Arial", Font.BOLD, 40));
        breakingLabel.setForeground(Color.white);
        breakingLabel.setBounds(centeredX(300), 190, 300, 60);

        // Timer Text
        JLabel timerLabel = new JLabel(String.valueOf(studyTime / 60 + ":" + "0" + studyTime % 60));
        timerLabel.setFont(new Font("Arial", Font.BOLD, 40));
        timerLabel.setForeground(Color.white);
        timerLabel.setBounds(175, 300, 320, 80);
        add(timerLabel);
        
        //Checkbox Icons
        ImageIcon checkboxF1 = new ImageIcon("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\checkboxF1.png");
        ImageIcon checkboxF2 = new ImageIcon("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\checkboxF2.png");
        
        // White Noise Audio
        JLabel whiteNoiseLabel = new JLabel("White Noise");
        whiteNoiseLabel.setBounds(60, 0, 200, 60);
        whiteNoiseLabel.setFont(new Font("Arial", Font.BOLD, 18));
        whiteNoiseLabel.setForeground(Color.WHITE);
        add(whiteNoiseLabel);
        JCheckBox whiteNoiseCheckBox = new JCheckBox();
        whiteNoiseCheckBox.setIcon(checkboxF1);
        whiteNoiseCheckBox.setFocusable(false);
        whiteNoiseCheckBox.setBounds(0, 0, 60, 60);
        whiteNoiseCheckBox.setOpaque(false);
        add(whiteNoiseCheckBox);
        whiteNoiseCheckBox.addActionListener(e -> {
            try {
                if (whiteNoiseCheckBox.isSelected()) {
                    whiteNoiseCheckBox.setIcon(checkboxF2);
                    File whiteNoiseFile = new File("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\white_noise.wav");
                    AudioInputStream audioStreamWhite = AudioSystem.getAudioInputStream(whiteNoiseFile);
                    clipWhite = AudioSystem.getClip();
                    clipWhite.open(audioStreamWhite);
                    clipWhite.start();
                    clipWhite.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    if (clipWhite != null && clipWhite.isRunning()) {
                        clipWhite.stop();
                        whiteNoiseCheckBox.setIcon(checkboxF1);
                    }
                }
            } catch (Exception exc) {
            }
        });

        // Gamma Waves Audio
        JLabel gammaWavesLabel = new JLabel("Gamma Waves");
        gammaWavesLabel.setBounds(60, 50, 200, 60);
        gammaWavesLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gammaWavesLabel.setForeground(Color.WHITE);
        add(gammaWavesLabel);
        JCheckBox gammaCheckBox = new JCheckBox();
        gammaCheckBox.setIcon(checkboxF1);
        gammaCheckBox.setBounds(0, 50, 60, 60);
        gammaCheckBox.setFocusable(false);
        gammaCheckBox.setOpaque(false);
        add(gammaCheckBox);
        gammaCheckBox.addActionListener(e -> {
            try {
                if (gammaCheckBox.isSelected()) {
                    gammaCheckBox.setIcon(checkboxF2);
                    File gammaFile = new File("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\gamma.wav");
                    AudioInputStream audioStreamGamma = AudioSystem.getAudioInputStream(gammaFile);
                    clipGamma = AudioSystem.getClip();
                    clipGamma.open(audioStreamGamma);
                    clipGamma.start();
                    clipGamma.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    if (clipGamma != null && clipGamma.isRunning()) {
                        clipGamma.stop();
                        gammaCheckBox.setIcon(checkboxF1);
                    }
                }
            } catch (Exception exc) {
            }
        });

        // Rain Noise Audio
        JLabel rainNoiseLabel = new JLabel("Rain Noise");
        rainNoiseLabel.setBounds(60, 100, 200, 60);
        rainNoiseLabel.setFont(new Font("Arial", Font.BOLD, 18));
        rainNoiseLabel.setForeground(Color.WHITE);
        add(rainNoiseLabel);
        JCheckBox rainCheckBox = new JCheckBox();
        rainCheckBox.setIcon(checkboxF1);
        rainCheckBox.setBounds(0, 100, 60, 60);
        rainCheckBox.setFocusable(false);
        rainCheckBox.setOpaque(false);
        add(rainCheckBox);
        rainCheckBox.addActionListener(e -> {
            try {
                if (rainCheckBox.isSelected()) {
                    rainCheckBox.setIcon(checkboxF2);
                    File rainFile = new File("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\gammaWithRain.wav");
                    AudioInputStream audioStreamRain = AudioSystem.getAudioInputStream(rainFile);
                    clipRain = AudioSystem.getClip();
                    clipRain.open(audioStreamRain);
                    clipRain.start();
                    clipRain.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    if (clipRain != null && clipRain.isRunning()) {
                        clipRain.stop();
                        rainCheckBox.setIcon(checkboxF1);
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
        ImageIcon resetF1 = new ImageIcon("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\resetF1.png");
        ImageIcon resetF2 = new ImageIcon("C:\\Users\\spype\\OneDrive\\Documents\\ICS4U (AP CS)\\zendoro\\resetF2.png");
        JButton resetButton = new JButton(resetF1);
        resetButton.setBounds(centeredX(110), 600, 110, 70);
        resetButton.setBorderPainted(false);
        resetButton.setFocusPainted(false);
        resetButton.setContentAreaFilled(false);
        add(resetButton);

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetButton.setIcon(resetF2);
                javax.swing.Timer t = new javax.swing.Timer(150, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        resetButton.setIcon(resetF1);

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
                        ((javax.swing.Timer) evt.getSource()).stop();
                    }
                });
                t.setRepeats(false);
                t.start();
            }
        });
    }
}
