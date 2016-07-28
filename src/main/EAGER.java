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

package main;

import IO.*;
import com.google.common.io.Files;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: peltzer
 * Date: 14.08.13
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 */
public class EAGER {
    private final String EAGER_VERSION = "1.92.12";

    private String filepath;
    private JCheckBox fastQCAnalysisCheckBox;
    private JCheckBox qualityFilteringCheckBox;
    private JButton advancedVCF2DraftButton;
    private JButton selectInputFqFilesButton;
    private JComboBox genotyper;
    private JButton QualityButton;
    private JButton RunButton;
    private JButton MergeButton;
    private JButton MapButton;
    private JButton MapDamageButton;
    private JCheckBox GATKSNPFilteringCheckBox;
    private JButton SNPFilterButton;
    private JButton selectall;
    private JButton deselectAllButton;
    private JButton referenceButton;
    private JCheckBox VCF2DraftCheckBox;
    private JPanel mainpanel;
    private JCheckBox merge;
    private JCheckBox map;
    private JCheckBox coverage;
    private JCheckBox mapdamage;
    private JCheckBox complexity;
    private JCheckBox gatksnpcall;
    private JButton snpcalladvanced;
    private JPanel Masterpanel;
    private JCheckBox circularMappingCheckBox;
    private JTextField cpucores_textfield;
    private JCheckBox cleanUpBox;
    private JTextField maxmemory_textfield;
    private JButton preseq_advanced_button;
    private JButton referenceMT;
    private JButton output_button;
    private JButton cm_jbutton;
    private JButton advanced_fastqc_button;
    private JButton aboutButton;
    private JCheckBox runReportGenerator;
    private JComboBox mapper_selection;
    private JCheckBox checkbox_rmdup;
    private JComboBox rmdup_combobox;
    private JCheckBox schmutzi_checkbox;
    private JButton schmutzi_advanced_button;
    private JCheckBox useSystemTmpDirCheckBox;
    private JButton dedup_advanced_button;
    private JMenuBar jmenubar;
    private JMenu jmenu;
    public Image icon;




    /**
     * Important Variables for Configuration Generator
     */

    private Communicator communicator;


    /**
     * Main method, creating the graphical interface with several default parameters.
     *
     * @param args No Arguments required
     */
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("EAGER");
        setWindowPosition(frame);
        EAGER eager = new EAGER();
        frame.setIconImage(eager.icon);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setContentPane(eager.Masterpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(d.width / 6, d.height / 4));
        frame.pack();
        frame.setVisible(true);
        eager.checkBoxes();
        eager.qualityFilteringCheckBox.setSelected(false);
        eager.qualityFilteringCheckBox.setEnabled(false);
        eager.RunButton.setEnabled(false);
    }

    /**
     * Main method used to generate the EAGER GUI
     */

    public EAGER() throws IOException {
        this.icon = ImageIO.read(getClass().getResource("/resource/EAGER_Logo.png"));
        communicator = new Communicator();
        checkBoxes();
        VCF2DraftCheckBox.setSelected(false);
        qualityFilteringCheckBox.setSelected(false);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        /**
         * Listener Section
         */

        fastQCAnalysisCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);    //To change body of overridden methods use File | Settings | File Templates.
                fastQCAnalysisCheckBox.setToolTipText("FastQC Analysis: Runs a FastQC analysis on your input dataset.");
            }
        });
        qualityFilteringCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);    //To change body of overridden methods use File | Settings | File Templates.
                qualityFilteringCheckBox.setToolTipText("Quality Filter: Runs a quality filtering step on your input dataset, using default parameters. To change these, click on advanced.");
            }
        });
        merge.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);    //To change body of overridden methods use File | Settings | File Templates.
                merge.setToolTipText("Mate-Pair Merging: Runs a merging step on both your forward and reverse reads of your input dataset, using default parameters. To change these, click on advanced.");
            }
        });
        map.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);    //To change body of overridden methods use File | Settings | File Templates.
                map.setToolTipText("Mapping: Maps the previously entered input files on a given reference dataset with BWA, using default parameters. To change these, click on advanced.");
            }
        });
        coverage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);    //To change body of overridden methods use File | Settings | File Templates.
                coverage.setToolTipText("Calculates the coverage of the input files using samtools mpileup and a small Java calculator tool. No further parameters are necessary.");
            }
        });
        mapdamage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);    //To change body of overridden methods use File | Settings | File Templates.
                mapdamage.setToolTipText("mapDamage calculation: Calculates the map damage and deamination patterns on the input files. To change default parameters, click on advanced.");
            }
        });
        complexity.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);    //To change body of overridden methods use File | Settings | File Templates.
                complexity.setToolTipText("Complexity Estimation of the library: Estimates the library complexity of the provided input data.");
            }
        });

        gatksnpcall.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);    //To change body of overridden methods use File | Settings | File Templates.
                gatksnpcall.setToolTipText("Runs the GATK SOP SNP calling pipeline utilizing the Haplotype Caller as a default value. You may change these settings inside the checkbox and by clicking on advanced.");
            }
        });
        VCF2DraftCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);    //To change body of overridden methods use File | Settings | File Templates.
                VCF2DraftCheckBox.setToolTipText("Generates the VCF2Genome genome, instead of only marking SNPs like the previous step. To change default parameters, click on advanced.");
            }
        });
        GATKSNPFilteringCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);    //To change body of overridden methods use File | Settings | File Templates.
                GATKSNPFilteringCheckBox.setToolTipText("Quality filters the VCF file using GATK filtering methods. Default parameters may be changed by clicking on advanced.");
            }
        });
        RunButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);    //To change body of overridden methods use File | Settings | File Templates.
                RunButton.setToolTipText("By clicking on this, you generate the appropriate Shell script required to run the overall pipeline.");
            }
        });
        genotyper.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);    //To change body of overridden methods use File | Settings | File Templates.
                genotyper.setToolTipText("You may select which Genotyper GATK should use here.");
            }
        });
        selectInputFqFilesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);    //To change body of overridden methods use File | Settings | File Templates.
                selectInputFqFilesButton.setToolTipText("Click on this and select your FW / BW read pair. Do _not_ select more than the corresponding pair of reads.");
            }
        });
        referenceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);    //To change body of overridden methods use File | Settings | File Templates.
                referenceButton.setToolTipText("Select your reference genome in FastA format here.");
            }
        });

        selectall.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);    //To change body of overridden methods use File | Settings | File Templates.
                selectall.setToolTipText("Selects all checkboxes.");
            }
        });

        output_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                output_button.setToolTipText("Select your output folder in which the results of the pipeline should be stored.");
            }
        });

        useSystemTmpDirCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                useSystemTmpDirCheckBox.setToolTipText("If not selected EAGER will attempt to use the output folder for temporary files");
            }
        });

        /**
         *   Action Listener Section
         */
        selectall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                checkBoxes();
            }
        });


        QualityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                QualityFilterDialog qf = new QualityFilterDialog(communicator);
                setWindowPosition(qf);
                qf.setSize(400, 400);
                qf.setVisible(true);
            }
        });

        MergeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MergeScriptDialog ms = new MergeScriptDialog(communicator);
                setWindowPosition(ms);

                ms.setSize(400, 400);
                ms.setVisible(true);
                ms.setLocationByPlatform(true);
            }
        });

        MapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Depending on which one was selected, we need different dialogs!
                if (mapper_selection.getSelectedItem().equals("BWA")) {
                    MapBWADialog mpd = new MapBWADialog(communicator);
                    setWindowPosition(mpd);
                    mpd.setSize(600, 400);
                    mpd.setVisible(true);
                } else if (mapper_selection.getSelectedItem().equals("BWAMem")) {
                    MapBWAMemDialog mpbwamemd = new MapBWAMemDialog(communicator);
                    setWindowPosition(mpbwamemd);
                    mpbwamemd.setSize(600, 400);
                    mpbwamemd.setVisible(true);
                } else if (mapper_selection.getSelectedItem().equals("Stampy")) {
                    //Then do stampy!
                    MapStampyDialog mapStampyDialog = new MapStampyDialog(communicator);
                    setWindowPosition(mapStampyDialog);
                    mapStampyDialog.setSize(600, 400);
                    mapStampyDialog.setVisible(true);
                } else if (mapper_selection.getSelectedItem().equals("Bowtie 2")) {
                    //Then do Bowtie 2!
                    MapBowtie2Dialog mpbowtie = new MapBowtie2Dialog(communicator);
                    setWindowPosition(mpbowtie);
                    mpbowtie.setSize(600, 400);
                    mpbowtie.setVisible(true);
                } else if (mapper_selection.getSelectedItem().equals("CircularMapper")) {
                    MapCircularMappingDialog mpcircular = new MapCircularMappingDialog(communicator);
                    setWindowPosition(mpcircular);
                    mpcircular.setSize(600, 400);
                    mpcircular.setVisible(true);
                }

            }
        });

        selectInputFqFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FastQFileChooser fqfc = new FastQFileChooser(communicator);
                selectInputFqFilesButton.setForeground(Color.green);
                if (checkIfInputWasSelected()) {
                    RunButton.setEnabled(true);
                } else {
                    RunButton.setEnabled(false);
                }
            }
        });

        referenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ReferenceFastAChooser rffqc = new ReferenceFastAChooser(communicator);
                referenceButton.setForeground(Color.green);

                if (checkIfInputWasSelected()) {
                    RunButton.setEnabled(true);
                } else {
                    RunButton.setEnabled(false);
                }
            }
        });

        MapDamageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MapDamageDialog mapDamageDialog = new MapDamageDialog(communicator);
                setWindowPosition(mapDamageDialog);

                mapDamageDialog.setSize(400, 400);
                mapDamageDialog.setVisible(true);
            }
        });

        snpcalladvanced.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SNPCallingForm SNPCallingForm = new SNPCallingForm(communicator);
                setWindowPosition(SNPCallingForm);
                SNPCallingForm.setSize(900, 500);
                SNPCallingForm.setVisible(true);
            }
        });

        advancedVCF2DraftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                VCF2Genome vcf2Draft = new VCF2Genome(communicator);
                communicator.setGatk_emit_all_sites(true);
                setWindowPosition(vcf2Draft);
                vcf2Draft.setSize(600, 500);
                vcf2Draft.setVisible(true);
            }
        });

        SNPFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SNPFilteringDialog snpFilteringDialog = new SNPFilteringDialog(communicator);
                setWindowPosition(snpFilteringDialog);
                snpFilteringDialog.setSize(500, 400);
                snpFilteringDialog.setVisible(true);
            }
        });

        genotyper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
                communicator.setGatk_caller(genotyper.getSelectedItem().toString());
                if (genotyper.getSelectedItem().toString().equals("ANGSD")) {
                    GATKSNPFilteringCheckBox.setEnabled(false);
                    VCF2DraftCheckBox.setEnabled(false);
                    GATKSNPFilteringCheckBox.setSelected(false);
                    VCF2DraftCheckBox.setSelected(false);
                    SNPFilterButton.setEnabled(false);
                    advancedVCF2DraftButton.setEnabled(false);
                } else {
                    GATKSNPFilteringCheckBox.setEnabled(true);
                    VCF2DraftCheckBox.setEnabled(true);
                    GATKSNPFilteringCheckBox.setSelected(true);
                    VCF2DraftCheckBox.setSelected(true);
                    SNPFilterButton.setEnabled(true);
                    advancedVCF2DraftButton.setEnabled(true);
                }
            }
        });

        preseq_advanced_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Preseq_Dialog preseq_dialog = new Preseq_Dialog(communicator);
                setWindowPosition(preseq_dialog);
                preseq_dialog.setSize(400, 400);
                preseq_dialog.setVisible(true);
            }
        });

        output_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ResultsFolderChooser rfc = new ResultsFolderChooser(communicator);
                output_button.setForeground(Color.green);
                if (checkIfInputWasSelected()) {
                    RunButton.setEnabled(true);
                } else {
                    RunButton.setEnabled(false);
                }
            }
        });


        RunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //This should generate the actual configuration file but first we need to check
                //which parts of the config need to be created
                try {
                    collectMainWindowInformation();
                    if (communicator.getConflict() == 0) {
                        if (communicator.getGUI_inputfiles().size() == 1) { //Then this is a folder
                            File f = new File(communicator.getGUI_inputfiles().get(0));
                            if (f.isDirectory()) {
                                FileSearcher fs = new FileSearcher();
                                ArrayList<String> listoffiles = fs.processFiles(f.getAbsolutePath());
                                String resultsfolder = communicator.getGUI_resultspath();
                                if (communicator.isInput_already_merged() && !communicator.isMerge_bam_files()) {
                                    communicator.setRun_clipandmerge(false);
                                    for (String filename : listoffiles) {
                                        ArrayList<String> files = new ArrayList<String>();
                                        files.add(filename);
                                        communicator.setGUI_inputfiles(files);
                                        File parent = new File(new File(filename).getParent());
                                        parent.mkdirs();
                                        String fileoutput = resultsfolder + "/" + parent.getName();
                                        communicator.setGUI_resultspath(fileoutput);
                                        generateConfiguration(communicator, fileoutput);
                                    }
                                } else if (communicator.isPairmenttype() && !communicator.isMerge_bam_files()) {
                                    FilePairer fp = new FilePairer(listoffiles);
                                    ArrayList<FilePair> filePairs = fp.getListofpairs();
                                    for (FilePair filepair : filePairs) {
                                        communicator.setGUI_inputfiles(filepair.getFilepaths());
                                        File parent = new File(new File(filepair.getF1()).getParent());
                                        parent.mkdirs();
                                        String fileoutput = resultsfolder + "/" + parent.getName();
                                        communicator.setGUI_resultspath(fileoutput);
                                        generateConfiguration(communicator, fileoutput);
                                    }
                                } else if (!communicator.isPairmenttype() && !communicator.isMerge_bam_files()) {
                                    FilePairer fp = new FilePairer(listoffiles);
                                    ArrayList<ArrayList<String>> filePairs = fp.getSingleEndDataList();
                                    for (ArrayList<String> filename : filePairs) {
                                        communicator.setGUI_inputfiles(filename);
                                        File parent = new File(new File(filename.get(0)).getParent());
                                        parent.mkdirs();
                                        String fileoutput = resultsfolder + "/" + parent.getName();
                                        communicator.setGUI_resultspath(fileoutput);
                                        generateConfiguration(communicator, fileoutput);
                                    }
                                } else if (!communicator.isPairmenttype() && communicator.isMerge_bam_files()) {
                                    FilePairer fp = new FilePairer(listoffiles);
                                    ArrayList<ArrayList<String>> filePairs = fp.getSingleEndDataList();
                                    for (ArrayList<String> filename : filePairs) {
                                        communicator.setGUI_inputfiles(filename);
                                        File parent = new File(new File(filename.get(0)).getParent());
                                        parent.mkdirs();
                                        String fileoutput = resultsfolder + "/" + parent.getName();
                                        communicator.setGUI_resultspath(fileoutput);
                                        generateConfiguration(communicator, fileoutput);
                                    }
                                } else if (communicator.isMerge_bam_files()) {
                                    FilePairer fp = new FilePairer(listoffiles);
                                    ArrayList<FilePair> filePairs = fp.getListofpairs();
                                    ArrayList<String> fw_data = new ArrayList<String>();
                                    ArrayList<String> rv_data = new ArrayList<String>();
                                    File parent = null;
                                    File temp = null;
                                    boolean init = true;
                                    int exit = filePairs.size()+1;
                                    for (FilePair filepair : filePairs) {
                                        exit--;
                                        parent = new File(new File(filepair.getF1()).getParent());
                                        if (init) {
                                            temp = new File(new File(filepair.getF1()).getParent());
                                            init = false;
                                        }

                                        if (!temp.equals(parent) || exit == 0) {
                                            temp.mkdirs();
                                            String fileoutput = resultsfolder + "/" + temp.getName();
                                            communicator.setGUI_resultspath(fileoutput);
                                            ArrayList<String> combined = new ArrayList<String>();
                                            combined.addAll(fw_data);
                                            combined.addAll(rv_data);
                                            communicator.setGUI_inputfiles(combined);
                                            generateConfiguration(communicator, fileoutput);
                                            temp = parent;
                                            fw_data = new ArrayList<String>();
                                            rv_data = new ArrayList<String>();
                                        }

                                        while (temp.equals(parent)) {
                                            temp = new File(new File(filepair.getF1()).getParent());
                                            fw_data.add(filepair.getF1());
                                            rv_data.add(filepair.getF2());
                                            break;
                                        }
                                    }
                                    //flush in the last case
                                    temp.mkdirs();
                                    String fileoutput = resultsfolder + "/" + temp.getName();
                                    communicator.setGUI_resultspath(fileoutput);
                                    ArrayList<String> combined = new ArrayList<String>();
                                    combined.addAll(fw_data);
                                    combined.addAll(rv_data);
                                    communicator.setGUI_inputfiles(combined);
                                    generateConfiguration(communicator, fileoutput);


                                }

                            } else {
                                //if we only select one pair of files
                                String id = Files.getNameWithoutExtension(communicator.getGUI_inputfiles().get(0)).split("_")[0];

                                communicator.setGUI_resultspath(communicator.getGUI_resultspath()+"/"+id);
                                generateConfiguration(communicator);
                            }
                        } else {
                            //if we only select one pair of files
                            String id = Files.getNameWithoutExtension(communicator.getGUI_inputfiles().get(0)).split("_")[0];

                            communicator.setGUI_resultspath(communicator.getGUI_resultspath()+"/"+id);
                            generateConfiguration(communicator);
                            // For each pair create a new communicator object and let ConfigGenerator store this object

                        }
                        ConfigurationCreated configurationCreated = new ConfigurationCreated(communicator);
                        setWindowPosition(configurationCreated);
                        configurationCreated.setSize(650, 400);
                        configurationCreated.setVisible(true);
                    } else {
                        ConfigurationException cfge = new ConfigurationException(communicator);
                        cfge.setSize(650, 400);
                        cfge.setVisible(true);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EAGERAbout eagerAbout = new EAGERAbout();
                setWindowPosition(eagerAbout);
                eagerAbout.setSize(650, 650);
                eagerAbout.setVisible(true);

            }
        });

        merge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (merge.isSelected()) {
                    qualityFilteringCheckBox.setSelected(false);
                    qualityFilteringCheckBox.setEnabled(false);
                    QualityButton.setEnabled(false);
                }
                if (!merge.isSelected()) {
                    qualityFilteringCheckBox.setEnabled(true);
                    QualityButton.setEnabled(true);
                }

            }
        });
        rmdup_combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkbox_rmdup.isSelected()) {
                    if(rmdup_combobox.getModel().getSelectedItem().equals("DeDup")){
                        communicator.setRmdup_run(true);
                        communicator.setMarkdup_run(false);
                    } else {
                        communicator.setRmdup_run(false);
                        communicator.setMarkdup_run(true);
                    }
                } else {
                    communicator.setRmdup_run(false);
                    communicator.setMarkdup_run(false);
                }


            }
        });

        dedup_advanced_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeDupDialog deDupDialog = new DeDupDialog(communicator);
                setWindowPosition(deDupDialog);
                deDupDialog.setSize(650, 650);
                deDupDialog.setVisible(true);
            }
        });

        schmutzi_advanced_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContaminationDialog contaminationDialog = new ContaminationDialog(communicator);
                setWindowPosition(contaminationDialog);
                contaminationDialog.setSize(650, 650);
                contaminationDialog.setVisible(true);

            }
        });

        useSystemTmpDirCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                communicator.setUsesystemtmpdir(useSystemTmpDirCheckBox.isSelected());
            }
        });
    }

    /**
     * This method collects the information that is written in the main configuration panel of the EAGER pipeline and
     * passes this information to the IO.Communicator Class. The method gets called whenever the user hits the run button,
     * updating the IO.Communicator Object with the currently entered information.
     */
    private void collectMainWindowInformation() {
        communicator.setMaxmemory(maxmemory_textfield.getText());
        communicator.setCpucores(cpucores_textfield.getText());
        communicator.setUsesystemtmpdir(useSystemTmpDirCheckBox.isSelected());
        communicator.setGatk_caller(genotyper.getSelectedItem().toString());
        communicator.setRun_fastqc(fastQCAnalysisCheckBox.isSelected());
        communicator.setRun_cleanup(cleanUpBox.isSelected());
        communicator.setRun_clipandmerge(merge.isSelected());
        communicator.setRun_complexityestimation(complexity.isSelected());
        communicator.setRun_coveragecalc(coverage.isSelected());
        communicator.setRun_gatksnpcalling(gatksnpcall.isSelected());
        communicator.setRun_gatksnpfiltering(GATKSNPFilteringCheckBox.isSelected());
        communicator.setRun_mapdamage(mapdamage.isSelected());
        communicator.setRun_mapping(map.isSelected());
        communicator.setRmdup_run(checkbox_rmdup.isSelected());
        communicator.setRun_qualityfilter(qualityFilteringCheckBox.isSelected());
        communicator.setRun_vcf2draft(VCF2DraftCheckBox.isSelected());
        communicator.setSchmutzi_run(schmutzi_checkbox.isSelected());
        if(VCF2DraftCheckBox.isSelected()){
            communicator.setGatk_emit_all_sites(true);
        }
        communicator.setMapper_to_use(this.mapper_selection.getSelectedItem().toString());
        communicator.setRun_reportgenerator(runReportGenerator.isSelected());


    }

    /**
     * Method that can check/uncheck (given a boolean value) the appropriate checkboxes in the main GUI of EAGER.
     * Can be used to ease the usage of the overall GUI to quickly deselect/select the parameters.
     */

    private void checkBoxes() {
        boolean b = false;
        if (fastQCAnalysisCheckBox.isSelected()) {
            b = false;
        } else {
            b = true;
        }

        fastQCAnalysisCheckBox.setSelected(b);
        qualityFilteringCheckBox.setSelected(b);
        checkbox_rmdup.setSelected(b);
        merge.setSelected(b);
        map.setSelected(b);
        coverage.setSelected(b);
        mapdamage.setSelected(b);
        complexity.setSelected(b);
        gatksnpcall.setSelected(b);
        VCF2DraftCheckBox.setSelected(b);
        GATKSNPFilteringCheckBox.setSelected(b);
        schmutzi_checkbox.setSelected(b);
        cleanUpBox.setSelected(b);

    }


    /**
     * This calls the IO.ConfigGenerator according to the selected
     * tasks of the pipeline, to generate the appropriate configuration files
     */
    private void generateConfiguration(Communicator c) throws IOException {
        generateRGConfiguration(communicator);

        ConfigGenerator cg = new ConfigGenerator(communicator, communicator.getGUI_resultspath(), this, this.runReportGenerator.isSelected());
    }

    /**
     * This calls the IO.ConfigGenerator according to the selected
     * tasks of the pipeline, to generate the appropriate configuration files
     */
    private void generateConfiguration(Communicator c, String resultspath) throws IOException {
        generateEagerVersionConfiguration(communicator);
        generateRGConfiguration(communicator);

        ConfigGenerator cg = new ConfigGenerator(communicator, resultspath, this, this.runReportGenerator.isSelected());
    }


    public static void setWindowPosition(java.awt.Window window) {
        GraphicsDevice[] sd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        //Use first/primary screen
        Rectangle primaryScreen = sd[0].getDefaultConfiguration().getBounds();
        int w = window.getWidth();
        int h = window.getHeight();
        window.setLocation(new Point(
                ((primaryScreen.width - w) / 2) + primaryScreen.x,
                ((primaryScreen.height - h) / 2) + primaryScreen.y));

    }

    private void generateEagerVersionConfiguration(Communicator communicator) {
        this.communicator.setEager_verison(EAGER_VERSION);
    }

    private void generateRGConfiguration(Communicator communicator) {
        String filenames = Files.getNameWithoutExtension(this.communicator.getGUI_inputfiles().get(0));
        String readgroup = "@RG" + "\\t" + "ID:ILLUMINA-" + filenames + "\\t" + "SM:" + filenames + "\\t" + "PL:illumina";
        this.communicator.setMapper_readgroup(readgroup);
    }


    private boolean checkIfInputWasSelected() {
        boolean tmp = false;
        if (communicator.getGUI_inputfiles().size() != 0 && communicator.getGUI_reference() != null && communicator.getGUI_resultspath() != null) {
            tmp = true;
        }
        return tmp;
    }
}
