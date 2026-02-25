FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN if [ -f package-lock.json ]; then npm ci; else npm install; fi
COPY . .
ARG VITE_API_GATEWAY_URL=http://localhost:8080
ARG VITE_USE_API_GATEWAY=true
ENV VITE_API_GATEWAY_URL=$VITE_API_GATEWAY_URL
ENV VITE_USE_API_GATEWAY=$VITE_USE_API_GATEWAY
RUN npm run build

FROM nginx:1.27-alpine
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
