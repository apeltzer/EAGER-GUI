FAQ
===

I am missing Feature X for my analysis
--------------------------------------

You can either contact Alexander Peltzer <alexander.peltzer@uni-tuebingen.de> directly or open a `ticket <https://github.com/apeltzer/EAGER-GUI/issues>`_ on Github directly, describing what you would like to see in the pipeline. We can not make promises to fullfill all the questions you might ask for but are constantly looking for feedback to improve the usability of EAGER and provide more features for users.

I get an error message when running `deager gui`
------------------------------------------------

If your message looks like this:

.. code-block:: bash

  deager gui --data /home/username/data/
  INFO: 2016/04/27 09:31:17 TLS is disabled
  INFO: 2016/04/27 09:31:17 Extracted '127.0.1.1' by looking up IP address
  INFO: 2016/04/27 09:31:17 ssh -Y -p 2222 -i /tmp/eager_ssh_key_183426350 -l eager 127.0.1.1 eager
  panic: exit status 255

  goroutine 1 [running]:
  main.check(0x7f88b27518d8, 0xc82002a570)
     /usr/local/src/github.com/apeltzer/deager/deager.go:222 +0x4b
  main.main()
     /usr/local/src/github.com/apeltzer/deager/deager.go:139 +0x1fef

  goroutine 17 [syscall, locked to thread]:
  runtime.goexit()
     /usr/lib/go/src/runtime/asm_amd64.s:1696 +0x1

You can fix this:

.. code-block:: bash

  sed -i '/127.0.1.1/d' ~/.ssh/known_hosts
  docker rm eager_$(whoami)
  ./deager start
  ./deager gui

And you should be able to run it again!

.. note::

  You need to replace "127.0.1.1" with your IP adress, that you find in the INFO field of the error message.

.. note::

  Quick explanation: The image has been updated for example and thus the SSH fingerprint doesn't match anymore with what your local ssh ''known_hosts'' states. We remove this line and then the image is accepted again.

I have some BAM files already preprocessed and don't want to map everything again
---------------------------------------------------------------------------------

You can also select BAM files in the EAGER pipeline! Just select the BAM files as input, set the _same_ reference genome as you used for mapping, select an output folder and *deselect adapter clipping & mapping modules* and you're ready to go! You could for example use EAGER in these cases for the assessment of BAM files, genotyping and duplicate removal, without mapping your preprocessed BAM files again.

I'm using the Docker image to run my analysis but it won't work
---------------------------------------------------------------

Please check first, whether you have file paths with spaces or special characters, for example é or ö/ä/ü. Ideally, restrict your file naming and storing patterns to english paths. Furthermore, make sure that you follow the file naming patterns in detail, as this is mandatory for the pipeline usage.
