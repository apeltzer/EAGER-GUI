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
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 20.08.13
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public class VCFFileChooser {
    private JFileChooser jfc = new JFileChooser();
    private FileFilter ff;


    public VCFFileChooser(Communicator c){
        ff = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith(".vcf");
            }

            @Override
            public String getDescription() {
                return "*.vcf files only";  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        jfc.setFileFilter(ff);
        int state = jfc.showOpenDialog(null);
        if (state == JFileChooser.APPROVE_OPTION){
            File f = jfc.getSelectedFile().getAbsoluteFile();
            c.setGUI_GATKSNPreference(f.getAbsolutePath());
            c.setDbsnpreference(true);
        }
    }
}
