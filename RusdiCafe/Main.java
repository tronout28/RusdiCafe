import java.util.ArrayList;

import App.Auth;
import App.Menu;
import App.Kasir;
import App.Transaksi;
import App.Laporan;

public class Main {
public static void main(String[] args) {

    Auth auth = new Auth();
    Menu menu = new Menu();
    Kasir kasir = new Kasir();
    Transaksi transaksi = new Transaksi();
    Laporan laporan = new Laporan();

    auth.testAuth();
    menu.testMenu();
    kasir.testKasir();
    transaksi.testTransaksi();
    laporan.testLaporan();

    }
}