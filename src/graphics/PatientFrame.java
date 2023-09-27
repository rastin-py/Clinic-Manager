package graphics;

import clinic_structure.Patient;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class PatientFrame extends JFrame implements Serializable {
    LaunchFrame launchFrame;
    private JPanel PatientPanel;
    private JTextField nameTextField;
    private JTextField usernameTextField;
    private JTextField walletTextField;
    private JTextField debtTextField;
    private JButton logOutButton;
    private JButton increaseWalletButton;
    private JButton payDebtButton;
    private JTextField passwordTextField;

    public PatientFrame(Patient patient, LaunchFrame launchFrame) {
        this.launchFrame = launchFrame;
        UIManager.put("LaunchPanel.messageFont", new Font("Cascadia Code", Font.BOLD, 14));
        setContentPane(PatientPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 500);
        setResizable(false);
        this.setTitle("Patient Page");
        nameTextField.setText(patient.getName());
        usernameTextField.setText(patient.getUsername());
        passwordTextField.setText(patient.getPassword());
        walletTextField.setText(Integer.toString(patient.getWallet()));
        debtTextField.setText(Integer.toString(patient.getDebt()));
        logOutButton.addActionListener(e -> {
            launchFrame.setVisible(true);
            dispose();
        });
        increaseWalletButton.addActionListener(e -> {
            try {
                int num = Integer.parseInt(JOptionPane.showInputDialog(this, "How much would you like to add to your wallet?"));
                patient.increaseWallet(num);
                walletTextField.setText(Integer.toString(patient.getWallet()));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Please enter a integer.");
            }

        });

        payDebtButton.addActionListener(e -> {
            patient.payDebt(this);
            walletTextField.setText(Integer.toString(patient.getWallet()));
            debtTextField.setText(Integer.toString(patient.getDebt()));
        });

    }
}