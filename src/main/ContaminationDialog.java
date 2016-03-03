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
import IO.ContaminationDBChooser;
import IO.ContaminationReferenceChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContaminationDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox schmutzi_library_combobox;
    private JButton schmutzi_select_MT_ref;
    private JButton schmutzi_mt_referenceDB;

    public ContaminationDialog(final Communicator c) {
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
        schmutzi_select_MT_ref.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContaminationReferenceChooser crfchooser = new ContaminationReferenceChooser(c);

            }
        });
        schmutzi_mt_referenceDB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContaminationDBChooser contdbchooser = new ContaminationDBChooser(c);
            }
        });
    }

    private void setValues(Communicator c) {
        if(c.getSchmutzi_library_type() != null){
            this.schmutzi_library_combobox.setSelectedItem(c.getSchmutzi_library_type());
        }
    }

    private void onOK(Communicator c) {
        c.setSchmutzi_library_type((String) this.schmutzi_library_combobox.getModel().getSelectedItem());
        dispose();
    }

    private void onCancel() {
        dispose();
    }


}
