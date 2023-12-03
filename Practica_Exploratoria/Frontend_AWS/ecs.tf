data "aws_ssm_parameter" "ecs_optimized_ami" {
  name = "/aws/service/ecs/optimized-ami/amazon-linux-2023/recommended"
}

module "ecs-cluster" {
  source = "git@github.com:acolominas/tf-modules-aws.git//ecs-cluster"

  name             = var.name
  company          = var.company
  environment      = var.short_environment
  project          = var.project
  region           = var.region
  vpcid            = data.aws_vpc.default.id
  vpc-cidr         = data.aws_vpc.default.cidr_block
  private-subnets  = data.aws_subnets.this.ids
  desired-capacity = 1
  min-size         = 1
  ami-id           = jsondecode(data.aws_ssm_parameter.ecs_optimized_ami.value)["image_id"]
  instance-type    = "t3.small"
}

module "sg" {
  source = "git@github.com:acolominas/tf-modules-aws.git//sg"

  name        = var.name
  company     = var.company
  environment = var.short_environment
  project     = var.project
  region      = var.region
  description = "Security Group for Testing Module SG"
  vpc_id      = data.aws_vpc.default.id

  sg_rules_cidr = {
    postgres = {
      description = "Allow HTTP from Internet"
      cidr_blocks = ["37.223.168.251/32"]
      from_port   = 80
      to_port     = 80
      protocol    = "tcp"
    }
  }
}

module "ecs-service" {
  source = "git@github.com:acolominas/tf-modules-aws.git//ecs-service"

  name               = var.name
  company            = var.company
  environment        = var.short_environment
  project            = var.project
  region             = var.region
  ecs-cluster-arn    = module.ecs-cluster.cluster_arn
  security-groups    = [module.sg.id]
  internal           = false
  vpcid              = data.aws_vpc.default.id
  subnets            = data.aws_subnets.this.ids
  task-role-arn      = module.iam-role.iam_role_arn
  execution-role-arn = module.iam-role.iam_role_arn
  kms_key_id         = data.aws_kms_key.kms.arn
  desired-count      = 1
}

resource "aws_route53_record" "api" {
  zone_id = data.aws_route53_zone.public-zone.zone_id
  name    = "web"
  type    = "CNAME"
  ttl     = 300
  records = [module.ecs-service.alb_output]
}
