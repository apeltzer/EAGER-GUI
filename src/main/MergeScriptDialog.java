/*
 * Copyright (c) 2016. eager-gui Alexander Peltzer
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package main;

import IO.Communicator;

import javax.swing.*;
import java.awt.event.*;

public class MergeScriptDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField fwread;
    private JTextField bwread;
    private JTextField mergescript_advancedfield;
    private JLabel clipandmerge_minreadquality;
    private JTextField mergescript_textfield_minbasequality;
    private JTextField mergescript_textfield_minsequencelength;
    private JCheckBox performOnlyAdapterClippingCheckBox;
    private JTextField barcode_3p_clip;
    private JTextField barcode_5p_clip;
    private JCheckBox clipandmerge_mergedonly_checkbox;

    public MergeScriptDialog(final Communicator c) {
        setValues(c);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(c);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK(Communicator c) {
// add your code here
        c.setMerge_fwadaptor(fwread.getText());
        c.setMerge_bwadaptor(bwread.getText());
        c.setMerge_advanced(mergescript_advancedfield.getText());
        c.setQuality_minreadquality(Integer.parseInt(mergescript_textfield_minbasequality.getText()));
        c.setQuality_readlength(Integer.parseInt(mergescript_textfield_minsequencelength.getText()));
        c.setMerge_only_clipping(performOnlyAdapterClippingCheckBox.isSelected());
        c.setMerge_barcode3p(barcode_3p_clip.getText());
        c.setMerge_barcode5p(barcode_5p_clip.getText());
        c.setMerge_keep_only_merged(clipandmerge_mergedonly_checkbox.isSelected());
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    private void setValues(Communicator c){
        if(c.getMerge_fwadaptor() != null){
            this.fwread.setText(c.getMerge_fwadaptor());
        }
        if(c.getMerge_bwadaptor() != null){
            this.bwread.setText(c.getMerge_bwadaptor());
        }
        if(c.getMerge_advanced() != null){
            this.mergescript_advancedfield.setText(c.getMerge_advanced());
        }
        if(String.valueOf(c.getQuality_minreadquality()) != null){
            this.mergescript_textfield_minbasequality.setText(String.valueOf(c.getQuality_minreadquality()));
        }
        if(String.valueOf(c.getQuality_readlength()) != null){
            this.mergescript_textfield_minsequencelength.setText(String.valueOf(c.getQuality_readlength()));
        }
        if(String.valueOf(c.isMerge_only_clipping()) != null){
            this.performOnlyAdapterClippingCheckBox.setSelected(c.isMerge_only_clipping());
        }
        if((String.valueOf(c.getMerge_barcode3p()) != null)){
            this.barcode_3p_clip.setText(c.getMerge_barcode3p());
        }
        if((String.valueOf(c.getMerge_barcode5p()) != null)){
            this.barcode_5p_clip.setText(c.getMerge_barcode5p());
        }

        this.clipandmerge_mergedonly_checkbox.setSelected(c.isMerge_keep_only_merged());

    }



}
