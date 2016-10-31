Prerequisites for the installation of EAGER
-------------------------------------------

Operating System Support
~~~~~~~~~~~~~~~~~~~~~~~~

Linux
^^^^^

EAGER has been successfully tested on several types of operating systems, supporting the underlying tools and methods. These include several flavours of Linux based operating systems including Debian 'Jessie', Ubuntu 16.04 LTS, CentOS 7 and ArchLinux.

Mac OSX
^^^^^^^

The pipeline can be installed and configured on Mac OSX 10.x as well, however some of the tools used by EAGER are stating that they might be unstable on OSX. Therefore, we do not recommend to run the pipeline directly on OSX, but instead rely on a Linux workstation, cluster or the usage of our Docker based EAGER image instead, which is running perfectly fine on OSX as well.

Windows
^^^^^^^

.. note:: There are currently **no plans** to support Windows as a operating system.

Merely, this is a limitation posed not by EAGER itself, but rather of many of the underlying tools which are not running on Windows and have been developed for Linux based operating systems.

Software Requirements for EAGER
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Docker Image Based Installation
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
You will only need to install Docker on your host system. There are several manuals available to install and run docker on your machine, depending on your operating system. Note that this is the only supported installation method on OSX and Windows host machines.

- For Linux, look up the installation manual `here <https://docs.docker.com/linux/started/>`_
- For OSX, look up the installation manual `here <https://docs.docker.com/mac/started/>`_
- For Windows, look up the installation manual `here <https://docs.docker.com/windows/started/>`_

Furthermore, you will need to install the `Docker compose framework <https://docs.docker.com/compose/install/>`_ to make installation and maintenance of the Docker infrastructure easier. Furthermore, you will need `Git <https://git-scm.com/>`_ on your system.

.. note:: The usage of Docker on both OSX and Windows machines is relying on virtualization technology. This means that you will experience a performance drawback of roughly 10-20% compared to a native installation on a similar Linux machine. This is not specific to EAGER, but to the Windows operating system and Docker connection between the host and guest system.

Direct Host Installation
^^^^^^^^^^^^^^^^^^^^^^^^
You will need to have a running Linux machine (e.g. Ubuntu 16.04, ArchLinux, OpenSuSe, RedHat or CentOS), ideally in a 64bit flavor installed and running. Administrative rights are not required, but may make the installation of tools easier. Afterwards, follow the installation instructions in the advanced section.

VirtualBox Installation
^^^^^^^^^^^^^^^^^^^^^^^
If you would like to install EAGER as a VirtualBox image, to simply try out the pipeline without having to install many software packages, you will be required to install VirtualBox first on your operating system. To install the VirtualBox software, simply follow the instructions available here.

For OSX users, we also created a video, describing the whole process on a OSX Yosemite client machine. Afterwards, you can follow the setup instructions on VirtualBox Installation Guide for EAGER

.. _naming_scheme:

File Naming Scheme
~~~~~~~~~~~~~~~~~~

EAGER relies on naming patterns that files should follow, to determine read pairs for example. For sample identification, the pipeline assumes that samples are sharing a same identifier and follow this kind of naming pattern:

.. code-block:: bash

  SomeIDentifier_R1_LaneIdentifier.fq.gz
  SomeIDentifier_R2_LaneIdentifier.fq.gz

If you select several samples like this, EAGER will automatically determine which ones belong to each other and process all of them in a single processing run.

Typically, depending on your local sequencing infrastructure or if you received samples from e.g. other labs, downloaded them from the SRA or other resources, you will receive several folders with each folder corresponding to a sample, e.g.:

.. code-block:: bash

  Sample_XYZ/XYZ_R1_LaneIdentifier.fq.gz
            /XYZ_R1_LaneIdentifier.fq.gz
  Sample_UVW/UVW_R1_LaneIdentifier.fq.gz
            /UVW_R1_LaneIdentifier.fq.gz

In this case you can simply select the parent folder of your input data containing the folders "Sample_XYZ" and "Sample_UVW" and EAGER will cope with the data itself.

.. note::

  EAGER does not require your data to be uncompressed such as other pipelines do. All of the tools in the pipeline have been tuned to enable input to be compressed as `fq.gz`, so input from Illumina sequencers can directly processed without uncompressing the datasets first. 

Reference Genomes
^^^^^^^^^^^^^^^^^

EAGER requires your reference genomes to be in FastA format. Generating an index for mapping is not required, as the pipeline determines whether the index needs to be determined automatically.

.. note::

   If you have multiple reference genomes in a single folder, please generate folders for each of your references, otherwise index generation might run only once, creating indexes for only the first of your reference genomes.
   
   
.. warning::

    Furthermore, EAGER (and some downstream tools) require your input FastA file to have a *.fasta or *.fa file ending and being encoded using UNIX newline characters. The tool 'dos2unix' can be used to convert your input to proper unix formatted FastA files. Rename your input reference to have a proper file ending to ensure the first constraint is met. 
