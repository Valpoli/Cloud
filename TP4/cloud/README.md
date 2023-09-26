##  Commande permettant de construire l'image docker

* docker build -t telematics ../cloud/

## Commande permettant de lancer le serveur REST

* ./mvnw spring-boot:run

## Adresse utilisée par Nifi pour envoyer ses données au service REST

* `http://springboot:8080/telematics`