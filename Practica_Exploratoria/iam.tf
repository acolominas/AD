resource "aws_iam_role" "aws-elasticbeanstalk-ec2-role" {
  name = "aws-elasticbeanstalk-ec2-role"

  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "ec2.amazonaws.com"
      },
      "Effect": "Allow",
      "Sid": ""
    }
  ]
}
EOF
}

resource "aws_iam_policy_attachment" "aws-elasticbeanstalk-ec2-attach-S3" {
  name  = "test-attachment-1"
  roles = [aws_iam_role.aws-elasticbeanstalk-ec2-role.name]

  policy_arn = "arn:aws:iam::aws:policy/AmazonEC2ReadOnlyAccess"
}

resource "aws_iam_policy_attachment" "aws-elasticbeanstalk-ec2-attach-EC2" {
  name       = "test-attachment-1"
  roles      = [aws_iam_role.aws-elasticbeanstalk-ec2-role.name]
  policy_arn = "arn:aws:iam::aws:policy/AmazonS3ReadOnlyAccess"
}


resource "aws_iam_policy_attachment" "aws-elasticbeanstalk-ec2-attach-SSM" {
  name       = "test-attachment-1"
  roles      = [aws_iam_role.aws-elasticbeanstalk-ec2-role.name]
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonEC2RoleforSSM"
}

resource "aws_iam_policy_attachment" "aws-elasticbeanstalk-ec2-attach-EB" {
  name       = "test-attachment-1"
  roles      = [aws_iam_role.aws-elasticbeanstalk-ec2-role.name]
  policy_arn = "arn:aws:iam::aws:policy/AWSElasticBeanstalkWebTier"
}

resource "aws_iam_instance_profile" "aws-elasticbeanstalk-ec2-instance-profile" {
  name = "aws-elasticbeanstalk-ec2-instance-profile"
  role = aws_iam_role.aws-elasticbeanstalk-ec2-role.name
}
