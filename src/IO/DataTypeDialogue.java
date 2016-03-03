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

package IO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataTypeDialogue extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JComboBox organism_combobox;
    private JComboBox treatment_combobox;
    private JComboBox pairment_combobox;
    private JComboBox agecombobox;
    private JCheckBox snpcap_checkbox;
    private JCheckBox input_already_merged_jcheckbox;
    private JCheckBox merge_all_lanes_jcheckbox;
    private JCheckBox mt_capture_jcheckbox;
    private JTextField mtcapture_jtextfield;
    private JComboBox capture_type_combobox;
    private JButton capture_button_select;

    public DataTypeDialogue(final Communicator communicator) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        mtcapture_jtextfield.setText(communicator.getFilter_for_mt());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(communicator);
            }
        });
        mt_capture_jcheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mt_capture_jcheckbox.isSelected()){
                    mtcapture_jtextfield.setEnabled(true);
                } else {
                    mtcapture_jtextfield.setEnabled(false);
                }
            }
        });
        snpcap_checkbox.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent e) {
                if(snpcap_checkbox.isSelected()){
                    capture_type_combobox.setEnabled(true);
                    capture_button_select.setEnabled(true);
                } else {
                    capture_type_combobox.setEnabled(false);
                    capture_button_select.setEnabled(false);

                }
            }
        });
        capture_button_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BEDFileChooser bdf = new BEDFileChooser(communicator);
            }
        });
    };



    private void onOK(Communicator c) {
        //Checks what kind of data the user used as input and parses this to the communicator class
        //This way, we get the information on what we want to do later on with our data
        if(organism_combobox.getModel().getSelectedItem().equals("Human")){
            c.setOrganism(true);
        } else{
            c.setOrganism(false);
        }

        if(treatment_combobox.getModel().getSelectedItem().toString().equals("UDG Treated")){
            c.setUdgtreatment(true);
        } else{
            c.setUdgtreatment(false);
        }

        if(pairment_combobox.getModel().getSelectedItem().toString().equals("Paired Data")){
            c.setPairmenttype(true);
            c.setMerge_type("PAIRED");
        } else {
            c.setPairmenttype(false);
            c.setMerge_type("SINGLE");
        }

        if(agecombobox.getModel().getSelectedItem().equals("Ancient")){
            c.setOrganismage(true);
        } else {
            c.setOrganismage(false);
        }

        if(snpcap_checkbox.isSelected()){
            c.setSnpcapturedata(true);
            c.setSnpcapture_type((String) capture_type_combobox.getModel().getSelectedItem());
        } else {
            c.setSnpcapturedata(false);
        }

        if (input_already_merged_jcheckbox.isSelected()) {
            c.setInput_already_merged(true);
        } else {
            c.setInput_already_merged(false);
        }

        if(merge_all_lanes_jcheckbox.isSelected()) {
            c.setMerge_bam_files(true);
        } else {
            c.setMerge_bam_files(false);
        }

        if(mt_capture_jcheckbox.isSelected()) {
            c.setRun_mt_capture_mode(true);
            c.setFilter_for_mt(mtcapture_jtextfield.getText());
        } else {
            c.setRun_mt_capture_mode(false);
        }

        dispose();
    }



}
