package graphics;

import clinic_structure.Doctor;
import clinic_structure.Patient;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class DoctorFrame extends JFrame implements Serializable {
    LaunchFrame launchFrame;
    Doctor doctor;
    private JPanel doctorPanel;
    private JTextField nameTextField;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JTextField salaryTextField;
    private JTextField visitPaymentTextField;
    private JTextField incomeTextField;
    private JButton logOutButton;
    private JComboBox<String> patientSelectionComboBox;
    private JButton visitButton;
    private JTextField patientNameTextField;
    private JButton showNoteButton;

    public DoctorFrame(Doctor doctor, LaunchFrame launchFrame) {
        this.launchFrame = launchFrame;
        this.doctor = doctor;
        UIManager.put("LaunchPanel.messageFont", new Font("Cascadia Code", Font.BOLD, 14));
        setContentPane(doctorPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 500);
        setResizable(false);
        this.setTitle("Doctor Page");
        nameTextField.setText(doctor.getName());
        usernameTextField.setText(doctor.getUsername());
        passwordTextField.setText(doctor.getPassword());
        salaryTextField.setText(Integer.toString(doctor.getSalary()));
        visitPaymentTextField.setText(Integer.toString(doctor.getVisitPayment()));
        incomeTextField.setText(Integer.toString(doctor.getIncome()));
        showNoteButton.addActionListener(e-> JOptionPane.showMessageDialog(this,doctor.getNote()));
        logOutButton.addActionListener(e -> {
            launchFrame.setVisible(true);
            dispose();
        });
        try {
            for (Patient patient : doctor.getSection().getPatients()) {
                patientSelectionComboBox.addItem(patient.getUsername());
            }
        } catch (Exception ignored) {

        }
        try {
            patientNameTextField.setText(doctor.getSection().getPatients().get(patientSelectionComboBox.getSelectedIndex()).getName());
        } catch (Exception ignored) {
        }
        patientSelectionComboBox.addActionListener(e -> {
            try {
                patientNameTextField.setText(doctor.getSection().getPatients().get(patientSelectionComboBox.getSelectedIndex()).getName());
            } catch (Exception ignored) {
            }
        });
        visitButton.addActionListener(e -> {
            try {
                String date;
                date = JOptionPane.showInputDialog(this, "enter date:");
                if (date!=null) {
                    doctor.visitPatient(doctor.getSection().getPatients().get(patientSelectionComboBox.getSelectedIndex()), date);
                    JOptionPane.showMessageDialog(this, "patient visited.");
                }
            }
            catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "No patient selected.");
            }
        });
    }
}
