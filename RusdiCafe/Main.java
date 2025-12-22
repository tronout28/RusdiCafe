import App.Auth;
import App.Laporan;
import App.Menu;
import App.Transaksi;
import java.util.Scanner;

public class Main {

    private Scanner scan;
    private Auth auth;
    private Menu menu;
    private Transaksi transaksi;
    private Laporan laporan;

    public Main() {
        scan = new Scanner(System.in);
        auth = new Auth();
        menu = new Menu();
        transaksi = new Transaksi();
        laporan = new Laporan();
    }

    public void run() {

        if (!auth.login()) {
            System.out.println("Login gagal! Program dihentikan.");
            return;
        }

        System.out.println("Login berhasil!\n");

        int pilihan;
        do {
            tampilMenuUtama();
            pilihan = scan.nextInt();

            switch (pilihan) {
                case 1:
                    transaksi.Kasir();
                    break;
                case 2:
                    menu.menus();
                    break;
                case 3:
                    laporan.Laporan();
                    break;
                case 0:
                    System.out.println("Terima kasih, program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }

            System.out.println();
        } while (pilihan != 0);
    }

    private void tampilMenuUtama() {
        System.out.println("=== MENU UTAMA ===");
        System.out.println("1. Kasir");
        System.out.println("2. Menu");
        System.out.println("4. Laporan");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }
}
