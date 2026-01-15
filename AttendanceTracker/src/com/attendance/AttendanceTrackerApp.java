package com.attendance;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class AttendanceTrackerApp {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Attendance Tracker");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel nameLabel = new JLabel("Attendance Name:");
        JLabel courseLabel = new JLabel("Course / Year:");
        JLabel timeInLabel = new JLabel("Time In:");
        JLabel signatureLabel = new JLabel("E-Signature:");

        JTextField nameField = new JTextField();
        JTextField courseField = new JTextField();
        JTextField timeInField = new JTextField();
        JTextField signatureField = new JTextField();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeIn = now.format(formatter);
        timeInField.setText(timeIn);
        timeInField.setEditable(false); 

        nameField.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                Random rand = new Random();
                int digits = rand.nextInt(9000) + 1000; 
                String eSignature = name.substring(0, 1).toUpperCase() + digits;
                signatureField.setText(eSignature);
            }
        });
        signatureField.setEditable(false); 

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(courseLabel);
        panel.add(courseField);

        panel.add(timeInLabel);
        panel.add(timeInField);

        panel.add(signatureLabel);
        panel.add(signatureField);

        frame.add(panel);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
