package App;

import java.util.*;

public class Laporan {

    public void Laporan() {
        System.out.println("\n=== LAPORAN TRANSAKSI ===");
        System.out.println("1. Ringkasan Umum");
        System.out.println("2. Penjualan per Menu");
        System.out.println("3. Menu Terlaris");
        System.out.println("4. Stok Saat Ini");
        System.out.println("5. Detail Semua Transaksi");
        System.out.println("0. Kembali");

        Scanner scan = new Scanner(System.in);
        int pilih;
        do {
            System.out.print("\nPilih laporan: ");
            pilih = scan.nextInt();
            switch (pilih) {
                case 1:
                    tampilkanRingkasanUmum();
                    break;
                case 2:
                    tampilkanPenjualanPerMenu();
                    break;
                case 3:
                    tampilkanMenuTerlaris();
                    break;
                case 4:
                    tampilkanStokSaatIni();
                    break;
                case 5:
                    tampilkanSemuaTransaksi();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilih != 0);
    }

    private void tampilkanRingkasanUmum() {
        List<Transaksi> riwayat = Transaksi.getRiwayatTransaksi();
        int totalTransaksi = riwayat.size();
        int totalPendapatan = Transaksi.getTotalPendapatan();
        int jumlahMenu = Menu.daftarMenu.size();

        int totalQtyTerjual = 0;
        for (Transaksi t : riwayat) {
            totalQtyTerjual += t.getQtyTerjual();
        }

        System.out.println("\n--- RINGKASAN UMUM ---");
        System.out.println("Jumlah Menu Terdaftar : " + jumlahMenu);
        System.out.println("Jumlah Transaksi      : " + totalTransaksi);
        System.out.println("Total Item Terjual    : " + totalQtyTerjual);
        System.out.println("Total Pendapatan      : Rp " + totalPendapatan);
    }

    private void tampilkanPenjualanPerMenu() {
        if (Menu.daftarMenu.isEmpty()) {
            System.out.println("\nBelum ada menu terdaftar.");
            return;
        }

        Map<String, Integer> qtyMap = new HashMap<>();
        Map<String, Integer> pendapatanMap = new HashMap<>();

        for (Menu m : Menu.daftarMenu) {
            qtyMap.put(m.getNamaMenu(), 0);
            pendapatanMap.put(m.getNamaMenu(), 0);
        }

        List<Transaksi> riwayat = Transaksi.getRiwayatTransaksi();
        for (Transaksi t : riwayat) {
            String nama = t.getMenuTerjual().getNamaMenu();
            int qty = t.getQtyTerjual();
            int subtotal = t.getSubtotal();

            qtyMap.put(nama, qtyMap.get(nama) + qty);
            pendapatanMap.put(nama, pendapatanMap.get(nama) + subtotal);
        }

        System.out.println("\n--- PENJUALAN PER MENU ---");
        System.out.printf("%-20s | %-10s | %-12s%n", "Menu", "Qty Terjual", "Pendapatan");
        System.out.println("---------------------------------------------");
        for (Menu m : Menu.daftarMenu) {
            String nama = m.getNamaMenu();
            int qty = qtyMap.get(nama);
            int income = pendapatanMap.get(nama);
            System.out.printf("%-20s | %-10d | Rp %-10d%n", nama, qty, income);
        }
    }

    private void tampilkanMenuTerlaris() {
        List<Transaksi> riwayat = Transaksi.getRiwayatTransaksi();
        if (riwayat.isEmpty()) {
            System.out.println("\nBelum ada transaksi.");
            return;
        }

        Map<String, Integer> qtyMap = new HashMap<>();
        for (Menu m : Menu.daftarMenu) {
            qtyMap.put(m.getNamaMenu(), 0);
        }
        for (Transaksi t : riwayat) {
            String nama = t.getMenuTerjual().getNamaMenu();
            qtyMap.put(nama, qtyMap.get(nama) + t.getQtyTerjual());
        }

        String terlaris = "";
        int maxQty = -1;
        for (Map.Entry<String, Integer> entry : qtyMap.entrySet()) {
            if (entry.getValue() > maxQty) {
                maxQty = entry.getValue();
                terlaris = entry.getKey();
            }
        }

        System.out.println("\n--- MENU TERLARIS ---");
        if (maxQty == 0) {
            System.out.println("Belum ada penjualan.");
        } else {
            System.out.println("Menu   : " + terlaris);
            System.out.println("Terjual: " + maxQty + " item");
        }
    }

    private void tampilkanStokSaatIni() {
        System.out.println("\n--- STOK MENU SAAT INI ---");
        if (Menu.daftarMenu.isEmpty()) {
            System.out.println("Belum ada menu.");
            return;
        }

        System.out.printf("%-20s | %-6s%n", "Menu", "Stok");
        System.out.println("--------------------------");
        for (Menu m : Menu.daftarMenu) {
            System.out.printf("%-20s | %-6d%n", m.getNamaMenu(), m.getStok());
        }
    }

    private void tampilkanSemuaTransaksi() {
        List<Transaksi> riwayat = Transaksi.getRiwayatTransaksi();
        if (riwayat.isEmpty()) {
            System.out.println("\nBelum ada transaksi.");
            return;
        }

        System.out.println("\n--- RIWAYAT TRANSAKSI ---");
        System.out.println("No | Menu                | Qty | Subtotal");
        System.out.println("--------------------------------------------");
        for (int i = 0; i < riwayat.size(); i++) {
            Transaksi t = riwayat.get(i);
            System.out.printf("%-2d | %-18s | %-3d | Rp %-8d%n",
                (i + 1),
                t.getMenuTerjual().getNamaMenu(),
                t.getQtyTerjual(),
                t.getSubtotal()
            );
        }
    }
}
