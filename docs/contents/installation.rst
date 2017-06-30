Installation Instructions for the EAGER Pipeline
================================================

We provide three kinds of installation instructions.

.. note::

   We do not provide a docker image anymore, as the much more flexible Singularity method superseded the ``deager`` application and our Docker based approach.


VirtualBox
----------

.. warning::

  This should only be used for testing purposes as the image does not get updated at all and was only intended to try out the pipeline!

.. note::

  This has some performance drawbacks due to virtualization techniques (typically ~20%).

We provide a VirtualBox based operating system image to end users that contains all the required software tools.

  * Download the corresponding `VirtualBox Image <http://bit.ly/eagervbox>`_
  * Unpack the image
  * Load the image with VirtualBox, click on File, Open, Image and select the unpacked image file
  * Click on Start in VirtualBox and wait a couple of seconds until you see a regular desktop environment in VirtualBox
  * You may run the pipeline’s two components now typing either ``eager`` or ``eagercli``.

Two small videos illustrating the whole setup process can be found `online <http://bit.ly/eagervbox-installation>`_ and `here <http://bit.ly/eagervbox-running>`_.

Singularity
-----------

.. note::

   This is the default way to use EAGER in a containerized environment. Best user experience, minimum performance drawbacks.

In order to use this approach, you will need a running Linux operating system at hand (e.g. ArchLinux, Ubuntu > 14.04, CentOS 7 or similar).

.. warning::

   In theory, this should work on OSX, but due to the nature of OSX using a Virtualization technique based on VirtualBox, you could instead use the VirtualBox image on such systems, too.


First of all, install Singularity on your machine that you would like to use for the setup.
To do this, follow the instructions from the authors `here <http://singularity.lbl.gov/install-linux>`_. There are installation instructions for OSX and Windows, too - but these will have some performance drawbacks.
Once you have a working singularity installation, there is just two commands you will need to run for getting EAGER to work:


Running the GUI
^^^^^^^^^^^^^^^

.. code-block:: bash

   singularity exec -B /path/to/your/data:/data shup://apeltzer/eager-gui eager
   #/path/to/your/data = Path where you store RAW sequencing data, a reference genome in FastA format and the folder where you store your results in the end.

This will open the EAGER graphical user interface (GUI), that is required for configuring the pipeline.
Make sure to remember this path, as you will need it for the pipeline execution later on. Within the GUI, you can find your data in ``/data``. You can navigate there when opening input files, the reference genome or the results and should also not select any folders or files in other directories.

.. note::

   The ``path/to/your/data`` can be any path accessible from your workstation, so for example a departments data storage in the network would work, too.

.. warning::

   Please make sure, that you have a following ``:/data`` after entering the path to your data storage. Otherwise, you will not be able to run a configuration.

After you are done with configuring your data, please close the graphical user interface.

Running the Analysis
^^^^^^^^^^^^^^^^^^^^

You can now run the actual analysis procedure with ``eagercli`` by issuing the following command.

.. code-block:: bash

   singularity exec -B /path/to/your/data:/data shup://apeltzer/eager-gui eagercli /data

This will run the analysis procedure on your machine using the ``eagercli`` application inside the container.

.. note::

   The results will be stored in the folder you selected in the configuration procedure. A good practice would be to have a separate folder inside your ``path/to/your/data`` just for this purpose.







Manual Installation
-------------------

.. note::

  This is the native installation of the EAGER pipeline. It requires you to download tools manually, compile them and set paths accordingly in order for the pipeline to work on your operating system.

The manual installation on an infrastructure without access to a docker container is a bit more complex than installing the docker image, as all the requirements and subsequent tools for EAGER need to be linked correctly on the system running the pipeline in the end. This has certain requirements:

  * Java 8 Environment, preferably the Oracle JDK8
  * GNU Bash

After this, the following tools need to be installed by the user, ideally system wide or (if this is not possible due to access rights), by manually compiling them. In parentheses you can find the version(s) EAGER has been tested with.

.. note::

  The EAGER-GUI, EAGER-CLI and all other components developed within the EAGER pipeline can be downloaded from Bintray as pre-compiled JAR files. You don't need to re-compile these applications manually. In case you prefer to, please use `IntelliJ IDE <http://jetbrains.com>`_ to do so.

List of Tools tested with EAGER:

  * `ANGSD(v0.910) <http://popgen.dk/wiki/index.php/ANGSD>`_
  * `AdapterRemoval (v2.2.1) <https://github.com/MikkelSchubert/adapterremoval>`_
  * `BAM2TDF(v14) <http://genomeview.org/manual/Bam2tdf>`_
  * BGZip (depending on your linux distribution, you have this already installed)
  * `Bowtie 2(v2+ <http://bowtie-bio.sourceforge.net/bowtie2/index.shtml>`_
  * `BWA (v0.7.15+) <https://sourceforge.net/projects/bio-bwa/>`_
  * `CircularMapper(latest) <https://github.com/apeltzer/CircularMapper>`_
  * `Clip & Merge(latest) <https://github.com/apeltzer/ClipAndMerge>`_
  * `Schmutzi (latest) <https://github.com/grenaud/schmutzi>`_
  * `DeDup (latest) <https://github.com/apeltzer/DeDup>`_
  * `EAGER (latest) <https://github.com/apeltzer/EAGER-GUI>`_
  * `EAGER-CLI (latest) <https://github.com/apeltzer/EAGER-CLI>`_
  * `FastX-Tools (v0.0.13) <http://hannonlab.cshl.edu/fastx_toolkit/>`_
  * `FastQC (v0.11.4) <http://www.bioinformatics.babraham.ac.uk/projects/fastqc/>`_
  * `GATK (v3.7+) <https://www.broadinstitute.org/gatk/>`_
  * `LibraryComplexityPlotter (latest) <https://github.com/apeltzer/LibraryComplexityPlotter>`_
  * `mapDamage (v2.0+) <http://ginolhac.github.io/mapDamage/>`_
  * `MTNucRatioCalculator (latest) <https://github.com/apeltzer/MTNucRatioCalculator>`_
  * `Picard-Tools (v2+) <http://broadinstitute.github.io/picard/>`_
  * `Preseq (v2.0+) <http://smithlabresearch.org/software/preseq/>`_
  * `QualiMap (v2.3) <http://qualimap.bioinfo.cipf.es/>`_
  * `ReportTable (latest) <https://github.com/apeltzer/ReportTable>`_
  * `Samtools (v1.4.0+) <http://www.htslib.org/>`_
  * `Stampy (current) <http://www.well.ox.ac.uk/project-stampy>`_
  * `Tabix (v1.3.0) <http://www.htslib.org/download/>`_
  * `VCF2Genome (latest) <https://github.com/apeltzer/VCF2Genome>`_

In order to make installation more easy, I provide `installation files for linking <https://github.com/apeltzer/EAGER-links>`_ the tools correctly. You will have to adjust in each file (open with a text editor) the correct location to the executables. Once you've done this and installed all the tools required for EAGER, you can simply add the location of these scripts to your path, e.g.

.. code-block:: bash

   PATH=/data/eager-links/:$PATH

This will *add* links to the respective tools in order to allow EAGER to find the corresponding tools. If you for example already have working installations of `BWA`, `samtools` or similar, you will only need to install the missing tools of course. Please make sure, that you have the proper versions of the tools installed that EAGER needs or otherwise you might have to define these in your path as well.

Now you can check by e.g. entering `eager` whether you get a message about running EAGER. If you set EAGER up on a cluster infrastructure, you may need to have X11 forwarding enabled there to run the pipeline. For windows clients, there is a howto available `here <https://www.youtube.com/watch?v=QRsma2vkEQE>`_. For Linux client machines, you'd probably only have to run:

.. code-block:: bash

   ssh you@yourheadnode.yourcluster -Y

If you are uncertain on how to run X11 forwarded applications on your local infrastructure, your IT department should be able to set this up for you or will help you in achieving this.
