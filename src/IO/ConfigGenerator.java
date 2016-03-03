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

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import main.EAGER;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 04.10.13
 * Time: 13:38
 * This can generate the configuration necessary to run EAGER.
 * Furthermore it can be used to directly run the EAGER pipeline, after clicking run configuration.
 */
public class ConfigGenerator {
    private Communicator c;
    private BufferedWriter bfw;


    public ConfigGenerator(Communicator c, String fileoutput, EAGER eager, boolean runDirectly) throws IOException {
        this.c = c;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        Date date = new Date();
        new File(fileoutput).mkdirs(); //Create Path if necessary!
        String binaryformat = fileoutput + "/" + dateFormat.format(date) + "-EAGER.xml";
        XStream xstream = new XStream(new StaxDriver());
        String xml = xstream.toXML(c);
        FileWriter fw = new FileWriter(new File(binaryformat));
        fw.write(xml);
        fw.flush();
        fw.close();
    }





}
