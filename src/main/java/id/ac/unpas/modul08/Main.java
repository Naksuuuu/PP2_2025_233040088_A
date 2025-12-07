package id.ac.unpas.modul08;

import id.ac.unpas.modul08.controller.PersegiPanjangController;
import id.ac.unpas.modul08.model.PersegiPanjangModel;
import id.ac.unpas.modul08.view.PersegiPanjangView;

public class Main {
    public static void main(String[] args) {
        // 1. Instansiasi Model
        PersegiPanjangModel model = new PersegiPanjangModel();

        // 2. Instansiasi View
        PersegiPanjangView view = new PersegiPanjangView();

        // 3. Instansiasi Controller (Hubungkan Model & View)
        PersegiPanjangController controller = new PersegiPanjangController(model, view);

        // 4. Tampilkan View
        view.setVisible(true);
    }
}