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
import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by peltzer on 01/12/15.
 */
public class ContaminationReferenceChooser {
        private JFileChooser jfc = new JFileChooser();
        private FileFilter ff;


        public ContaminationReferenceChooser(Communicator c){
            if(c.getGUI_reference() != null){
                jfc.setCurrentDirectory(new File(c.getGUI_reference()));
            }
            ff = new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.isDirectory() || file.getName().toLowerCase().endsWith(".fa") || file.getName().toLowerCase().endsWith(".fasta") | file.getName().toLowerCase().endsWith(".fas");
                }

                @Override
                public String getDescription() {
                    return "*.fa or *.fasta files only";  //To change body of implemented methods use File | Settings | File Templates.
                }
            };
            jfc.setFileFilter(ff);
            int state = jfc.showOpenDialog(null);
            if (state == JFileChooser.APPROVE_OPTION){
                File f = jfc.getSelectedFile();
                c.setSchmutzi_mt_ref(f.getAbsolutePath());
            }
        }
    }

