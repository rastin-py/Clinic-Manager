package graphics;

import clinic_structure.Doctor;
import clinic_structure.Receptionist;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class ReceptionistFrame extends JFrame implements Serializable {
    LaunchFrame launchFrame;
    Receptionist receptionist;
    private JPanel receptionistPanel;
    private JTextField recepNameTextField;
    private JTextField recepUsernameTextField;
    private JTextField recepPasswordTextField;
    private JTextField recepSalaryTextField;
    private JTextField recepIncomeTextField;
    private JButton logOutButton;
    private JComboBox<String> doctorSelectionComboBox;
    private JTextField doctorNameTextField;
    private JButton leaveANoteButton;
    private JButton showVisitHistoryButton;
    private JButton showNoteButton;

    public ReceptionistFrame(Receptionist receptionist, LaunchFrame launchFrame) {
        this.launchFrame = launchFrame;
        this.receptionist = receptionist;
        UIManager.put("LaunchPanel.messageFont", new Font("Cascadia Code", Font.BOLD, 14));
        setContentPane(receptionistPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 500);
        setResizable(false);
        this.setTitle("Receptionist Page");
        recepNameTextField.setText(receptionist.getName());
        recepUsernameTextField.setText(receptionist.getUsername());
        recepPasswordTextField.setText(receptionist.getPassword());
        recepSalaryTextField.setText(Integer.toString(receptionist.getSalary()));
        recepIncomeTextField.setText(Integer.toString(receptionist.getSalary()));
        logOutButton.addActionListener(e -> {
            launchFrame.setVisible(true);
            dispose();
        });
        try {
            for (Doctor doctor : receptionist.getSection().getDoctors()) {
                doctorSelectionComboBox.addItem(doctor.getUsername());
            }
        } catch (Exception ignored) {

        }
        showNoteButton.addActionListener(e -> JOptionPane.showMessageDialog(this, receptionist.getNote()));
        leaveANoteButton.addActionListener(e -> {
            try {
                String note = JOptionPane.showInputDialog(this, "Leave a note:");
                receptionist.getSection().getDoctors().get(doctorSelectionComboBox.getSelectedIndex()).setNote(note);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "No doctor is selected.");
            }
        });
        try {
            doctorNameTextField.setText(receptionist.getSection().getDoctors().get(doctorSelectionComboBox.getSelectedIndex()).getName());
        } catch (Exception ignored) {
        }
        doctorSelectionComboBox.addActionListener(e -> {
            try {
                doctorNameTextField.setText(receptionist.getSection().getDoctors().get(doctorSelectionComboBox.getSelectedIndex()).getName());
            } catch (Exception ignored) {
            }
        });
        showVisitHistoryButton.addActionListener(e -> receptionist.getSection().showVisitHistory(this));
    }
}