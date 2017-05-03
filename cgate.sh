#!/bin/bash

cd task6-gateway
mvn package docker:build

cd ..
docker-compose up -d

