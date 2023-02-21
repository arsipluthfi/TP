package assignments.assignment1;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int pilihan;

        mainloop:
        while (true) {

            printMenu();
            System.out.print("Pilihan : ");

            try {
                pilihan = Integer.parseInt(input.nextLine());

                if (pilihan < 0 || pilihan > 2) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException $) {
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                continue;
            }

            System.out.println("================================");

            switch (pilihan) {
                case 0 -> {
                    System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                    break mainloop;
                }
                case 1 -> {
                    String[] namaHP = inputNamaHP();
                    System.out.printf("ID Anda : %s\n", generateId(namaHP[0], namaHP[1]));
                }
                case 2 -> {
                    String[] namaHP = inputNamaHP();
                    String id = generateId(namaHP[0], namaHP[1]);

                    String paket, tanggalTerima;
                    int berat;

                    System.out.println("Masukkan tanggal terima:");
                    tanggalTerima = input.nextLine();

                    do {
                        System.out.println("Masukkan paket laundry:");
                        paket = input.nextLine();

                        if (paket.equals("?")) showPaket();

                        else if (!paket.matches("(?i)reguler|fast|express")) {
                            System.out.printf("Paket %s tidak diketahui\n", paket);
                            System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                        }

                    } while (!paket.matches("(?i)reguler|fast|express"));

                    System.out.println("Masukkan berat cucian Anda [Kg]:");
                    while (true) {
                        try {
                            berat = Integer.parseInt(input.nextLine());

                            if (berat < 0) {
                                throw new NumberFormatException();
                            } else if (berat < 2) {
                                berat = 2;
                                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                                break;
                            } else break;

                        } catch (NumberFormatException $) {
                            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        }
                    }

                    System.out.printf("Nota Laundry\n%s", generateNota(id, paket.toLowerCase(), berat, tanggalTerima));
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    private static String[] inputNamaHP() {
        String nama, hp;

        System.out.println("Masukkan nama Anda:");
        nama = input.nextLine();

        System.out.println("Masukkan nomor handphone Anda:");

        do {
            hp = input.nextLine();

            if (!hp.matches("[0-9]+")) {
                System.out.println("Nomor hp hanya menerima digit");
            }

        } while (!hp.matches("[0-9]+"));

        String[] result = {nama, hp};
        return result;
    }

    public static String generateId(String nama, String nomorHP) {

        int checkSum = 0;
        String firstName = "";

        for (char c: nama.toCharArray()) {

            if (c == ' ') {
                break;
            }

            firstName += ("" + c).toUpperCase();

        }

        for (char c: (firstName + '-' + nomorHP).toCharArray()) {
            switch (c) {
                case
                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                    'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                    'Y', 'Z' -> {
                        checkSum += (c - 'A') + 1;
                    }
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                    checkSum += c - '0';
                }
                default -> checkSum += 7;
            }
        }

        return "%s-%s-%02d".formatted(firstName, nomorHP, checkSum);
    }

    public static String generateNota(String id, String paket, int berat, String tanggalTerima) {

        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        String result = "ID    : %s\n".formatted(id);
        String tanggalSelesai = "";
        Date terima, selesai;

        try{
            terima = format.parse(tanggalTerima);
            format.applyPattern("yyyy-mm-dd");
            tanggalTerima = format.format(terima);
        } catch (ParseException $) {}

        result += "Paket : %s\n".formatted(paket);

        switch (paket) {
            case "express" -> {
                result += "Harga :\n%d kg x 12000 = %d\n".formatted(berat, berat * 12000);
                tanggalSelesai = LocalDate.parse(tanggalTerima).plusDays(1).toString();
            }
            case "fast" -> {
                result += "Harga :\n%d kg x 10000 = %d\n".formatted(berat, berat * 10000);
                tanggalSelesai = LocalDate.parse(tanggalTerima).plusDays(2).toString();
            }
            case "reguler" -> {
                result += "Harga :\n%d kg x 7000 = %d\n".formatted(berat, berat * 7000);
                tanggalSelesai = LocalDate.parse(tanggalTerima).plusDays(3).toString();
            }
        }

        try{
            selesai = format.parse(tanggalSelesai);
            terima = format.parse(tanggalTerima);
            format.applyPattern("dd/mm/yyyy");
            tanggalSelesai = format.format(selesai);
            tanggalTerima = format.format(terima);
        } catch (ParseException $) {}

        result += "Tanggal Terima  : %s\n".formatted(tanggalTerima);
        result += "Tanggal Selesai : %s".formatted(tanggalSelesai);

        return result;
    }
}
