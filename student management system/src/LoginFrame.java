import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class LoginFrame extends JFrame {

    JTextField userField = new JTextField();
    JPasswordField passField = new JPasswordField();

    public LoginFrame() {
        setTitle("Login");
        setSize(300,220);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3,2,10,10));

        JButton loginBtn = new JButton("Login");
        JButton signupBtn = new JButton("Sign Up");

        loginBtn.addActionListener(e -> login());
        signupBtn.addActionListener(e -> new SignupFrame().setVisible(true));

        add(new JLabel("Username:")); add(userField);
        add(new JLabel("Password:")); add(passField);
        add(loginBtn); add(signupBtn);

        getRootPane().setDefaultButton(loginBtn);
        userField.requestFocus();
    }

    private void login() {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Enter credentials");
            return;
        }

        try (Connection c = Database.connect()) {
            PreparedStatement ps = c.prepareStatement(
                "SELECT password FROM users WHERE username=?");
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String stored = rs.getString("password");
                String input = SignupFrame.hashPassword(password);

                if (stored.equals(input)) {
                    new DashboardFrame().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this,"Wrong password");
                }
            } else {
                JOptionPane.showMessageDialog(this,"User not found");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Error");
        }
    }
}
