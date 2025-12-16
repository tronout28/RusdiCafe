package App;

import java.util.ArrayList;

public class Menu {

    // =========================
    // FIELD MENU
    // =========================
    public int id;
    public String namaMenu;
    public double harga;
    public int stok;

    // =========================
    // DAFTAR MENU (STATIC)
    // =========================
    public static ArrayList<Menu> menuList = new ArrayList<>();

    // =========================
    // CONSTRUCTOR
    // =========================
    public Menu(int id, String namaMenu, double harga, int stok) {
        this.id = id;
        this.namaMenu = namaMenu;
        this.harga = harga;
        this.stok = stok;
    }

    // =========================
    // INIT DATA AWAL
    // =========================
    public static void initMenu() {
        menuList.add(new Menu(1, "Ayam Goreng", 6000, 20));
        menuList.add(new Menu(2, "Es Teh", 3000, 30));
        menuList.add(new Menu(3, "Nasi", 4000, 25));
    }

    // =========================
    // TAMPILKAN DAFTAR MENU
    // =========================
    public static void tampilkanMenu() {
        System.out.println("\n==============================");
        System.out.println("DAFTAR MENU");
        System.out.println("==============================");

        if (menuList.size() == 0) {
            System.out.println("Menu masih kosong.");
            return;
        }

        System.out.println("ID | Nama Menu | Harga | Stok");
        System.out.println("--------------------------------");

        for (int i = 0; i < menuList.size(); i++) {
            Menu m = menuList.get(i);
            System.out.println(
                m.id + " | " +
                m.namaMenu + " | " +
                m.harga + " | " +
                m.stok
            );
        }
    }

    // =========================
    // CARI MENU BERDASARKAN ID
    // =========================
    public static Menu cariMenuById(int idMenu) {
        for (int i = 0; i < menuList.size(); i++) {
            if (menuList.get(i).id == idMenu) {
                return menuList.get(i);
            }
        }
        return null;
    }
}
