package main;

import javax.swing.*;
import IO.Communicator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by clayton on 25/07/16.
 */
public class DeDupDialog extends JDialog {

    private JPanel contentPane;
    private JButton buttonOk;
    private JButton buttonCancel;
    private JCheckBox checkBox_allReadsAsMerged;
    private JTextArea textArea_allReadsAsDescription;

    public DeDupDialog (final Communicator c) {
        setValues(c);
        setContentPane(contentPane);
        setModal(true);

        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOk(c);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
    }

    private void setValues(Communicator c) {
        checkBox_allReadsAsMerged.setSelected(c.isRmdup_allReadsAsMerged());
    }

    private void onOk (Communicator c) {
        c.setRmdup_allReadsAsMerged(checkBox_allReadsAsMerged.isSelected());
        dispose();
    }

    private void onCancel () {
        dispose();
    }
}
