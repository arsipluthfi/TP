package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {

    final private String nama, noHp, id;
    private int bonusCounter;

    public Member(String nama, String noHp) {
        this.nama = nama;
        this.noHp = noHp;
        this.id = NotaGenerator.generateId(this.nama, this.noHp);
    }

    public String getNama() {
        return this.nama;
    };

    public String getNoHP() {
        return this.noHp;
    };

    public String getID() {
        return this.id;
    }

    public int getBonus() {
        return this.bonusCounter;
    }

    public void incrementBonus() {
        this.bonusCounter = this.bonusCounter > 3 ? 0 : ++this.bonusCounter;
    }

    public Member[] appendTo(Member[] array) {                                  /* Efficient dynamic array            */

        if (array == null) array = new Member[1];

        Member[] newArray = new Member[array.length];
        int idx;

        if (array[array.length - 1] != null) {                                  /* Kalau sudah penuh                  */
            newArray =  new Member[array.length * 2];                           /* Create new array twice the size */
        }

        for (idx = 0; idx < array.length && array[idx] != null; idx++) {        /* Copy arrays                        */
            newArray[idx] = array[idx];                                         /* Inserting values                   */
        }

        newArray[idx] = this;                                                   /* Adding the new element             */

        return newArray;
    }

    public boolean isIn(Member[] array) {                                       /* Validasi array                     */
        for (Member m: array) {
            if (
                this.nama.equals(m.getNama()) &&
                this.noHp.equals(m.getNoHP())
            ) return true;
        }

        return false;                                                           /* else                               */
    }

    public String toString() {
        return "- %s : %s".formatted(this.id, this.nama);
    }

    public static Member find(Member[] array, String id) {
        if (array == null) return null;
        for (Member m: array) if (m.getID().equals(id)) return m;
        return null;
    }

}
