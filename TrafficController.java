
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TrafficController extends JFrame {

    Queue<String> vehicles = new LinkedList<>();
    PriorityQueue<String> emergency = new PriorityQueue<>();

    JTextArea area;
    JLabel signal;

    TrafficController() {
        setTitle("Traffic Signal Controller");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton addVehicle = new JButton("Add Vehicle");
        JButton addEmergency = new JButton("Add Emergency");
        JButton passVehicle = new JButton("Pass Vehicle");
        JButton showVehicles = new JButton("Show Vehicles");

        signal = new JLabel("RED SIGNAL");
        signal.setForeground(Color.RED);

        area = new JTextArea(10, 35);
        area.setEditable(false);

        add(signal);
        add(addVehicle);
        add(addEmergency);
        add(passVehicle);
        add(showVehicles);
        add(new JScrollPane(area));

        addVehicle.addActionListener(e -> {
            String v = JOptionPane.showInputDialog(
                    this, "Enter vehicle name:");

            if (v != null && !v.trim().isEmpty()) {
                vehicles.offer(v.trim());
                area.setText("Vehicle added: " + v);
            }
        });

        addEmergency.addActionListener(e -> {
            String v = JOptionPane.showInputDialog(
                    this, "Enter emergency vehicle name:");

            if (v != null && !v.trim().isEmpty()) {
                emergency.offer(v.trim());
                area.setText("Emergency vehicle added: " + v);
            }
        });

        passVehicle.addActionListener(e -> {
            signal.setText("GREEN SIGNAL");
            signal.setForeground(new Color(0, 150, 0));

            if (!emergency.isEmpty()) {
                area.setText("Emergency passed: " + emergency.poll());
            } else if (!vehicles.isEmpty()) {
                area.setText("Vehicle passed: " + vehicles.poll());
            } else {
                area.setText("No vehicles waiting!");
            }
        });

        showVehicles.addActionListener(e -> {
            area.setText("Normal vehicles: " + vehicles
                    + "\nEmergency vehicles: " + emergency);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TrafficController app = new TrafficController();
            app.setVisible(true);
        });
    }
}
