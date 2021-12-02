resource "aws_s3_bucket" "bucket-s3-app" {
  bucket = "image-manager-app-file"
}


resource "aws_s3_bucket_object" "bucket-s3-app-war" {
  bucket = aws_s3_bucket.bucket-s3-app.id
  key    = "beanstalk/ImageManager_v1.war"
  source = "files/ImageManager_v1.war"
}
