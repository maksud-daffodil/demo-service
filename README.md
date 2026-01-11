# Demo Service

### by Farhan Tanvir

## Docker Instruction
### build docker image
```shell
sudo docker build -f Dockerfile -t demo-service .
```

### run docker container in Dev Server
```shell
sudo docker run --name institute-service -d -p 5004:5004 demo-service
```

### tagging a docker image
```shell
sudo docker tag demo-service:latest daffodilsoftwaresection/demo-service:1.0.0
```

### upload docker image to docker hub
```shell
sudo docker push daffodilsoftwaresection/demo-service:1.0.0
```

### run docker container Prod Server
```shell
sudo docker run --name demo-service  -d -p 6004:6004 daffodilsoftwaresection/demo-service:1.0.0
```

### To delete all containers including its volumes use,

```shell
docker rm -vf $(docker ps -aq)
```

### To delete all the images,

```shell
sudo docker rmi -f $(sudo docker images -aq)
```
