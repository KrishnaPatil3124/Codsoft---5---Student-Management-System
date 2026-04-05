import java.awt.*;
import java.security.MessageDigest;
import java.sql.*;
import javax.swing.*;

public class SignupFrame extends JFrame {

    JTextField user = new JTextField();
    JPasswordField pass = new JPasswordField();

    public SignupFrame() {
        setTitle("Sign Up");
        setSize(300,220);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3,2,10,10));

        JButton create = new JButton("Create Account");
        create.addActionListener(e -> signup());

        add(new JLabel("Username:")); add(user);
        add(new JLabel("Password:")); add(pass);
        add(new JLabel()); add(create);

        getRootPane().setDefaultButton(create);
        user.requestFocus();
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());

            StringBuilder hex = new StringBuilder();
            for (byte b : hash)
                hex.append(String.format("%02x", b));

            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void signup() {
        String username = user.getText().trim();
        String password = new String(pass.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Required fields");
            return;
        }

        try (Connection c = Database.connect()) {
            PreparedStatement ps = c.prepareStatement(
                "INSERT INTO users(username,password) VALUES(?,?)");

            ps.setString(1, username);
            ps.setString(2, hashPassword(password));
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Account created!");
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Username exists");
        }
    }
}
