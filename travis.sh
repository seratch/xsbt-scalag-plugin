#!/bin/sh
rm project/gpg.sbt
wget https://raw.github.com/paulp/sbt-extras/master/sbt &&
chmod u+x ./sbt &&
./sbt -sbt-version 0.11.3 -mem 512 test scripted &&
./sbt -sbt-version 0.12.0 -mem 512 test scripted 

