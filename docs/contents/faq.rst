FAQ
===

I am missing Feature X for my analysis
--------------------------------------

You can either contact Alexander Peltzer <alexander.peltzer@uni-tuebingen.de> directly or open a `ticket <https://github.com/apeltzer/EAGER-GUI/issues>`_ on Github directly, describing what you would like to see in the pipeline. We can not make promises to fullfill all the questions you might ask for but are constantly looking for feedback to improve the usability of EAGER and provide more features for users.
  Quick explanation: The image has been updated for example and thus the SSH fingerprint doesn't match anymore with what your local ssh ''known_hosts'' states. We remove this line and then the image is accepted again.

I have some BAM files already preprocessed and don't want to map everything again
----------------------------------------------------------------------------------

You can also select BAM files in the EAGER pipeline! Just select the BAM files as input, set the _same_ reference genome as you used for mapping, select an output folder and *deselect adapter clipping & mapping modules* and you're ready to go! You could for example use EAGER in these cases for the assessment of BAM files, genotyping and duplicate removal, without mapping your preprocessed BAM files again.


I am using EAGER to reconstruct several genomes simultaneously but it doesn't work
------------------------------------------------------------------------------------

Make sure that you have your individual reference genomes in separate folders and don't use a single folder for all of your references. EAGER relies on generating execution files (named DONE.<modulname>) to figure out whether parts of the pipeline have been executed before.
If you have two reference genomes in one folder, it will generate indexes for the first reference genome and then find these files, stopping to create an index for the second genome. In order to prevent this, please use different folders for different reference genomes, as mentioned in the file naming pattern section of this documentation.

I have several samples from the same individual (e.g. pre-screening and a wgs dataset) and would like to combine these
----------------------------------------------------------------------------------------------------------------------

This is not directly supported in the pipeline but there is a possibility to achieve this. 

1. Run your preprocessing and mapping steps as usual (up to deduplication) for all your individual samples.
2. Combine these using e.g. 

.. code-block:: bash

   samtools merge output.bam input1.bam input2.bam input3.bam ... 

3. This will create a file called 'output.bam' that you can then subsequently load into EAGER (yes, select as input!). 
4. Deselect everything up till "Duplicate Removal" as these modules can't be run on BAM files.
5. Continue with e.g. Genotyping - you can also select to get the proper statistics. 

.. note:: 

    You need to select the same reference genome in the input selection to get proper statistics.

I have an error and I don't know what to do
-------------------------------------------

A good start would be having a look at your ''EAGER.log'' logfile. This contains information about the commands causing potential errors, runtime and other important information. It helps us to verify whats going on even if you contact us directly.
In many cases you might already figure out what went wrong with just reading this file!

.. note::

    If you send us an e-mail reporting an error/bug or just because you didn't find out yourself what might have went wrong, please always include the ''EAGER.log'' logfile. This is important for us to understand what might have gone wrong.