package assignments.assignment1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    static void outln(Object x) {
        System.out.println(x);
    }

    static void outf(String format, Object... args) {
        System.out.printf(format, args);
    }

    static void out(Object x) {
        System.out.print(x);
    }

    public static void main(String[] args) {

        int pilihan;

        mainloop: while (true) {

            printMenu();
            out("Pilihan : ");

            try {
                pilihan = Integer.parseInt(input.nextLine());

                if (pilihan < 0 || pilihan > 2) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException $) {
                outln("Perintah tidak diketahui, silakan periksa kembali.");
                continue;
            }

            outln("================================");

            switch (pilihan) {
                case 0 -> {
                    outln("Terima kasih telah menggunakan NotaGenerator!");
                    break mainloop;
                }
                case 1 -> {
                    String[] namaHP = inputNamaHP();
                    outf("ID Anda : %s\n", generateId(namaHP[0], namaHP[1]));
                }
                case 2 -> {
                    String[] namaHP = inputNamaHP();
                    String id = generateId(namaHP[0], namaHP[1]);

                    String paket, tanggalTerima;
                    int berat;

                    outln("Masukkan tanggal terima:");
                    tanggalTerima = input.nextLine();

                    do {
                        outln("Masukkan paket laundry:");
                        paket = input.nextLine();

                        if (paket.equals("?"))
                            showPaket();

                        else if (!paket.matches("(?i)reguler|fast|express")) {
                            outf("Paket %s tidak diketahui\n", paket);
                            outln("[ketik ? untuk mencari tahu jenis paket]");
                        }

                    } while (!paket.matches("(?i)reguler|fast|express"));

                    outln("Masukkan berat cucian Anda [Kg]:");
                    while (true) {
                        try {
                            berat = Integer.parseInt(input.nextLine());

                            if (berat <= 0) {
                                throw new NumberFormatException();
                            } else if (berat == 1 && berat++ == 1) {
                                outln("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                            }
                            break;

                        } catch (NumberFormatException $) {
                            outln("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        }
                    }

                    outf("Nota Laundry\n%s\n",
                        generateNota(id, paket, berat, tanggalTerima)
                    );
                }
            }
        }
    }

    private static void printMenu() {
        outln("Selamat datang di NotaGenerator!");
        outln("==============Menu==============");
        outln("[1] Generate ID");
        outln("[2] Generate Nota");
        outln("[0] Exit");
    }

    private static void showPaket() {
        outln("+-------------Paket-------------+");
        outln("| Express | 1 Hari | 12000 / Kg |");
        outln("| Fast    | 2 Hari | 10000 / Kg |");
        outln("| Reguler | 3 Hari |  7000 / Kg |");
        outln("+-------------------------------+");
    }

    private static String[] inputNamaHP() {
        String nama, hp;

        outln("Masukkan nama Anda:");
        nama = input.nextLine();

        outln("Masukkan nomor handphone Anda:");

        do {
            hp = input.nextLine();

            if (!hp.matches("[0-9]+")) {
                outln("Nomor hp hanya menerima digit");
            }

        } while (!hp.matches("[0-9]+"));

        String[] result = { nama, hp };
        return result;
    }

    public static String generateId(String nama, String nomorHP) {

        int checkSum = 0;
        String firstName = "";

        for (char c : nama.toCharArray()) {

            if (c == ' ') {
                break;
            }

            firstName += ("" + c).toUpperCase();

        }

        for (char c : (firstName + '-' + nomorHP).toCharArray()) {
            switch (c) {
                case
                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                    'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                    'Y', 'Z' -> checkSum += (c - 'A') + 1;
                case
                    '0', '1', '2', '3', '4',
                    '5', '6', '7', '8', '9' -> checkSum += c - '0';
                default -> checkSum += 7;
            }
        }

        return "%s-%s-%02d".formatted(firstName, nomorHP, checkSum);
    }

    public static String generateNota(String id, String paket, int berat, String tanggalTerima) {

        SimpleDateFormat formatOne = new SimpleDateFormat("dd/mm/yyyy");
        SimpleDateFormat formatTwo = new SimpleDateFormat("yyyy-mm-dd");
        String result = "ID    : %s\n".formatted(id);
        String tanggalSelesai;
        Date terima, selesai;

        try {
            terima = formatOne.parse(tanggalTerima);
        } catch (ParseException $) {
            return null;
        }

        result += "Paket : %s\n".formatted(paket.toLowerCase());
        tanggalTerima = formatTwo.format(terima);

        switch (paket.toLowerCase()) {
            case "express" -> {
                result += "Harga :\n%d kg x 12000 = %d\n"
                        .formatted(berat, berat * 12000);
                tanggalSelesai = LocalDate
                        .parse(tanggalTerima)
                        .plusDays(1)
                        .toString();
            }
            case "fast" -> {
                result += "Harga :\n%d kg x 10000 = %d\n"
                        .formatted(berat, berat * 10000);
                tanggalSelesai = LocalDate
                        .parse(tanggalTerima)
                        .plusDays(2)
                        .toString();
            }
            case "reguler" -> {
                result += "Harga :\n%d kg x 7000 = %d\n"
                        .formatted(berat, berat * 7000);
                tanggalSelesai = LocalDate
                        .parse(tanggalTerima)
                        .plusDays(3)
                        .toString();
            }
            default -> tanggalSelesai = "IMPOSSIBLE";
        }

        try {
            selesai = formatTwo.parse(tanggalSelesai);
        } catch (ParseException $) {
            return null;
        }

        result += "Tanggal Terima  : %s\n".formatted(formatOne.format(terima));
        result += "Tanggal Selesai : %s".formatted(formatOne.format(selesai));

        return result;
    }
}
