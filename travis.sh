#!/bin/sh
rm project/gpg.sbt
wget https://raw.github.com/paulp/sbt-extras/master/sbt &&
chmod u+x ./sbt &&
./sbt -sbt-version 0.13.0 test scripted

