#!/bin/bash
docker build . -t demo
mkdir -p build
docker run --rm --entrypoint cat demo  /home/application/function.zip > build/function.zip
