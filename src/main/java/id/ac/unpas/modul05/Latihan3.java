package id.ac.unpas.modul05;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Latihan3 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Label dan Tombol");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setLayout( new FlowLayout() );

                JLabel label = new JLabel("Text awal.");
                JButton button = new JButton("Klik Saya");

                frame.add(label);
                frame.add(button);

                frame.setVisible(true);

                button.addActionListener(e -> {
                    label.setText("text berubah");
                });

            }
        });
    }
}