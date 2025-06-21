//view paketi
package view;

import controller.LoginController;
import model.User;
//import Result; // Result sınıfının tam adresi eklenecek
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final JTextField userTextField;
    private final JPasswordField passwordField;
    private final LoginController loginController;

    public LoginFrame() {
        this.loginController = new LoginController();

        setTitle("MyLibrary - Giriş");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel userLabel = new JLabel("Kullanıcı Adı:");
        userLabel.setBounds(50, 50, 100, 25);
        panel.add(userLabel);

        userTextField = new JTextField();
        userTextField.setBounds(150, 50, 180, 25);
        panel.add(userTextField);

        JLabel passLabel = new JLabel("Şifre:");
        passLabel.setBounds(50, 90, 100, 25);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 90, 180, 25);
        panel.add(passwordField);

        JButton loginButton = new JButton("Giriş Yap");
        loginButton.setBounds(150, 140, 100, 30);
        panel.add(loginButton);
        
        add(panel);

        loginButton.addActionListener(e -> handleLogin());
    }
    
    private void handleLogin(){
        String username = userTextField.getText();
        String password = new String(passwordField.getPassword());

        SwingWorker<Result, Void> worker = new SwingWorker<>() {
            @Override
            protected Result doInBackground() throws Exception {
                return loginController.login(username, password);
            }

            @Override
            protected void done() {
                try {
                    Result result = get();
                    if (result.getStatus().equals("success")) {
                        dispose();
                        User user = (User) result.getData();
                        int sessionId = result.getSessionID();
                        if (user.getUserType() == 1) {
                            new Type1MainFrame(user, sessionId).setVisible(true);
                        } else {
                            // new Type2MainFrame(user, sessionId).setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(LoginFrame.this, result.getMessage(), "Giriş Hatası", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LoginFrame.this, "İstemci tarafında bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();
    }
}
