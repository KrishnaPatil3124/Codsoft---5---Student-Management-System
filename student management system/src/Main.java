import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        Database.init();

        SwingUtilities.invokeLater(() ->
                new LoginFrame().setVisible(true));
    }
}
