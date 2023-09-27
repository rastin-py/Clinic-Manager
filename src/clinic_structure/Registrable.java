package clinic_structure;

import javax.swing.*;
import java.io.Serializable;
import java.util.HashMap;

public interface Registrable extends Serializable {
    void createAccount(Section section, HashMap<String, User> userPassList);

    void openPanel(JFrame jFrame);
}
