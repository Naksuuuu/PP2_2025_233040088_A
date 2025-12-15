package id.ac.unpas.modul10.controller;

import id.ac.unpas.modul10.KoneksiDB;
import id.ac.unpas.modul10.model.Mahasiswa;
import id.ac.unpas.modul10.view.MahasiswaView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MahasiswaController {
    private MahasiswaView view;

    public MahasiswaController(MahasiswaView view) {
        this.view = view;

        // Inisialisasi Event Listeners
        initListeners();
        // Load data awal
        loadData(null);
    }

    private void initListeners() {
        view.btnSimpan.addActionListener(e -> tambahData());
        view.btnEdit.addActionListener(e -> ubahData());
        view.btnHapus.addActionListener(e -> hapusData());
        view.btnClear.addActionListener(e -> view.kosongkanForm());
        view.btnCari.addActionListener(e -> cariData());

        view.tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabelClicked();
            }
        });
    }

    // Latihan 4: Cek Duplikasi NIM (Logika Database di Controller)
    private boolean isNIMExist(String nim) throws SQLException {
        Connection conn = KoneksiDB.configDB();
        String sql = "SELECT COUNT(*) AS count FROM mahasiswa WHERE nim = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nim);

        ResultSet res = pst.executeQuery();

        if (res.next()) {
            return res.getInt("count") > 0;
        }
        return false;
    }

    // READ: Memuat data ke tabel (Logika Database di Controller)
    public void loadData(String kataKunci) {
        DefaultTableModel tableModel = view.model;
        tableModel.setRowCount(0);
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet res = null;

        try {
            conn = KoneksiDB.configDB();

            String sql;
            if (kataKunci == null || kataKunci.isEmpty()) {
                sql = "SELECT nama, nim, jurusan FROM mahasiswa";
                pst = conn.prepareStatement(sql);
            } else {
                sql = "SELECT nama, nim, jurusan FROM mahasiswa WHERE nama LIKE ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, "%" + kataKunci + "%");
            }

            res = pst.executeQuery();

            int no = 1;
            while (res.next()) {
                tableModel.addRow(new Object[]{
                        no++,
                        res.getString("nama"),
                        res.getString("nim"),
                        res.getString("jurusan")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Gagal Load Data: " + e.getMessage());
        } finally {
            // Penanganan resource close (optional, tapi disarankan)
            try { if (res != null) res.close(); } catch (Exception e) {}
            try { if (pst != null) pst.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    private void cariData() {
        String kataKunci = view.txtCari.getText();
        loadData(kataKunci);
    }

    private void tabelClicked() {
        int row = view.tableMahasiswa.getSelectedRow();
        if (row != -1) {
            view.txtNama.setText(view.model.getValueAt(row, 1).toString());
            view.txtNIM.setText(view.model.getValueAt(row, 2).toString());
            view.txtJurusan.setText(view.model.getValueAt(row, 3).toString());
        }
    }

    // CREATE: Menambah Data (Logika Database di Controller)
    private void tambahData() {
        String nama = view.txtNama.getText();
        String nim = view.txtNIM.getText();
        String jurusan = view.txtJurusan.getText();

        // Latihan 2: Validasi Kekosongan
        if (nama.isEmpty() || nim.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Data tidak boleh kosong!");
            return;
        }

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            // Latihan 4: Pengecekan Duplikasi NIM
            if (isNIMExist(nim)) {
                JOptionPane.showMessageDialog(view, "Gagal Simpan: NIM " + nim + " sudah terdaftar!");
                return;
            }

            conn = KoneksiDB.configDB();
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            pst = conn.prepareStatement(sql);

            pst.setString(1, nama);
            pst.setString(2, nim);
            pst.setString(3, jurusan);
            pst.execute();

            JOptionPane.showMessageDialog(view, "Data Berhasil Disimpan");
            loadData(null);
            view.kosongkanForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Gagal Simpan: " + e.getMessage());
        } finally {
            try { if (pst != null) pst.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    // UPDATE: Mengubah Data (Logika Database di Controller)
    private void ubahData() {
        String nama = view.txtNama.getText();
        String nim = view.txtNIM.getText();
        String jurusan = view.txtJurusan.getText();

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = KoneksiDB.configDB();
            String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
            pst = conn.prepareStatement(sql);

            pst.setString(1, nama);
            pst.setString(2, jurusan);
            pst.setString(3, nim);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(view, "Data Berhasil Diubah");
            loadData(null);
            view.kosongkanForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Gagal Edit: " + e.getMessage());
        } finally {
            try { if (pst != null) pst.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    // DELETE: Menghapus Data (Logika Database di Controller)
    private void hapusData() {
        String nim = view.txtNIM.getText();

        if (nim.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih data yang akan dihapus dari tabel.");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(view, "Yakin hapus data NIM " + nim + "?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            Connection conn = null;
            PreparedStatement pst = null;
            try {
                conn = KoneksiDB.configDB();
                String sql = "DELETE FROM mahasiswa WHERE nim = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, nim);
                pst.execute();

                JOptionPane.showMessageDialog(view, "Data Berhasil Dihapus");
                loadData(null);
                view.kosongkanForm();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(view, "Gagal Hapus: " + e.getMessage());
            } finally {
                try { if (pst != null) pst.close(); } catch (Exception e) {}
                try { if (conn != null) conn.close(); } catch (Exception e) {}
            }
        }
    }
}