package id.ac.unpas.modul06;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Latihan2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                // Bagian 1 pembuatan frame utama
                JFrame frame = new JFrame("Konversi Suhu Celcius ke Fahrenheit");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 200);
                frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

                // Bagian 2 pembuatan komponen
                JLabel labelCelcius = new JLabel("Celcius:");
                JTextField inputCelcius = new JTextField(10);
                JButton buttonKonversi = new JButton("Konversi");
                JLabel labelFahrenheit = new JLabel("Fahrenheit:");
                JLabel hasilFahrenheit = new JLabel("");

                // Bagian 3 menambahkan komponen ke frame
                frame.add(labelCelcius);
                frame.add(inputCelcius);
                frame.add(buttonKonversi);
                frame.add(labelFahrenheit);
                frame.add(hasilFahrenheit);

                // Bagian 4 menambahkan ActionListener untuk tombol Konversi
                buttonKonversi.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            double celcius = Double.parseDouble(inputCelcius.getText());
                            double fahrenheit = (celcius * 9 / 5) + 32;
                            hasilFahrenheit.setText(String.format("%.2f °F", fahrenheit));
                        } catch (NumberFormatException ex) {
                            hasilFahrenheit.setText("Input tidak valid!");
                        }
                    }
                });

                // Bagian 5 menampilkan frame
                frame.setVisible(true);
            }
        });
    }
}
