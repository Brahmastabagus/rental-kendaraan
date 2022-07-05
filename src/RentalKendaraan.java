import java.awt.*;
import java.util.Scanner;

public class RentalKendaraan {
    //    Menginisialisasi Scanner
    static Scanner Sstring = new Scanner(System.in);
    static Scanner Sint = new Scanner(System.in);

    public static final String RESET = "\033[0m";
    public static final String YELLOW = "\u001B[33m";
    //    Form Pendaftaran
    public static void daftar(String[] login) {
        System.out.println(YELLOW + ">\tForm Daftar" + RESET);
        System.out.print("\tMasukkan username\t: ");
        String user = Sstring.nextLine();
        login[0] = user;

        System.out.print("\tMasukkan password\t: ");
        String pass = Sstring.nextLine();
        login[1] = pass;

        System.out.println();
    }

    //    Form Login
    public static String signUp(String[] login) {
        String cek = "t";

        System.out.println(">\tForm Login");
        System.out.print("\tMasukkan Username\t: ");
        String user = Sstring.nextLine();
        System.out.print("\tMasukkan Password\t: ");
        String pass = Sstring.nextLine();

        if ( user.equals(login[0]) ) {
            if ( pass.equals(login[1]) ) {
                cek = "y";
            } else {
                cek = "t";
            }
        }

        return cek;
    }

    //    Menampilkan menu pilihan
    public static void pilihan(int kendaraan) {
        System.out.println(">\tPilihan");
        System.out.println("\t1. Tambah Transaksi");
        System.out.println("\t2. Mengurutkan Data " + Kendaraan(kendaraan));
        System.out.println("\t3. Pencarian");
        System.out.println("\t4. Pengembalian");
        System.out.println("\t5. Nota");
        System.out.println("\t6. Keluar");
        System.out.print("\tPilih Menu: ");
    }

    //    Menampilkan menu nama kendaraan
    public static void menu(String[] list) {
        System.out.println(">\tMenu");
        System.out.println("\tNo\t\tNama Kendaraan");

        for (int i = 0; i < list.length; i++) {
            System.out.println("\t" + (i+1) + "\t\t" + list[i]);
        }
    }

    //    Menampilkan merk kendaraan
    public static void listKendaraan(String[][] temp, int kendaraan) {
        System.out.println(">\tMenu");
        System.out.println("\tNo\t\t  " + Kendaraan(kendaraan) + "\t\t\tHarga(perHari)\t\t   Stok");

        for (int i = 0; i < temp.length; i++) {
            for (int j = 1; j < temp[i].length-1; j++) {
                int m = j - 1;
                System.out.print("\t" + (i+1) + ". \t\t" + temp[i][m] + "\t\t\t" + temp[i][m+1] + "\t\t\t\t"+temp[i][m+2]);
            }
            System.out.println();
        }
    }

    //    Function nama kendaraan
    public static String Kendaraan(int kendaraan) {
        String transport = "";

        if ( kendaraan == 1 ) {
            transport = "Mobil";
        } else if ( kendaraan == 2 ) {
            transport = "Motor";
        } else if ( kendaraan == 3 ) {
            transport = "Sepeda";
        }

        return transport;
    }

    //    Fungsi tambah dan memasukkan ke array
    public static String[] tambah(String[] data, int kendaraan) {
        String tanya;

//        Perulangan menambahkan transaksi, jika ada data yang salah, maka akan diulang
        do {
            System.out.println(">\tMemasukkan Data");
            int rand = (10000 - 999) + 1;
            int acak = (int)((Math.random() * rand) + 999);
            data[0] = String.valueOf(acak);

            System.out.println("\tNo Transaksi\t\t: " + data[0]);
            System.out.print("\tMasukkan nama anda\t: ");
            data[1] = Sstring.nextLine();
            System.out.print("\tPilih " + Kendaraan(kendaraan) + "\t\t\t: ");
            data[2] = Sstring.nextLine();
            System.out.print("\tJumlah Sewa\t\t\t: ");
            data[3] = Sstring.nextLine();
            System.out.print("\tLama Sewa\t\t\t: ");
            data[4] = Sstring.nextLine();
            System.out.print("\tApakah data yang dimasukkan sudah benar?(y/t): ");
            tanya = Sstring.nextLine().toUpperCase();
        } while ( tanya.equals("T") );

        return data;
    }

    //    Function Sorting, dengan cara membandingkan
    public static String[][] selectionAsc(String[][] temp){
        int flag;
        String[] tempo = new String[3];

        for (int i = 0; i < temp.length - 1; i++) {
            flag = i;

            for (int j = i + 1; j < temp.length; j++) {
                if ( temp[j][0].compareTo(temp[flag][0]) < 0 ) flag = j;
            }

            if (flag != i){
                for (int j = 0; j < tempo.length; j++)
                    tempo[j] = temp[i][j];
                for (int j = 0; j < tempo.length; j++)
                    temp[i][j] = temp[flag][j];
                for (int j = 0; j < tempo.length; j++)
                    temp[flag][j] = tempo[j];
            }
        }

        return temp;
    }

    public static int binarySearch(String[] namaKendaraan, String cari) {
        int left = 0;
        int right = namaKendaraan.length - 1;

        while ( left <= right ) {
            int mid = (right + left)/2;
            int res = cari.compareTo(namaKendaraan[mid]);

            if ( res == 0 ) {
                return mid;
            } else if ( res > 0 ) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void resultSearch(String[] namaKendaraan, String cari, String[][] temp, int kendaraan) {
        int result = binarySearch(namaKendaraan, cari);
        if ( result == -1 ) {
            System.out.println("\tData " + cari + " tidak ditemukan");
        } else {
            System.out.println("\n>\tResult Search");
            System.out.println("\tNo\t\t  " + Kendaraan(kendaraan) + "\t\t\tHarga(perHari)\t\t   Stok");
            System.out.print("\t" + (result+1) + ". \t\t  " + temp[result][0] + "\t\t\t" + temp[result][1] + "\t\t\t\t"+temp[result][2]);
            System.out.println();
        }
    }

    //    Fungsi hitung
    public static int hitung(String[][] temp, String[] data) {
        int total, harga;
        int pilihMenu = Integer.parseInt(data[2]);
        int jmlSewa = Integer.parseInt(data[3]);
        int lmSewa = Integer.parseInt(data[4]);

        harga = Integer.parseInt(temp[pilihMenu-1][1]);
        total = (harga * lmSewa) * jmlSewa;

        return total;
    }

    //    Fungsi membayar
    public static void membayar(String[][] temp, String[] data, int bayar) {
        data[6] = String.valueOf(bayar);
        bayar -= Integer.parseInt(data[5]);

//        Pemilihan jika bayar lebih, pas, atau kurang. Jika kurang akan dimasukkan ke denda
        if ( bayar >= 0 ) {
            data[7] = String.valueOf(Integer.parseInt(data[7]) + bayar);
        } else if ( bayar < 0 ) {
            data[7] = String.valueOf(0);
            data[8] = String.valueOf(Integer.parseInt(data[8]) + (bayar * -1));
        }

//        Mengurangi stok
        int transportasi = Integer.parseInt(data[2]);
        int jumlah = Integer.parseInt(data[3]);
        int kurang = Integer.parseInt(temp[transportasi-1][2]) - jumlah;

        temp[transportasi-1][2] = String.valueOf(kurang);

        System.out.println("\n\tTerima kasih " + data[1] + " atas kepercayaan anda menggunakan jasa transportasi kami.\n");
    }

    //    Fungsi pengembalian
    public static void kembali(String[][] temp, String[] data) {
        System.out.println(">\tPengembalian\n");
        System.out.print("\tJumlah Waktu Pemakaian\t: ");
        int waktu = Sint.nextInt();

        int jmlSewa = Integer.parseInt(data[3]);
        int kendaraan = Integer.parseInt(data[2]);
        int lmSewa = Integer.parseInt(data[4]);
        data[9] = String.valueOf(waktu);

//        Pemilihan jika lama waktu pengembalian lebih, sama, atau kurang dari lama waktu sewa
        if ( waktu > lmSewa ) {
            int denda = ((waktu - lmSewa) * Integer.parseInt(temp[kendaraan-1][1]) * jmlSewa) + Integer.parseInt(data[8]);

            if ( Integer.parseInt(data[7]) > 0 ) {
                denda -= Integer.parseInt(data[7]);
                data[7] = String.valueOf(0);
            }

            data[8] = String.valueOf(denda);
        } else if ( waktu == lmSewa || waktu == 0 ) {
            data[8] = String.valueOf(Integer.parseInt(data[8]));
        } else if ( waktu < lmSewa ) {
            int kembalian = ((lmSewa - waktu) * Integer.parseInt(temp[kendaraan-1][1]) * jmlSewa) + Integer.parseInt(data[7]);
            data[7] = String.valueOf(kembalian);
            data[8] = String.valueOf(Integer.parseInt(data[8]));
        }

//        Menambah stok
        int tambah = Integer.parseInt(temp[kendaraan-1][2]) + jmlSewa;
        temp[kendaraan-1][2] = String.valueOf(tambah);

        System.out.println();
    }

    //    Menampilkan nota
    public static void nota(String[][] temp, String[] data, int kendaraan) {
        int pilihMenu = Integer.parseInt(data[2]);

        System.out.println(">\tNota");
        System.out.println("\tNo Transaksi\t\t: " + data[0]);
        System.out.println("\tNama Pengguna\t\t: " + data[1]);
        System.out.println("\tNama Kendaraan\t\t: " + Kendaraan(kendaraan));
        System.out.println("\tMerk " + Kendaraan(kendaraan) + "\t\t\t: " + temp[pilihMenu - 1][0]);
        System.out.println("\tHarga(perHari)\t\t: Rp. " + temp[pilihMenu - 1][1]);
        System.out.println("\tJumlah Sewa\t\t\t: " + data[3] + " " + Kendaraan(kendaraan));
        System.out.println("\tLama Sewa\t\t\t: " + data[4] + " hari");

        if ( data[9] == null ) {
            System.out.println("\tWaktu Pengembalian\t: 0 hari" );
        } else {
            System.out.println("\tWaktu Pengembalian\t: " + data[9] + " hari" );
        }

        System.out.println("\tTotal Bayar\t\t\t: Rp. " + data[5]);
        System.out.println("\tNominal Bayar\t\t: Rp. " + data[6]);
        System.out.println("\tKembalian\t\t\t: Rp. " + data[7]);
        System.out.println("\tDenda\t\t\t\t: Rp. " + data[8]);

        System.out.println();
    }

    //    Fungsi bayar denda
    public static void bayarDenda(String[] data, int Denda) {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\tMembayar Denda");
        System.out.println("----------------------------------------------------------------------");

        System.out.println("\tTotal Denda\t\t: " + data[8]);
        System.out.print("\tNominal Bayar\t: Rp. ");
        int bayar = Sint.nextInt();
        System.out.println();

        int membayar = bayar - Denda;
        int kembalian = Integer.parseInt(data[7]);

//        Pemilihan jika jumlah bayar denda lebih, pas, atau kurang dari jumlah denda. Jika lebih akan dimasukkan ke kembalian
        if ( membayar > 0 ) {
            kembalian += membayar;
            data[7] = String.valueOf(kembalian);
            data[8] = String.valueOf(0);
        } else if ( membayar == 0 ) {
            data[8] = String.valueOf(0);
        } else if ( membayar < 0 ) {
            data[8] = String.valueOf(membayar * -1);
        }
    }

    public static void main(String[] args) {
        boolean terurut = false, transaksi = false, cekString = false, pengembalian = false;
        int bayar, pilih = 0, kendaraan = 0;
        String cek;

        String[] list = {"Mobil", "Motor", "Sepeda"};
        String[][] listMobil = {
                {"Fortuner", "600000", "2"},
                {"Avanza\t", "300000", "4"},
                {"Xpander\t", "500000", "3"},
                {"Alphard\t", "800000", "1"},
                {"Xenia\t", "400000", "4"}
        };
        String[][] listMotor = {
                {"Nmax\t", "200000", "3"},
                {"Scoopy\t", "250000", "2"},
                {"Kawasaki", "300000", "2"},
                {"Beat\t", "120000", "5"},
                {"Vario\t", "150000", "4"}
        };
        String[][] listSepeda = {
                {"Tandem\t\t", "85000", "2"},
                {"Lipat\t\t", "60000", "4"},
                {"Listrik\t\t", "65000", "3"},
                {"BMX\t\t\t", "50000", "5"},
                {"Onthel\t\t", "70000", "1"}
        };
        String[] data = new String[10];
        String[] login = new String[2];
        data[8] = String.valueOf(0);
        data[7] = String.valueOf(0);
        data[9] = String.valueOf(0);

        System.out.println("----------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\tRental Kendaraan");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t Selamat Datang\n");

        daftar(login);

//        Perulangan jika username dan password salah
        do {
            cek = signUp(login);

            if (cek.equals("y")) {
                System.out.println("\tAnda berhasil login!\n");
            } else {
                System.out.println("\tUsername atau Password yang anda masukkan salah!\n");
            }
        } while ( cek.equals("t") );


        System.out.println("----------------------------------------------------------------------");
        System.out.println("\t\t\t\tPilih Kendaraan Yang Diinginkan");
        System.out.println("----------------------------------------------------------------------");

        do {
            menu(list);
            System.out.print("\tPilih Kendaraan: ");
            String b = Sstring.nextLine();

            Scanner Stemp = new Scanner(b);

            if ( !Stemp.hasNextInt() ) {
                System.out.println("\tTidak boleh diisi selain angka.\n");
            } else {
                kendaraan = Stemp.nextInt();

                if ( kendaraan >= 4 || kendaraan <= 0 ) {
                    System.out.println("\tTidak boleh lebih diluar rentang dari 1 - 3.\n");
                } else {
                    cekString = true;
                }
            }
        } while ( !cekString );

        System.out.println();

        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\tMenu Daftar " + Kendaraan(kendaraan));
        System.out.println("----------------------------------------------------------------------");

        String[][] temp = new String[5][3];

//        Pemilihan merk kendaraan dan memasukkan array kendaraan yang dipilih ke array sementara
        switch ( kendaraan ) {
            case 1:
                temp = listMobil;
                listKendaraan(temp, kendaraan);
                break;
            case 2:
                temp = listMotor;
                listKendaraan(temp, kendaraan);
                break;
            case 3:
                temp = listSepeda;
                listKendaraan(temp, kendaraan);
                break;
        }

        System.out.println();

//        Perulangan menu pemilihan
        do {
            cekString = false;
            do {
                pilihan(kendaraan);
                String b = Sstring.nextLine();

                Scanner Stemp = new Scanner(b);

                if ( !Stemp.hasNextInt() ) {
                    System.out.println("\tTidak boleh diisi selain angka.\n");
                } else {
                    pilih = Stemp.nextInt();

                    if ( pilih >= 7 || pilih <= 0 ) {
                        System.out.println("\tTidak boleh lebih diluar rentang dari 1 - 6.\n");
                    } else {
                        cekString = true;
                    }
                }
            } while ( !cekString );

            System.out.println();

            switch ( pilih ) {
                case 1:
                    if ( transaksi ) {
                        System.out.println("\tTidak bisa melakukan lebih dari satu transaksi.\n");
                    } else {
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("\t\t\t\t\t\tTambah Data Transaksi");
                        System.out.println("----------------------------------------------------------------------");

                        data = tambah(data, kendaraan);
                        data[5] = String.valueOf(hitung(temp, data));

                        System.out.println("\tTotal Bayar\t\t\t: " + data[5]);
                        System.out.print("\tMasukkan Nominal Bayar: Rp. ");
                        bayar = Sint.nextInt();

                        membayar(temp, data, bayar);
                        transaksi = true;
                    }
                    break;
                case 2:
                    if ( transaksi && !terurut ) {
                        System.out.println("\tMenu tidak dapat diurutkan, karena anda tidak mengurutkan menu sebelum melakukan transaksi.\n");
                    } else {
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("\t\t\t\t\tMengurutkan Nama " + Kendaraan(kendaraan));
                        System.out.println("----------------------------------------------------------------------");

                        System.out.println(">\tSorting");
                        selectionAsc(temp);
                        listKendaraan(temp, kendaraan);
                        terurut = true;

                        System.out.println();
                    }
                    break;
                case 3:
                    if ( terurut ) {
                        String[] namaKendaraan = new String[5];

                        for (int i = 0; i < temp.length; i++) {
                            namaKendaraan[i] = temp[i][0].trim();
                        }

                        System.out.println(">\tPencarian");
                        System.out.print("\tMasukkan nama kendaraan yang dicari\t: ");
                        String cari = Sstring.nextLine();
                        cari = cari.substring(0, 1).toUpperCase() + cari.substring(1);

                        resultSearch(namaKendaraan, cari, temp, kendaraan);
                        System.out.println();
                    } else {
                        System.out.println("\tTidak dapat melakukan pencarian, karena menu belum terurut. Silahkan pilih no 2, agar bisa melakukan pencarian.\n");
                    }
                    break;
                case 4:
                    if ( !transaksi || data[0] == null ) {
                        System.out.println("\tAnda belum melakukan transaksi.\n");
                    } else if ( pengembalian ) {
                        System.out.println("\tTidak bisa melakukan pengembalian lebih dari satu kali.\n");
                    } else {
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("\t\t\t\t\tPengembalian " + Kendaraan(kendaraan));
                        System.out.println("----------------------------------------------------------------------");

                        kembali(temp, data);
                        int waktu = Integer.parseInt(data[9]);

                        if ( waktu == 0 ) {
                            System.out.println("\tAnda belum melakukan pengembalian.\n");
                        } else {
                            System.out.println("\tAnda sudah melakukan pengembalian.\n");
                            pengembalian = true;
                        }
                    }
                    break;
                case 5:
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println("\t\t\t\t\t\tNota Pembayaran");
                    System.out.println("----------------------------------------------------------------------");

                    if ( !transaksi || data[0] == null ) {
                        System.out.println("\tAnda belum melakukan transaksi.\n");
                    } else {
                        nota(temp, data, kendaraan);
                        int Denda = Integer.parseInt(data[8]);
                        boolean lunas = true;

                        //                    Pemilihan jika denda lebih atau sama dengan 0
                        if ( Denda > 0 ) {
                            if ( !pengembalian ) {
                                System.out.println("\tHarap melakukan pengembalian dahulu.\n");
                            } else {
                                bayarDenda(data, Denda);
                                nota(temp, data, kendaraan);
                            }
                            int dendaUang = Integer.parseInt(data[8]);

                            if ( dendaUang == 0 ) {
                                System.out.println("\tLunas\n");
                            } else if ( dendaUang > 0 ) {
                                System.out.println("\tBelum Lunas\n");
                                lunas = false;
                            }
                        } else if ( Denda == 0 ) {
                            System.out.println("\tLunas\n");
                        }

                        System.out.println(">\tPilihan");
                        System.out.println("\t1. Kembali ke Menu");
                        System.out.println("\t2. Hapus Data Transaksi");
                        System.out.print("\tPilih Aksi: ");
                        int hapusData = Sint.nextInt();

                        switch ( hapusData ) {
                            case 1:
                                System.out.println("\tKembali ke Menu.\n");
                                break;
                            case 2:
                                if ( !lunas ) {
                                    System.out.println("\tTidak dapat menghapus, karena denda belum lunas.\n");
                                } else if ( !pengembalian ) {
                                    System.out.println("\tAnda belum melakukan pengembalian.\n");
                                } else {
                                    for (int i = 0; i < data.length; i++) {
                                        if (i == 7 || i == 8) {
                                            data[i] = "0";
                                        } else {
                                            data[i] = null;
                                        }
                                    }

                                    transaksi = false;
                                    pengembalian = false;
                                    System.out.println("\tData Anda telah dihapus.\n");
                                }
                                break;
                        }
                    }
                    break;
                case 6:
                    System.out.println("\tTerima Kasih Sudah Datang, Sampai Bertemu Kembali.");
                    break;
            }
        } while ( pilih != 6 );
    }
}
