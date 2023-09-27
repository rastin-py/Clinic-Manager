package graphics;

import clinic_structure.Manager;
import clinic_structure.Receptionist;
import clinic_structure.Section;
import clinic_structure.User;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class ManagerFrame extends JFrame implements Serializable {
    LaunchFrame launchFrame;
    Manager manager;
    private JPanel managerPanel;
    private JTextField nameTextField;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JTextField salaryTextField;
    private JButton addSectionButton;
    private JTextField sectionAddNameTextField;
    private JComboBox<String> sectionSelectionNameComboBox;
    private JTextField sectionAddTypeTextField;
    private JButton sectionSettingButton;
    private JButton showVisitHistoryButton;
    private JTextField clinicTypeTextField;
    private JTextField clinicNameTextField;
    private JTextField recepAddSalaryTextField;
    private JTextField recepAddPassTextField;
    private JTextField CONTROLPANELTextField;
    private JTextField PROFILETextField;
    private JTextField recepAddUserTextField;
    private JTextField recepAddNameTextField;
    private JButton logOutButton;
    private JTextField sectionSelectionTypeTextField;
    private JButton deleteSectionButton;

    public ManagerFrame(Manager manager, LaunchFrame launchFrame) {
        this.launchFrame = launchFrame;
        this.manager = manager;
        UIManager.put("LaunchPanel.messageFont", new Font("Cascadia Code", Font.BOLD, 14));
        setContentPane(managerPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setResizable(false);
        this.setTitle("Manager Page");
        nameTextField.setText(manager.getName());
        usernameTextField.setText(manager.getUsername());
        passwordTextField.setText(manager.getPassword());
        salaryTextField.setText(Integer.toString(manager.getSalary()));
        clinicNameTextField.setText(manager.getClinic().getName());
        clinicTypeTextField.setText(manager.getClinic().getClinicType().toString());
        refreshElements();
        logOutButton.addActionListener(e -> {
            launchFrame.setVisible(true);
            launchFrame.refreshElements();
            dispose();
        });
        addSectionButton.addActionListener(e -> verifySectionCreationInfo());
        deleteSectionButton.addActionListener(e -> {
            try {
                manager.getClinic().deleteSection(sectionSelectionNameComboBox.getSelectedIndex());
                refreshElements();
                JOptionPane.showMessageDialog(this, "Section deleted.");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "No section exists.");
            }
        });
        showVisitHistoryButton.addActionListener(e -> {
            try {
                manager.getClinic().getSections().get(sectionSelectionNameComboBox.getSelectedIndex()).showVisitHistory(this);
                refreshElements();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "No section exists.");
            }
        });
        sectionSettingButton.addActionListener(e -> {
            try {
                SectionControl sectionControl = new SectionControl(manager.getClinic(), manager.getClinic().getSections().get(sectionSelectionNameComboBox.getSelectedIndex()), this);
                sectionControl.setVisible(true);
                this.setVisible(false);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "No section exists.");
            }
        });
        refreshElements();
        sectionSelectionNameComboBox.addActionListener(e -> {
            try {
                sectionSelectionTypeTextField.setText(manager.getClinic().getSections().get(sectionSelectionNameComboBox.getSelectedIndex()).getType());
            } catch (Exception ignored) {
            }
        });
    }

    private void refreshElements() {
        sectionSelectionNameComboBox.removeAllItems();
        for (Section section : manager.getClinic().getSections()) {
            sectionSelectionNameComboBox.addItem(section.getName());
        }
        try {
            sectionSelectionTypeTextField.setText(manager.getClinic().getSections().get(sectionSelectionNameComboBox.getSelectedIndex()).getType());
        } catch (Exception exception) {
            sectionSelectionTypeTextField.setText("");
        }
    }

    private void verifySectionCreationInfo() {
        if (sectionAddTypeTextField.getText().isBlank() || sectionAddNameTextField.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Text fields can't be empty.");
        } else if (User.isInteger(recepAddSalaryTextField.getText())) {
            JOptionPane.showMessageDialog(this, "Enter a number for receptionist salary.");
        } else {
            manager.getClinic().addSection(new Section(sectionAddNameTextField.getText(), sectionAddTypeTextField.getText(), new Receptionist(recepAddNameTextField.getText(), recepAddUserTextField.getText(), recepAddPassTextField.getText(), Integer.parseInt(recepAddSalaryTextField.getText()))), this);
            refreshElements();
        }
    }
}
