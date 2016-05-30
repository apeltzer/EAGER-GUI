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

public class MapCircularMappingDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField mapping_circular_elongationfactor_jtextfield;
    private JTextField bwa_seedlength;
    private JTextField bwa_maxdiff;
    private JTextField bwa_qualityfilter;
    private JTextField chrMTTextField;
    private JCheckBox extractMappedUnmappedReadsCheckBox;

    public MapCircularMappingDialog(final Communicator communicator) {
        setValues(communicator);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(communicator);
            }
        });}





    private void onOK(Communicator communicator) {
// add your code here
        communicator.setMapper_readgroup(mapping_circular_elongationfactor_jtextfield.getText());
        communicator.setMapper_seedlength(bwa_seedlength.getText());
        communicator.setMapper_mismatches(bwa_maxdiff.getText());
        communicator.setMapper_mapquality_filter(bwa_qualityfilter.getText());
        communicator.setCM_tobemapped_against(chrMTTextField.getText());
        communicator.setRun_mapping_extractmappedandunmapped(extractMappedUnmappedReadsCheckBox.isSelected());
        dispose();
    }

    private void setValues(Communicator c){
        if(String.valueOf(c.getCM_elongationfac()) != null){
            mapping_circular_elongationfactor_jtextfield.setText(String.valueOf(c.getCM_elongationfac()));
        }

        if(c.getMapper_seedlength() != null){
            this.bwa_seedlength.setText(c.getMapper_seedlength());
        }
        if(c.getMapper_mismatches() != null){
            this.bwa_maxdiff.setText(c.getMapper_mismatches());
        }
        if(c.getMapper_mapquality_filter() != null){
            this.bwa_qualityfilter.setText(c.getMapper_mapquality_filter());
        }
        if(String.valueOf(c.getCM_tobemapped_against()) != null){
            chrMTTextField.setText(c.getCM_tobemapped_against());
        }

        if(c.isRun_mapping_extractmappedandunmapped()){
            this.extractMappedUnmappedReadsCheckBox.setSelected(true);
        } else {
            this.extractMappedUnmappedReadsCheckBox.setSelected(false);
        }


    }



}
