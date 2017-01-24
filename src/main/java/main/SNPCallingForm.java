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
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
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
        if (referenceselected) {
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
            sr.setSize(600, 300);
            sr.setVisible(true);
            dispose();
        }

        c.setAngsd_create_fasta(createFastAFileCheckBox.isSelected());
        c.setAngsd_fasta_callmethod(getFastaOutformat((String) angsd_fasta_calling_method_combobox.getModel().getSelectedItem(), true));
        c.setAngsd_glf_model(getModel((String) angsd_glm_checkbox.getModel().getSelectedItem(), true));
        c.setAngsd_glm_outformat(getOutformat((String) angsd_glm_outformat.getModel().getSelectedItem(), true));


    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    /**
     * Keep configuration if set to the same values!
     *
     * @param c
     */
    private void setValues(Communicator c) {
        if (c.getGatk_standard_call_confidence() != null) {
            this.gatk_standcallconf_textfield.setText(c.getGatk_standard_call_confidence());
        }
        if (c.getGatk_standard_emit_confidence() != null) {
            this.gatk_emitconf_textfield.setText(c.getGatk_standard_emit_confidence());
        }
        if (c.getGatk_downsampling() != null) {
            this.gatk_downsampling_textfield.setText(c.getGatk_downsampling());
        }
        if (c.getGatk_snp_advanced() != null) {
            this.gatk_advanced_textfield.setText(c.getGatk_snp_advanced());
        }

        if (c.isGatk_emit_all_sites()) {
            this.emitAllSitesCheckbox.setSelected(true);
        } else {
            this.emitAllSitesCheckbox.setSelected(false);
        }
        if (c.isGatk_emit_all_confident_sites()) {
            this.emitAllSitesCheckbox.setSelected(true);
        } else {
            this.emitAllSitesCheckbox.setSelected(false);
        }
        if (c.getGatk_ploidy() != null) {
            this.gatk_ploidy_textfield.setText(c.getGatk_ploidy());
        }

        if (c.getAngsd_glf_model() != null) {
            this.angsd_glm_checkbox.setSelectedItem(getModel(c.getAngsd_glf_model(), false));
        }
        if (c.getAngsd_glm_outformat() != null) {
            this.angsd_glm_outformat.setSelectedItem(getOutformat(c.getAngsd_glm_outformat(), false));
        }

        if (c.isAngsd_create_fasta()) {
            createFastAFileCheckBox.setSelected(true);
        } else {
            createFastAFileCheckBox.setSelected(false);
        }

        if (c.getAngsd_fasta_callmethod() != null) {
            angsd_fasta_calling_method_combobox.setSelectedItem(getFastaOutformat(c.getAngsd_fasta_callmethod(), false));
        }


    }

    /**
     * Returns the correct model for ANGSD
     *
     * @param args
     * @param forward
     * @return
     */

    private String getModel(String args, boolean forward) {
        if (forward) {
            switch (args) {
                case "SAMTools (1)":
                    return "1";
                case "GATK  (2)":
                    return "2";
                case "SOAPsnp (3)":
                    return "3";
                case "SYK (4)":
                    return "4";
                default:
                    return "1";
            }
        } else {
            switch (args) {
                case "1":
                    return "SAMTools (1)";
                case "2":
                    return "GATK  (2)";
                case "3":
                    return "SOAPsnp (3)";
                case "4":
                    return "SYK (4)";
                default:
                    return "GATK  (2)";
            }
        }
    }


    /**
     * Returns the correct outputformat for ANGSD
     *
     * @param args
     * @return
     */
    private String getOutformat(String args, boolean forward) {
        if (forward) {

            switch (args) {
                case "binary all 10 log genotype likelihood":
                    return "1";
                case "beagle genotype likelihood":
                    return "2";
                case "beagle binary":
                    return "3";
                case "textoutput of all 10 log genotype likelihood":
                    return "4";
                default:
                    return "1";
            }
        } else {
            switch (args) {
                case "1":
                    return "binary all 10 log genotype likelihood";
                case "2":
                    return "beagle genotype likelihood";
                case "3":
                    return "beagle binary";
                case "4":
                    return "textoutput of all 10 log genotype likelihood";
                default:
                    return "binary all 10 log genotype likelihood";
            }
        }
    }

    /**
     * Method to reproduce Fasta outputformat code...
     */

    private String getFastaOutformat(String args, boolean forward) {
        if (forward) {
            switch (args) {
                case "sample a random base":
                    return "1";
                case "use the most common base":
                    return "2";
                default:
                    return "1";
            }
        } else {
            switch (args) {
                case "1":
                    return "sample a random base";
                case "2":
                    return "use the most common base";
                default:
                    return "sample a random base";
            }
        }
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setBorder(BorderFactory.createTitledBorder("SNP Calling Advanced Menu"));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("OK");
        panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(14, 6, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("SNP Reference (e.g. dbSNP)");
        panel3.add(label1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Standard Call Confidence");
        panel3.add(label2, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gatk_standcallconf_textfield = new JTextField();
        gatk_standcallconf_textfield.setText("50");
        gatk_standcallconf_textfield.setToolTipText("The minimum phred-scaled confidence threshold at which variants should be called");
        panel3.add(gatk_standcallconf_textfield, new GridConstraints(3, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Caller Configuration");
        panel3.add(label3, new GridConstraints(1, 3, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gatk_downsampling_textfield = new JTextField();
        gatk_downsampling_textfield.setText("250");
        panel3.add(gatk_downsampling_textfield, new GridConstraints(6, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Standard Emit Confidence");
        panel3.add(label4, new GridConstraints(4, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gatk_emitconf_textfield = new JTextField();
        gatk_emitconf_textfield.setText("10");
        gatk_emitconf_textfield.setToolTipText("The minimum phred-scaled confidence threshold at which variants should be emitted (and filtered with LowQual if less than the calling threshold)");
        panel3.add(gatk_emitconf_textfield, new GridConstraints(4, 3, 2, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Downsampling");
        panel3.add(label5, new GridConstraints(6, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gatk_advanced_textfield = new JTextField();
        gatk_advanced_textfield.setToolTipText("<html>\nFor further parameters, lookup the \"UnifiedGenotyper\" or \"HaplotypeCaller\" documentation on the Broad Institutes Website at https://broadinstitute.org/gatk/\n</html>");
        panel3.add(gatk_advanced_textfield, new GridConstraints(7, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Advanced Parameters");
        panel3.add(label6, new GridConstraints(7, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        emitAllSitesCheckbox = new JCheckBox();
        emitAllSitesCheckbox.setText("Emit All Sites / BP_RES Mode HC");
        emitAllSitesCheckbox.setToolTipText("Emit a call for all sites on the genome, even if only reference call has been made. In UnifiedGenotyper this refers to EMIT_ALL_SITES mode, in HaplotypeCaller to BP_RESOLUTION mode.");
        panel3.add(emitAllSitesCheckbox, new GridConstraints(9, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("");
        panel3.add(label7, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gatk_ploidy_textfield = new JTextField();
        gatk_ploidy_textfield.setText("2");
        panel3.add(gatk_ploidy_textfield, new GridConstraints(2, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Ploidy of Organism");
        panel3.add(label8, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("ANGSD Configuration");
        panel3.add(label9, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        genotypeLikelihoodModelLabel = new JLabel();
        genotypeLikelihoodModelLabel.setText("Genotype Likelihood Model");
        panel3.add(genotypeLikelihoodModelLabel, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        angsd_glm_checkbox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("SAMTools (1)");
        defaultComboBoxModel1.addElement("GATK  (2)");
        defaultComboBoxModel1.addElement("SOAPsnp (3)");
        defaultComboBoxModel1.addElement("SYK (4)");
        angsd_glm_checkbox.setModel(defaultComboBoxModel1);
        panel3.add(angsd_glm_checkbox, new GridConstraints(11, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        angsd_glm_outformat = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("binary all 10 log genotype likelihood");
        defaultComboBoxModel2.addElement("beagle genotype likelihood");
        defaultComboBoxModel2.addElement("beagle binary");
        defaultComboBoxModel2.addElement("textoutput of all 10 log genotype likelihood");
        angsd_glm_outformat.setModel(defaultComboBoxModel2);
        panel3.add(angsd_glm_outformat, new GridConstraints(12, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Genotype Likelihood Outformat");
        panel3.add(label10, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createFastAFileCheckBox = new JCheckBox();
        createFastAFileCheckBox.setText("Create FastA file? ");
        panel3.add(createFastAFileCheckBox, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        angsd_fasta_calling_method_combobox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        defaultComboBoxModel3.addElement("sample a random base");
        defaultComboBoxModel3.addElement("use the most common base");
        angsd_fasta_calling_method_combobox.setModel(defaultComboBoxModel3);
        panel3.add(angsd_fasta_calling_method_combobox, new GridConstraints(13, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dbSNPButton = new JButton();
        dbSNPButton.setText("dbSNP / VCF reference");
        dbSNPButton.setToolTipText("Select a dbSNP database for annotating variants with existing data. ");
        panel3.add(dbSNPButton, new GridConstraints(0, 3, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gatk_confident_sites = new JCheckBox();
        gatk_confident_sites.setText("Emit Conf Sites / GVCF Mode HC");
        gatk_confident_sites.setToolTipText("Emits confident sites for UnifiedGenotyper and a GVCF file for HaplotypeCaller if ticked on.");
        panel3.add(gatk_confident_sites, new GridConstraints(9, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
