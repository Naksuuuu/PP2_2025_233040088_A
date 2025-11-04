package id.ac.unpas.modul06;

import java.awt.*;
import javax.swing.*;

public class Latihan1 {
public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {

//            Bagian 1 bagian pembuatan frame dan panel
            JFrame frame = new JFrame("Kalkulator Sederhana");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLayout(new BorderLayout());

            JPanel UppperPanel = new JPanel();
            JPanel BottomPanel = new JPanel();
            BottomPanel.setBackground(Color.black);
            BottomPanel.setLayout(new GridLayout(5, 4, 5, 5));

//            Bagian 2 memasang panel pada frame beserta posisinya

            frame.add(UppperPanel, BorderLayout.NORTH);
            frame.add(BottomPanel, BorderLayout.CENTER);

// Bagian 3 Membuat konten untuk masing masing panel
            JTextField UppperText = new JTextField();
            UppperText.setPreferredSize(new Dimension(500, 150));
            UppperText.setFont(UppperText.getFont().deriveFont(38.0f));

            JButton ButtonC = new JButton("C");
            ButtonC.setBackground(Color.ORANGE);
            JButton ButtonTK = new JButton("Tk");
            ButtonTK.setBackground(Color.ORANGE);
            JButton ButtonPercent = new JButton("%");
            ButtonPercent.setBackground(Color.ORANGE);
            JButton ButtonDivide = new JButton("/");
            ButtonDivide.setBackground(Color.ORANGE);
            JButton Button7 = new JButton("7");
            Button7.setBackground(Color.WHITE);
            JButton Button8 = new JButton("8");
            Button8.setBackground(Color.WHITE);
            JButton Button9 = new JButton("9");
            Button9.setBackground(Color.WHITE);
            JButton ButtonX = new JButton("X");
            ButtonX.setBackground(Color.ORANGE);
            JButton Button4 = new JButton("4");
            Button4.setBackground(Color.WHITE);
            JButton Button5 = new JButton("5");
            Button5.setBackground(Color.WHITE);
            JButton Button6 = new JButton("6");
            Button6.setBackground(Color.WHITE);
            JButton ButtonMinus = new JButton("-");
            ButtonMinus.setBackground(Color.ORANGE);
            JButton Button1 = new JButton("1");
            Button1.setBackground(Color.WHITE);
            JButton Button2 = new JButton("2");
            Button2.setBackground(Color.WHITE);
            JButton Button3 = new JButton("3");
            Button3.setBackground(Color.WHITE);
            JButton ButtonPlus = new JButton("+");
            ButtonPlus.setBackground(Color.ORANGE);
            JButton Button0 = new JButton("0");
            Button0.setBackground(Color.WHITE);
            JButton ButtonDot = new JButton(".");
            ButtonDot.setBackground(Color.ORANGE);
            JButton ButtonEquels = new JButton("=");
            ButtonEquels.setBackground(Color.ORANGE);

//            Bagian 4 memasukan konten yang sudah dibuat ke masing masing panel
            BottomPanel.add(ButtonC);
            BottomPanel.add(ButtonTK);
            BottomPanel.add(ButtonPercent);
            BottomPanel.add(ButtonDivide);
            BottomPanel.add(Button7);
            BottomPanel.add(Button8);
            BottomPanel.add(Button9);
            BottomPanel.add(ButtonX);
            BottomPanel.add(Button4);
            BottomPanel.add(Button5);
            BottomPanel.add(Button6);
            BottomPanel.add(ButtonMinus);
            BottomPanel.add(Button1);
            BottomPanel.add(Button2);
            BottomPanel.add(Button3);
            BottomPanel.add(ButtonPlus);
            BottomPanel.add(Button0);
            BottomPanel.add(ButtonDot);
            BottomPanel.add(ButtonEquels);


            UppperPanel.add(UppperText);




// Bagian 5 mengubah visibilitas frame
            frame.setVisible(true);

        }
    });
}
}

