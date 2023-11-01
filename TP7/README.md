# Launch the project

## In Docker :

### Build Vue application image
docker build --pull --rm -f "TP7/Dockerfile" -t cloud:latest "TP7"

### Build our springboot image
docker build --pull --rm -f "TP4/cloud/Dockerfile" -t telematics "TP4/cloud" 

### Launch our docker with docker compose
docker compose  -f "TP2/docker-compose.yml" up -d --build nginx springboot

## In local

### in TP7 folder

#### Install dependencies

```sh
npm install
```

#### Compile and Hot-Reload for Development

```sh
npm run dev
```

### in TP4 folder

`./mvnw spring-boot:run`