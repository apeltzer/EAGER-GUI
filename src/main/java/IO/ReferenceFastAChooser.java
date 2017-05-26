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
 * Time: 15:56
 * To change this template use File | Settings | File Templates.
 */


public class ReferenceFastAChooser {
    public static final int REFERENCE_FASTA = 0;
    public static final int REFERENCE_FASTA_MASKED_CAPTURE = 1;

    private JFileChooser jfc = new JFileChooser();
    private FileFilter ff;


    public ReferenceFastAChooser(Communicator c, int updateTarget){
        if ( !(updateTarget == ReferenceFastAChooser.REFERENCE_FASTA || updateTarget == ReferenceFastAChooser.REFERENCE_FASTA_MASKED_CAPTURE) ) {
            throw new RuntimeException("Invalid target passed to ReferenceFastaAChooser");
        }

        if(updateTarget == ReferenceFastAChooser.REFERENCE_FASTA && c.getGUI_reference() != null) {
            jfc.setCurrentDirectory(new File(c.getGUI_reference()));
        } else if (updateTarget == ReferenceFastAChooser.REFERENCE_FASTA_MASKED_CAPTURE && c.getGUI_reference_mask() != null) {
            jfc.setCurrentDirectory(new File(c.getGUI_reference_mask()));
        }
        ff = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith(".fa") || file.getName().toLowerCase().endsWith(".fasta") | file.getName().toLowerCase().endsWith(".fas") || file.getName().toLowerCase().endsWith(".fna");
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
            if ( updateTarget == ReferenceFastAChooser.REFERENCE_FASTA ) {
                c.setGUI_reference(f.getAbsolutePath());
            } else if (updateTarget == ReferenceFastAChooser.REFERENCE_FASTA_MASKED_CAPTURE) {
                c.setGUI_reference_mask(f.getAbsolutePath());
            }
        }
    }
}