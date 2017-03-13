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
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

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
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: peltzer
 * Date: 14.08.13
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 */
public class EAGER {
    private final String EAGER_VERSION = "1.92.21";

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
    private JButton DamageButton;
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
    private JCheckBox damage;
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
    private JComboBox damageSelection;
    private JCheckBox PMDtoolsCheckBox;
    private JButton pmdtools_advanced_button;
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
        this.icon = ImageIO.read(getClass().getResource("/EAGER_Logo.png"));
        communicator = new Communicator();
        checkBoxes();
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
        damage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);    //To change body of overridden methods use File | Settings | File Templates.
                damage.setToolTipText("mapDamage calculation: Calculates the map damage and deamination patterns on the input files. To change default parameters, click on advanced.");
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
                //Check if some input was truly selected
                if (communicator.getGUI_inputfiles() != null) {
                    selectInputFqFilesButton.setForeground(Color.green);
                }
                if (checkIfInputWasSelected()) {
                    RunButton.setEnabled(true);
                } else {
                    RunButton.setEnabled(false);
                }

                updateSelectedTools();
            }
        });

        referenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ReferenceDialog rd = new ReferenceDialog(communicator, RunButton);
                rd.setSize(600, 400);
                rd.setVisible(true);
                referenceButton.setForeground(Color.green);
            }
        });

        DamageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (damageSelection.getSelectedItem().equals("DamageProfiler")) {
                    DamageProfilerDialog damageProfilerDialog = new DamageProfilerDialog(communicator);
                    setWindowPosition(damageProfilerDialog);

                    damageProfilerDialog.setSize(400, 400);
                    damageProfilerDialog.setVisible(true);

                } else if (damageSelection.getSelectedItem().equals("mapDamage")) {
                    MapDamageDialog mapDamageDialog = new MapDamageDialog(communicator);
                    setWindowPosition(mapDamageDialog);

                    mapDamageDialog.setSize(400, 400);
                    mapDamageDialog.setVisible(true);
                }
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
                updateSelectedTools();
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

        pmdtools_advanced_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                PmdToolsDialog pmdtools_dialog = new PmdToolsDialog(communicator);
                setWindowPosition(pmdtools_dialog);
                pmdtools_dialog.setSize(400, 400);
                pmdtools_dialog.setVisible(true);


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
                        ArrayList<String> original_gui_inputfiles = communicator.getGUI_inputfiles().stream().map(s -> s.toString()).collect(Collectors.toCollection(ArrayList::new));
                        //Make a backup of results path, since we are modifying this and need to reset it later..
                        String backupResultsPath = communicator.getGUI_resultspath();
                        if (communicator.getGUI_inputfiles().size() == 1) { //Then this is a folder
                            File f = new File(communicator.getGUI_inputfiles().get(0));
                            if (f.isDirectory()) {
                                FileSearcher fs = new FileSearcher();
                                ArrayList<String> listoffiles = fs.processFiles(f.getAbsolutePath());
                                boolean bamInput = false;
                                for (String str : listoffiles) {
                                    if (str.endsWith(".bam")) {
                                        bamInput = true;
                                    } else {
                                        bamInput = false;
                                    }
                                }

                                String resultsfolder = communicator.getGUI_resultspath();

                                if (bamInput) {
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
                                    communicator.setGUI_resultspath(backupResultsPath);

                                }


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
                                    communicator.setGUI_resultspath(backupResultsPath);
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
                                    communicator.setGUI_resultspath(backupResultsPath);

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
                                    communicator.setGUI_resultspath(backupResultsPath);

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
                                    communicator.setGUI_resultspath(backupResultsPath);
                                } else if (communicator.isMerge_bam_files()) {
                                    FilePairer fp = new FilePairer(listoffiles);
                                    ArrayList<FilePair> filePairs = fp.getListofpairs();
                                    ArrayList<String> fw_data = new ArrayList<String>();
                                    ArrayList<String> rv_data = new ArrayList<String>();
                                    File parent = null;
                                    File temp = null;
                                    boolean init = true;
                                    int exit = filePairs.size() + 1;
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

                                communicator.setGUI_resultspath(communicator.getGUI_resultspath() + "/" + id);
                                generateConfiguration(communicator);
                                communicator.setGUI_resultspath(backupResultsPath);

                            }
                        } else {
                            //if we only select one pair of files
                            String id = Files.getNameWithoutExtension(communicator.getGUI_inputfiles().get(0)).split("_")[0];

                            communicator.setGUI_resultspath(communicator.getGUI_resultspath() + "/" + id);
                            generateConfiguration(communicator);
                            communicator.setGUI_resultspath(backupResultsPath);


                        }

                        /*Check whether we created a proper configuration and if not, issue an Exception Dialogue!
                         *
                         */
                        if (communicator.getGUI_inputfiles().size() <= 1 && communicator.isPairmenttype()) {
                            ConfigurationException cfge = new ConfigurationException(communicator);
                            cfge.setSize(650, 400);
                            cfge.setVisible(true);
                        } else {
                            ConfigurationCreated configurationCreated = new ConfigurationCreated(communicator);
                            setWindowPosition(configurationCreated);
                            configurationCreated.setSize(650, 400);
                            configurationCreated.setVisible(true);

                            communicator.setGUI_inputfiles(original_gui_inputfiles);
                            communicator.setGUI_resultspath(backupResultsPath);
                        }

                    } else {
                        ConfigurationException cfge = new ConfigurationException(communicator);
                        cfge.setSize(650, 400);
                        cfge.setVisible(true);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //To make users be able to see whether they already ran the GUI once...

                referenceButton.setForeground(Color.orange);
                output_button.setForeground(Color.orange);
                selectInputFqFilesButton.setForeground(Color.orange);


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
                if (checkbox_rmdup.isSelected()) {
                    if (rmdup_combobox.getModel().getSelectedItem().equals("DeDup")) {
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

        schmutzi_checkbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //Check whether database is selected, state a warning that this needs to be done
                SchmutziWarningDialog schmutzidialog = new SchmutziWarningDialog(communicator);
                setWindowPosition(schmutzidialog);
                schmutzidialog.setSize(650, 650);
                schmutzidialog.setVisible(true);
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
        communicator.setRun_mapdamage(damage.isSelected());
        communicator.setRun_mapping(map.isSelected());
        communicator.setRmdup_run(checkbox_rmdup.isSelected());
        communicator.setRun_qualityfilter(qualityFilteringCheckBox.isSelected());
        communicator.setRun_vcf2draft(VCF2DraftCheckBox.isSelected());
        communicator.setSchmutzi_run(schmutzi_checkbox.isSelected());
        if (VCF2DraftCheckBox.isSelected()) {
            communicator.setGatk_emit_all_sites(true);
        }
        communicator.setMapper_to_use(this.mapper_selection.getSelectedItem().toString());
        communicator.setRun_reportgenerator(runReportGenerator.isSelected());
        communicator.setDNA_damage_calculator_to_use(this.damageSelection.getSelectedItem().toString());
        communicator.setRun_pmdtools(PMDtoolsCheckBox.isSelected());


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
        cleanUpBox.setSelected(b);


    }

    /**
     * Set some tools selected/deselected upon type of things...
     */
    private void updateSelectedTools() {
        //Human vs Other cases
        if (communicator.isOrganism()) { //Human case, remove VCF2Genome
            VCF2DraftCheckBox.setSelected(false);
        } else { // bacterial and other case
            schmutzi_checkbox.setSelected(false);
            VCF2DraftCheckBox.setSelected(true);
            VCF2DraftCheckBox.setEnabled(true);
        }

        //Special cases for genotypers

        if (communicator.getGatk_caller().equals("HaplotypeCaller")) {
            VCF2DraftCheckBox.setEnabled(false); // The output of Haplotype Caller is currently not supported at all by VCF2Genome
            VCF2DraftCheckBox.setSelected(false);
        }

        if (genotyper.getSelectedItem().toString().equals("ANGSD")) {
            GATKSNPFilteringCheckBox.setEnabled(false);
            VCF2DraftCheckBox.setEnabled(false);
            GATKSNPFilteringCheckBox.setSelected(false);
            SNPFilterButton.setEnabled(false);
            advancedVCF2DraftButton.setEnabled(false);
        }

        // disable PMDtools if sample is modern
        if (!communicator.isOrganismage()) {
            pmdtools_advanced_button.setEnabled(false);
            PMDtoolsCheckBox.setEnabled(false);
        }


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


    public static void setWindowPosition(Window window) {
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
        this.communicator.setEager_version(EAGER_VERSION);
    }

    private void generateRGConfiguration(Communicator communicator) {
        String filenames = Files.getNameWithoutExtension(this.communicator.getGUI_inputfiles().get(0));
        String readgroup = "@RG" + "\\t" + "ID:ILLUMINA-" + filenames + "\\t" + "SM:" + filenames + "\\t" + "PL:illumina";
        this.communicator.setMapper_readgroup(readgroup);
    }


    private boolean checkIfInputWasSelected() {
        boolean tmp = false;
        if (communicator.getGUI_inputfiles() != null && communicator.getGUI_reference() != null && communicator.getGUI_resultspath() != null) {
            if (communicator.getGUI_inputfiles().size() != 0) {
                tmp = true;
            }
        }
        return tmp;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        Masterpanel = new JPanel();
        Masterpanel.setLayout(new GridLayoutManager(14, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainpanel = new JPanel();
        mainpanel.setLayout(new GridLayoutManager(19, 3, new Insets(0, 0, 0, 0), -1, -1));
        Masterpanel.add(mainpanel, new GridConstraints(2, 0, 9, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        mainpanel.setBorder(BorderFactory.createTitledBorder(""));
        fastQCAnalysisCheckBox = new JCheckBox();
        fastQCAnalysisCheckBox.setSelected(true);
        fastQCAnalysisCheckBox.setText("FastQC Analysis");
        fastQCAnalysisCheckBox.setMnemonic('F');
        fastQCAnalysisCheckBox.setDisplayedMnemonicIndex(0);
        fastQCAnalysisCheckBox.setToolTipText("This will provide you with reports on the quality of your raw sequencing data.");
        mainpanel.add(fastQCAnalysisCheckBox, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        map = new JCheckBox();
        map.setSelected(true);
        map.setText("Mapping");
        map.setMnemonic('P');
        map.setDisplayedMnemonicIndex(2);
        map.setToolTipText("This will actually determine whether you want your data to be mapped or not.");
        mainpanel.add(map, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        coverage = new JCheckBox();
        coverage.setEnabled(false);
        coverage.setSelected(true);
        coverage.setText("Coverage / Statistics Calculation");
        coverage.setToolTipText("Calculate coverage statistics.\nThis is not deselectable as it is required in all cases. ");
        mainpanel.add(coverage, new GridConstraints(13, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gatksnpcall = new JCheckBox();
        gatksnpcall.setSelected(false);
        gatksnpcall.setText("SNP Calling");
        gatksnpcall.setToolTipText("Select this and SNP Calling will be run in the pipeline, using the method available in the dropdown menu next to this checkbox.");
        mainpanel.add(gatksnpcall, new GridConstraints(15, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        genotyper = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("UnifiedGenotyper");
        defaultComboBoxModel1.addElement("HaplotypeCaller");
        defaultComboBoxModel1.addElement("ANGSD");
        genotyper.setModel(defaultComboBoxModel1);
        genotyper.setToolTipText("Select the genotyper of choice here.");
        mainpanel.add(genotyper, new GridConstraints(15, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        snpcalladvanced = new JButton();
        snpcalladvanced.setText("Advanced");
        mainpanel.add(snpcalladvanced, new GridConstraints(15, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        MapButton = new JButton();
        MapButton.setText("Advanced");
        mainpanel.add(MapButton, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DamageButton = new JButton();
        DamageButton.setText("Advanced");
        mainpanel.add(DamageButton, new GridConstraints(14, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        VCF2DraftCheckBox = new JCheckBox();
        VCF2DraftCheckBox.setSelected(false);
        VCF2DraftCheckBox.setText("VCF2Genome");
        VCF2DraftCheckBox.setMnemonic('V');
        VCF2DraftCheckBox.setDisplayedMnemonicIndex(0);
        VCF2DraftCheckBox.setToolTipText("Generate a draft genome with some filtering parameters set.");
        mainpanel.add(VCF2DraftCheckBox, new GridConstraints(17, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        advancedVCF2DraftButton = new JButton();
        advancedVCF2DraftButton.setText("Advanced");
        mainpanel.add(advancedVCF2DraftButton, new GridConstraints(17, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        GATKSNPFilteringCheckBox = new JCheckBox();
        GATKSNPFilteringCheckBox.setSelected(false);
        GATKSNPFilteringCheckBox.setText("SNP Filtering");
        GATKSNPFilteringCheckBox.setToolTipText("Select this and set the min coverage and min quality that you want your variants to have in the resulting VCF file you created using the genotyper of your choice.");
        mainpanel.add(GATKSNPFilteringCheckBox, new GridConstraints(16, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SNPFilterButton = new JButton();
        SNPFilterButton.setText("Advanced");
        mainpanel.add(SNPFilterButton, new GridConstraints(16, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cpucores_textfield = new JTextField();
        cpucores_textfield.setHorizontalAlignment(0);
        cpucores_textfield.setText("4");
        cpucores_textfield.setToolTipText("Enter the number of CPU cores to use here. Do not choose more than the number of cores you have on your machine.");
        mainpanel.add(cpucores_textfield, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("CPU Cores to be used");
        mainpanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cleanUpBox = new JCheckBox();
        cleanUpBox.setEnabled(true);
        cleanUpBox.setSelected(true);
        cleanUpBox.setText("CleanUp");
        cleanUpBox.setToolTipText("Select this and redundant data files will be deleted. Redundant means, that only files are deleted that are still reproducible without the whole pipeline to rerun, e.g. we delete files to get rid unsorted BAM files (as the sorted BAM inherits the same data!).");
        mainpanel.add(cleanUpBox, new GridConstraints(18, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Memory in GB");
        mainpanel.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxmemory_textfield = new JTextField();
        maxmemory_textfield.setHorizontalAlignment(0);
        maxmemory_textfield.setText("32");
        maxmemory_textfield.setToolTipText("Choose the amount of memory you'd like to use with the pipeline. Do not choose more than you actually have, this might crash the pipeline!");
        mainpanel.add(maxmemory_textfield, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        merge = new JCheckBox();
        merge.setSelected(true);
        merge.setText("Clip and Merge");
        merge.setToolTipText("Do you want to merge your reads, e.g. when having paired end reads with negative overlap?");
        mainpanel.add(merge, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        qualityFilteringCheckBox = new JCheckBox();
        qualityFilteringCheckBox.setEnabled(true);
        qualityFilteringCheckBox.setSelected(true);
        qualityFilteringCheckBox.setText("QualityFiltering");
        qualityFilteringCheckBox.setMnemonic('Q');
        qualityFilteringCheckBox.setDisplayedMnemonicIndex(0);
        qualityFilteringCheckBox.setToolTipText("This will determine whether you want to quality trim or apply a length filter on your data. If you use the Merging step, you don't need to do this, as this is done inside the Clip&Merge tool already.");
        mainpanel.add(qualityFilteringCheckBox, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        MergeButton = new JButton();
        MergeButton.setText("Advanced");
        mainpanel.add(MergeButton, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        QualityButton = new JButton();
        QualityButton.setText("Advanced");
        mainpanel.add(QualityButton, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mapper_selection = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("BWA");
        defaultComboBoxModel2.addElement("CircularMapper");
        defaultComboBoxModel2.addElement("BWAMem");
        defaultComboBoxModel2.addElement("Bowtie 2");
        defaultComboBoxModel2.addElement("Stampy");
        mapper_selection.setModel(defaultComboBoxModel2);
        mapper_selection.setToolTipText("Choose the mapping algorithm you'd like your data to be mapped with. If unsure, choose BWA.");
        mainpanel.add(mapper_selection, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkbox_rmdup = new JCheckBox();
        checkbox_rmdup.setSelected(true);
        checkbox_rmdup.setText("Remove Duplicates");
        checkbox_rmdup.setToolTipText("This will determine whether you want to run duplicate removal on your data or not.");
        mainpanel.add(checkbox_rmdup, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        selectall = new JButton();
        selectall.setText("Select / Deselect All");
        mainpanel.add(selectall, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        mainpanel.add(separator1, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        complexity = new JCheckBox();
        complexity.setSelected(false);
        complexity.setText("Complexity Estimation");
        complexity.setMnemonic('O');
        complexity.setDisplayedMnemonicIndex(1);
        complexity.setToolTipText("Select this if you'd like to get a library complexity estimation to be done, e.g. for screening a library and finding out if there are enough unique DNA fragments in the library, so a further sequencing would be beneficial.");
        mainpanel.add(complexity, new GridConstraints(9, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        preseq_advanced_button = new JButton();
        preseq_advanced_button.setText("Advanced");
        mainpanel.add(preseq_advanced_button, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rmdup_combobox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        defaultComboBoxModel3.addElement("DeDup");
        defaultComboBoxModel3.addElement("MarkDuplicates");
        rmdup_combobox.setModel(defaultComboBoxModel3);
        mainpanel.add(rmdup_combobox, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        schmutzi_checkbox = new JCheckBox();
        schmutzi_checkbox.setEnabled(true);
        schmutzi_checkbox.setSelected(false);
        schmutzi_checkbox.setText("Contamination Estimation");
        schmutzi_checkbox.setToolTipText("Run contamination estimation using Schmutzi. ");
        mainpanel.add(schmutzi_checkbox, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        schmutzi_advanced_button = new JButton();
        schmutzi_advanced_button.setText("Advanced");
        mainpanel.add(schmutzi_advanced_button, new GridConstraints(12, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        useSystemTmpDirCheckBox = new JCheckBox();
        useSystemTmpDirCheckBox.setSelected(false);
        useSystemTmpDirCheckBox.setText("Use system tmp dir");
        mainpanel.add(useSystemTmpDirCheckBox, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dedup_advanced_button = new JButton();
        dedup_advanced_button.setText("Advanced");
        mainpanel.add(dedup_advanced_button, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        damageSelection = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel4 = new DefaultComboBoxModel();
        defaultComboBoxModel4.addElement("DamageProfiler");
        defaultComboBoxModel4.addElement("mapDamage");
        damageSelection.setModel(defaultComboBoxModel4);
        mainpanel.add(damageSelection, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        damage = new JCheckBox();
        damage.setEnabled(true);
        damage.setSelected(false);
        damage.setText("Damage Calculation");
        damage.setToolTipText("Calculates post mortem damage patterns.");
        mainpanel.add(damage, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PMDtoolsCheckBox = new JCheckBox();
        PMDtoolsCheckBox.setEnabled(true);
        PMDtoolsCheckBox.setSelected(false);
        PMDtoolsCheckBox.setText("PMDtools");
        PMDtoolsCheckBox.setToolTipText("Likelihood framework to detect DNA sequences, which probably originate from modern day DNA contamination.  Only available for ancient samples.");
        mainpanel.add(PMDtoolsCheckBox, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pmdtools_advanced_button = new JButton();
        pmdtools_advanced_button.setText("Advanced");
        mainpanel.add(pmdtools_advanced_button, new GridConstraints(11, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        selectInputFqFilesButton = new JButton();
        selectInputFqFilesButton.setText("Select Input *.fq/*.fq.gz Files");
        selectInputFqFilesButton.setToolTipText("Please select your input FastQ files here, OR a folder containing your unzipped (!) FastQ files.");
        Masterpanel.add(selectInputFqFilesButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        referenceButton = new JButton();
        referenceButton.setText("Select Reference");
        referenceButton.setToolTipText("Select your reference sequence in FASTA format here. You do not need to create indices, these will be created if required by the pipeline itself.");
        Masterpanel.add(referenceButton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        runReportGenerator = new JCheckBox();
        runReportGenerator.setSelected(true);
        runReportGenerator.setText("Create Report?");
        runReportGenerator.setToolTipText("Generates useful reports of your processed data.");
        Masterpanel.add(runReportGenerator, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RunButton = new JButton();
        RunButton.setText("Generate Config File");
        RunButton.setToolTipText("<html>\n<br>This will generate the EAGER config file required to process the data.</br>\n<br>This is inactive as long as you didn't select reference, input and output folders</br>\n</html>");
        Masterpanel.add(RunButton, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        aboutButton = new JButton();
        aboutButton.setText("About");
        Masterpanel.add(aboutButton, new GridConstraints(13, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator2 = new JSeparator();
        Masterpanel.add(separator2, new GridConstraints(12, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        output_button = new JButton();
        output_button.setText("Select output folder");
        output_button.setToolTipText("Select your output folder here. This can be a single folder for one sample or a general output folder for more than one sample.");
        Masterpanel.add(output_button, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        Masterpanel.add(panel1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return Masterpanel;
    }
}
