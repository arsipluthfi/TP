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

    public static <T> int getLength(T[] arr){
        int count = 0;
        for(T el : arr) if (el != null) ++count;
        return count;
    }

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

        outln("Masukan ID member:");
        String id = input.nextLine();                                           /* Input id                           */
        Member member = Member.find(memberList, id);

        if (Member.find(memberList, id) == null) {
            outf("Member dengan ID %s tidak ditemukan!\n", id);
            return;
        }

        String[] paketBerat = inputPaketBerat();
        String paket = paketBerat[0];
        int berat = Integer.parseInt(paketBerat[1]);

        member.incrementBonus();
        Nota nota = new Nota(member, paket, berat, fmt.format(cal.getTime()));

        notaList = nota.appendTo(notaList);
        outf(
            "Berhasil menambahkan nota!\n[ID Nota = %d]\n%s\n",
            nota.getID(),
            nota.toVerboseString()
        );
    }

    private static void handleListNota() {
        outf(
            "Terdaftar %d nota dalam sistem.\n",
            notaList == null ? 0 : getLength(notaList));

        if (notaList != null) {
            for (Nota n: notaList) if (n != null) outln(n.toString());
        }
    }

    private static void handleListUser() {
        outf(
            "Terdaftar %d member dalam sistem.\n",
            memberList == null ? 0 : getLength(memberList));

        if (memberList != null) {
            for (Member m: memberList) if (m != null) outln(m.toString());
        }
    }

    private static void handleAmbilCucian() {

        String id;                                                              /* Input id                           */

        outln("Masukan ID nota yang akan diambil:");
        do {                                                                    /* Input nota id                      */
            id = input.nextLine();

            if (!id.matches("[0-9]+")) {
                outln("ID nota berbentuk angka!");
            }

        } while (!id.matches("[0-9]+"));                                        /* Validasi input nomor hp            */

        Nota nota = Nota.find(notaList, Integer.parseInt(id));

        if (nota == null) {
            outf("Nota dengan ID %s tidak ditemukan!", id);
        } else if (nota.getIsReady()) {
            outf("Nota dengan ID %s berhasil diambil!", id);
            notaList = nota.popFrom(notaList);
        } else {
            outf("Nota dengan ID %s gagal diambil!", id);
        }

    }

    private static void handleNextDay() {
        cal.add(Calendar.DATE, 1);
        outln("Dek Depe tidur hari ini... zzz...");

        for (Nota n: notaList) if (n != null) {
            n.decrementSisa();

            if(n.getIsReady()) {
                outf(
                    "Laundry dengan nota ID %d sudah dapat diambil!\n",
                    n.getID()
                );
            }
        }

        outln("Selamat pagi dunia!\nDek Depe: It's CuciCuci Time.");
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
