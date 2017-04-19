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
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
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
    private JTextField minimum_adapter_overlap_textfield;

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
        c.setMerge_min_adapter_overlap(Integer.parseInt(minimum_adapter_overlap_textfield.getText()));
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

    private void setValues(Communicator c) {
        if (c.getMerge_fwadaptor() != null) {
            this.fwread.setText(c.getMerge_fwadaptor());
        }
        if (c.getMerge_bwadaptor() != null) {
            this.bwread.setText(c.getMerge_bwadaptor());
        }
        if (c.getMerge_advanced() != null) {
            this.mergescript_advancedfield.setText(c.getMerge_advanced());
        }

        if(c.getMerge_min_adapter_overlap() != 0){
            this.minimum_adapter_overlap_textfield.setText(String.valueOf(c.getMerge_min_adapter_overlap()));
        }
        if (String.valueOf(c.getQuality_minreadquality()) != null) {
            this.mergescript_textfield_minbasequality.setText(String.valueOf(c.getQuality_minreadquality()));
        }
        if (String.valueOf(c.getQuality_readlength()) != null) {
            this.mergescript_textfield_minsequencelength.setText(String.valueOf(c.getQuality_readlength()));
        }
        if (String.valueOf(c.isMerge_only_clipping()) != null) {
            this.performOnlyAdapterClippingCheckBox.setSelected(c.isMerge_only_clipping());
        }
        if ((String.valueOf(c.getMerge_barcode3p()) != null)) {
            this.barcode_3p_clip.setText(c.getMerge_barcode3p());
        }
        if ((String.valueOf(c.getMerge_barcode5p()) != null)) {
            this.barcode_5p_clip.setText(c.getMerge_barcode5p());
        }

        this.clipandmerge_mergedonly_checkbox.setSelected(c.isMerge_keep_only_merged());

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
        contentPane.setLayout(new GridLayoutManager(8, 2, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setBorder(BorderFactory.createTitledBorder("Clip&Merge Advanced Configuration"));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
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
        panel3.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        bwread = new JTextField();
        bwread.setText("AGATCGGAAGAGCGTCGTGTAGGGAAAGAGTGTA");
        bwread.setToolTipText("Enter different BW read adaptor: Default is 'AGATCGGAAGAGCGTCGTGTAGGGAAAGAGTGTA'");
        panel3.add(bwread, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        fwread = new JTextField();
        fwread.setText("AGATCGGAAGAGCACACGTCTGAACTCCAGTCAC");
        fwread.setToolTipText("Enter different FW read adaptor: Default is 'AGATCGGAAGAGCGTCGTGTAGGGAAAGAGTGTA'");
        panel3.add(fwread, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Forward read adaptor");
        label1.setToolTipText("Choose a different FW read adaptor for merging");
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Backward read adaptor");
        label2.setToolTipText("Choose different backward read adaptor");
        panel3.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mergescript_advancedfield = new JTextField();
        mergescript_advancedfield.setToolTipText("<html>\n<br>-m (minimum overlap of adapter vs read for adapter clipping, default=8, use smaller values for SE data</br>\n<br>-p (min number of nucleotides overlapping for merging,default=10)</br>\n<br>-e (error rate for merging forward and reverse reads, default=0.05)</br>\n<br>-u (write unmerged fw+rv reads into separate file)</br>\n<br>-no_clipping</br>\n<br>-no_merging</br>\n<br>--timeEstimation for estimating runtimes</br>\n<br>--verbose for printing additional processing information.</br>");
        panel3.add(mergescript_advancedfield, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Advanced Parameters");
        panel3.add(label3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clipandmerge_minreadquality = new JLabel();
        clipandmerge_minreadquality.setText("Minimum Base Quality");
        contentPane.add(clipandmerge_minreadquality, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mergescript_textfield_minbasequality = new JTextField();
        mergescript_textfield_minbasequality.setHorizontalAlignment(0);
        mergescript_textfield_minbasequality.setText("30");
        contentPane.add(mergescript_textfield_minbasequality, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        mergescript_textfield_minsequencelength = new JTextField();
        mergescript_textfield_minsequencelength.setHorizontalAlignment(0);
        mergescript_textfield_minsequencelength.setText("20");
        contentPane.add(mergescript_textfield_minsequencelength, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Minimum Sequence Length");
        contentPane.add(label4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        performOnlyAdapterClippingCheckBox = new JCheckBox();
        performOnlyAdapterClippingCheckBox.setText("Perform only adapter clipping");
        performOnlyAdapterClippingCheckBox.setToolTipText("If you tick this, only adapter clipping without merging is performed.");
        contentPane.add(performOnlyAdapterClippingCheckBox, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Clip N 3' Bases (Barcode)");
        contentPane.add(label5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Clip N 5' Bases (Barcode)");
        contentPane.add(label6, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        barcode_3p_clip = new JTextField();
        barcode_3p_clip.setHorizontalAlignment(0);
        barcode_3p_clip.setText("");
        contentPane.add(barcode_3p_clip, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        barcode_5p_clip = new JTextField();
        barcode_5p_clip.setHorizontalAlignment(0);
        barcode_5p_clip.setText("");
        contentPane.add(barcode_5p_clip, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        clipandmerge_mergedonly_checkbox = new JCheckBox();
        clipandmerge_mergedonly_checkbox.setText("Keep merged only");
        contentPane.add(clipandmerge_mergedonly_checkbox, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
