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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: peltzer
 * Date: 04.11.13
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public class FilePair {
    private String f1;

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    private String f2;

    public FilePair(String f1, String f2){
        this.f1 = f1;
        this.f2 = f2;

    }

    public ArrayList<String> getFilepaths(){
        ArrayList<String> list = new ArrayList<String>();
        list.add(f1);
        list.add(f2);
        Collections.sort(list); //FIXME this is bad ...
        return list;
    }

}
