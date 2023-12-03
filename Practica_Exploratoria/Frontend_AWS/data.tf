data "aws_vpc" "default" {
  default = true
}

data "aws_subnets" "this" {
  filter {
    name   = "vpc-id"
    values = [ data.aws_vpc.default.id ]
  }
}

data "aws_route53_zone" "public-zone" {
  name = "dev.arnaucolominas.net."
}

data "aws_caller_identity" "current" {}

data "aws_region" "current" {}

data "aws_kms_key" "kms" {
  key_id = "alias/aci-kmskey-account-dev-euw1-01"
}
