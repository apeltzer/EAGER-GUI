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

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: peltzer
 */
public class FilePairer {
    private ArrayList<UnpairedFile> listoffiles = new ArrayList<UnpairedFile>();
    private ArrayList<FilePair> listofpairs = new ArrayList<FilePair>();



    public FilePairer(ArrayList<String> allinputfiles) throws IOException {
        for (String s : allinputfiles) {
            File f = new File(s);
            String filename = Files.getNameWithoutExtension(s);

            //New Method based on the file names, as this old method doesnt work anymore!
            UnpairedFile unp = new UnpairedFile(f.getAbsolutePath(), filename);
            listoffiles.add(unp);
        }
        pairFiles();
    }

    public void pairFiles() {


        ImmutableList<UnpairedFile> sortedRequests = Ordering.natural()
                .nullsFirst()
                .onResultOf(new Function<UnpairedFile, String>() {
                    @Override
                    public String apply(UnpairedFile unpairedFile) {
                        return unpairedFile.getDescriptor();
                    }
                })
                .immutableSortedCopy(listoffiles);


        for (int i = 0; i < sortedRequests.size(); i++) {
            for (int j = i + 1; j < sortedRequests.size(); j++) {
                UnpairedFile f1 = sortedRequests.get(i);
                UnpairedFile f2 = sortedRequests.get(j);
                if (!(f1.isPaired() || f2.isPaired())) {
                    checkForMate(f1, f2);
                }
            }
        }
    }

    private void checkForMate(UnpairedFile f1, UnpairedFile f2) {

        String[] splitf1 = f1.getDescriptor().split("_");
        String[] splitf2 = f2.getDescriptor().split("_");
        String f1_lane = "";
        String f2_lane = "";
        String f1_pair = "";
        String f2_pair = "";
        String common_f1 = "";
        String common_f2 = "";

        //Information for File #1
        for (String s : splitf1) {
            if (s.startsWith("L")) {
                //then we have the lane information here
                f1_lane = s;
                continue;
            }
            if(s.startsWith("R")){
                //then we have the pair information like R1 = forward, R2 = reverse
                f1_pair = s;
                continue;
            }
            if(f1_lane.isEmpty()){
                common_f1 +=s;
                continue;
            }
        }

        //Information for File #2
        for (String s : splitf2) {
            if (s.startsWith("L")) {
                //then we have the lane information here
                f2_lane = s;
                continue;
            }
            if(s.startsWith("R")){
                //then we have the pair information like R1 = forward, R2 = reverse
                f2_pair = s;
                continue;
            }
            if(f2_lane.isEmpty()){
                common_f2 +=s;
                continue;
            }
        }

        if (common_f1.equals(common_f2) && f1_lane.equals(f2_lane) && !f1_pair.equals(f2_pair)) {
            FilePair fp = new FilePair(f1.getFilepath(), f2.getFilepath());
            listofpairs.add(fp);
            f1.setPaired(true);
            f2.setPaired(true);
        }

    }


    public ArrayList<FilePair> getListofpairs() {
        return listofpairs;
    }

    public ArrayList<ArrayList<String>> getSingleEndDataList(){
        ArrayList<ArrayList<String>> out = new ArrayList<ArrayList<String>>();
        String common= "";
        String lane = "";
        String commonTemp = "";
        ArrayList<String> tmp = new ArrayList<String>();

        for(UnpairedFile entry : listoffiles){
            String[] split = entry.getDescriptor().split("_");



            //Information for File #1
            for (String s : split) {
                if (s.startsWith("L")) {
                    //then we have the lane information here
                    continue;
                }
                if(s.startsWith("R")){
                    //then we have the pair information like R1 = forward, R2 = reverse
                    continue;
                }
                if(lane.isEmpty()){
                    common +=s;
                    continue;
                }
            }

            //Initialize

            if(commonTemp.isEmpty()){
               commonTemp = common;
            }

            if(common.equals(commonTemp)){
                //then we have a file that shares the same type with our other files up to this point...
                tmp.add(entry.getFilepath());
                commonTemp = common;
                common = "";
            } else {
                out.add(tmp);
                tmp = new ArrayList<String>();
                tmp.add(entry.getFilepath());
                commonTemp = common;
                common = "";
            }
        }

        //Clear case
        out.add(tmp);

        return out;
    }

}
