#!/bin/bash

EXIT_STATUS=0

./gradlew assemble
native-image --no-fallback --class-path build/libs/mn-aws-app-graal-*-all.jar || EXIT_STATUS=$?
if [[ $EXIT_STATUS -eq 0 ]]; then
  zip -j build/function.zip bootstrap aws-app
else
  exit 1
fi
