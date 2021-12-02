resource "aws_elastic_beanstalk_application" "tftest" {
  name        = "ImageManager"
  description = "Image Manager"
}

resource "aws_elastic_beanstalk_environment" "tfenvtest" {
  name        = "image-manager"
  application = aws_elastic_beanstalk_application.tftest.name

  //https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/concepts.platforms.html
  solution_stack_name = "64bit Amazon Linux 2 v4.2.8 running Tomcat 8.5 Corretto 8"

  //https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/command-options-general.html
  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "IamInstanceProfile"
    value     = aws_iam_instance_profile.aws-elasticbeanstalk-ec2-instance-profile.name
  }
  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "InstanceType"
    value     = "t3.medium"
  }

  setting {
    namespace = "aws:elasticbeanstalk:environment"
    name      = "LoadBalancerType"
    value     = "application"
  }

  setting {
    namespace = "aws:autoscaling:asg"
    name      = "MinSize"
    value     = "2"
  }
  tags = {
    Environment = "test"
  }
}

resource "aws_elastic_beanstalk_application_version" "default" {
  name        = "ImageManager"
  application = aws_elastic_beanstalk_application.tftest.name
  description = "application version created by terraform"
  bucket      = aws_s3_bucket.bucket-s3-app.id
  key         = aws_s3_bucket_object.bucket-s3-app-war.id
}
