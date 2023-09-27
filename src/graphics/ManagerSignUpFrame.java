package graphics;

import clinic_structure.Clinic;
import clinic_structure.ClinicType;
import clinic_structure.Manager;
import clinic_structure.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManagerSignUpFrame extends JFrame {
    Clinic clinic;
    private JPanel managerSignUpPanel;
    private JTextField managerUsernameTextField;
    private JTextField manageNameTextField;
    private JTextField managerPasswordTextField;
    private JTextField managerSalaryTextField;
    private JTextField clinicNameTextField;
    private JTextField MANAGERSPECIFICATIONSTextField;
    private JTextField CLINICSPECIFICATIONSTextField;
    private JComboBox<ClinicType> clinicTypeComboBox;
    private JButton createClinicButton;

    public ManagerSignUpFrame() {
        UIManager.put("LaunchPanel.messageFont", new Font("Cascadia Code", Font.BOLD, 14));
        setContentPane(managerSignUpPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setResizable(false);
        this.setTitle("Manager Sign-Up Page");
        clinicTypeComboBox.addItem(ClinicType.NORMAL);
        clinicTypeComboBox.addItem(ClinicType.TWENTY_FOUR_SEVEN);
        createClinicButton.addActionListener(e -> {
            if (isClinicSignUpInfoValid()) {
                clinic = new Clinic(clinicNameTextField.getText(), (ClinicType) clinicTypeComboBox.getSelectedItem(), new ArrayList<>(), new Manager(manageNameTextField.getText(),
                        managerUsernameTextField.getText(), managerPasswordTextField.getText(), Integer.parseInt(managerSalaryTextField.getText())));
                LaunchFrame launchFrame = new LaunchFrame(clinic);
                launchFrame.setVisible(true);
                dispose();
            }
        });
    }
    private boolean isClinicSignUpInfoValid() {
        if (clinicNameTextField.getText().isEmpty() || manageNameTextField.getText().isEmpty() || managerUsernameTextField.getText().isEmpty() ||
                managerPasswordTextField.getText().isEmpty() || User.isInteger(managerSalaryTextField.getText())) {
            JOptionPane.showMessageDialog(this, "Sorry, fields can't be empty and make sure you've entered a integer in manage salary field.");
            return false;
        } else return !User.isSignUpInfoInvalid(manageNameTextField.getText(), managerUsernameTextField.getText(), managerPasswordTextField.getText(), this);
    }
    public Clinic getClinic() {
        return clinic;
    }
}