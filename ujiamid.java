import java.util.Date;

public class ujiamid {
}
class java.util.ArrayList;
import java.util.Date;

// 1. Kelas Kendaraan
class Kendaraan {
    private String nomorPlat;
    private String merek;
    private String model;
    private int kapasitas;
    private boolean status; // true = tersedia, false = disewa
    private double tarifSewa;

    public Kendaraan(String nomorPlat, String merek, String model, int kapasitas, double tarifSewa) {
        this.nomorPlat = nomorPlat;
        this.merek = merek;
        this.model = model;
        this.kapasitas = kapasitas;
        this.tarifSewa = tarifSewa;
        this.status = true; // kendaraan tersedia saat pertama kali ditambahkan
    }

    public String getMerek() {
        return merek;
    }

    public double getTarifSewa() {
        return tarifSewa;
    }

    public void ubahStatus(boolean status) {
        this.status = status;
    }

    public boolean isTersedia() {
        return status;
    }

    public void tampilkanInfo() {
        System.out.println("Nomor Plat: " + nomorPlat + ", Merek: " + merek + ", Model: " + model +
                ", Kapasitas: " + kapasitas + ", Tarif Sewa: " + tarifSewa + ", Status: " + (status ? "Tersedia" : "Disewa"));
    }
}

// 2. Kelas Pelanggan
class Pelanggan {
    private String nama;
    private String noTelepon;
    private String alamat;
    private String identitas;

    public Pelanggan(String nama, String noTelepon, String alamat, String identitas) {
        this.nama = nama;
        this.noTelepon = noTelepon;
        this.alamat = alamat;
        this.identitas = identitas;
    }

    public void tampilkanInfo() {
        System.out.println("Nama: " + nama + ", No Telepon: " + noTelepon + ", Alamat: " + alamat + ", Identitas: " + identitas);
    }
}

// 3. Kelas Peminjaman
class Peminjaman {
    private Pelanggan pelanggan;
    public Kendaraan kendaraan;
    private Date tanggalPeminjaman;
    private int lamaSewa;

    public Peminjaman(Pelanggan pelanggan, Kendaraan kendaraan, Date tanggalPeminjaman, int lamaSewa) {
        this.pelanggan = pelanggan;
        this.kendaraan = kendaraan;
        this.tanggalPeminjaman = tanggalPeminjaman;
        this.lamaSewa = lamaSewa;
    }

    public void pinjamKendaraan() {
        if (kendaraan.isTersedia()) {
            kendaraan.ubahStatus(false);
            System.out.println("Kendaraan berhasil dipinjam oleh " + pelanggan);
        } else {
            System.out.println("Kendaraan tidak tersedia.");
        }
    }
}

// 4. Kelas Pengembalian
class Pengembalian {
    private Peminjaman peminjaman;
    private Date tanggalPengembalian;

    public Pengembalian(Peminjaman peminjaman, Date tanggalPengembalian) {
        this.peminjaman = peminjaman;
        this.tanggalPengembalian = tanggalPengembalian;
    }

    public double hitungBiaya(double tarifSewa, int lamaSewa) {
        return tarifSewa * lamaSewa;
    }

    public void prosesPengembalian() {
        peminjaman.kendaraan.ubahStatus(true);
        System.out.println("Kendaraan berhasil dikembalikan.");
    }
}

// 5. Kelas Transaksi (Histori Transaksi)
class Transaksi {
    private ArrayList<String> historiTransaksi = new ArrayList<>();

    public void tambahTransaksi(String detail) {
        historiTransaksi.add(detail);
    }

    public void tampilkanHistori() {
        for (String transaksi : historiTransaksi) {
            System.out.println(transaksi);
        }
    }
}

// 6. Kelas PencarianKendaraan
class PencarianKendaraan {
    public void cariKendaraan(ArrayList<Kendaraan> kendaraanList, String merek) {
        for (Kendaraan kendaraan : kendaraanList) {
            if (kendaraan.getMerek().equalsIgnoreCase(merek) && kendaraan.isTersedia()) {
                kendaraan.tampilkanInfo();
            }
        }
    }

    public void filterKendaraan(ArrayList<Kendaraan> kendaraanList, double maxTarif) {
        for (Kendaraan kendaraan : kendaraanList) {
            if (kendaraan.getTarifSewa() <= maxTarif && kendaraan.isTersedia()) {
                kendaraan.tampilkanInfo();
            }
        }
    }
}

// 7. Kelas Pemeliharaan
class Pemeliharaan {
    private Kendaraan kendaraan;
    private Date tanggalPemeliharaan;

    public Pemeliharaan(Kendaraan kendaraan, Date tanggalPemeliharaan) {
        this.kendaraan = kendaraan;
        this.tanggalPemeliharaan = tanggalPemeliharaan;
    }

    public void jadwalkanPemeliharaan() {
        kendaraan.ubahStatus(false); // kendaraan tidak tersedia selama pemeliharaan
        System.out.println("Pemeliharaan untuk kendaraan telah dijadwalkan.");
    }
}

// 8. Kelas Notifikasi
class Notifikasi {
    private String penerima;
    private String pesan;
    private String jenisNotifikasi;

    public Notifikasi(String penerima, String pesan, String jenisNotifikasi) {
        this.penerima = penerima;
        this.pesan = pesan;
        this.jenisNotifikasi = jenisNotifikasi;
    }

    public void kirimNotifikasi() {
        System.out.println("Notifikasi dikirim ke " + penerima + ": " + pesan);
    }
}

// Main Program untuk Menggabungkan Semua Fitur
public class Main {
    public static void main(String[] args) {
        // Buat kendaraan dan pelanggan
        Kendaraan mobil1 = new Kendaraan("B1234ABC", "Toyota", "Avanza", 7, 500000);
        Kendaraan mobil2 = new Kendaraan("B5678DEF", "Honda", "Civic", 4, 700000);

        Pelanggan pelanggan1 = new Pelanggan("John Doe", "08123456789", "Jl. Merdeka", "123456789");

        // Buat daftar kendaraan
        ArrayList<Kendaraan> kendaraanList = new ArrayList<>();
        kendaraanList.add(mobil1);
        kendaraanList.add(mobil2);

        // Proses pencarian kendaraan
        PencarianKendaraan pencarian = new PencarianKendaraan();
        System.out.println("Hasil pencarian kendaraan berdasarkan merek 'Toyota':");
        pencarian.cariKendaraan(kendaraanList, "Toyota");

        // Proses peminjaman
        Peminjaman peminjaman = new Peminjaman(pelanggan1, mobil1, new Date(), 3);
        peminjaman.pinjamKendaraan();

        // Tambah transaksi ke dalam histori
        Transaksi transaksi = new Transaksi();
        transaksi.tambahTransaksi("Peminjaman kendaraan " + mobil1.getMerek() + " oleh " + pelanggan1);

        // Proses pengembalian
        Pengembalian pengembalian = new Pengembalian(peminjaman, new Date());
        double biaya = pengembalian.hitungBiaya(mobil1.getTarifSewa(), 3);
        System.out.println("Biaya total: " + biaya);
        pengembalian.prosesPengembalian();

        // Tampilkan histori transaksi
        System.out.println("\nHistori Transaksi:");
        transaksi.tampilkanHistori();

        // Jadwalkan pemeliharaan kendaraan
        Pemeliharaan pemeliharaan = new Pemeliharaan(mobil1, new Date());
        pemeliharaan.jadwalkanPemeliharaan();

        // Kirim notifikasi ke pelanggan
        Notifikasi notifikasi = new Notifikasi(pelanggan1.toString(), "Pengembalian kendaraan berhasil.", "Pengembalian");
        notifikasi.kirimNotifikasi();
    }
}
