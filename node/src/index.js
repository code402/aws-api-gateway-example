const AWS = require('aws-sdk');

exports.handler = async (event) => {
  const input = event.queryStringParameters.message;
  console.log(`Input: ${input}`);
  return {
    'body': input.toUpperCase() + "\n",
    'headers': {
      'Content-Type': 'text/plain'
    },
    'statusCode': 200
  };
}
