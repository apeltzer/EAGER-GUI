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



- Docker based EAGER Pipeline: This is the default way to use EAGER in a dockerized environment. Best user experience, minimum performance drawbacks - maximum installation benefits ;-)

- Native installation EAGER Pipeline: This is a more advanced version, requiring you to install all the tools manually. Maximum performance, but requires manual installation.
