General Usage of EAGER/EAGER-CLI
================================

The second part of EAGER is referred to as `eagercli`, also called the command line version of EAGER. In order for running this part of the pipeline, it is crucial, that you followed the installation instructions and have a working installation of EAGER at your hand.

Once you have created configuration files for your analysis, you may run and execute the configuration files using this command line component of EAGER. Depending on your installation, you can simply execute:

.. code-block:: bash

  eagercli /path/to/configuration/files/


The CLI should then find all the generated configuration files (which are stored in a XML format), then subsequently execute them and run the required analysis steps as you configured them. You may call individual XML files directly, too:

.. code-block:: bash

  eagercli /data/real_analysis/Sample_XYZ/2016-03-07-EAGER.xml


If you have multiple samples, we however recommend executing these by selecting a folder/directory one level up and EAGER would find all XML files in the subdirectories of this folder:

.. code-block:: bash

  eagercli /data/real_analysis/

.. note::

  This will run all samples in the folder **real_analysis** for your convenience.


Docker Installation / First Usage Ubuntu 16.04 LTS
--------------------------------------------------

This is a simple use case for first time users to run the pipeline using our docker image on a Ubuntu 16.04 LTS host system. It requires you to install Docker on Ubuntu, then running the image and setting up your analysis and finally running the pipeline on your created configuration files. Open up a terminal on your Ubuntu system:

.. code-block:: bash

  sudo apt install docker.io
  sudo apt install docker-compose
  sudo gpasswd -a $(whoami) docker

.. note::

  This is installing Docker, the Docker Compose framework and adds your username to the Docker group in order to be able to use docker on the system. For detailed instructions refer to the Ubuntu user manual, which contains more elaborate descriptions of installation procedures.

Next, we can download the `deager` executable in order to run EAGER using docker more easily.

.. code-block:: bash

  git clone https://github.com/apeltzer/deager
  cd deager/bin/Ubuntu

This will download the `deager` repository, we switch to the binary location and then start the helper tool by simply typing

.. code-block:: bash

  ./deager

To run the container for the first time, simply type:

.. code-block:: bash

  ./deager start --data /path/to/your/data --uid #t

.. note::

  This can take some minutes, as the docker image with the pipeline is pulled from the internet automatically. The UID mapping is required to keep the container using your same local user id. Otherwise you will get access denied errors. If you receive an error message, that you have to set a certain unix variable, please issue:

  .. code-block:: bash

    export DOCKER_HOST=unix:///var/run/docker.sock

Afterwards, the image will be started and you are ready to go!

.. code-block:: bash

  ./deager gui --data /path/to/your/data

Now configure the pipeline, e.g. following one of our tutorials at :ref:`tutorials` . After you are done with this, you can simply execute the configuration files:

.. code-block:: bash

  ./deager run --data /path/to/your/data

And the pipeline will execute your configurations.

This whole process is also available as a short little video available `here <https://youtu.be/dfsrUIEr2UY>`_ .
