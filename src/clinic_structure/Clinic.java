package clinic_structure;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Clinic implements Serializable {
    private final String name;
    private final ClinicType clinicType;
    private final Manager manager;
    private ArrayList<Section> sections;
    private HashMap<String, User> userPassList = new HashMap<>();

    public Clinic(String name, ClinicType clinicType, ArrayList<Section> sections, Manager manager) {
        this.name = name;
        this.clinicType = clinicType;
        this.sections = sections;
        this.manager = manager;
        manager.setClinic(this);
        updateUserPass(this.manager.getUsername(), this.manager);
    }

    public String getName() {
        return name;
    }

    public ClinicType getClinicType() {
        return clinicType;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void updateUserPass(String username, User user) {
        userPassList.put(username, user);
    }

    public HashMap<String, User> getUserPassList() {
        return userPassList;
    }


    public void addSection(Section section, JFrame jFrame) {
        for (Section section1 : sections) {
            if (section1.getName().equals(section.getName())) {
                JOptionPane.showMessageDialog(jFrame, "Sorry, this section name already exists.");
                return;
            }
        }
        if (User.isSignUpInfoInvalid(section.getReceptionist().getName(), section.getReceptionist().getUsername(), section.getReceptionist().getPassword(), jFrame))
            return;
        if (userPassList.containsKey(section.getReceptionist().getUsername())) {
            JOptionPane.showMessageDialog(jFrame, "Sorry, this username already exists.");
            return;
        }
        section.getReceptionist().createAccount(section, userPassList);
        updateUserPass(section.getReceptionist().getUsername(), section.getReceptionist());
        sections.add(section);
        JOptionPane.showMessageDialog(jFrame, "Section successfully added.");
    }

    public void deleteSection(int index) {
        for (Doctor doctor : sections.get(index).getDoctors()) {
            userPassList.remove(doctor.getUsername(), doctor);
        }
        for (Patient patient : sections.get(index).getPatients()) {
            userPassList.remove(patient.getUsername(), patient);
        }
        userPassList.remove(sections.get(index).getReceptionist().getUsername(), sections.get(index).getReceptionist());
        sections.remove(index);
    }

    public void signIn(String username, String password, JFrame jFrame) {
        if (userPassList.containsKey(username)) {
            if (userPassList.get(username).getPassword().equals(password)) {
                userPassList.get(username).openPanel(jFrame);
                jFrame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(jFrame, "Sorry, password is wrong.");
            }
        } else {
            JOptionPane.showMessageDialog(jFrame, "Sorry, username is wrong.");
        }
    }

    public void signUp(User user, JFrame jFrame, Section section) {
        if (User.isSignUpInfoInvalid(user.getName(), user.getUsername(), user.getPassword(), jFrame))
            return;
        if (userPassList.containsKey(user.getUsername())) {
            JOptionPane.showMessageDialog(jFrame, "Sorry, this username already exists.");
            return;
        }
        user.createAccount(section, userPassList);
        updateUserPass(user.getUsername(), user);
        System.out.println(userPassList);
        JOptionPane.showMessageDialog(jFrame, "Account successfully created.");
    }
}


