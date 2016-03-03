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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfigurationCreated extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel jtextfield_eagerconf;
    private JLabel outputtextfield;
    private JButton quitbutton;
    private JLabel info_label;

    public ConfigurationCreated(Communicator c) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        Date date = new Date();

        outputtextfield.setText("Your configuration " + dateFormat.format(date) + "-EAGER.xml" + " has been generated successfully.");

        String single_files = "<html>If you've only created a single configuration file, you may run it now by executing:<br>" +
                " eagercli " + c.getGUI_filepathresults()+"/Your_Sample/"
                + dateFormat.format(date)+"-EAGER.xml</html>";
        info_label.setText(single_files);

        String output = "If you created more than one configuration, you could run: <br>\" eagercli ";
        String path = c.getGUI_filepathresults();
        jtextfield_eagerconf.setText("<html>"+output+path + "\"" + "<br> or simply navigate to your resultsfolder and run <br>\" eagercli . \"</html>");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        quitbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    private void onOK() {
// add your code here
        dispose();
    }
}
