import IO.FilePair;
import com.google.common.io.Files;

import java.io.File;
import com.google.common.io.Files;
import java.util.ArrayList;

/**
 * Created by peltzer on 17/10/2016.
 */
public class FilePairData {


    private static ArrayList<String> input = new ArrayList<String>();
    private static ArrayList<FilePair> output = new ArrayList<FilePair>();



    private static void setUp_file_pairing_r12_in_name() {

        input = new ArrayList<>();
        output = new ArrayList<>();
        //TestCase1, R1/2 in the filename and pattern
        input.add("OberW_3Pet/OberW_3Pet_S0_L003_R1_001.fastq.gz");
        input.add("OberW_3Pet/OberW_3Pet_S0_L003_R2_001.fastq.gz");

        //The Files.getNameWithoutExtension() directive is required unfortunately, as the path to a file is not given in our testing environment
        output.add(new FilePair(new File("OberW_3Pet/OberW_3Pet_S0_L003_R1_001.fastq.gz").getAbsolutePath(), new File("OberW_3Pet/OberW_3Pet_S0_L003_R2_001.fastq.gz").getAbsolutePath()));
        System.out.println("Ready to go!");
    }


    private static void setUp_file_pairing_several_lanes (){
        input = new ArrayList<>();
        output = new ArrayList<>();
        //Testcase2, more than one lane
        //Need to use
        input.add("Halb_430W/Halb_430W_S0_L002_R1_001.fastq.gz");
        input.add("Halb_430W/Halb_430W_S0_L002_R2_001.fastq.gz");
        input.add("Halb_430W/Halb_430W_S0_L003_R1_001.fastq.gz");
        input.add("Halb_430W/Halb_430W_S0_L003_R2_001.fastq.gz");
        output.add(new FilePair(new File("Halb_430W/Halb_430W_S0_L002_R1_001.fastq.gz").getAbsolutePath(), new File("Halb_430W/Halb_430W_S0_L002_R2_001.fastq.gz").getAbsolutePath()));
        output.add(new FilePair(new File("Halb_430W/Halb_430W_S0_L003_R1_001.fastq.gz").getAbsolutePath(), new File("Halb_430W/Halb_430W_S0_L003_R2_001.fastq.gz").getAbsolutePath()));
    }


    private static void setUp_file_pairing_normal_case () {
        input = new ArrayList<>();
        output = new ArrayList<>();
        input.add("SZ11/Sample_SZ11-BIS_S16_L001_R1_001.fastq");
        input.add("SZ11/Sample_SZ11-BIS_S16_L001_R2_001.fastq");
        input.add("SZ10/Sample_SZ10-BIS_S15_L001_R1_001.fastq");
        input.add("SZ10/Sample_SZ10-BIS_S15_L001_R2_001.fastq");
        output.add(new FilePair(new File("SZ10/Sample_SZ10-BIS_S15_L001_R1_001.fastq").getAbsolutePath(), new File("SZ10/Sample_SZ10-BIS_S15_L001_R2_001.fastq").getAbsolutePath()));
        output.add(new FilePair(new File("SZ11/Sample_SZ11-BIS_S16_L001_R1_001.fastq").getAbsolutePath(), new File("SZ11/Sample_SZ11-BIS_S16_L001_R2_001.fastq").getAbsolutePath()));

    }


    /**
     * Getter and setter methods
     * @return
     */

    public static ArrayList<String> getInput_file_pairing_r12_in_name(){
        setUp_file_pairing_r12_in_name();
        return input;
    }

    public static ArrayList<String> getInput_file_pairing_several_lanes(){
        setUp_file_pairing_several_lanes();
        return input;
    }

    public static ArrayList<String> getInput_file_pairing_normal_case() {
        setUp_file_pairing_normal_case();
        return input;
    }

    public static ArrayList<FilePair> getOutput(){
        return output;
    }

}
