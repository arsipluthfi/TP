package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override
    public String getPageName(){
        return KEY;
    }

    @Override
    protected JButton[] createButtons() {
        return new JButton[]{
            new JButton("It's nyuci time!"),
            new JButton("Display list nota")
        };
    }

    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
            e -> cuci(),
            e -> displayNota(),
        };
    }

    private void displayNota() {
        String notaListString = "";

        for (Nota nota : NotaManager.notaList) if (nota != null){
            notaListString += "%s\n".formatted(nota.getNotaStatus());
        }

        JTextArea textArea = new JTextArea(notaListString);
        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setPreferredSize(new Dimension(500, 300));

        JOptionPane.showMessageDialog(
            this,
            scrollPane,
            "Nota Information",
            JOptionPane.PLAIN_MESSAGE
        );
    }

    private void cuci() {
        String notaStatusString = "";

        for (Nota nota: NotaManager.notaList) if (nota != null) {
            notaStatusString += nota.kerjakan();
        }

        JTextArea textArea = new JTextArea(notaStatusString);
        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setPreferredSize(new Dimension(500, 300));

        JOptionPane.showMessageDialog(
            this,
            scrollPane,
            "Nota Information",
            JOptionPane.PLAIN_MESSAGE
        );
    }
}
