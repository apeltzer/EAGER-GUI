package main;

import IO.Communicator;

import javax.swing.*;
import java.awt.event.*;

public class PmdToolsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox PMDSFilterCheckBox;
    private JCheckBox cpGRestrictionCheckBox;
    private JTextField pmdsThreshold_field;
    private JTextField range_field;
    private JTextField pmdTools_advanced_parameters;
    private JLabel pmdsThreshold;
    private JLabel range;
    private JLabel adv_parameters;

    public PmdToolsDialog(final Communicator communicator) {
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
        c.setPmdtools_advanced(this.pmdTools_advanced_parameters.getText());
        c.setPMDSFilter(this.PMDSFilterCheckBox.isSelected());
        c.setPmdtoolsThreshold(this.pmdsThreshold_field.getText());
        c.setCpGRestriction(this.cpGRestrictionCheckBox.isSelected());
        c.setCpGRange(this.range_field.getText());

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


    private void setValues(Communicator c) {

        this.PMDSFilterCheckBox.setSelected(c.isPMDSFilter());

        if(c.getPmdtoolsThreshold() != null){
            this.pmdsThreshold_field.setText(c.getPmdtoolsThreshold());
        }

        // isOrganism = true -> human
        if(c.isOrganism()){
            this.cpGRestrictionCheckBox.setSelected(c.isCpGRestriction());
            this.range_field.setText(c.getCpGRange());
        } else {
            this.cpGRestrictionCheckBox.setEnabled(false);
            this.range_field.setEnabled(false);
        }

        if(c.getPmdtools_advanced() != null){
            this.pmdTools_advanced_parameters.setText(c.getPmdtools_advanced());
        }

    }

}
