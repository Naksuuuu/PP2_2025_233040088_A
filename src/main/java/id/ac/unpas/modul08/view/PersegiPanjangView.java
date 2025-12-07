package id.ac.unpas.modul08.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PersegiPanjangView extends JFrame {
    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);
    private JLabel lblHasilLuas = new JLabel("-");     // Ganti nama agar spesifik
    private JLabel lblHasilKeliling = new JLabel("-"); // 1. Label baru untuk Keliling
    private JButton btnHitung = new JButton("Hitung");
    private JButton btnReset = new JButton("Reset");

    public PersegiPanjangView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        // 2. Ubah Grid menjadi 5 baris (tambah 1 baris untuk keliling)
        this.setLayout(new GridLayout(5, 2, 10, 10));
        this.setTitle("MVC Persegi Panjang");

        this.add(new JLabel("Panjang:"));
        this.add(txtPanjang);

        this.add(new JLabel("Lebar:"));
        this.add(txtLebar);

        this.add(new JLabel("Hasil Luas:"));
        this.add(lblHasilLuas);

        // 3. Tambahkan komponen Keliling ke Layar
        this.add(new JLabel("Hasil Keliling:"));
        this.add(lblHasilKeliling);

        this.add(btnHitung);
        this.add(btnReset);
    }

    public double getPanjang() {
        return Double.parseDouble(txtPanjang.getText());
    }

    public double getLebar() {
        return Double.parseDouble(txtLebar.getText());
    }

    public void setHasilLuas(double luas) {
        lblHasilLuas.setText(String.valueOf(luas));
    }

    // 4. Setter untuk menampilkan Keliling
    public void setHasilKeliling(double keliling) {
        lblHasilKeliling.setText(String.valueOf(keliling));
    }

    public void resetValue() {
        txtPanjang.setText("");
        txtLebar.setText("");
        lblHasilLuas.setText("-");
        lblHasilKeliling.setText("-"); // Reset juga label keliling
    }

    public void tampilkanPesanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    public void addHitungListener(ActionListener listener) {
        btnHitung.addActionListener(listener);
    }

    public void addResetListener(ActionListener listener) {
        btnReset.addActionListener(listener);
    }
}