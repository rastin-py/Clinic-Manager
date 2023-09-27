package clinic_structure;

import graphics.DoctorFrame;
import graphics.LaunchFrame;

import javax.swing.*;
import java.io.Serializable;
import java.util.HashMap;

public class Doctor extends Staff implements Registrable {
    private final int visitPayment;

    public Doctor(String name, String username, String password, int salary, int visitPayment, Section section) {
        super(name, username, password, salary);
        this.visitPayment = visitPayment;
        setSection(section);
    }

    public void visitPatient(Patient patient, String date) {
        patient.increaseDebt(visitPayment);
        section.addVisitHistory(new VisitHistory(this.getName(), patient.getName(), date));
    }

    public void createAccount(Section section, HashMap<String, User> userPassList) {
        section.getDoctors().add(this);
    }

    public void openPanel(JFrame jFrame) {
        DoctorFrame doctorFrame = new DoctorFrame(this, (LaunchFrame) jFrame);
        doctorFrame.setVisible(true);
        jFrame.setVisible(false);
    }

    public int getVisitPayment() {
        return visitPayment;
    }
}
