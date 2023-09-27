package graphics;

import clinic_structure.Clinic;
import clinic_structure.Patient;
import clinic_structure.Section;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Locale;

public class LaunchFrame extends JFrame implements Serializable {
    protected JTextField clinicManagerTextField;
    Clinic clinic;
    private JPanel LaunchPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton resetButton;
    private JCheckBox showPasswordCheckBox;
    private JButton signUpButton;
    private JButton signInButton;
    private JComboBox<String> sectionSelectionComboBox;
    private JTextField chooseASectionTextField;
    private JCheckBox iWantToSignCheckBox;
    private JTextField nameTextField;
    private JLabel nameLabel;

    public LaunchFrame(Clinic clinic) {
        this.clinic = clinic;
        clinicManagerTextField.setEditable(false);
        UIManager.put("LaunchPanel.messageFont", new Font("Cascadia Code", Font.BOLD, 14));
        setContentPane(LaunchPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 500);
        setResizable(false);
        this.setTitle("Launch Page");
        nameLabel.setVisible(false);
        chooseASectionTextField.setVisible(false);
        sectionSelectionComboBox.setVisible(false);
        nameTextField.setVisible(false);
        signUpButton.setVisible(false);
        refreshElements();
        resetButton.addActionListener(e -> resetFields());
        showPasswordCheckBox.addActionListener(e -> setPasswordFieldVisible());
        signInButton.addActionListener(e -> clinic.signIn(usernameField.getText().toLowerCase(Locale.ROOT), passwordField.getText(), this));
        signUpButton.addActionListener(e -> {
            try {
                clinic.signUp(new Patient(nameTextField.getText(), usernameField.getText(), passwordField.getText()), this, clinic.getSections().get(sectionSelectionComboBox.getSelectedIndex()));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "No section exists.");
            }
        });
        iWantToSignCheckBox.addActionListener(e -> insertSignUpElements());
    }

    public void refreshElements() {
        sectionSelectionComboBox.removeAllItems();
        for (Section section : clinic.getSections()) {
            sectionSelectionComboBox.addItem(section.getName());
        }
    }

    private void insertSignUpElements() {
        if (chooseASectionTextField.isVisible()) {
            signInButton.setVisible(true);
            nameLabel.setVisible(false);
            chooseASectionTextField.setVisible(false);
            sectionSelectionComboBox.setVisible(false);
            nameTextField.setVisible(false);
            signUpButton.setVisible(false);
        } else {
            signInButton.setVisible(false);
            nameLabel.setVisible(true);
            chooseASectionTextField.setVisible(true);
            sectionSelectionComboBox.setVisible(true);
            nameTextField.setVisible(true);
            signUpButton.setVisible(true);
        }
    }

    private void resetFields() {
        usernameField.setText("");
        passwordField.setText("");
        nameTextField.setText("");
    }

    private void setPasswordFieldVisible() {
        if (showPasswordCheckBox.isSelected()) {
            passwordField.setEchoChar((char) 0);
        } else {
            passwordField.setEchoChar('•');
        }
    }


}
