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

public class MapBWADialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField readgroupTextField;
    private JCheckBox map_filteroutunmapped;
    private JTextField mapper_seedlength_textfield;
    private JTextField mapper_mismatches_textfield;
    private JCheckBox extractMappedUnmappedReadsCheckBox;
    private JTextField mapquality_filter_field;

    public MapBWADialog(final Communicator c) {
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

    private void onOK(Communicator communicator) {
// add your code here
        communicator.setMapper_readgroup(readgroupTextField.getText());
        communicator.setMapper_filter_unmapped(map_filteroutunmapped.isSelected());
        communicator.setMapper_seedlength(mapper_seedlength_textfield.getText());
        communicator.setMapper_mismatches(mapper_mismatches_textfield.getText());
        communicator.setRun_mapping_extractmappedandunmapped(extractMappedUnmappedReadsCheckBox.isSelected());
        communicator.setMapper_mapquality_filter(mapquality_filter_field.getText());
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }


    private void setValues(Communicator c){
        if(c.getMapper_readgroup() != null){
            this.readgroupTextField.setText(c.getMapper_readgroup());
        }

        if(c.isMapper_filter_unmapped()){
            this.map_filteroutunmapped.setSelected(true);
        } else {
            this.map_filteroutunmapped.setSelected(false);
        }
        if(c.isRun_mapping_extractmappedandunmapped()){
            this.extractMappedUnmappedReadsCheckBox.setSelected(true);
        } else {
            this.extractMappedUnmappedReadsCheckBox.setSelected(false);
        }
        if(c.getMapper_seedlength() != null){
            this.mapper_seedlength_textfield.setText(c.getMapper_seedlength());
        }
        if(c.getMapper_mismatches() != null){
            this.mapper_mismatches_textfield.setText(c.getMapper_mismatches());
        }
        if(c.getMapper_mapquality_filter() != null){
            this.mapquality_filter_field.setText(c.getMapper_mapquality_filter());
        }
    }
}
