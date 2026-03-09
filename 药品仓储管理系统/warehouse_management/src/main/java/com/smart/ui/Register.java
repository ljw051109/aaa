package com.smart.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Register extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> addressComboBox;
    private JTextField phoneField;
    private JButton registerButton;
    private JButton backButton;

    public Register() {
        setTitle("Register");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle registration logic here
                String id = generateRandomId();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String gender = (String) genderComboBox.getSelectedItem();
                String address = (String) addressComboBox.getSelectedItem();
                String phone = phoneField.getText();
                // Save user data
                saveUserData(id, username, password, gender, phone, address);
                JOptionPane.showMessageDialog(null, "User registered successfully with ID: " + id);
                new Login().setVisible(true);
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(10, 80, 80, 25);
        panel.add(genderLabel);

        genderComboBox = new JComboBox<>(new String[]{"男", "女"});
        genderComboBox.setBounds(100, 80, 165, 25);
        panel.add(genderComboBox);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(10, 110, 80, 25);
        panel.add(addressLabel);

        addressComboBox = new JComboBox<>(new String[]{"北京", "天津", "上海"});
        addressComboBox.setBounds(100, 110, 165, 25);
        panel.add(addressComboBox);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(10, 140, 80, 25);
        panel.add(phoneLabel);

        phoneField = new JTextField(20);
        phoneField.setBounds(100, 140, 165, 25);
        panel.add(phoneField);

        registerButton = new JButton("Register");
        registerButton.setBounds(10, 170, 100, 25);
        panel.add(registerButton);

        backButton = new JButton("Back");
        backButton.setBounds(180, 170, 80, 25);
        panel.add(backButton);
    }

    private String generateRandomId() {
        Random random = new Random();
        return String.valueOf(random.nextInt(10000));
    }

    private void saveUserData(String id, String username, String password, String gender, String phone, String address) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/com/smart/data/user.txt", true))) {
            writer.write(id + "," + username + "," + password + "," + gender + "," + phone + "," + address);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}