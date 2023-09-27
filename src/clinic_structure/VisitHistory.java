package clinic_structure;

import java.io.Serializable;

public class VisitHistory  implements Serializable {
    private final String doctorName;
    private final String patientName;
    private final String date;

    public VisitHistory(String doctorName, String patientName, String date) {
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.date = date;
    }

    public String getDoctorName() {
        return doctorName;
    }
    public String getPatientName() {
        return patientName;
    }
    public String getDate() {
        return date;
    }

}
