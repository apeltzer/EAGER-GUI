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

- For Linux, look up the installation manual `here https://docs.docker.com/linux/started/`
- For OSX, look up the installation manual `here https://docs.docker.com/mac/started/`
- For Windows, look up the installation manual `here https://docs.docker.com/windows/started/`
Furthermore, you will need to install the `Docker compose framework https://docs.docker.com/compose/install/` to make installation and maintenance of the Docker infrastructure easier. Furthermore, you will need `Git https://git-scm.com/` (>2.0) on your system.

.. note:: The usage of Docker on both OSX and Windows machines is relying on virtualization technology. This means that you will experience a performance drawback of roughly 10-20% compared to a native installation on a similar Linux machine. This is not specific to EAGER, but to the Windows operating system and Docker connection between the host and guest system.

Direct Host Installation
^^^^^^^^^^^^^^^^^^^^^^^^
You will need to have a running Linux machine (e.g. Ubuntu 16.04, ArchLinux, OpenSuSe, RedHat or CentOS), ideally in a 64bit flavor installed and running. Administrative rights are not required, but may make the installation of tools easier. Afterwards, follow the installation instructions in the advanced section.

VirtualBox Installation
^^^^^^^^^^^^^^^^^^^^^^^
If you would like to install EAGER as a VirtualBox image, to simply try out the pipeline without having to install many software packages, you will be required to install VirtualBox first on your operating system. To install the VirtualBox software, simply follow the instructions available here.

For OSX users, we also created a video, describing the whole process on a OSX Yosemite client machine. Afterwards, you can follow the setup instructions on VirtualBox Installation Guide for EAGER
