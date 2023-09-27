package clinic_structure;

import java.io.Serializable;

public abstract class Staff extends User {
    int salary;
    int income;
    String note;
    Section section;

    public Staff(String name, String username, String password, int salary) {
        super(name, username, password);
        this.salary = salary;
        this.income = 0;
    }

    public int getSalary() {
        return salary;
    }

    public void paySalary() {
        income += salary;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;

    }

    public int getIncome() {
        return income;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

}
