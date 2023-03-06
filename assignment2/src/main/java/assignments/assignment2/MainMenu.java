package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static Nota[] notaList;
    private static Member[] memberList;
    private int idNota;

    public static void main(String[] args) {
        mainloop: while (true) {

            printMenu();

            out("Pilihan : ");
            String command = input.nextLine();
            outln("================================");

            switch (command) {
                case "0" -> {break mainloop;}
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                default -> {
                    outln("Perintah tidak diketahui, silakan periksa kembali.");
                }
            }
        }
        outln("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void handleGenerateUser() {
        String[] namaHP = inputNamaHP();
        String nama = namaHP[0], noHP = namaHP[1];

        Member newMember = new Member(nama, noHP);
        if (memberList == null || !newMember.isIn(memberList)) {
            memberList = newMember.appendTo(memberList);
            outf("Berhasil membuat member dengan ID %s!\n", newMember.getID());
        } else {
            outf(
                "Member dengan nama %s dan nomor hp %s sudah ada!\n",
                nama,
                noHP
            );
        }
    }

    private static void handleGenerateNota() {
        String[] beratString = inputPaketBerat();
    }

    private static void handleListNota() {
    }

    private static void handleListUser() {
    }

    private static void handleAmbilCucian() {
    }

    private static void handleNextDay() {
    }

    private static void printMenu() {
        outln("\nSelamat datang di CuciCuci!");
        outf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        outln("==============Menu==============");
        outln("[1] Generate User");
        outln("[2] Generate Nota");
        outln("[3] List Nota");
        outln("[4] List User");
        outln("[5] Ambil Cucian");
        outln("[6] Next Day");
        outln("[0] Exit");
    }

}
