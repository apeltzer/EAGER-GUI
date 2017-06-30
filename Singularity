Bootstrap: docker
From: finalduty/archlinux:daily 

%post

#Adding mirrors to pacman mirrorlist
echo "Server = http://mirror.de.leaseweb.net/archlinux/\$repo/os/\$arch" >> /etc/pacman.d/mirrorlist
echo "[lambdait]" >> /etc/pacman.conf
echo "SigLevel = Never" >> /etc/pacman.conf
echo "Server = https://lambda.informatik.uni-tuebingen.de/repo/mypkgs/" >> /etc/pacman.conf
echo "#!/bin/bash\n pacman -Q eager-gui eager-cli gatk jdk dedup circularmapper clipandmerge fastqc preseq vcf2genome bwa mapdamage fastx_toolkit htslib eagerstat r eager-reportengine bowtie2 picard-tools stampy angsd schmutzi" >> /usr/bin/versions


#Installing basic dependencies
pacman -Sy --noconfirm freetype2 ttf-dejavu git libcups mesa-libgl rsync strace r python2 gsl libxtst

#Clean up
paccache -r -k0

#Install all the dependencies of my pipeline
#JDK8, BT2, BWA, Samtools, etc.

pacman -Sy --noconfirm --force jdk bam2tdf dedup circularmapper clipandmerge fastqc preseq vcf2genome

#Clean up intermediate files
paccache -r -k0

pacman -Sy --noconfirm --force fastx_toolkit htslib qualimap mapdamage bwa eager-reportengine eagerstat

#Clean up intermediate files
paccache -r -k0

pacman -Sy --noconfirm --force bowtie2 picard-tools stampy angsd gatk schmutzi

#Clean up intermediate files
paccache -r -k0

pacman -Sy --noconfirm --force eager-gui eager-cli

#Create analysis mountpoint
mkdir -p /data

#Clean up
paccache -r -k0 #clean up

%files
# Add GATK Licence to image to be consistent with Licencing Permission by Broad Institute
GATKLicence.txt /usr/share/licenses/common/GATKLicence.txt
runVersions.sh /usr/bin/runVersions

%labels
Maintainer	alexander.peltzer@uni-tuebingen.de
Version	1.92

