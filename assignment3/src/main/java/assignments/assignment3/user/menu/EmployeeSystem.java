package assignments.assignment3.user.menu;

/*====================================***=====================================*\
|---------------------------------- IMPORTS -----------------------------------|
\*============================================================================*/

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.notaList;

/*========================================================***=========================================================*\
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|-------------------------------------------------------- CLASS -------------------------------------------------------|

->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*====================================================================================================================*/

public class EmployeeSystem extends SystemCLI {

/*====================================***=====================================*\
|--------------------------------- CONSTRUCTOR --------------------------------|
\*============================================================================*/

    public EmployeeSystem() {
        Member[] employeeList = {
            new Employee("Dek Depe", "akuDDP"),
            new Employee("Depram", "musiktualembut"),
            new Employee("Lita Duo", "gitCommitPush"),
            new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
        };

        this.memberList = employeeList;
    }

/*====================================***=====================================*\
|--------------------------------- IO METHODS ---------------------------------|
\*============================================================================*/

    @Override
    protected void displaySpecificMenu() {
        outln("1. It's nyuci time");
        outln("2. Display List Nota");
        outln("3. Logout");
    }

/*====================================***=====================================*\
|-------------------------------- FUNCTIONALITY -------------------------------|
\*============================================================================*/

    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;

        switch (choice) {
            case 1 -> nyuciTime();
            case 2 -> diplayListNota(notaList);
            case 3 -> logout = true;
        }

        return logout;
    }

    private void diplayListNota(Nota[] list) {
        for (Nota nota : list) if (nota != null) outln(nota.getNotaStatus());
    }

    private void nyuciTime() {
        outf("Stand back! %s beginning to nyuci!\n", loginMember.getNama());

        for (Nota nota: notaList) if (nota != null) {
            outln(nota.kerjakan());
        }
    }
}