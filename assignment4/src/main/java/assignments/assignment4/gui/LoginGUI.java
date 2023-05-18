package assignments.assignment4.gui;

import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;

    public LoginGUI() {
        super(new BorderLayout());

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();
        add(mainPanel, BorderLayout.CENTER);
    }

    private void initGUI() {


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        idLabel = new JLabel("Masukkan ID Anda:");
        constraints.gridy = 0;
        constraints.weighty = 0.5;
        mainPanel.add(idLabel, constraints);

        idTextField = new JTextField(50);
        constraints.gridy = 1;
        constraints.weighty = 0;
        mainPanel.add(idTextField, constraints);

        passwordLabel = new JLabel("Masukkan password Anda:");
        constraints.gridy = 2;
        constraints.weighty = 0.5;
        mainPanel.add(passwordLabel, constraints);

        passwordField = new JPasswordField(50);
        constraints.gridy = 3;
        constraints.weighty = 0;
        mainPanel.add(passwordField, constraints);

        loginButton = new JButton("Login");
        constraints.gridy = 4;
        constraints.weighty = 0.5;
        mainPanel.add(loginButton, constraints);

        backButton = new JButton("Kembali");
        constraints.gridy = 5;
        constraints.weighty = 0.5;
        mainPanel.add(backButton, constraints);

        loginButton.addActionListener(e -> handleLogin());
        backButton.addActionListener(e -> handleBack());
    }

    private void handleBack() {
        resetFields();
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
    }

    private void handleLogin() {
        String password = String.valueOf(passwordField.getPassword());

        if (!MainFrame.getInstance().login(idTextField.getText(), password)) {
            JOptionPane.showMessageDialog(
                this,
                "Login gagal!",
                "Gagal",
                JOptionPane.WARNING_MESSAGE
            );
        };

        resetFields();
    }

    private void resetFields() {
        idTextField.setText("");
        passwordField.setText("");
    }
}
