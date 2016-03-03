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

import javax.swing.*;
import java.io.File;
import IO.Communicator;

public class ContaminationDBChooser {
    private JFileChooser jfc = new JFileChooser();

    public ContaminationDBChooser(Communicator c){
        if(c.getGUI_filepathresults() != null){
            jfc.setCurrentDirectory(new File(c.getGUI_filepathresults()));
        }
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int state = jfc.showOpenDialog(null);
        if(state == JFileChooser.APPROVE_OPTION){
            File f = jfc.getSelectedFile();
            c.setSchmutzi_mt_refDB(f.getAbsolutePath());
        }

    }
















}
