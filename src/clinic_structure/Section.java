package clinic_structure;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Section implements Serializable {
    private final String name;
    private final String sectionType;
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Patient> patients = new ArrayList<>();
    private Receptionist receptionist;
    private ArrayList<VisitHistory> visitHistories = new ArrayList<>();

    public Section(String name, String sectionType, Receptionist receptionist) {
        this.name = name;
        this.sectionType = sectionType;
        this.receptionist = receptionist;
        receptionist.setSection(this);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return sectionType;
    }

    public void fireDoctor(Doctor doctor, HashMap<String, User> userPassList) {
        userPassList.remove(doctor.getUsername(), doctor);
        doctors.remove(doctor);
    }

    public void addVisitHistory(VisitHistory visitHistory) {
        visitHistories.add(visitHistory);
    }

    public void showVisitHistory(JFrame jFrame) {
        StringBuilder stringBuilder = new StringBuilder();
        for (VisitHistory visitHistory : visitHistories) {
            stringBuilder.append("Doctor. ").append(visitHistory.getDoctorName()).append(" visited ").append(visitHistory.getPatientName()).append(" on ").append(visitHistory.getDate()).append("\n");
        }
        if (!stringBuilder.toString().isBlank()) {
            JOptionPane.showMessageDialog(jFrame, stringBuilder.toString());
        } else
            JOptionPane.showMessageDialog(jFrame, "No history.");
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    public Receptionist getReceptionist() {
        return receptionist;
    }
}
