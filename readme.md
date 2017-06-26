Clone the project:
git clone https://gitlab.com/soa-labs/task6

Build the projects task6-app1, task6-app2, task6-eureka, task6-zuul, task6-gateway and create the docker images (change each pom.xml file and edit the docker_hub_user and change docker-compose.yml):
mvn package docker:build

Start containers defined in docker-compose:
docker-compose up -d

Ensure containers are running:
docker ps

Test the Eureka service registry:
curl http://localhost/my-eureka

Test the application:
curl http://localhost/my-app1/greeting
curl http://localhost/my-app2/greeting

Test Zuul:
curl http://localhost/my-zuul/my-app1/greeting
curl http://localhost/my-zuul/my-app2/greeting
curl http://localhost/my-zuul/my-gateway/greeting

Test the API gateway:
curl http://localhost/my-gateway/greeting

Scale application with 3 instances:
docker-compose scale my-app=3

Check Traefik web-panel:
http://135026-app.dck.mojserver.mk:8080

Downscale application with 1 instance:
docker-compose scale my-app=1

Shutdown containers:
docker-compose down
