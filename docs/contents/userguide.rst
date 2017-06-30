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


