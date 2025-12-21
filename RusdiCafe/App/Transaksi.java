package App;

import java.util.ArrayList;
import java.util.Scanner;

public class Transaksi {

    private Menu menuTerjual;
    private int qtyTerjual;
    private int subtotal;
    private static int totalPendapatan = 0;

    private static ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);

    public Transaksi() {}

    public Transaksi(Menu menuTerjual, int qtyTerjual) {
        this.menuTerjual = menuTerjual;
        this.qtyTerjual = qtyTerjual;
        this.subtotal = menuTerjual.getHarga() * qtyTerjual;
        totalPendapatan += this.subtotal;
    }

    public Menu getMenuTerjual() {
        return menuTerjual;
    }

    public int getQtyTerjual() {
        return qtyTerjual;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public static int getTotalPendapatan() {
        return totalPendapatan;
    }

    public void Kasir() {
        int pilih;
        do {
            System.out.println("=== TRANSAKSI / KASIR ===");
            System.out.println("1. Pilih Menu");
            System.out.println("2. Bayar & Cetak Struk");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            pilih = scan.nextInt();

            switch (pilih) {
                case 1:
                    prosesPembelian();
                    break;
                case 2:
                    prosesPembayaran();
                    break;
            }
            System.out.println();
        } while (pilih != 0);
    }

    private void prosesPembelian() {
        Menu.lihatMenu();
        System.out.print("Pilih index menu: ");
        int idx = scan.nextInt();

        Menu menu = Menu.getMenu(idx);
        if (menu == null) {
            System.out.println("Menu tidak valid!");
            return;
        }

        System.out.print("Jumlah beli: ");
        int qty = scan.nextInt();

        if (qty > menu.getStok()) {
            System.out.println("Stok tidak mencukupi!");
            return;
        }

        menu.setStok(menu.getStok() - qty);
        daftarTransaksi.add(new Transaksi(menu, qty));

        System.out.println("Menu berhasil ditambahkan ke transaksi.");
    }

    private int hitungTotal() {
        int total = 0;
        for (Transaksi t : daftarTransaksi) {
            total += t.getSubtotal();
        }
        return total;
    }

    private void cetakStruk() {
        System.out.println("\n===================================");
        System.out.println("            STRUK PEMBELIAN         ");
        System.out.println("===================================");

        for (Transaksi t : daftarTransaksi) {
            System.out.println(
                t.menuTerjual.getNamaMenu() +
                " x" + t.qtyTerjual +
                " = Rp " + t.subtotal
            );
        }

        System.out.println("-----------------------------------");
        System.out.println("TOTAL : Rp " + hitungTotal());
        System.out.println("===================================");
    }

    private void prosesPembayaran() {
        if (daftarTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }

        cetakStruk();
        System.out.print("Uang dibayarkan: Rp ");
        int bayar = scan.nextInt();

        int total = hitungTotal();
        if (bayar < total) {
            System.out.println("Uang tidak cukup!");
            return;
        }

        System.out.println("Kembalian: Rp " + (bayar - total));
        System.out.println("Terima kasih!");

        daftarTransaksi.clear();
    }
}
