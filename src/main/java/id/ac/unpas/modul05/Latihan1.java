package id.ac.unpas.modul05;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Latihan1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {


                JFrame frame = new JFrame("Jendela Pertamaku");

                frame.setSize(400, 300);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}



