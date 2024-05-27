# Usa una imagen base con Node.js para compilar y servir la aplicación Angular
FROM node:latest as build-stage

# Configura el directorio de trabajo
WORKDIR /app

# Copia los archivos del frontend al directorio de trabajo
COPY . .

# Instala las dependencias y compila la aplicación Angular
RUN npm install
RUN npm run build

# Configura la imagen base para servir la aplicación Angular compilada
FROM nginx:latest
COPY --from=build-stage /app/dist/paseo-front-end /usr/share/nginx/html
