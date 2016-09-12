package main;

import IO.Communicator;

import javax.swing.*;
import java.awt.event.*;

public class DamageProfilerDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField DamageProfiler_threshold_field;
    private JTextField DamageProfiler_length_field;
    private JLabel length;
    private JLabel threshold;
    private JCheckBox useAllMappedReadsCheckBox;

    public DamageProfilerDialog(final Communicator communicator) {
        setValues(communicator);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(communicator);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK(Communicator c) {
        c.setMapdamage_length(DamageProfiler_length_field.getText());
        c.setMapdamage_advanced("-t "+DamageProfiler_threshold_field.getText());
        if(useAllMappedReadsCheckBox.isSelected()){
            c.setDamageProfilerOnlyMerged(false);
        } else {
            c.setDamageProfilerOnlyMerged(true);
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


    private void setValues(Communicator c){
        if(c.getMapdamage_length() != null){
            this.DamageProfiler_length_field.setText(c.getMapdamage_length());
        }
        if(c.getMapdamage_advanced() != null){
            this.DamageProfiler_threshold_field.setText("25");
        }

    }

}
