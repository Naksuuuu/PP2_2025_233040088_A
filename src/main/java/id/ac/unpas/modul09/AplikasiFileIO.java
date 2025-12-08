/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package id.ac.unpas.modul09;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 *
 * @author Acer
 */
public class AplikasiFileIO extends JFrame {

    // Komponen UI
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JButton btnSaveObject, btnLoadObject;
    private JFileChooser fileChooser;

    public AplikasiFileIO() {
        super("Tutorial File IO & Exception Handling");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inisialisasi Komponen
        textArea = new JTextArea();
        fileChooser = new JFileChooser();

        // Membaca file otomatis saat aplikasi dibuka
        try (BufferedReader reader = new BufferedReader(new FileReader("last_notes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
            System.out.println("Berhasil memuat last_notes.txt otomatis.");
        } catch (IOException e) {
            // Jika file tidak ada, aplikasi diam saja (tidak crash) sesuai instruksi
            System.out.println("File last_notes.txt tidak ditemukan, memulai dengan kosong.");
        }
        // ---------------------------------------------

        // Panel Tombol
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 5, 5));

        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text");
        btnSaveBinary = new JButton("Simpan Config (Binary)");
        btnLoadBinary = new JButton("Muat Config (Binary)");

        // Tombol untuk Latihan 3
        btnSaveObject = new JButton("Simpan Objek (Serialize)");
        btnLoadObject = new JButton("Muat Objek (Deserialize)");

        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);
        buttonPanel.add(btnSaveObject);
        buttonPanel.add(btnLoadObject);

        // Layout
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event Handling
        btnOpenText.addActionListener(e -> bukaFileTeks());
        btnSaveText.addActionListener(e -> simpanFileTeks());
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());
        btnLoadBinary.addActionListener(e -> muatConfigBinary());

        // Event Handling Latihan 3
        btnSaveObject.addActionListener(e -> simpanObject());
        btnLoadObject.addActionListener(e -> muatObject());
    }

    // --- Method Dasar (Modul) ---

    private void bukaFileTeks() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            textArea.setText("");
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                JOptionPane.showMessageDialog(this, "File berhasil dimuat!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void simpanFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void simpanConfigBinary() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("config.bin"))) {
            int fontSize = textArea.getFont().getSize();
            dos.writeInt(fontSize);
            JOptionPane.showMessageDialog(this, "Ukuran font (" + fontSize + ") disimpan ke config.bin");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void muatConfigBinary() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("config.bin"))) {
            int fontSize = dis.readInt();
            textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
            JOptionPane.showMessageDialog(this, "Font diubah menjadi: " + fontSize);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca config: " + ex.getMessage());
        }
    }


    private void simpanObject() {
        // Membuat objek UserConfig dari data layar saat ini
        UserConfig config = new UserConfig();

        // Kita gunakan isi textArea sebagai 'username' (hanya contoh)
        config.setUsername(textArea.getText());
        // Kita gunakan ukuran font saat ini
        config.setFontsize(textArea.getFont().getSize());

        // Proses Serialisasi (Menyimpan objek ke file)
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("user.obj"))) {
            out.writeObject(config);
            JOptionPane.showMessageDialog(this, "Objek berhasil disimpan ke 'user.obj'!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan objek: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void muatObject() {
        // Proses Deserialisasi (Membaca objek dari file)
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("user.obj"))) {
            // Casting dari Object umum ke UserConfig
            UserConfig config = (UserConfig) in.readObject();

            // Terapkan data dari objek ke UI
            textArea.setText(config.getUsername());
            textArea.setFont(new Font("Monospaced", Font.PLAIN, config.getFontsize()));

            JOptionPane.showMessageDialog(this, "Objek berhasil dimuat!\nFont size: " + config.getFontsize());
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File user.obj belum ada!");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat objek: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiFileIO().setVisible(true);
        });
    }
}