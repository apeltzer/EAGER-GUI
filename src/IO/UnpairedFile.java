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

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: peltzer
 * Date: 04.11.13
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 */
public class UnpairedFile{
    private boolean paired = false;
    private String descriptor = "";
    private String filepath;

    public UnpairedFile(String filepath, String descriptor){
        this.filepath = filepath;
        this.descriptor = descriptor;
    }

    private void setPaired(){
        this.paired = true;
    }

    public void setPaired(boolean paired) {
        this.paired = paired;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public boolean isPaired(){
        return  this.paired;
    }


}
