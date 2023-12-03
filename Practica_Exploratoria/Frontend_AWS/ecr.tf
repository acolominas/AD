module "ecr" {
  source = "git@github.com:acolominas/tf-modules-aws.git//ecr"

  name        = var.name
  company     = var.company
  environment = var.short_environment
  project     = var.project
  region      = var.region
}
