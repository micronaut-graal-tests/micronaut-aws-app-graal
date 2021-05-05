# Micronaut application AWS GraalVM

Test application for Micronaut application (with controllers) deployed to AWS as a GraalVM native image.

To build the application and test it locally:

```shell
./build-native-image.sh
./sam-local.sh
```

To build the application inside Docker:

```shell
./gradlew buildNativeLambda -Pmicronaut.runtime=lambda
cp build/libs/mn-aws-app-graal-0.1-lambda.zip build/function.zip
```

To send a request:

```shell
curl -X GET localhost:3000/jokes/nerdy
curl -X GET localhost:3000/github/releases
```

To deploy to AWS:

```shell
S3_BUCKET=USE-YOUR-OWN-BUCKET
STACK_NAME=USE-YOUR-OWN-STACK-NAME

aws cloudformation package --template-file sam-native.yaml --output-template-file build/output-sam.yaml --s3-bucket $S3_BUCKET
aws cloudformation deploy --template-file build/output-sam.yaml --stack-name $STACK_NAME --capabilities CAPABILITY_IAM

API_ENDPOINT=`aws cloudformation describe-stacks --stack-name $STACK_NAME | jq -r '.Stacks[0] .Outputs[0] .OutputValue'`

curl $API_ENDPOINT/jokes/nerdy
curl $API_ENDPOINT/jokes/566
curl $API_ENDPOINT/bintray/packages

# To delete everything
aws cloudformation delete-stack --stack-name $STACK_NAME
```
