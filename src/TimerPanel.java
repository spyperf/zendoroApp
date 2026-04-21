
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class TimerPanel extends JPanel {

    // These instance variables prevent the user from changing the time and gaining an advantage
    // Timer is private so users cannot change the study time once they have locked in their choices
    private Timer timer;
    // The current break/study time, and the original break/study time from selected options
    private int breakTime;
    private int originalBreakTime;
    private int studyTime;
    private int originalStudyTime;
    // Amount of study periods and the original study periods
    private int studyPeriods;
    private int originalStudyPeriods;
    // Whether user is on break or is studying
    private boolean isStudying = true;
    // Clips for three audio files and an alarm
    private Clip clipWhite;
    private Clip clipGamma;
    private Clip clipRain;
    private Clip clipAlarm;
    // 1 second delay between each action when the Timer goes down by one
    private int delay = 1000;

    // Center components
    private int centeredX(int componentWidth) {
        return (480 - componentWidth) / 2;
    }

    // Stop all audio
    private void stopAllAudio() {
        if (clipWhite != null && clipWhite.isRunning()) {
            clipWhite.stop();
        }
        if (clipGamma != null && clipGamma.isRunning()) {
            clipGamma.stop();
        }
        if (clipRain != null && clipRain.isRunning()) {
            clipRain.stop();
        }
    }

    public TimerPanel(int studyPeriods, int studyTime, int breakTime) {
        // The class constructor is different from the others, as it has to set values for studyPeriods, studyTime, breakTime, and other isntance variables
        this.breakTime = breakTime;
        this.studyTime = studyTime;
        originalBreakTime = this.breakTime;
        originalStudyTime = this.studyTime;
        this.studyPeriods = studyPeriods;
        this.originalStudyPeriods = studyPeriods;
        // This retrieves the total minutes after the most recent time the user has loaded the leaderboard
        int previousMinutes = TitlePanel.getSavedMinutes();
        // Pink background colour
        setBackground(Color.PINK);
        // No layout
        setLayout(null);
        // At 0,0, size 480,720
        setBounds(0, 0, 480, 720);

        // Instructions - Creating new TextArea
        JTextArea instructions = new JTextArea("Pressing \"Stop\" will reset the session, and any progress from the current period will be lost.");
        // TextArea will now automatically account for if the text is too long (it will do a newline)
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        // Invisible background
        instructions.setOpaque(false);
        // White textbox
        instructions.setForeground(Color.white);
        instructions.setBounds(centeredX(480), 500, 480, 100);
        instructions.setFont(new Font("Arial", Font.BOLD, 20));
        add(instructions);

        // Amount of study periods
        JLabel amountOfStudyPeriods = new JLabel("Number of Study Periods: " + String.valueOf(studyPeriods));
        // Arial font, BOLD, size 20, white text colour, at a centered x position, y at 150 ,and width and height of 300, 60
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
        // Blank Checkbox
        ImageIcon checkboxF1 = new ImageIcon("src/images/checkboxF1.png");
        // Filled in Checkbox
        ImageIcon checkboxF2 = new ImageIcon("src/images/checkboxF2.png");

        // White Noise Audio Text
        JLabel whiteNoiseLabel = new JLabel("White Noise");
        whiteNoiseLabel.setBounds(60, 0, 200, 60);
        whiteNoiseLabel.setFont(new Font("Arial", Font.BOLD, 18));
        whiteNoiseLabel.setForeground(Color.WHITE);
        add(whiteNoiseLabel);
        // Creating a new JCheckBox
        JCheckBox whiteNoiseCheckBox = new JCheckBox();
        // Setting checkbox to unchecked icon
        whiteNoiseCheckBox.setIcon(checkboxF1);
        whiteNoiseCheckBox.setBounds(0, 0, 60, 60);
        // Removing original JCheckBox Components
        whiteNoiseCheckBox.setFocusable(false);
        whiteNoiseCheckBox.setOpaque(false);
        // Adding the checkbox to the panel
        add(whiteNoiseCheckBox);
        // When JCheckBox is clicked...
        whiteNoiseCheckBox.addActionListener(e -> {
            try {
                // When the checkbox is selected...
                if (whiteNoiseCheckBox.isSelected()) {
                    // Setting checkbox to filled
                    whiteNoiseCheckBox.setIcon(checkboxF2);
                    // Creating new file (accessing white_noise.wav)
                    File whiteNoiseFile = new File("src/audio/white_noise.wav");
                    // Reading audio file from whiteNoiseFile
                    AudioInputStream audioStreamWhite = AudioSystem.getAudioInputStream(whiteNoiseFile);
                    // Pre-loading before playing
                    clipWhite = AudioSystem.getClip();
                    // Playing the white noise audio
                    clipWhite.open(audioStreamWhite);
                    clipWhite.start();
                    // Looping audio
                    clipWhite.loop(Clip.LOOP_CONTINUOUSLY);
                    // When checkbox is not selected
                } else {
                    // If the clip is not null and is currently running:
                    if (clipWhite != null && clipWhite.isRunning()) {
                        // Audio will stop playing
                        clipWhite.stop();
                        // Checkbox becomes blank
                        whiteNoiseCheckBox.setIcon(checkboxF1);
                    }
                }
            } catch (Exception exc) {
                // Prints Exception
                exc.printStackTrace();
            }
        });

        // Gamma Waves Audio Text
        JLabel gammaWavesLabel = new JLabel("Gamma Waves");
        gammaWavesLabel.setBounds(60, 50, 200, 60);
        gammaWavesLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gammaWavesLabel.setForeground(Color.WHITE);
        add(gammaWavesLabel);
        // Creating a new JCheckBox
        JCheckBox gammaCheckBox = new JCheckBox();
        // Setting checkbox to unchecked icon
        gammaCheckBox.setIcon(checkboxF1);
        gammaCheckBox.setBounds(0, 50, 60, 60);
        // Removing original JCheckBox Components
        gammaCheckBox.setFocusable(false);
        gammaCheckBox.setOpaque(false);
        // Adding the checkbox to the panel
        add(gammaCheckBox);
        // When JCheckBox is clicked...
        gammaCheckBox.addActionListener(e -> {
            try {
                // When the checkbox is selected...
                if (gammaCheckBox.isSelected()) {
                    // Setting checkbox to filled
                    gammaCheckBox.setIcon(checkboxF2);
                    // Creating new file (accessing gamma.wav)
                    File gammaFile = new File("src/audio/gamma.wav");
                    // Reading audio file from gammaFile
                    AudioInputStream audioStreamGamma = AudioSystem.getAudioInputStream(gammaFile);
                    // Pre-loading before playing
                    clipGamma = AudioSystem.getClip();
                    // Playing the gamma waves audio
                    clipGamma.open(audioStreamGamma);
                    clipGamma.start();
                    // Looping audio
                    clipGamma.loop(Clip.LOOP_CONTINUOUSLY);
                    // When checkbox is not selected
                } else {
                    // If the clip is not null and is currently running:
                    if (clipGamma != null && clipGamma.isRunning()) {
                        // Audio will stop playing
                        clipGamma.stop();
                        // Checkbox becomes blank
                        gammaCheckBox.setIcon(checkboxF1);
                    }
                }
            } catch (Exception exc) {
                // Prints Exception
                exc.printStackTrace();
            }
        });

        // Rain Noise Audio Text
        JLabel rainNoiseLabel = new JLabel("Rain Noise");
        rainNoiseLabel.setBounds(60, 100, 200, 60);
        rainNoiseLabel.setFont(new Font("Arial", Font.BOLD, 18));
        rainNoiseLabel.setForeground(Color.WHITE);
        add(rainNoiseLabel);
        // Creating a new JCheckBox
        JCheckBox rainCheckBox = new JCheckBox();
        // Setting checkbox to unchecked icon
        rainCheckBox.setIcon(checkboxF1);
        rainCheckBox.setBounds(0, 100, 60, 60);
        // Removing original JCheckBox Components
        rainCheckBox.setFocusable(false);
        rainCheckBox.setOpaque(false);
        // Adding the checkbox to the panel
        add(rainCheckBox);
        // When JCheckBox is clicked...
        rainCheckBox.addActionListener(e -> {
            try {
                // When the checkbox is selected...
                if (rainCheckBox.isSelected()) {
                    // Setting checkbox to filled
                    rainCheckBox.setIcon(checkboxF2);
                    // Creating new file (accessing gammaWithRain.wav)
                    File rainFile = new File("src/audio/gammaWithRain.wav");
                    // Reading audio file from rainFile
                    AudioInputStream audioStreamRain = AudioSystem.getAudioInputStream(rainFile);
                    // Pre-loading before playing
                    clipRain = AudioSystem.getClip();
                    // Playing the rain audio
                    clipRain.open(audioStreamRain);
                    clipRain.start();
                    // Looping audio
                    clipRain.loop(Clip.LOOP_CONTINUOUSLY);
                    // When checkbox is not selected
                } else {
                    // If the clip is not null and is currently running:
                    if (clipRain != null && clipRain.isRunning()) {
                        // Audio will stop playing
                        clipRain.stop();
                        // Checkbox becomes blank
                        rainCheckBox.setIcon(checkboxF1);
                    }
                }
            } catch (Exception exc) {
                // Prints Exception
                exc.printStackTrace();
            }
        });

        // OLD CODE:
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
            // Prints Exception
            exc.printStackTrace();
        }
         */
        // Adding the label for the timer and the amount of study periods left
        add(timerLabel);
        add(amountOfStudyPeriods);
        // Red Colour (because user is currently studying)
        setBackground(new Color(255, 150, 150));
        // This will indicate whether the user is currently in study period or on break
        add(studyingLabel);

        // When reset timer button is hit:
        ImageIcon resetF1 = new ImageIcon("src/images/resetF1.png");
        // Animation 2
        ImageIcon resetF2 = new ImageIcon("src/images/resetF2.png");
        // Logo 1
        JButton resetButton = new JButton(resetF1);
        resetButton.setBounds(centeredX(110), 600, 110, 70);
        // Remove the default looks in Java components
        resetButton.setBorderPainted(false);
        resetButton.setFocusPainted(false);
        resetButton.setContentAreaFilled(false);
        // Add to panel
        add(resetButton);

        // When there is an action happening...
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to animation 2
                resetButton.setIcon(resetF2);
                // 150 ms Timer (works a bit differnelty than Thread.skeep())
                javax.swing.Timer t = new javax.swing.Timer(150, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        // Stoping the timer (only happens once)
                        timer.stop();
                        // If the three clips are not null and are running...the clip will stop
                        stopAllAudio();
                        // Returns the window
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(TimerPanel.this);
                        // Removes all instances and objects
                        frame.getContentPane().removeAll();
                        // OptionsPanel() appears
                        frame.add(new OptionsPanel());
                        // Refreshes and updates the data and information
                        frame.revalidate();
                        frame.repaint();
                        // Stopping the 150ms timer
                        ((javax.swing.Timer) evt.getSource()).stop();
                    }
                });
                // Timer will only run once
                t.setRepeats(false);
                t.start();
            }
        });
        // TIMER MECHANISM - 1000 ms delay
        timer = new Timer(delay, e -> {
            // When the user is currently studying and there are study periods
            if (isStudying == true && this.studyPeriods > 0) {
                // "Study Period Text"
                add(studyingLabel);
                remove(breakingLabel);
                // Red background (because user is currently studying)
                setBackground(new Color(255, 150, 150));
                // Update and refresh data
                revalidate();
                repaint();
                // Every second the study time will decrease by 1
                this.studyTime--;
                // "minutes : seconds" format
                timerLabel.setText(String.valueOf(this.studyTime / 60 + ":" + this.studyTime % 60));
                // When the current study time has ended
                if (this.studyTime <= 0) {
                    // The current break time will be the original break time
                    this.breakTime = originalBreakTime;
                    // One less study period
                    this.studyPeriods--;
                    // Displays the new and updated study period value
                    amountOfStudyPeriods.setText("Number of Study Periods: " + String.valueOf(this.studyPeriods));
                    // The user will now enter the break phase
                    isStudying = false;
                    // When there is less than 10 seconds left
                } else if (this.studyTime % 60 < 10) {
                    // Format will change (with extra zero)
                    timerLabel.setText(this.studyTime / 60 + ":" + "0" + this.studyTime % 60);
                }
            }
            // Is not currently studying (currently in the break period)
            if (isStudying == false) {
                // Adding the text lables of "Break Period"
                add(breakingLabel);
                remove(studyingLabel);
                // Now background colour will be green because they are currently taking a break
                setBackground(new Color(150, 255, 150));
                // Refresh and updates the information
                revalidate();
                repaint();
                // The breaktime will decrease (same as studyTime)
                this.breakTime--;
                // "minutes : seconds" format
                timerLabel.setText(String.valueOf(this.breakTime / 60 + ":" + this.breakTime % 60));
                // When break time has ended...
                if (this.breakTime <= 0) {
                    // Now study period starts and the current study time is equal to the original study time
                    this.studyTime = originalStudyTime;
                    // Currently in study mode:
                    isStudying = true;
                    // If the break time is less than 10 seconds...
                } else if (this.breakTime % 60 < 10) {
                    // Will be in the same format but with an extra zero
                    timerLabel.setText(this.breakTime / 60 + ":" + "0" + this.breakTime % 60);
                }
            }
            // If there are no study periods left
            if (this.studyPeriods <= 0) {
                // Timer will stop
                timer.stop();
                // Hide everything once done
                removeAll();
                // Session Completed Text
                JLabel completeLabel = new JLabel("Pomodoro Completed!");
                completeLabel.setBounds(50, 300, 700, 60);
                completeLabel.setFont(new Font("Arial", Font.BOLD, 36));
                completeLabel.setForeground(Color.WHITE);
                add(completeLabel);
                // Now in break mode (the user can take a longer break than usual)
                JLabel takeBreakLabel = new JLabel("Take a break.");
                takeBreakLabel.setBounds(centeredX(200), 420, 200, 60);
                takeBreakLabel.setFont(new Font("Arial", Font.BOLD, 24));
                takeBreakLabel.setForeground(Color.WHITE);
                add(takeBreakLabel);
                // Stoping Music if clips are not null and are currently running
                stopAllAudio();
                // Alarm Audio
                try {
                    // Creating new file of alarm.wav
                    File alarmFile = new File("src/audio/alarm.wav");
                    // Reading audio file
                    AudioInputStream audioStreamAlarm = AudioSystem.getAudioInputStream(alarmFile);
                    // Storing memory to play back with new Clip
                    clipAlarm = AudioSystem.getClip();
                    clipAlarm.open(audioStreamAlarm);
                    // Starting clip
                    clipAlarm.start();
                    // Always looping (until stopped)
                    clipAlarm.loop(Clip.LOOP_CONTINUOUSLY);
                } catch (Exception exc) {
                    // Prints Exception
                    exc.printStackTrace();
                }
                // Total minutes studied is saved
                int totalMinutes = originalStudyTime * originalStudyPeriods / 60;
                try {
                    // output.txt is the time NOT submitted to the leaderboard, not the total time studied
                    BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
                    // Convert into String
                    writer.write(String.valueOf(previousMinutes + totalMinutes));
                    writer.close();
                    // Add back button
                    ImageIcon backF1 = new ImageIcon("src/images/backF1.png");
                    // Animation 2
                    ImageIcon backF2 = new ImageIcon("src/images/backF2.png");
                    JButton backButton = new JButton(backF1);
                    backButton.setBounds(centeredX(110), 600, 110, 70);
                    backButton.setBorderPainted(false);
                    backButton.setFocusPainted(false);
                    backButton.setContentAreaFilled(false);
                    add(backButton);
                    // If back button is pressed...
                    backButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Switch to animation 2
                            backButton.setIcon(backF2);
                            // Timer for 150 ms
                            javax.swing.Timer t = new javax.swing.Timer(150, new ActionListener() {
                                public void actionPerformed(ActionEvent evt) {
                                    // Switch back to animation 1
                                    backButton.setIcon(backF1);
                                    // If the alarm audio is not null and is currently running...
                                    if (clipAlarm != null && clipAlarm.isRunning()) {
                                        // The audio will stop playing
                                        clipAlarm.stop();
                                    }
                                    // Returns the frame
                                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(TimerPanel.this);
                                    // Removes all instances
                                    frame.getContentPane().removeAll();
                                    // OptionsPanel() is created
                                    frame.add(new OptionsPanel());
                                    // Updated and refreshed once changes are finished
                                    frame.revalidate();
                                    frame.repaint();
                                    // Stopping the 150 ms timer
                                    ((javax.swing.Timer) evt.getSource()).stop();
                                }
                            });
                            // No repeating
                            t.setRepeats(false);
                            t.start();
                        }
                    });
                    // variable e is already defined in constructor TimerPanel(int,int,int), so change to "ex"
                } catch (IOException ex) {
                    // Prints Exception
                    ex.printStackTrace();
                }
                // Background colour set to pink
                setBackground(Color.PINK);
                // Updated and refreshed
                revalidate();
                repaint();
            }
        });
        timer.start();
    }
}
