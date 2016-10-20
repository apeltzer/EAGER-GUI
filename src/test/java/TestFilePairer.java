import IO.FilePair;
import IO.FilePairer;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by peltzer on 17/10/2016.
 * This class is used to test whether file pairing works properly in all given cases and automatically
 */
public class TestFilePairer {


    /*
    Inline Class for comparing FilePairs properly!
     */
    class FilePairCompare implements Comparator<ArrayList<FilePair>> {

        @Override
        public int compare(ArrayList<FilePair> listA, ArrayList<FilePair> listB) {
            int result = 0;
            for(int i = 0; i < listA.size(); i++){
                FilePair pairone = listA.get(i);
                FilePair pairtwo = listB.get(i);
                if (pairone.getF1().equals(pairtwo.getF1()) && pairone.getF2().equals(pairtwo.getF2())) {
                    result+=0;
                } else {
                    result+=-1;
                }
            }
            return result;
        }
    }

    private final FilePairCompare fpcompare = new FilePairCompare();



    @Test
    public void file_pairing_r12_in_name () throws IOException {
        FilePairer fp = new FilePairer(FilePairData.getInput_file_pairing_r12_in_name());

        int result = fpcompare.compare(fp.getListofpairs(), FilePairData.getOutput());
        assertTrue("expected to be equal", result == 0);
    }

    @Test
    public void file_pairing_several_lanes () throws IOException {
        FilePairer fp = new FilePairer(FilePairData.getInput_file_pairing_several_lanes());

        int result = fpcompare.compare(fp.getListofpairs(), FilePairData.getOutput());
        assertTrue("expected to be equal", result == 0);
    }

    @Test
    public void file_pairing_normal_case () throws IOException {
        FilePairer fp = new FilePairer(FilePairData.getInput_file_pairing_normal_case());

        int result = fpcompare.compare(fp.getListofpairs(), FilePairData.getOutput());
        assertTrue("expected to be equal", result == 0);
    }


}
