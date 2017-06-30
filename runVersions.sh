#!/bin/bash
#This script determines the installed versions of each software tool within the EAGER pipeline and tells the user about it. 

pacman -Q eager-gui eager-cli gatk jdk dedup circularmapper clipandmerge fastqc preseq vcf2genome bwa mapdamage fastx_toolkit htslib eagerstat r eager-reportengine bowtie2 picard-tools stampy angsd schmutzi
