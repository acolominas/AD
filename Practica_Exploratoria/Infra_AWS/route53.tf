resource "aws_route53_zone" "private" {
  name = "image-manager.local"

  comment = "Image Manager Private Zone"

  vpc {
    vpc_id = data.aws_vpc.default.id
  }
}


resource "aws_route53_record" "api-record" {
  zone_id = aws_route53_zone.private.zone_id
  name    = "api.image-manager.local"
  type    = "CNAME"
  ttl     = "300"
  records = ["${aws_api_gateway_rest_api.image-manager-api-gw.id}.execute-api.${data.aws_region.current.name}.amazonaws.com"]
}
