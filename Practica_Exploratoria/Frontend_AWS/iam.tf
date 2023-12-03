data "aws_iam_policy_document" "ecr-read" {
  statement {

    actions = [
      "ecr:Get*",
      "ecr:List*",
      "ecr:Describe*",
      "ecr:BatchGetImage",
      "ecr:BatchCheckLayerAvailability"
    ]

    resources = [ module.ecr.repository_arn ]
  }

  statement {
    actions = [
      "ecr:GetAuthorizationToken"
    ]

    resources = ["*"]
  }
}

data "aws_iam_policy_document" "ecr-write" {
  statement {

    actions = [
      "ecr:*"
    ]

    resources = [ module.ecr.repository_arn ]
  }

  statement {
    actions = [
      "ecr:GetAuthorizationToken"
    ]

    resources = ["*"]
  }
}


data "aws_iam_policy_document" "cw-logs" {
  statement {

    actions = ["logs:*"]

    resources = [ "${module.ecs-service.cw-log-arn}:*" ]
  }
}

data "aws_iam_policy_document" "ecs-service" {

  statement {

    actions = ["ecs:UpdateService"]

    resources = [ module.ecs-service.ecs_service_arn]
  }

  statement {

    actions = [
      "iam:PassRole",
      "ecs:DescribeServices",
      "ecs:RegisterTaskDefinition"
    ]

    resources = [ "*" ]
  }
}

module "iam-role" {
  source = "git@github.com:acolominas/tf-modules-aws.git//iam-role"

  name        = var.name
  company     = var.company
  environment = var.short_environment
  project     = var.project
  region      = var.region

  role_description      = "Role to attach to ECS Service"
  trusted_role_services = ["ecs-tasks.amazonaws.com"]

  custom_role_policies = {
    policy1 = {
      name        = "ecr-read"
      description = "ECR Read"
      policy      = data.aws_iam_policy_document.ecr-read.json
    },
    policy2 = {
      name        = "cw-logs"
      description = "CW Logs"
      policy      = data.aws_iam_policy_document.cw-logs.json
    }
  }
}

module "iam-user-cicd" {
  source = "git@github.com:acolominas/tf-modules-aws.git//iam-user"

  name        = var.name
  company     = var.company
  environment = var.short_environment
  project     = var.project
  region      = var.region

  create_iam_access_key = true
}

resource "aws_iam_user_policy" "ecr" {
  name = "ecr"
  user = module.iam-user-cicd.iam_user_name

  policy = data.aws_iam_policy_document.ecr-write.json
}

resource "aws_iam_user_policy" "ecs" {
  name = "ecs"
  user = module.iam-user-cicd.iam_user_name

  policy = data.aws_iam_policy_document.ecs-service.json
}
