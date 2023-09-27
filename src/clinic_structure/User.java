package clinic_structure;

import javax.swing.*;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class User implements Registrable  {
    private final String name;
    private final String username;
    private final String password;

    public User(String name, String username, String password) {
        this.name = name;
        this.password = password;
        this.username = username.toLowerCase();
    }

    static public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
    public static boolean isSignUpInfoInvalid(String name, String username, String password, JFrame jFrame) {
        if (username.isBlank() || password.isBlank() || name.isBlank()) {
            JOptionPane.showMessageDialog(jFrame, "Sorry, name, username or password can't be empty.");
            return true;
        }
        if (username.length() < 8 || password.length() < 8) {
            JOptionPane.showMessageDialog(jFrame, "Sorry, username or password can't be less than 8 characters.");
            return true;
        }
        Pattern pattern = Pattern.compile("[^a-z0-9.]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(username);
        if (matcher.find()) {
            JOptionPane.showMessageDialog(jFrame, "Sorry, only letters (a-z), numbers (0-9), and periods (.) are allowed.");
            return true;
        }
        if (username.charAt(0) == '.' || username.charAt(username.length() - 1) == '.') {
            JOptionPane.showMessageDialog(jFrame, "Sorry, the first and last character of your username must be an ascii letter (a-z) or number (0-9).");
            return true;
        }
        return false;
    }
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
