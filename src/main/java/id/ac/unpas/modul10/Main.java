package id.ac.unpas.modul10;

import id.ac.unpas.modul10.controller.MahasiswaController;
import id.ac.unpas.modul10.view.MahasiswaView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Buat View
            MahasiswaView view = new MahasiswaView();

            // 2. Buat Controller dan hubungkan ke View (tanpa Model DAO)
            new MahasiswaController(view);

            // 3. Tampilkan View
            view.setVisible(true);
        });
    }
}