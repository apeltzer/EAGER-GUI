package main;

import IO.Communicator;

import javax.swing.*;
import java.awt.event.*;

public class AdapterRemovalDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField minimum_base_quality_textfield;
    private JTextField minimum_sequence_length_textfield;
    private JTextField minimum_adapter_overlap_textfield;
    private JCheckBox performOnlyAdapterClippingCheckBox;
    private JTextField forward_adapter_textfield;
    private JTextField reverse_adapter_textfield;

    public AdapterRemovalDialog(Communicator communicator) {
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
        c.setMerge_fwadaptor(forward_adapter_textfield.getText());
        c.setMerge_bwadaptor(reverse_adapter_textfield.getText());
        c.setQuality_minreadquality(Integer.parseInt(minimum_base_quality_textfield.getText()));
        c.setQuality_readlength(Integer.parseInt(minimum_sequence_length_textfield.getText()));
        c.setMerge_only_clipping(performOnlyAdapterClippingCheckBox.isSelected());
        c.setMerge_min_adapter_overlap(Integer.parseInt(minimum_adapter_overlap_textfield.getText()));
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void setValues(Communicator c){
        if(c.getMerge_fwadaptor() != null) {
            this.forward_adapter_textfield.setText(c.getMerge_fwadaptor());
        }
        if(c.getMerge_bwadaptor() != null){
            this.reverse_adapter_textfield.setText(c.getMerge_bwadaptor());
        }
        if (c.getQuality_minreadquality() == 0) {
            this.minimum_base_quality_textfield.setText(String.valueOf(c.getQuality_minreadquality()));
        }
        if (c.getQuality_readlength() == 0) {
            this.minimum_sequence_length_textfield.setText(String.valueOf(c.getQuality_readlength()));
        }
        if (c.isMerge_only_clipping()) {
            this.performOnlyAdapterClippingCheckBox.setSelected(c.isMerge_only_clipping());
        }
        if(c.getMerge_min_adapter_overlap() == 0){
            this.minimum_adapter_overlap_textfield.setText(String.valueOf(c.getMerge_min_adapter_overlap()));
        }
    }
}
