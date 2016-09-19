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

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
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
    private JComboBox capture_type_combobox;
    private JButton capture_button_select;
    private JButton capture_mt_button_select;

    public DataTypeDialogue(final Communicator communicator) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(communicator);
            }
        });
        mt_capture_jcheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mt_capture_jcheckbox.isSelected()) {
                    capture_mt_button_select.setEnabled(true);
                } else {
                    capture_mt_button_select.setEnabled(false);
                }
            }
        });
        snpcap_checkbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (snpcap_checkbox.isSelected()) {
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
        capture_mt_button_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BEDFileChooser bdf = new BEDFileChooser(communicator);
            }
        });
    }

    ;


    private void onOK(Communicator c) {
        //Checks what kind of data the user used as input and parses this to the communicator class
        //This way, we get the information on what we want to do later on with our data
        if (organism_combobox.getModel().getSelectedItem().equals("Human")) {
            c.setOrganism(true);
        } else {
            c.setOrganism(false);
        }

        if (treatment_combobox.getModel().getSelectedItem().toString().equals("UDG Treated")) {
            c.setUdgtreatment(true);
        } else {
            c.setUdgtreatment(false);
        }

        if (pairment_combobox.getModel().getSelectedItem().toString().equals("Paired Data")) {
            c.setPairmenttype(true);
            c.setMerge_type("PAIRED");
        } else {
            c.setPairmenttype(false);
            c.setMerge_type("SINGLE");
        }

        if (agecombobox.getModel().getSelectedItem().equals("Ancient")) {
            c.setOrganismage(true);
        } else {
            c.setOrganismage(false);
        }

        if (snpcap_checkbox.isSelected()) {
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

        if (merge_all_lanes_jcheckbox.isSelected()) {
            c.setMerge_bam_files(true);
        } else {
            c.setMerge_bam_files(false);
        }

        if (mt_capture_jcheckbox.isSelected()) {
            c.setRun_mt_capture_mode(true);
        } else {
            c.setRun_mt_capture_mode(false);
        }

        dispose();
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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("OK");
        panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(8, 5, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder("Please tell us some more about your input dataset"));
        final JLabel label1 = new JLabel();
        label1.setText("Organism Type");
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        organism_combobox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Human");
        defaultComboBoxModel1.addElement("Bacterial");
        defaultComboBoxModel1.addElement("Other");
        organism_combobox.setModel(defaultComboBoxModel1);
        panel3.add(organism_combobox, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pairment_combobox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Paired Data");
        defaultComboBoxModel2.addElement("Single-End Data");
        pairment_combobox.setModel(defaultComboBoxModel2);
        panel3.add(pairment_combobox, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Pairment");
        panel3.add(label2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Treated Data");
        panel3.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        treatment_combobox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        defaultComboBoxModel3.addElement("UDG Treated");
        defaultComboBoxModel3.addElement("non-UDG Treated");
        treatment_combobox.setModel(defaultComboBoxModel3);
        panel3.add(treatment_combobox, new GridConstraints(2, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Age of Dataset");
        panel3.add(label4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        agecombobox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel4 = new DefaultComboBoxModel();
        defaultComboBoxModel4.addElement("Ancient");
        defaultComboBoxModel4.addElement("Modern");
        agecombobox.setModel(defaultComboBoxModel4);
        panel3.add(agecombobox, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        snpcap_checkbox = new JCheckBox();
        snpcap_checkbox.setText("Capture data?");
        panel3.add(snpcap_checkbox, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        input_already_merged_jcheckbox = new JCheckBox();
        input_already_merged_jcheckbox.setText("Input is already concatenated (skip merging)");
        panel3.add(input_already_merged_jcheckbox, new GridConstraints(5, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        merge_all_lanes_jcheckbox = new JCheckBox();
        merge_all_lanes_jcheckbox.setText("Concatenate lanewise together");
        merge_all_lanes_jcheckbox.setToolTipText("<html>\nTick this if you want to merge e.g. NextSeq Data together into two corresponding R1 and R2 files respectively.\n</html> ");
        panel3.add(merge_all_lanes_jcheckbox, new GridConstraints(6, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mt_capture_jcheckbox = new JCheckBox();
        mt_capture_jcheckbox.setText("MTCapture Data? ");
        mt_capture_jcheckbox.setToolTipText("Check this if you'd like to filter out reads mapping to MT chromosome. In this case specify the FastA identifier for the MT chromosome.");
        panel3.add(mt_capture_jcheckbox, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        capture_type_combobox = new JComboBox();
        capture_type_combobox.setEnabled(false);
        final DefaultComboBoxModel defaultComboBoxModel5 = new DefaultComboBoxModel();
        defaultComboBoxModel5.addElement("390K");
        defaultComboBoxModel5.addElement("1240K");
        capture_type_combobox.setModel(defaultComboBoxModel5);
        panel3.add(capture_type_combobox, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        capture_button_select = new JButton();
        capture_button_select.setEnabled(false);
        capture_button_select.setText("Select BED file");
        panel3.add(capture_button_select, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        capture_mt_button_select = new JButton();
        capture_mt_button_select.setEnabled(false);
        capture_mt_button_select.setText("Select BED file");
        panel3.add(capture_mt_button_select, new GridConstraints(7, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
