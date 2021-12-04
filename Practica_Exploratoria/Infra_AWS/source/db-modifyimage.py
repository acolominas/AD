import json
import boto3
import os


from boto3.dynamodb.conditions import Attr

dynamodb = boto3.resource('dynamodb')
s3 = boto3.client('s3')
table_name = os.environ['TABLE_NAME']
bucketS3 = os.environ['BUCKET_S3']
region = os.environ['AWS_REGION']


def store_image_s3(image,filename,identifier):
    extension = filename.split('.')[1]
    new_filename = identifier + '.' + extension
    file_content = base64.b64decode(image)
    s3.put_object(Bucket=bucketS3, Key=new_filename, Body=file_content,ContentType='image/'+extension, ACL='public-read')
    return new_filename, None

def delete_image_s3(id):
    try:
        res = table.scan(
            FilterExpression=Attr('id').eq(id)
        )
        image = res['Items'][0]
        filename = image['filename']
        resp = s3.delete_object(Bucket=bucketS3, Key=filename)
    except Exception as e:
        return None, str(e)
    return resp, None

def store_image_dynamodb(id,title,description,keywords,author,creator,capture_date,storage_date,filename):
    table = dynamodb.Table(table_name)
    table.put_item(
        Item={
            'id': id,
            'title': title,
            'description': description,
            'keywords': keywords,
            'author': author,
            'creator': creator,
            'capture_date': capture_date,
            'storage_date': storage_date,
            'filename': filename,
            'object_url': f'https://{bucketS3}.s3.{region}.amazonaws.com/{filename}'
        }
    )
    return "OK", None

def lambda_handler(event, context):
    image = event['image']

    resp = "OK";

    for field in ["id","title", "description", "keywords","author","creator","capture_date","storage_date","filename"]:
        if not event.get(field):
            return {
                'status': 'fail',
                'msg': f"{field} is not present"
            }

        id    = event['id']
        title = event['title']
        description= event['description']
        keywords= event['keywords']
        author= event['author']
        creator = event['creator']
        storage_date = event['storage_date']
        capture_date = event['capture_date']
        filename = event['filename']

    if event['image_content'] != "":
        resp, msg = delete_image_s3(id)
            if resp != None:
                filename, msg = store_image_s3(image,filename,id)

    if resp!= None:
        resp, msg = store_image_dynamodb(id,title,description,keywords,author,creator,capture_date,storage_date,filename)

    response = {
      'statusCode': 200,
      'body': json.dumps(response),
      'headers': {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      },
    }

    return response
