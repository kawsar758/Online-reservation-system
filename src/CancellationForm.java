import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CancellationForm extends JFrame {

    private JTextField pnrField;
    private JButton cancelButton;
    private ArrayList<Reservation> reservations;

    public CancellationForm(ArrayList<Reservation> reservations) {
        this.reservations = reservations;

        setTitle("Cancel Reservation");
        setLayout(new FlowLayout());
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // PNR Field
        JLabel pnrLabel = new JLabel("Enter PNR to cancel:");
        pnrField = new JTextField(15);

        // Cancel button
        cancelButton = new JButton("Cancel Reservation");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelReservation();
            }
        });

        // Add components to frame
        add(pnrLabel);
        add(pnrField);
        add(cancelButton);
    }

    private void cancelReservation() {
        String pnr = pnrField.getText();

        // Search for the reservation with the provided PNR
        for (Reservation reservation : reservations) {
            if (reservation.getPnrNumber().equals(pnr)) {
                // Display user info if reservation is found
                String userInfo = "Reservation Found: \n" +
                        "PNR: " + reservation.getPnrNumber() + "\n" +
                        "Name: " + reservation.getName() + "\n" +
                        "Age: " + reservation.getAge() + "\n" +
                        "Train Name: " + reservation.getTrainName() + "\n" +
                        "Class: " + reservation.getClassType() + "\n" +
                        "From: " + reservation.getFrom() + "\n" +
                        "To: " + reservation.getTo() + "\n" +
                        "Journey Date: " + reservation.getDate();

                int confirm = JOptionPane.showConfirmDialog(this, userInfo + "\n\nDo you want to cancel this reservation?", "Confirm Cancellation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    reservations.remove(reservation); // Remove the reservation
                    JOptionPane.showMessageDialog(this, "Reservation canceled successfully.");
                }
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "PNR not found.");
    }
}
