module "tags" {
  source = "git@github.com:acolominas/tf-modules-aws.git//tags"

  project     = var.project
  company     = var.company
  cost-center = var.cost-center
  environment = var.environment
  managed-by  = var.managed-by
}
