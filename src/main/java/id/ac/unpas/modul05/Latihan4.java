package id.ac.unpas.modul05;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Latihan4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//                bagian 1
                JFrame frame = new JFrame("Contoh BorderLayout");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout( new BorderLayout() );

//                bagina 2
                JLabel label = new JLabel("Label ada di Atas (NORTH)");
                JButton SOUTHbutton = new JButton("SOUTH");
                JButton WESTbutton = new JButton("WEST");
                JButton EASTbutton = new JButton("EAST");
                JButton CENTERbutton = new JButton("CENTER");

//                bagian 3
                ActionListener listener = e -> {
                    JButton button = (JButton) e.getSource();
                    label.setText("Tombol " + button.getText() + " Di KLIK!");
                };

                WESTbutton.addActionListener(listener);
                EASTbutton.addActionListener(listener);
                CENTERbutton.addActionListener(listener);
                SOUTHbutton.addActionListener(listener);

//                bagian 4
                frame.add(label, BorderLayout.NORTH);
                frame.add(SOUTHbutton, BorderLayout.SOUTH);
                frame.add(WESTbutton, BorderLayout.WEST);
                frame.add(EASTbutton, BorderLayout.EAST);
                frame.add(CENTERbutton, BorderLayout.CENTER);

                frame.setVisible(true);


            }
        });
    }
}