package clinic_structure;

import graphics.LaunchFrame;
import graphics.PatientFrame;

import javax.swing.*;
import java.io.Serializable;
import java.util.HashMap;

public class Patient extends User implements Registrable {
    int wallet;
    int debt;

    public Patient(String name, String username, String password) {
        super(name, username, password);
        this.wallet = 0;
        this.debt = 0;
    }


    public void createAccount(Section section, HashMap<String, User> userPassList) {
        section.getPatients().add(this);
    }

    public void openPanel(JFrame jFrame) {
        PatientFrame patientFrame = new PatientFrame(this, (LaunchFrame) jFrame);
        patientFrame.setVisible(true);
        jFrame.setVisible(false);
    }

    public void increaseDebt(int visitPayment) {
        debt += visitPayment;
    }

    public void payDebt(JFrame jFrame) {
        if (debt != 0) {
            if (wallet >= debt) {
                wallet -= debt;
                debt = 0;
                JOptionPane.showMessageDialog(jFrame, "Debt paid.");
            } else
                JOptionPane.showMessageDialog(jFrame, "Sorry, you don't have enough money in your wallet to pay your debt.");
        } else
            JOptionPane.showMessageDialog(jFrame, "You are not in debt. :)");

    }

    public void increaseWallet(int i) {
        wallet += i;
    }

    public int getWallet() {
        return wallet;
    }

    public int getDebt() {
        return debt;
    }

}
