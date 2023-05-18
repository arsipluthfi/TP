package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class MemberSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "MEMBER";

    public MemberSystemGUI(MemberSystem memberSystem) {
        super(memberSystem);
    }

    @Override
    public String getPageName(){
        return KEY;
    }

    public Member getLoggedInMember(){
        return loggedInMember;
    }

    @Override
    protected JButton[] createButtons() {
        return new JButton[]{
            new JButton("Saya ingin laundry"),
            new JButton("Lihat detail nota saya")
        };
    }

    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[] {
            e -> createNota(),
            e -> showDetailNota(),
        };
    }

    private void showDetailNota() {
        String notaListString = "";

        for (Nota nota : loggedInMember.getNotaList()) if (nota != null){
            notaListString += "%s\n\n".formatted(nota.toString());
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

    private void createNota() {
        MainFrame.getInstance().navigateTo(CreateNotaGUI.KEY);
    }

}
