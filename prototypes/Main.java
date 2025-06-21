import view.LoginFrame;
import jobs.Jobs;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Jobs.calljobs();

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
