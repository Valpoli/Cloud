FROM nginx:stable-alpine

# Définir le répertoire de travail dans le conteneur
WORKDIR /usr/share/nginx/html

# Copier le fichier dist
COPY ./dist/ ./

# exposing of our port app
EXPOSE 80

# call npm to run the project
CMD ["nginx", "-g", "daemon off;"]
