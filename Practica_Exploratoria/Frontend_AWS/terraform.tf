# Copyright (c) HashiCorp, Inc.
# SPDX-License-Identifier: MPL-2.0

terraform {

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.7.0"
    }
  }

  required_version = "~> 1.1.0"

  backend "s3" {
    bucket = "terraform-aws-account-dev"
    key    = "front-image-manager-dev"
    region = "eu-west-1"
  }
}

provider "aws" {
  region = "eu-west-1"
  default_tags {
    tags = module.tags.tags
  }
}
