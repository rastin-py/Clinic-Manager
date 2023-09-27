package clinic_structure;

import graphics.LaunchFrame;
import graphics.ReceptionistFrame;

import javax.swing.*;
import java.util.HashMap;

public class Receptionist extends Staff implements Registrable {
    public Receptionist(String name, String username, String password, int salary) {
        super(name, username, password, salary);
    }

    public void createAccount(Section section, HashMap<String, User> userPassList) {
        userPassList.remove(section.getReceptionist().getUsername(), section.getReceptionist());
        section.setReceptionist(this);
        this.setSection(section);
    }

    public void openPanel(JFrame jFrame) {
        ReceptionistFrame receptionistFrame = new ReceptionistFrame(this, (LaunchFrame) jFrame);
        receptionistFrame.setVisible(true);
    }
}
