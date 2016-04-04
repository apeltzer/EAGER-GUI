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

public class MapBowtie2Dialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField bowtie2_advanced_params;
    private JButton buttonCancel;

    public MapBowtie2Dialog(final Communicator c) {
        initialize(c);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(c);
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    }

    private void onOK(Communicator communicator) {
        communicator.setMapper_advanced(this.bowtie2_advanced_params.getText());
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    private void initialize(Communicator communicator){
        if(communicator.getMapper_advanced() != null){
            this.bowtie2_advanced_params.setText(communicator.getMapper_advanced());
        }
    }

}
