# node

## Without custom domain name

Create a zip file with the application and its dependencies:

```bash
./build
```

Upload it to a bucket that you control.
```bash
aws s3 cp node.zip s3://code402
```

Create the AWS CloudFormat stack. You'll need to change the end of the line to tell it which S3 bucket contains the node.zip file:

```bash
aws cloudformation create-stack --region us-east-1 --stack-name node-demo --template-body file://stack.yaml --capabilities CAPABILITY_IAM --parameters ParameterKey=S3Bucket,ParameterValue=code402
```

You can check the progress at the [AWS dashboard](https://console.aws.amazon.com/cloudformation/home).

The outputs tab will show the URL of your API, e.g. something like https://p8nf9ly3s4.execute-api.us-east-1.amazonaws.com/echo

Future updates to the stack should use `update-stack` instead of `create-stack`:
```bash
aws cloudformation update-stack --region us-east-1 --stack-name node-demo --template-body file://stack.yaml --capabilities CAPABILITY_IAM
```

When you're done, delete it:

```bash
aws cloudformation delete-stack --region us-east-1 --stack-name node-demo
```

## With custom domain name

Create a zip file with the application and its dependencies:

```bash
./build
```

Upload it to a bucket that you control.
```bash
aws s3 cp node.zip s3://code402
```

Create the AWS CloudFormat stack. You'll need to change the end of the line to tell it which S3 bucket contains the node.zip file:

```bash
aws cloudformation create-stack --region us-east-1 --stack-name node-custom-domain-demo --template-body file://stack-custom-domain.yaml --capabilities CAPABILITY_IAM --parameters ParameterKey=S3Bucket,ParameterValue=code402 ParameterKey=DomainName,ParameterValue=java-echo.code402.com ParameterKey=HostedZoneId,ParameterValue=Z2M7YRI8V9GBAF
```

You can check the progress at the [AWS dashboard](https://console.aws.amazon.com/cloudformation/home).

The outputs tab will show the URL of your API, e.g. something like https://p8nf9ly3s4.execute-api.us-east-1.amazonaws.com/echo

Future updates to the stack should use `update-stack` instead of `create-stack`:
```bash
aws cloudformation update-stack --region us-east-1 --stack-name node-custom-domain-demo --template-body file://stack-custom-domain.yaml --capabilities CAPABILITY_IAM
```

When you're done, delete it:

```bash
aws cloudformation delete-stack --region us-east-1 --stack-name node-custom-domain-demo
```
