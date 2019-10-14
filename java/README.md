# java

## Without custom domain name

Build the JAR:

```bash
mvn clean compile assembly:single   # Build JAR
```

Upload it to a bucket that you control.
```bash
aws s3 cp target/java-demo.jar s3://code402
```

Create the AWS CloudFormat stack. You'll need to change the end of the line to tell it which S3 bucket contains the JAR in:

```bash
aws cloudformation --region us-east-1 create-stack --stack-name java-demo --template-body file://stack.yaml --capabilities CAPABILITY_IAM --parameters ParameterKey=S3Bucket,ParameterValue=code402
```

You can check the progress at the [AWS dashboard](https://console.aws.amazon.com/cloudformation/home).

The outputs tab will show the URL of your API, e.g. something like https://p8nf9ly3s4.execute-api.us-east-1.amazonaws.com/echo

Future updates to the stack should use `update-stack` instead of `create-stack`:
```bash
aws cloudformation --region us-east-1 update-stack --stack-name java-demo --template-body file://stack.yaml --capabilities CAPABILITY_IAM
```

When you're done, delete it:

```bash
aws cloudformation --region us-east-1 delete-stack --stack-name java-demo
```

## With custom domain name

Build the JAR:

```bash
mvn clean compile assembly:single   # Build JAR
```

Upload it to a bucket that you control.
```bash
aws s3 cp target/java-demo.jar s3://code402
```

Create the AWS CloudFormat stack. You'll need to change the end of the line to tell it which S3 bucket contains the JAR in:

```bash
aws cloudformation --region us-east-1 create-stack --stack-name java-custom-domain-demo --template-body file://stack-custom-domain.yaml --capabilities CAPABILITY_IAM --parameters ParameterKey=S3Bucket,ParameterValue=code402 ParameterKey=DomainName,ParameterValue=java-echo.code402.com ParameterKey=HostedZoneId,ParameterValue=Z2M7YRI8V9GBAF
```

You can check the progress at the [AWS dashboard](https://console.aws.amazon.com/cloudformation/home).

The outputs tab will show the URL of your API, e.g. something like https://p8nf9ly3s4.execute-api.us-east-1.amazonaws.com/echo

Future updates to the stack should use `update-stack` instead of `create-stack`:
```bash
aws cloudformation --region us-east-1 update-stack --stack-name java-custom-domain-demo --template-body file://stack-custom-domain.yaml --capabilities CAPABILITY_IAM
```

When you're done, delete it:

```bash
aws cloudformation --region us-east-1 delete-stack --stack-name java-custom-domain-demo
```
