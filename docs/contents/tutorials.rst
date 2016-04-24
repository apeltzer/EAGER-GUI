Tutorials
=========

This section describes several potential use cases for EAGER. A complete analysis of a couple of mitochondrial captures, a bacterial genome analysis and a analysis of human whole genome shotgun data.

Mitochondrial analysis
----------------------

First, we will have to make sure that all data that we are using is there. This consists of three samples (Sample A-C) that have been created using a paired-end sequencing run on an Illumina sequencer. In order to run the analysis, we will utilize the EAGER pipeline and configure it step-by-step to run our analysis.

Step I: Data preparation
~~~~~~~~~~~~~~~~~~~~~~~~

You should have a couple of folders set up, mainly containing the data, making sure the data follows the guidelines for :ref:`naming_scheme` .
Ideally, your folder structure should look like:

.. image:: images/tutorials/mito/01_mito_data.png
   :width: 200px
   :height: 200px
   :align: center

.. note::

  You don't need to index any reference genomes manually. EAGER will take care of generating required indices on-the-fly when running the pipeline.

.. warning::
  If you need to perform genotyping, please ensure that your genome FastA file is ending with a `.fasta` file extension, or otherwise the GATK might complain about this.

Step II: Starting the GUI
~~~~~~~~~~~~~~~~~~~~~~~~~

Depending on your installation type, you should have ensured a working graphical user interface of EAGER and start it. Once you have started the GUI, you will be welcomed by the main user interface of EAGER.

.. image:: images/tutorials/mito/02_GUI.png
   :width: 200px
   :height: 200px
   :align: center

Step III: Selecting input
~~~~~~~~~~~~~~~~~~~~~~~~~

Selecting FastQ input
^^^^^^^^^^^^^^^^^^^^^

You can now click on *Select input \*fq/\*.fq.gz Files* and navigate to your folder where the RAW sequencing input is stored on your network share or local hard drive.

.. image:: images/tutorials/mito/03_select_input.png
   :width: 200px
   :height: 200px
   :align: center

.. note::

  You may select **either** single/multiple FastQ files, **or** a folder containing subfolders with FastQ files. EAGER will pick up every FastQ file in all subfolders automatically.

In our case here, we simply select the folder `RAW` and click on *Choose*. A new window is opening up, asking you several questions to determine which kind of analysis should be performed on the selected data.

.. image:: images/tutorials/mito/04_input_choices.png
   :width: 200px
   :height: 200px
   :align: center

In our case here, we choose that our data has not been treated with UDG, we have paired-end sequencing data and want to analyse a mitochondrial capture dataset.

.. note::

  You have so specify a **BED** file for your reference genome if you want to analyse capture data in general. A typical BED file that could be used e.g. for HG19 mitochondrial analysis could look like this.

  .. code-block:: bash

    chrMT 1 16770 MT 1 +


Bacterial analysis
------------------


Human (WGS) analysis
--------------------
