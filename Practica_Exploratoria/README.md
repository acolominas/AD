# Practica Exploratoria

##Frontend

Application deployed through Elastic Beanstalk Service using Tomcat 8/Nginx proxy middleware.

## Backend

Serverless API REST built with API Gateway Service to store images to s3 and manage it.

![Alt text](readme_files/practica_exploratoria.jpg?raw=true "Title")

To add security to API, we will use Cognito service to authenticate & authorize the access to the API methods using OAuth 2 protocol.

All API methods are developed in Python using lambdas.

Physical images are stored in S3 Bucket and its metadata is stored in a DynamoDB table. The images stored are public and publicated through bucket S3.

### AWS Services Involved

* Elastic Beansatalk
* Route53
* API Gateway
* Lambda
* S3
* DynamoDB
* Cognito
* IAM

### TODO
* Federate authentication with other identity providers like Facebook,Google...
