#!/bin/bash

EXIT_STATUS=0

./gradlew nativeImage || EXIT_STATUS=$?
if [[ $EXIT_STATUS -eq 0 ]]; then
  zip -j build/function.zip bootstrap build/native-image/application
else
  exit 1
fi
