Bootstrap: docker
From: finalduty/archlinux:daily 

%post

echo "Server = http://mirror.de.leaseweb.net/archlinux/\$repo/os/\$arch" >> /etc/pacman.d/mirrorlist

echo "Server = http://mirror.de.leaseweb.net/archlinux/\$repo/os/\$arch" >> /etc/pacman.d/mirrorlist
echo "[lambdait]" >> /etc/pacman.conf
echo "SigLevel = Never" >> /etc/pacman.conf
echo "Server = https://lambda.informatik.uni-tuebingen.de/repo/mypkgs/" >> /etc/pacman.conf
pacman -Sy --noconfirm freetype2 ttf-dejavu git libcups mesa-libgl rsync strace r python2 gsl libxtst
paccache -r -k0 #clean up
#Install all the dependencies of my pipelin
#Installing Required Packages
#Oracle JDK7, BT2, BWA, Samtools, etc.
#Install all the dependencies of my pipeline

pacman -S --noconfirm --force jdk bam2tdf dedup circularmapper clipandmerge fastqc preseq vcf2genome 
paccache -r -k0 #clean up again
pacman -S --noconfirm --force fastx_toolkit htslib qualimap mapdamage bwa eager-reportengine eagerstat 
pacman -S --noconfirm --force bowtie2 picard-tools stampy angsd 
pacman -S --noconfirm --force schmutzi eager-gui eager-cli gatk
mkdir -p /data
rm /var/cache/pacman/pkg/*

%files
# Add GATK Licence to image to be consistent with Licencing Permission by Broad Institute
GATKLicence.txt /usr/share/licenses/common/GATKLicence.txt

%labels
Maintainer	alexander.peltzer@uni-tuebingen.de
Version	1.92

