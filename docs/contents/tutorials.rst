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


Bacterial analysis
------------------


Human (WGS) analysis
--------------------
