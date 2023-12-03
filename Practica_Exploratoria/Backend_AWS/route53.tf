data "aws_route53_zone" "public-zone" {
  name = "xpendack.net."
}

resource "aws_route53_record" "eb-record" {
  zone_id = data.aws_route53_zone.public-zone.zone_id
  name    = "image-site.xpendack.net"
  type    = "CNAME"
  ttl     = "300"
  records = ["${aws_elastic_beanstalk_environment.image-manager-app-pro.cname}"]
}
