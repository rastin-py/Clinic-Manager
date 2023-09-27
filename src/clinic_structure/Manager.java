package clinic_structure;


import graphics.LaunchFrame;
import graphics.ManagerFrame;

import javax.swing.*;
import java.io.Serializable;
import java.util.HashMap;

public class Manager extends User implements Registrable {
    int salary;
    Clinic clinic;

    public Manager(String name, String username, String password, int salary) {
        super(name, username, password);
        this.salary = salary;
    }

    @Override
    public void createAccount(Section section, HashMap<String, User> userPassList) {

    }

    public void openPanel(JFrame jFrame) {
        ManagerFrame managerFrame = new ManagerFrame(this, (LaunchFrame) jFrame);
        managerFrame.setVisible(true);
        jFrame.setVisible(false);
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public int getSalary() {
        return salary;
    }

    public Clinic getClinic() {
        return clinic;
    }
}
