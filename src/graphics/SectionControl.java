package graphics;

import clinic_structure.*;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class SectionControl extends JFrame implements Serializable {
    ManagerFrame managerFrame;
    Section section;
    Clinic clinic;
    Doctor selectedDoctor;
    private JPanel sectionControl;
    private JButton addDoctorButton;
    private JButton replaceReceptionistButton;
    private JButton fireDoctorButton;
    private JTextField doctorAddUserTextField;
    private JTextField doctorAddSalaryTextField;
    private JTextField recepReplaceUserTextField;
    private JTextField doctorAddPassTextField;
    private JTextField doctorAddVisitTextField;
    private JTextField recepReplacePassTextField;
    private JTextField recepReplaceSalaryTextField;
    private JTextField doctorSelectionNameTextField;
    private JButton payDoctorSalaryButton;
    private JComboBox<String> doctorSelectionComboBox;
    private JButton payReceptionistSalaryButton;
    private JTextField recepSelectionUserTextField;
    private JTextField DOCTORSTextField;
    private JTextField RECEPTIONISTTextField;
    private JButton backButton;
    private JTextField sectionNameTextField;
    private JButton leaveANoteButton;
    private JTextField doctorSelectionSalaryTextField;
    private JTextField recepSelectionNameTextField;
    private JTextField recepSelectionSalaryTextField;

    public SectionControl(Clinic clinic, Section section, ManagerFrame managerFrame) {
        this.managerFrame = managerFrame;
        this.clinic = clinic;
        this.section = section;
        UIManager.put("LaunchPanel.messageFont", new Font("Cascadia Code", Font.BOLD, 14));
        setContentPane(sectionControl);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1600, 500);
        setResizable(false);
        this.setTitle("Section Control");
        refreshElements();
        sectionNameTextField.setText(section.getName());
        backButton.addActionListener(e -> {
            managerFrame.setVisible(true);
            dispose();
        });
        addDoctorButton.addActionListener(e -> addDoctor());
        fireDoctorButton.addActionListener(e -> {
            try {
                section.fireDoctor(selectedDoctor, clinic.getUserPassList());
                refreshElements();
                selectedDoctor = section.getDoctors().get(doctorSelectionComboBox.getSelectedIndex());
                doctorSelectionNameTextField.setText(selectedDoctor.getName());
                doctorSelectionSalaryTextField.setText(Integer.toString(selectedDoctor.getSalary()));
                JOptionPane.showMessageDialog(this, "doctor fired.");
            } catch (Exception exception) {
                doctorSelectionNameTextField.setText("");
                doctorSelectionSalaryTextField.setText("");
            }
        });
        doctorSelectionComboBox.addActionListener(e -> {
            try {
                selectedDoctor = section.getDoctors().get(doctorSelectionComboBox.getSelectedIndex());
                doctorSelectionNameTextField.setText(selectedDoctor.getName());
                doctorSelectionSalaryTextField.setText(Integer.toString(selectedDoctor.getSalary()));
            } catch (Exception ignored) {
            }
        });
        payDoctorSalaryButton.addActionListener(e -> {
            try {
                selectedDoctor.paySalary();
                JOptionPane.showMessageDialog(this, "doctor salary is paid.");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "choose a doctor.");
            }
        });
        leaveANoteButton.addActionListener(e -> {
            String note = JOptionPane.showInputDialog(this, "Leave a note:");
            section.getReceptionist().setNote(note);
        });
        replaceReceptionistButton.addActionListener(e -> replaceReceptionist());
        payReceptionistSalaryButton.addActionListener(e -> {
            try {
                section.getReceptionist().paySalary();
                JOptionPane.showMessageDialog(this, "receptionist salary is paid.");

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "no receptionist.");
            }
        });
    }

    private void refreshElements() {
        doctorSelectionComboBox.removeAllItems();
        for (Doctor doctor : section.getDoctors()) {
            doctorSelectionComboBox.addItem(doctor.getUsername());
        }
        recepSelectionNameTextField.setText(section.getReceptionist().getName());
        recepSelectionUserTextField.setText(section.getReceptionist().getUsername());
        recepSelectionSalaryTextField.setText(Integer.toString(section.getReceptionist().getSalary()));

    }

    private void addDoctor() {
        if (User.isInteger(doctorAddSalaryTextField.getText()) || User.isInteger(doctorAddVisitTextField.getText())) {
            JOptionPane.showMessageDialog(this, "Enter a number for doctor salary and visit payment.");
            return;
        }
        String doctorName = JOptionPane.showInputDialog(this, "Enter doctor's name:");
        if (doctorName != null) {
            clinic.signUp(new Doctor(doctorName, doctorAddUserTextField.getText(), doctorAddPassTextField.getText(), Integer.parseInt(doctorAddSalaryTextField.getText()), Integer.parseInt(doctorAddVisitTextField.getText()), section), this, section);
        }
        refreshElements();
    }

    private void replaceReceptionist() {
        if (User.isInteger(recepReplaceSalaryTextField.getText())) {
            JOptionPane.showMessageDialog(this, "Enter a number for receptionist salary.");
            return;
        }
        String recepName = JOptionPane.showInputDialog(this, "Enter receptionist's name:");
        if (!recepName.isBlank()) {
            clinic.signUp(new Receptionist(recepName, recepReplaceUserTextField.getText(), recepReplacePassTextField.getText(), Integer.parseInt(recepReplaceSalaryTextField.getText())), this, section);
            refreshElements();
        }
    }


}
