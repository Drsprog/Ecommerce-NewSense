# Ecommerce-NewSense

![Ecommerce-NewSense](https://placehold.co/600x400/EEE/31343C?font=open-sans&text=NewSense)

**Ecommerce-NewSense** es una aplicación web de comercio electrónico full-stack desarrollada con **Angular 19** actualizado con paradigmas de la versión 20, **Spring Boot (Java 21)** y **MongoDB**, usando **Node.js 20** para el frontend.  
Permite a los usuarios registrarse, explorar productos, agregarlos al carrito y realizar compras, todo con un frontend moderno y un backend seguro y escalable.

---

## 🛠 Tecnologías utilizadas

![Java](https://img.shields.io/badge/Java-21-007396?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-6DB33F?logo=spring&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-20-DD0031?logo=angular&logoColor=white)
![Node.js](https://img.shields.io/badge/Node.js-20-339933?logo=node.js&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-6.0-47A248?logo=mongodb&logoColor=white)
![TailwindCSS](https://img.shields.io/badge/TailwindCSS-4.1-38BDF8?logo=tailwindcss&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-auth-orange?logo=json-web-tokens&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-24.0-2496ED?logo=docker&logoColor=white)

---

## 🚀 Funcionalidades principales

- Registro y autenticación de usuarios con **JWT**
- Gestión de productos (Crear, Leer, Actualizar, Eliminar)
- Filtrado y ordenamiento de productos por categoría y precio
- Carrito de compras interactivo
- Frontend moderno y responsivo usando **Angular 19 + TailwindCSS**

---

## 📁 Estructura del Proyecto

El repositorio está dividido en dos partes principales:
- `/backend/backend/`: Contiene la API REST desarrollada en Spring Boot y la configuración de Docker para la base de datos.
- `/frontend/`: Contiene la aplicación web cliente desarrollada en Angular.

---

## 🏗 Instalación y ejecución

### Requisitos previos

- **Java 21**  
- **Node.js 20** y **npm**
- **Maven**
- **Docker y Docker Compose** (Recomendado para levantar la base de datos fácilmente) o **MongoDB** instalado localmente.

---

### 1. Clonar el repositorio
```bash
git clone https://github.com/Drsprog/Ecommerce-NewSense.git
cd Ecommerce-NewSense
```

### 2. Levantar la Base de Datos (Recomendado con Docker)
El proyecto incluye un archivo `docker-compose.yml` que levanta MongoDB y Mongo-Express (panel de administración web).

```bash
cd backend/backend
docker-compose up -d
```
*Nota: Mongo-Express estará disponible en `http://localhost:8081`.*

### 3. Configurar y ejecutar el Backend (Spring Boot)

Dentro de la carpeta `backend/backend`:

1. **Configurar variables de entorno:**
   Copia el archivo de plantilla `.env.template` y renómbralo a `.env`.
   ```bash
   cp .env.template .env
   ```
   Asegúrate de llenarlo con las credenciales correspondientes. Si usas el `docker-compose` incluido, el archivo `.env` debería verse así:
   ```env
   MONGO_URL=mongodb://admin:admin123@localhost:27017/ecommerce?authSource=admin
   MONGO_DB_NAME=ecommerce
   ```

2. **Instalar dependencias y compilar:**
   ```bash
   mvn clean install
   ```

3. **Ejecutar el backend:**
   ```bash
   mvn spring-boot:run
   ```
   *El servidor backend estará corriendo por defecto en el puerto 8080.*

### 4. Configurar y ejecutar el Frontend (Angular)

Abre una nueva terminal y navega a la carpeta del frontend (desde la raíz del proyecto):

```bash
cd frontend
```

1. **Instalar dependencias:**
   ```bash
   npm install
   ```

2. **Ejecutar la aplicación:**
   ```bash
   ng serve
   ```
   *El frontend estará disponible en `http://localhost:4200`.*
