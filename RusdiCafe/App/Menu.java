package App;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    // ===== ATRIBUT =====
    private String namaMenu;
    private int harga;
    private int stok;

    // ===== STORAGE MENU =====
    private static ArrayList<Menu> daftarMenu = new ArrayList<>();

    // ===== UTIL =====
    private Scanner scan = new Scanner(System.in);

    // ===== CONSTRUCTOR =====
    public Menu() {
        // Biar program langsung bisa dipakai tanpa harus input menu dulu
        if (daftarMenu.isEmpty()) {
            daftarMenu.add(new Menu("Ayam Original", 6000, 50));
            daftarMenu.add(new Menu("Ayam Crispy", 7000, 40));
            daftarMenu.add(new Menu("Nasi", 3000, 100));
            daftarMenu.add(new Menu("Es Teh", 4000, 80));
        }
    }

    public Menu(String namaMenu, int harga, int stok) {
        this.namaMenu = namaMenu;
        this.harga = harga;
        this.stok = stok;
    }

    // ===== GETTER & SETTER =====
    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    // ===== MENU CRUD =====
    public void menus() {
        int pilih;
        do {
            System.out.println("=== MENU (CRUD) ===");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Lihat Menu");
            System.out.println("3. Update Menu");
            System.out.println("4. Hapus Menu");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            pilih = scan.nextInt();
            scan.nextLine(); // buang enter

            switch (pilih) {
                case 1:
                    createMenu();
                    break;
                case 2:
                    readMenu();
                    break;
                case 3:
                    updateMenu();
                    break;
                case 4:
                    deleteMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
            System.out.println();
        } while (pilih != 0);
    }

    // ===== CREATE =====
    private void createMenu() {
        System.out.println("=== TAMBAH MENU ===");

        System.out.print("Nama Menu : ");
        String nama = scan.nextLine();

        System.out.print("Harga     : ");
        int harga = scan.nextInt();

        System.out.print("Stok      : ");
        int stok = scan.nextInt();
        scan.nextLine(); // buang enter

        daftarMenu.add(new Menu(nama, harga, stok));
        System.out.println("Menu berhasil ditambahkan.");
    }

    // ===== READ =====
    private void readMenu() {
        System.out.println("=== DAFTAR MENU ===");
        lihatMenu();
    }

    // ===== UPDATE =====
    private void updateMenu() {
        System.out.println("=== UPDATE MENU ===");
        lihatMenu();

        if (daftarMenu.isEmpty()) {
            return;
        }

        System.out.print("Pilih index menu yang mau diupdate: ");
        int idx = scan.nextInt();
        scan.nextLine(); // buang enter

        Menu m = getMenu(idx);
        if (m == null) {
            System.out.println("Index tidak valid!");
            return;
        }

        System.out.print("Nama baru (" + m.getNamaMenu() + ") : ");
        String namaBaru = scan.nextLine();
        if (!namaBaru.trim().isEmpty()) {
            m.setNamaMenu(namaBaru);
        }

        System.out.print("Harga baru (" + m.getHarga() + ") : ");
        String hargaInput = scan.nextLine();
        if (!hargaInput.trim().isEmpty()) {
            m.setHarga(Integer.parseInt(hargaInput));
        }

        System.out.print("Stok baru (" + m.getStok() + ") : ");
        String stokInput = scan.nextLine();
        if (!stokInput.trim().isEmpty()) {
            m.setStok(Integer.parseInt(stokInput));
        }

        System.out.println("Menu berhasil diupdate.");
    }

    // ===== DELETE =====
    private void deleteMenu() {
        System.out.println("=== HAPUS MENU ===");
        lihatMenu();

        if (daftarMenu.isEmpty()) {
            return;
        }

        System.out.print("Pilih index menu yang mau dihapus: ");
        int idx = scan.nextInt();
        scan.nextLine(); // buang enter

        if (idx < 0 || idx >= daftarMenu.size()) {
            System.out.println("Index tidak valid!");
            return;
        }

        daftarMenu.remove(idx);
        System.out.println("Menu berhasil dihapus.");
    }

    // ===== RELASI KE TRANSAKSI =====
    // Dipakai langsung oleh Transaksi.java
    public static void lihatMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("Menu masih kosong.");
            return;
        }

        System.out.println("-----------------------------------------------");
        System.out.println("Index | Nama Menu           | Harga   | Stok");
        System.out.println("-----------------------------------------------");

        for (int i = 0; i < daftarMenu.size(); i++) {
            Menu m = daftarMenu.get(i);
            System.out.printf(
                "%5d | %-19s | %7d | %4d\n",
                i, m.getNamaMenu(), m.getHarga(), m.getStok()
            );
        }

        System.out.println("-----------------------------------------------");
    }

    // Dipakai langsung oleh Transaksi.java
    public static Menu getMenu(int idx) {
        if (idx < 0 || idx >= daftarMenu.size()) {
            return null;
        }
        return daftarMenu.get(idx);
    }
}