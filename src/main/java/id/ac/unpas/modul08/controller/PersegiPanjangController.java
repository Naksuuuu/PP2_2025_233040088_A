package id.ac.unpas.modul08.controller;

import id.ac.unpas.modul08.model.PersegiPanjangModel;
import id.ac.unpas.modul08.view.PersegiPanjangView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersegiPanjangController {
    private PersegiPanjangModel model;
    private PersegiPanjangView view;

    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;
        this.view.addHitungListener(new HitungListener());
        this.view.addResetListener(new ResetListener());
    }

    class HitungListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double p = view.getPanjang();
                double l = view.getLebar();

                model.setPanjang(p);
                model.setLebar(l);

                // --- LOGIKA UTAMA ---
                // 1. Hitung Luas
                model.hitungLuas();
                // 2. Hitung Keliling (Baru)
                model.hitungKeliling();

                // 3. Update View
                view.setHasilLuas(model.getLuas());
                view.setHasilKeliling(model.getKeliling()); // Tampilkan Keliling

            } catch (NumberFormatException ex) {
                view.tampilkanPesanError("Masukkan angka yang valid!");
            }
        }
    }

    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.resetValue();
        }
    }
}