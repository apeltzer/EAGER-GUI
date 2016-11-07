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

/**
 * Created by peltzer on 16.07.14.
 * Takes a filepath (e.g. to a folder) and recursively searches files in it.
 */

package IO;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;

/**
 * Recursively lists files in Folders, ending with *.fq or *.fastq
 */
public final class FileSearcher {
    private ArrayList<String> fastq_files = new ArrayList<String>();

    public ArrayList<String> processFiles(String pathtoprocess) throws IOException{
        FileVisitor<Path> fileProcessor = new ProcessFile();
        Files.walkFileTree(Paths.get(pathtoprocess), EnumSet.of(FileVisitOption.FOLLOW_LINKS),100,fileProcessor );
        return fastq_files;
    }

    private class ProcessFile extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path aFile, BasicFileAttributes aAttrs
        ) throws IOException {
            if(!aFile.toFile().isDirectory() && !aFile.toFile().getAbsolutePath().startsWith(".")){
              if(aFile.toFile().getAbsolutePath().endsWith(".fq") || aFile.toFile().getAbsolutePath().endsWith(".fastq")
                      || aFile.toFile().getAbsolutePath().endsWith(".gz") || aFile.toFile().getAbsolutePath().endsWith(".gzip") || aFile.toFile().getAbsolutePath().endsWith(".bam")){
                  fastq_files.add(aFile.toString());
              }
            }
            return FileVisitResult.CONTINUE;
        }


    }
}

