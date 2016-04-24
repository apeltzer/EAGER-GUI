Installation Instructions for the EAGER Pipeline
================================================

We provide three kinds of installation instructions.

VirtualBox
----------

This should only be used for testing purposes.

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


Compiling Deager
^^^^^^^^^^^^^^^^

After you successfully installed all prerequisites (Docker, Docker-Compose) you can start by compiling the helper tool ``deager`` required to run EAGER within Docker more easily. Go to your Home Directory, e.g. Downloads

.. code-block:: bash

   cd ~/Downloads
   git clone https://github.com/apeltzer/deager.git
   cd deager
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
     --gatk <path>      Path to the GATK file (jar/tar.bz2) [default: ~/gatk/]
                        It has to be provided by the user, since the license prohibits packaging it in our image.
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

   deager start --gatk /path/to/gatk --data /path/to/your/datafolder

Afterwards, you can open the GUI to configure an analysis run, illustrated in this little `helper video <https://www.youtube.com/watch?v=cKrBuoiGgNE>`_

.. code-block:: bash
   deager gui --gatk ~/gatk --data ~/data


This should open a graphical interface on your machine, enabling you to configure everything and creating subsequently configuration files used for the pipeline execution in your ``/path/to/your/data`` folder.

You may then execute your configuration files by issuing:

.. code-block:: bash
   deager run --data /path/to/your/data

And EAGER will process your data given your configuration files sequentially. Afterwards, your output will be in the provided datafolder and you can then have a look at e.g. the ReportTable created by the pipeline. All data that has been processed will be available outside of the docker container, so you can basically spin up a container, analyse your data and destroy the container after the analysis process, leaving your system unchanged.

Note that the initial download of the image might take some time, depending on your internet connection.






- Native installation EAGER Pipeline: This is a more advanced version, requiring you to install all the tools manually. Maximum performance, but requires manual installation.
