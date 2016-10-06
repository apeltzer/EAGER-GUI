Installation Instructions for the EAGER Pipeline
================================================

We provide three kinds of installation instructions.

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

Docker
------
.. note::

   This is the default way to use EAGER in a dockerized environment. Best user experience, minimum performance drawbacks.

In order to use this approach, you will need either a running Linux operating system at your hand. 

.. warning::

   In theory, this should work on OSX, but due to the nature of OSX using a Virtualization technique based on VirtualBox, you could instead use the VirtualBox image on such systems, too. Docker uses VirtualBox on OSX, so the performance drawback will be there in all cases! 

Obtaining Deager
^^^^^^^^^^^^^^^^

Pre-Compiled binary
~~~~~~~~~~~~~~~~~~~


There are two ways to get our little helper tool `deager` for the easy interaction with the docker container running the actual pipeline. We provide a pre-compiled binary for you to download, or you can simply compile the binary yourself.

.. note:: 

   You should *always* try to use the pre-compiled binaries instead of compiling yourself to keep issues as low as possible. Compiling executables can be cumbersome and should only be done if the pre-compiled binaries are not working for your operating system. 

.. code-block:: bash

   cd ~/Downloads
   git clone https://github.com/apeltzer/deager.git
   cd deager/bin/Ubuntu
   ./deager

In the folder `deager/bin/Ubuntu/` you can find a precompiled binary that should work on 64bit Linux operating systems. In case this does not work, please compile manually as described in the next section.


Compile manually
~~~~~~~~~~~~~~~~

.. note::

   This is only required if you can't use the precompiled binary provided by us in the `deager/bin/Ubuntu` folder for some reason (e.g. incompatible platforms). In all other cases you shouldn't need to do this at all and can proceed to the next step in the setup phase.

.. code-block:: bash

  cd ~/Downloads
  git clone https://github.com/apeltzer/deager.git
  cd deager/
  docker-compose up

This will compile the **deager** executable, that you can then later use to run EAGER inside a Docker container more easily, hiding all of the complexity of the pipeline behind three commands. After 2-3 minutes, you should see something like this:

.. code-block:: bash

   docker-compose up
   Starting deager_build
   Attaching to deager_build
   deager_build | + apt-get update -qq
   deager_build | + apt-get install -qq -y make git golang
   deager_build | + go build -o amd64/deager
   deager_build | + echo 'La Fin!'
   deager_build | La Fin!

And you will find an executable called **deager** in the directory ``amd64/``. You may copy this executable somewhere else on your operating system, e.g. ``/usr/local/bin`` (if you have administrative rights), or call the tool directly. A small video illustrating this compilation procedure can be found `here <https://www.youtube.com/watch?v=kYaKgDixFoc>`_

.. warning:: 

   Attention: This is only generating an executable for linux operating systems, NOT for OSX. OSX is widely not supported as there is also no native docker client available for OSX. 

Running EAGER inside a Docker Container
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

If you now switch to the directory where the **deager** executable lays, you will see the main interface of the docker administration tool of the EAGER pipeline when calling the tool:

.. code-block:: bash

   ./deager
   Usage:
     deager [options] (start|stop|gui|run)
     deager -h | --help
     deager --version


A more extensive description of the options required to run the pipeline can be found by using the help function of "deager":

.. code-block:: bash

   ./deager -h
   Eager Docker Client
    Usage:
     deager [options] (start|stop|gui|run)
     deager -h | --help
     deager --version

   start:    Spins up the EAGER docker container
   stop:     Stop/remove the EAGER container
   gui:      Connect to container and start eager GUI
   run:      Run eagercli within --data directory

   Options:
     --data <path>      Directory to use as /data/ directory within eager (default: ~/data)
     --image <str>      Name of the eager image [default: apeltzer/eager]
     --container <str>  Name of the container spun up (default: eager_$USER)
     --uid              Use docker-client UID/GID for eager user within container.
                        This will cope with user rights. (depends on bindmount; boot2docker, local docker deamon...)
     -h --help          Show this screen.
     --version          Show version.

Running EAGER
^^^^^^^^^^^^^

You will be able to run the EAGER pipeline now with just these four commands offered by the CLI tool. An illustrative video on how to start the container can be found `here <https://www.youtube.com/watch?v=k2ta3345DUY>`_. Start by starting the container:

.. code-block:: bash

   deager start --data /path/to/your/datafolder

.. note::

  You may need to set the system variable on your system before this will run properly. To do so, simply issue the following on the command line and you're ready to go:

  .. code-block:: bash

     export DOCKER_HOST=unix:///var/run/docker.sock

Afterwards, you can open the GUI to configure an analysis run, illustrated in this little `helper video <https://www.youtube.com/watch?v=cKrBuoiGgNE>`_

.. code-block:: bash

   deager gui --data ~/data


This should open a graphical interface on your machine, enabling you to configure everything and creating subsequently configuration files used for the pipeline execution in your ``/path/to/your/data`` folder.

You may then execute your configuration files by issuing:

.. code-block:: bash

   deager run --data /path/to/your/data

And EAGER will process your data given your configuration files sequentially. Afterwards, your output will be in the provided datafolder and you can then have a look at e.g. the ReportTable created by the pipeline. All data that has been processed will be available outside of the docker container, so you can basically spin up a container, analyse your data and destroy the container after the analysis process, leaving your system unchanged.

Note that the initial download of the image might take some time, depending on your internet connection.


.. note::

   If you need assistance with the Docker image and or would like to get updates if the image is updated, please sign up at our mailing list for docker users of EAGER by sending an e-mail to `<eager-docker@googlegroups.com>`_ .




Manual Installation
-------------------

.. note::

  This is the native installation of the EAGER pipeline. It requires you to download tools manually, compile them and set paths accordingly in order for the pipeline to work on your operating system.

The manual installation on an infrastructure without access to a docker container is a bit more complex than installing the docker image, as all the requirements and subsequent tools for EAGER need to be linked correctly on the system running the pipeline in the end. This has certain requirements:

  * Java 8 Environment, preferably the Oracle JDK8
  * GNU Bash

After this, the following tools need to be installed by the user, ideally system wide or (if this is not possible due to access rights), by manually compiling them. In parentheses you can find the version(s) EAGER has been tested with.

.. note::

  The EAGER-GUI, EAGER-CLI and all other components developed within the EAGER pipeline can be downloaded from their respective **Release** sections on GitHub as pre-compiled JAR files. You don't need to re-compile these applications manually. In case you want to, please use `IntelliJ IDE <http://jetbrains.com>`_ to do so.

List of Tools tested with EAGER:

  * `ANGSD(v0.910) <http://popgen.dk/wiki/index.php/ANGSD>`_
  * `BAM2TDF(v14) <http://genomeview.org/manual/Bam2tdf>`_
  * BGZip (depending on your linux distribution, you have this already installed)
  * `Bowtie 2(v2.2.8) <http://bowtie-bio.sourceforge.net/bowtie2/index.shtml>`_
  * `BWA (v0.7.15) <https://sourceforge.net/projects/bio-bwa/>`_
  * `CircularMapper(latest) <https://github.com/apeltzer/CircularMapper>`_
  * `Clip & Merge(latest) <https://github.com/apeltzer/ClipAndMerge>`_
  * `Schmutzi (latest) <https://github.com/grenaud/schmutzi>`_
  * `DeDup (latest) <https://github.com/apeltzer/DeDup>`_
  * `EAGER (latest) <https://github.com/apeltzer/EAGER-GUI>`_
  * `EAGER-CLI (latest) <https://github.com/apeltzer/EAGER-CLI>`_
  * `FastX-Tools (v0.0.13) <http://hannonlab.cshl.edu/fastx_toolkit/>`_
  * `FastQC (v0.11.4) <http://www.bioinformatics.babraham.ac.uk/projects/fastqc/>`_
  * `GATK (v3.6) <https://www.broadinstitute.org/gatk/>`_
  * `mapDamage (v2.0) <http://ginolhac.github.io/mapDamage/>`_
  * `MergedReadExtractor (latest) <https://github.com/apeltzer/MergedReadExtractor>`_
  * `MTNucRatioCalculator (latest) <https://github.com/apeltzer/MTNucRatioCalculator>`_
  * `Picard-Tools (v1.140) <http://broadinstitute.github.io/picard/>`_
  * `Preseq (v2.0) <http://smithlabresearch.org/software/preseq/>`_
  * `QualiMap (v2.3) <http://qualimap.bioinfo.cipf.es/>`_
  * `ReportTable (latest) <https://github.com/apeltzer/ReportTable>`_
  * `Samtools (v1.3.0) <http://www.htslib.org/>`_
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
