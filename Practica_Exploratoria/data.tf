
#################################################################
# NOTE: DO NOT MAKE CHANGES TO THIS FILE AS IT MAY BE OVERRIDEN #
#################################################################

terraform {
  backend "s3" {
    bucket         = "terraform-state-004914726163"
    key            = "AD/terraform.tfstate"
    region         = "eu-west-1"
    encrypt        = true
    dynamodb_table = "tfstate-locking"
  }
}

data "aws_caller_identity" "current" {}

data "aws_region" "current" {}
