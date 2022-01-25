#!/bin/bash

echo "[*] Create SQS"
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name create-user | cat -
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name update-user | cat -
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name delete-user | cat -
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name create-article | cat -
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name update-article | cat -
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name delete-article | cat -

echo "[*] Create SNS"
aws --endpoint-url=http://localhost:4566 sns create-topic --name CreateUser | cat -
aws --endpoint-url=http://localhost:4566 sns create-topic --name UpdateUser | cat -
aws --endpoint-url=http://localhost:4566 sns create-topic --name DeleteUser | cat -
aws --endpoint-url=http://localhost:4566 sns create-topic --name CreateArticle | cat -
aws --endpoint-url=http://localhost:4566 sns create-topic --name UpdateArticle | cat -
aws --endpoint-url=http://localhost:4566 sns create-topic --name DeleteArticle | cat -

echo "[*] Connect SQS to SNS"
aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:ap-northeast-2:000000000000:CreateUser --protocol sqs --notification-endpoint http://localhost:4566/000000000000/create-user | cat -
aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:ap-northeast-2:000000000000:UpdateUser --protocol sqs --notification-endpoint http://localhost:4566/000000000000/update-user | cat -
aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:ap-northeast-2:000000000000:DeleteUser --protocol sqs --notification-endpoint http://localhost:4566/000000000000/delete-user | cat -
aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:ap-northeast-2:000000000000:CreateArticle --protocol sqs --notification-endpoint http://localhost:4566/000000000000/create-article | cat -
aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:ap-northeast-2:000000000000:UpdateArticle --protocol sqs --notification-endpoint http://localhost:4566/000000000000/update-article | cat -
aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:ap-northeast-2:000000000000:DeleteArticle --protocol sqs --notification-endpoint http://localhost:4566/000000000000/delete-article | cat -

echo "[*] Configure Localstack Done"


