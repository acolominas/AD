##ECS CLUSTER

variable "name" {
  type    = string
  default = "01"
}

variable "short_environment" {
  type    = string
  default = "dev"
}

variable "region" {
  type    = string
  default = "euw1"
}

##TAGS
variable "project" {
  type    = string
  default = "imgmanag"
}

variable "environment" {
  type    = string
  default = "development"
}

variable "company" {
  type    = string
  default = "aci"
}

variable "managed-by" {
  type    = string
  default = "acolominas"
}

variable "cost-center" {
  type    = string
  default = "acolominas"
}
