# ── Etapa 1: compilar el frontend con Node ────────────────────────────────────
FROM node:20-alpine AS builder
WORKDIR /app

# VITE_BACKEND_URL se incrusta en el bundle en build-time (no runtime)
ARG VITE_BACKEND_URL=http://localhost:8080
ENV VITE_BACKEND_URL=$VITE_BACKEND_URL

COPY package.json .
RUN npm install
COPY . .
RUN npm run build

# ── Etapa 2: servir con nginx ─────────────────────────────────────────────────
FROM nginx:stable-alpine
COPY --from=builder /app/dist /usr/share/nginx/html

# Configuración de nginx para SPA: redirige rutas desconocidas a index.html
RUN printf 'server {\n\
    listen 80;\n\
    root /usr/share/nginx/html;\n\
    index index.html;\n\
    location / {\n\
        try_files $uri $uri/ /index.html;\n\
    }\n\
}\n' > /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
