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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.util.Hashtable;

public class SNPFilteringDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    private JTextField min_quality_filter_SNPFilter;
    private JTextField min_coverage_filter_SNPFilter;


    public SNPFilteringDialog(final Communicator c) {
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

    private void onOK(Communicator c) {
        c.setGatk_variantfilter_coveragefilter(String.valueOf(min_coverage_filter_SNPFilter.getText()));
        c.setGatk_variantfilter_qualityfilter(String.valueOf(min_quality_filter_SNPFilter.getText()));
        dispose();
    }

    private void onCancel() {
        dispose();
    }


    private void setValues(Communicator c){
        if(c.getGatk_variantfilter_qualityfilter() != null){
            this.min_quality_filter_SNPFilter.setText(c.getGatk_variantfilter_qualityfilter());
        }
        if(c.getGatk_variantfilter_coveragefilter() != null){
            this.min_coverage_filter_SNPFilter.setText(c.getGatk_variantfilter_coveragefilter());
        }
    }



}
