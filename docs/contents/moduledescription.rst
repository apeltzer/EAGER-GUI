Module description
==================

EAGER comes with lots of different modules for different use cases, thus enabling the user to configure the pipeline in a fine granular way.
This section describes the different modules in more detail than e.g. the user tutorials that are offered here in the documentation too.

FastQC
------

This module can not be configured and is utilized to gain first insight into a raw sequencing dataset to determine important basic statistics, such as for example GC content and average read lengths prior to modifying the data. This should be used whenever you want to analyze data coming from sequencing without having an idea if even sequencing was successful as it creates basic plots showing whether the data is suitable for further downstream analysis.

Clip and Merge
--------------

This is our in-house application for adapter clipping and read merging. Clicking on the *Advanced* button next to this application, will

.. image:: images/modules/01_clipandmerge.png
    :width: 300px
    :height: 300px
    :align: center


QualityFiltering
----------------

Mapping
-------

BWA
^^^

.. image:: images/modules/02_mapping_BWA.png
    :width: 300px
    :height: 300px
    :align: center

CircularMapper
^^^^^^^^^^^^^^

.. image:: images/modules/03_mapping_CircularMapper.png
    :width: 300px
    :height: 300px
    :align: center

BWAMem
^^^^^^
.. image:: images/modules/04_mapping_Bowtie2.png
    :width: 300px
    :height: 300px
    :align: center

Bowtie2
^^^^^^^

Stampy
^^^^^^

Complexity Estimation
---------------------

.. image:: images/modules/05_complexityEstimation.png
    :width: 300px
    :height: 300px
    :align: center

Remove Duplicates
-----------------

DeDup
^^^^^^


MarkDuplicates
^^^^^^^^^^^^^^

Contamination Estimation
------------------------

.. image:: images/modules/06_contaminationEstimation.png
    :width: 300px
    :height: 300px
    :align: center

Coverage/Statistics Calculation
-------------------------------

MapDamage Calculation
---------------------
.. image:: images/modules/07_mapDamage.png
    :width: 300px
    :height: 300px
    :align: center

SNP Calling
-----------

UnifiedGenotyper
^^^^^^^^^^^^^^^^


HaplotypeCaller
^^^^^^^^^^^^^^^^

.. image:: images/modules/08_SNPcalling_GATK.png
    :width: 894px
    :height: 319px
    :align: center


ANGSD
^^^^^

.. image:: images/modules/09_SNPcalling_ANGSD.png
    :width: 882px
    :height: 179px
    :align: center

SNP Filtering
-------------

.. image:: images/modules/10_SNPFiltering_GATK.png
    :width: 300px
    :height: 300px
    :align: center


VCF2Genome
----------

.. image:: images/modules/11_VCF2Genome.png
    :width: 300px
    :height: 300px
    :align: center

CleanUp
-------

Create Report
-------------
