package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.border.LineBorder;

public class StopWatchClockApp extends JFrame {

    private JLabel clockLabel;
    private JLabel stopwatchLabel;
    private JButton startButton, stopButton, resetButton;

    private Timer clockTimer;
    private Timer stopwatchTimer;

    private long startTime = 0;
    private long elapsedTime = 0;
    private boolean running = false;

    public StopWatchClockApp() {
        setTitle("🕒 Stopwatch & Clock");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(30, 30, 50)); // dark background

        // Clock Label
        clockLabel = new JLabel("00:00:00");
        clockLabel.setFont(new Font("Courier New", Font.BOLD, 32));
        clockLabel.setForeground(new Color(0, 255, 255));
        clockLabel.setBounds(150, 30, 300, 50);
        add(clockLabel);

        // Stopwatch Label
        stopwatchLabel = new JLabel("00:00:00");
        stopwatchLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
        stopwatchLabel.setForeground(new Color(255, 215, 0));
        stopwatchLabel.setBounds(150, 110, 300, 60);
        add(stopwatchLabel);

        // Start Button
        startButton = new JButton("▶ Start");
        startButton.setBounds(60, 200, 100, 40);
        styleButton(startButton, new Color(76, 175, 80));
        add(startButton);

        // Stop Button
        stopButton = new JButton("⏸ Stop");
        stopButton.setBounds(190, 200, 100, 40);
        styleButton(stopButton, new Color(244, 67, 54));
        add(stopButton);

        // Reset Button
        resetButton = new JButton("🔁 Reset");
        resetButton.setBounds(320, 200, 100, 40);
        styleButton(resetButton, new Color(33, 150, 243));
        add(resetButton);

        // Clock Timer
        clockTimer = new Timer(1000, e -> updateClock());
        clockTimer.start();

        // Stopwatch Timer
        stopwatchTimer = new Timer(100, e -> updateStopwatch());

        // Button Actions
        startButton.addActionListener(e -> startStopwatch());
        stopButton.addActionListener(e -> stopStopwatch());
        resetButton.addActionListener(e -> resetStopwatch());
    }

    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(Color.white));
    }

    private void updateClock() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String timeStr = sdf.format(new Date());
        clockLabel.setText(timeStr);
    }

    private void updateStopwatch() {
        long now = System.currentTimeMillis();
        long timePassed = now - startTime + elapsedTime;

        int hours = (int) (timePassed / (1000 * 60 * 60));
        int minutes = (int) ((timePassed / (1000 * 60)) % 60);
        int seconds = (int) ((timePassed / 1000) % 60);

        stopwatchLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    private void startStopwatch() {
        if (!running) {
            startTime = System.currentTimeMillis();
            stopwatchTimer.start();
            running = true;
        }
    }

    private void stopStopwatch() {
        if (running) {
            elapsedTime += System.currentTimeMillis() - startTime;
            stopwatchTimer.stop();
            running = false;
        }
    }

    private void resetStopwatch() {
        stopwatchTimer.stop();
        running = false;
        elapsedTime = 0;
        stopwatchLabel.setText("00:00:00");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StopWatchClockApp().setVisible(true);
        });
    }
}

