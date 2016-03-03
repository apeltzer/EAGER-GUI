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

public class Preseq_Dialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField ccurve_stepsize_textfield;
    private JCheckBox lcextrap_quickmode_textfield;
    private JTextField lcextrap_stepsize_textfield;
    private JTextField lcextrap_extrapolationsize_textfield;
    private JTextField lcextrap_bootstrap_textfield;

    public Preseq_Dialog(final Communicator communicator) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        //Set values to something in communicator if already something has been changed!
        setValuesIfExisting(communicator);

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

    private void setValuesIfExisting(Communicator c) {
        if(String.valueOf(c.getPreseq_ccurve_stepsize()) !=  null){
            ccurve_stepsize_textfield.setText(String.valueOf(c.getPreseq_ccurve_stepsize()));
        }
        if(String.valueOf(c.getPreseq_lcextrap_stepsize()) != null){
            lcextrap_stepsize_textfield.setText(String.valueOf(c.getPreseq_lcextrap_stepsize()));
        }
        if(String.valueOf(c.getPreseq_lcextrap_extrapolationsize()) != null){
            lcextrap_extrapolationsize_textfield.setText(String.valueOf(c.getPreseq_lcextrap_extrapolationsize()));
        }
        if(String.valueOf(c.getPreseq_lcextrap_bootstraps()) != null){
            lcextrap_bootstrap_textfield.setText(String.valueOf(c.getPreseq_lcextrap_extrapolationsize()));
        }
    }

    private void onOK(Communicator communicator) {
// add your code here
        dispose();
        communicator.setPreseq_ccurve_stepsize(Integer.parseInt(ccurve_stepsize_textfield.getText()));
        communicator.setPreseq_lcextrap_stepsize(Integer.parseInt(lcextrap_stepsize_textfield.getText()));
        communicator.setPreseq_lcextrap_bootstraps(Integer.parseInt(lcextrap_bootstrap_textfield.getText()));
        communicator.setPreseq_lcextrap_extrapolationsize(lcextrap_extrapolationsize_textfield.getText());
        communicator.setPreseq_lcextrap_quickmode_nobootstrapping(lcextrap_quickmode_textfield.isSelected());
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

}
