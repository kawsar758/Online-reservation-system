import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ReservationForm extends JFrame {

    private JTextField nameField, trainNumberField, fromField, toField, dateField, ageField;
    private JComboBox<String> classComboBox;
    private JButton submitButton, cancelButton;
    private User user;

    // Temporary in-memory reservation list
    private static ArrayList<Reservation> reservations = new ArrayList<>();

    public ReservationForm(User user) {
        this.user = user;

        setTitle("Reservation Form");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null); // Center the form on the screen

        // Set background color
        getContentPane().setBackground(new Color(238, 238, 238));

        // Name field (pre-filled with username)
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameField = new JTextField(user.getUsername(), 20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Age field
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ageField = new JTextField(20);
        ageField.setFont(new Font("Arial", Font.PLAIN, 14));
        ageField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Train number field
        JLabel trainLabel = new JLabel("Train Number:");
        trainLabel.setFont(new Font("Arial", Font.BOLD, 16));
        trainNumberField = new JTextField(20);
        trainNumberField.setFont(new Font("Arial", Font.PLAIN, 14));
        trainNumberField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Class combo box
        JLabel classLabel = new JLabel("Class:");
        classLabel.setFont(new Font("Arial", Font.BOLD, 16));
        classComboBox = new JComboBox<>(new String[]{"Sleeper", "AC", "First Class"});
        classComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        // From place field
        JLabel fromLabel = new JLabel("From:");
        fromLabel.setFont(new Font("Arial", Font.BOLD, 16));
        fromField = new JTextField(20);
        fromField.setFont(new Font("Arial", Font.PLAIN, 14));
        fromField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // To place field
        JLabel toLabel = new JLabel("To:");
        toLabel.setFont(new Font("Arial", Font.BOLD, 16));
        toField = new JTextField(20);
        toField.setFont(new Font("Arial", Font.PLAIN, 14));
        toField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Journey date field
        JLabel dateLabel = new JLabel("Journey Date:");
        dateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dateField = new JTextField(20);
        dateField.setFont(new Font("Arial", Font.PLAIN, 14));
        dateField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Submit button
        submitButton = new JButton("Submit Reservation");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(new Color(34, 167, 240));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);

        // Cancel reservation button
        cancelButton = new JButton("Cancel Reservation");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelButton.setBackground(new Color(234, 67, 53));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);

        // Arrange components using GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);

        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(ageLabel, gbc);

        gbc.gridx = 1;
        add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(trainLabel, gbc);

        gbc.gridx = 1;
        add(trainNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(classLabel, gbc);

        gbc.gridx = 1;
        add(classComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(fromLabel, gbc);

        gbc.gridx = 1;
        add(fromField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(toLabel, gbc);

        gbc.gridx = 1;
        add(toField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(dateLabel, gbc);

        gbc.gridx = 1;
        add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(submitButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        add(cancelButton, gbc);

        // Submit reservation action
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitReservation();
            }
        });

        // Cancel reservation action
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCancellationForm();
            }
        });
    }

    private void submitReservation() {
        String name = nameField.getText();
        String age = ageField.getText();
        String trainNumber = trainNumberField.getText();
        String classType = (String) classComboBox.getSelectedItem();
        String from = fromField.getText();
        String to = toField.getText();
        String date = dateField.getText();

        // Validate the input fields
        if (name.isEmpty() || age.isEmpty() || trainNumber.isEmpty() || from.isEmpty() || to.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        try {
            // Create a new reservation
            Reservation reservation = new Reservation(name, age, "Train " + trainNumber, trainNumber, classType, from, to, date);
            reservations.add(reservation); // Add reservation to the list

            // Show the PNR number to the user
            JOptionPane.showMessageDialog(this, "Reservation Successful!\nPNR: " + reservation.getPnrNumber());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error occurred: " + ex.getMessage());
        }
    }

    private void openCancellationForm() {
        // Open the cancellation form
        new CancellationForm(reservations).setVisible(true);
        dispose(); // Close the reservation form
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReservationForm(new User("admin", "admin")).setVisible(true));
    }
}
