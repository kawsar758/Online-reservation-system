import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginForm() {
        setTitle("Login");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null); // Center the form on the screen

        // Set background color
        getContentPane().setBackground(new Color(238, 238, 238));

        // Username field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Login button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(34, 167, 240));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        // Arrange components using GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);

        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                String enteredPassword = new String(passwordField.getPassword());

                // Check if username and password are correct
                if (enteredUsername.equals("admin") && enteredPassword.equals("admin")) {
                    // Create a dummy User object for authentication
                    User user = new User("admin", "admin");
                    JOptionPane.showMessageDialog(LoginForm.this, "Login Successful!");

                    // Open Reservation Form after successful login
                    new ReservationForm(user).setVisible(true);
                    dispose(); // Close the login form
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid username or password");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}
