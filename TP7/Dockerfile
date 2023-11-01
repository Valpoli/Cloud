FROM nginx:stable-alpine
# Installer Node.js et npm
RUN apk add --update nodejs npm
# Définir le répertoire de travail dans le conteneur
WORKDIR /usr/share/nginx/html

COPY . ./
# Installer les dépendances du projet
RUN npm install
# build le projet
RUN npm run build
# our app is running on port 8082 within the container, so need to expose it
EXPOSE 80
# # call npm to run the project
CMD ["/bin/sh", "-c", "find . -maxdepth 1 ! -name '.' ! -name 'dist' -exec rm -rf {} + && cp -r dist/* ../html/ && rm -rf dist/ && nginx -g 'daemon off;'"]
