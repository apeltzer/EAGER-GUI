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

public class QualityFilterDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField minreadqual;
    private JTextField minreadlength;
    private JPanel mainQFDialog;
    private JTextField qualityfilter_advancedfield;


    public QualityFilterDialog(final Communicator c) {
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

        communicator.setQuality_minreadquality(Integer.parseInt(this.minreadqual.getText()));
        communicator.setQuality_readlength(Integer.parseInt(this.minreadlength.getText()));
        communicator.setQuality_advanced(qualityfilter_advancedfield.getText());
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void setValues(Communicator c){
        if(c.getQuality_advanced() != null){
            this.qualityfilter_advancedfield.setText(c.getQuality_advanced());
        }
        if(String.valueOf(c.getQuality_minreadquality()) != null){
            this.minreadqual.setText(String.valueOf(c.getQuality_minreadquality()));
        }
        if(String.valueOf(c.getQuality_readlength()) != null){
            this.minreadlength.setText(String.valueOf(c.getQuality_readlength()));
        }
    }


}
