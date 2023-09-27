import clinic_structure.Clinic;
import graphics.LaunchFrame;
import graphics.ManagerSignUpFrame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

    public static void main(String[] args) {
        try {
            Clinic clinic;
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("clinic.txt"));
            clinic = (Clinic) objectInputStream.readObject();
            LaunchFrame launchFrame = new LaunchFrame(clinic);
            launchFrame.setVisible(true);
            objectInputStream.close();
            System.out.println("reading from saved file.");
            Runtime.getRuntime().addShutdownHook(new ClosingOperationThread(clinic));
        } catch (Exception exception) {
            ManagerSignUpFrame managerSignUpFrame = new ManagerSignUpFrame();
            managerSignUpFrame.setVisible(true);
            System.out.println("not reading from saved file.");
            Runtime.getRuntime().addShutdownHook(new ClosingOperationThread(managerSignUpFrame));
        }
    }
}

class ClosingOperationThread extends Thread {
    ManagerSignUpFrame managerSignUpFrame;
    Clinic clinic;

    ClosingOperationThread(ManagerSignUpFrame managerSignUpFrame) {
        this.managerSignUpFrame = managerSignUpFrame;
    }

    ClosingOperationThread(Clinic clinic) {
        this.clinic = clinic;
    }

    public void run() {
        if (managerSignUpFrame != null) {
            clinic = managerSignUpFrame.getClinic();
        }
        try {
            System.out.println("saving file.");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("clinic.txt"));
            objectOutputStream.writeObject(clinic);
            objectOutputStream.close();
        } catch (Exception ignored) {
        }
    }
}



