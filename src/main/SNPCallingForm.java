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
import IO.VCFFileChooser;

import javax.swing.*;
import java.awt.event.*;

public class SNPCallingForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton dbSNPButton;
    private JTextField gatk_standcallconf_textfield;
    private JTextField gatk_emitconf_textfield;
    private JTextField gatk_downsampling_textfield;
    private JTextField gatk_advanced_textfield;
    private JCheckBox emitAllSitesCheckbox;
    private JCheckBox gatk_confident_sites;

    private JTextField gatk_ploidy_textfield;
    private JComboBox angsd_glm_checkbox;
    private JLabel genotypeLikelihoodModelLabel;
    private JComboBox angsd_glm_outformat;
    private JCheckBox createFastAFileCheckBox;
    private JComboBox angsd_fasta_calling_method_combobox;
    private boolean referenceselected = false;

    public SNPCallingForm(final Communicator communicator) {
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

        dbSNPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                VCFFileChooser vcfFileChooser = new VCFFileChooser(communicator);
                referenceselected = true;
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

        gatk_confident_sites.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emitAllSitesCheckbox.setSelected(false);
            }
        });
        emitAllSitesCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gatk_confident_sites.setSelected(false);
            }
        });
    }

    private void onOK(Communicator c) {
// add your code here
        if(referenceselected){
        c.setGatk_downsampling(gatk_downsampling_textfield.getText());
        c.setGatk_standard_call_confidence(gatk_standcallconf_textfield.getText());
        c.setGatk_standard_emit_confidence(gatk_emitconf_textfield.getText());
        c.setGatk_snp_advanced(gatk_advanced_textfield.getText());
        c.setGatk_emit_all_sites(emitAllSitesCheckbox.isSelected());
        c.setGatk_emit_all_confident_sites(gatk_confident_sites.isSelected());

        c.setGatk_ploidy(gatk_ploidy_textfield.getText());
        dispose();
        } else {
            referenceselected = true;
            c.setGatk_downsampling(gatk_downsampling_textfield.getText());
            c.setGatk_standard_call_confidence(gatk_standcallconf_textfield.getText());
            c.setGatk_standard_emit_confidence(gatk_emitconf_textfield.getText());
            c.setGatk_snp_advanced(gatk_advanced_textfield.getText());
            c.setGatk_emit_all_sites(emitAllSitesCheckbox.isSelected());
            c.setGatk_emit_all_confident_sites(gatk_confident_sites.isSelected());

            c.setGatk_ploidy(gatk_ploidy_textfield.getText());
            SelectReference sr = new SelectReference();
            sr.setSize(600,300);
            sr.setVisible(true);
            dispose();
        }

        c.setAngsd_create_fasta(createFastAFileCheckBox.isSelected());
        c.setAngsd_fasta_callmethod(getFastaOutformat((String) angsd_fasta_calling_method_combobox.getModel().getSelectedItem(), true));
        c.setAngsd_glf_model(getModel((String) angsd_glm_checkbox.getModel().getSelectedItem(), true));
        c.setAngsd_glm_outformat(getOutformat((String) angsd_glm_outformat.getModel().getSelectedItem(),true));


    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    /**
     * Keep configuration if set to the same values!
     * @param c
     */
    private void setValues(Communicator c){
        if(c.getGatk_standard_call_confidence() != null){
            this.gatk_standcallconf_textfield.setText(c.getGatk_standard_call_confidence());
        }
        if(c.getGatk_standard_emit_confidence() != null){
            this.gatk_emitconf_textfield.setText(c.getGatk_standard_emit_confidence());
        }
        if(c.getGatk_downsampling() != null){
            this.gatk_downsampling_textfield.setText(c.getGatk_downsampling());
        }
        if(c.getGatk_snp_advanced() != null){
            this.gatk_advanced_textfield.setText(c.getGatk_snp_advanced());
        }

        if(c.isGatk_emit_all_sites()){
            this.emitAllSitesCheckbox.setSelected(true);
        } else {
            this.emitAllSitesCheckbox.setSelected(false);
        }
        if(c.isGatk_emit_all_confident_sites()){
            this.emitAllSitesCheckbox.setSelected(true);
        } else {
            this.emitAllSitesCheckbox.setSelected(false);
        }
        if(c.getGatk_ploidy() != null){
            this.gatk_ploidy_textfield.setText(c.getGatk_ploidy());
        }

        if(c.getAngsd_glf_model() != null){
            this.angsd_glm_checkbox.setSelectedItem(getModel(c.getAngsd_glf_model(), false));
        }
        if(c.getAngsd_glm_outformat() != null){
            this.angsd_glm_outformat.setSelectedItem(getOutformat(c.getAngsd_glm_outformat(), false));
        }

        if(c.isAngsd_create_fasta()){
            createFastAFileCheckBox.setSelected(true);
        } else {
            createFastAFileCheckBox.setSelected(false);
        }

        if(c.getAngsd_fasta_callmethod() != null){
            angsd_fasta_calling_method_combobox.setSelectedItem(getFastaOutformat(c.getAngsd_fasta_callmethod(), false));
        }


    }

    /**
     * Returns the correct model for ANGSD
     * @param args
     * @param forward
     * @return
     */

    private String getModel(String args, boolean forward){
        if(forward){
        switch(args){
            case "SAMTools (1)": return "1";
            case "GATK  (2)": return "2";
            case "SOAPsnp (3)": return "3";
            case "SYK (4)" : return "4";
            default: return "1";
        } } else {
            switch(args){
                case "1": return "SAMTools (1)";
                case "2": return "GATK  (2)";
                case "3": return "SOAPsnp (3)";
                case "4": return "SYK (4)";
                default: return "GATK  (2)";
            }
        }
    }


    /**
     * Returns the correct outputformat for ANGSD
     * @param args
     * @return
     */
    private String getOutformat(String args, boolean forward){
        if(forward){

        switch(args){
            case "binary all 10 log genotype likelihood": return "1";
            case "beagle genotype likelihood": return "2";
            case "beagle binary": return "3";
            case "textoutput of all 10 log genotype likelihood": return "4";
            default: return "1";
        } } else {
            switch(args){
                case "1": return "binary all 10 log genotype likelihood";
                case "2": return "beagle genotype likelihood";
                case "3": return "beagle binary";
                case "4": return "textoutput of all 10 log genotype likelihood";
                default: return "binary all 10 log genotype likelihood";
        }
    }}

        /**
         * Method to reproduce Fasta outputformat code...
         */

    private String getFastaOutformat(String args, boolean forward){
        if(forward){
        switch(args){
            case "sample a random base": return "1";
            case "use the most common base": return "2";
            default: return "1";
        }} else {
            switch(args){
                case "1": return "sample a random base";
                case "2": return "use the most common base";
                default: return "sample a random base";
        }
    }}



}
