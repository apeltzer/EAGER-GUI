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

public class VCF2Genome extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField vcf2draft_minimumquality;
    private JTextField vcf2draft_mincov;
    private JTextField vcf2draft_relativebasedifference;
    private JTextField vcf2dname;

    public VCF2Genome(final Communicator c) {
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
        c.setVcf2dmincov(Integer.parseInt(vcf2draft_mincov.getText()));
        c.setVcf2dminsnpall(Double.parseDouble(vcf2draft_relativebasedifference.getText()));
        c.setVcf2dname(vcf2dname.getText());
        c.setVcf2draft_minquality(Integer.parseInt(vcf2draft_minimumquality.getText()));
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void setValues(Communicator c){
        if(String.valueOf(c.getVcf2dmincov()) != null){
            this.vcf2draft_mincov.setText(String.valueOf(c.getVcf2dmincov()));
        }
        if(String.valueOf(c.getVcf2dminsnpall()) != null){
            this.vcf2draft_relativebasedifference.setText(String.valueOf(c.getVcf2dminsnpall()));
        }
        if(c.getVcf2dname() != null){
            this.vcf2dname.setText(String.valueOf(c.getVcf2dname()));
        }

        if(String.valueOf(c.getVcf2draft_minquality()) != null){
            this.vcf2draft_minimumquality.setText(String.valueOf(c.getVcf2draft_minquality()));

        }
    }


}
