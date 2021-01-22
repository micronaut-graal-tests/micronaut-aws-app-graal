#!/bin/bash

./gradlew buildNativeLambda && \
cp build/libs/mn-aws-app-graal-0.1-lambda.zip build/function.zip
