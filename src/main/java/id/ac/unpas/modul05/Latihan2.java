package id.ac.unpas.modul05;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

public class Latihan2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Jendela Dengan Label");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JLabel label = new JLabel("ini adalah Jlabel.");

                frame.add(label);

                frame.setVisible(true);
            }
        });
    }
}
