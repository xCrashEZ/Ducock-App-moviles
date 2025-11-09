# Booksy SPA – Publicación Digital

## 1. Caso elegido y alcance

**¿De qué se trata?**
Booksy es una app para leer y descargar libros digitales de autores independientes. El catálogo crece siempre y la idea es que sea fácil de usar y segura para las descargas.

**Entidades:** Libro, Autor, Categoría, Cliente, Pedido/Descarga.

**Alcance (lo que se ve en la app):**

1. Catálogo: mirar por categorías, buscar y ver detalle (autor, sinopsis, disponibilidad).
2. Cuenta: registrarse/iniciar sesión y ver perfil con foto.
3. Pedidos/Descargas: pedir/descargar y ver el historial.
4. Estados claros: se ve cuando carga, cuando sale bien y cuando hay error.
   - UI simple y ordenada.
   - Validaciones básicas en formularios.
   - Navegación con backstack correcto.
   - Persistencia local (favoritos/recientes) y guardar avatar.
   - Recursos nativos: cámara/galería (con permisos).
   - Animaciones suaves para dar feedback.
   - Consumo de API (incluye `/auth/me`).

* **Caso:** Booksy SPA
* **Alcance EP3:** Diseño/UI, validaciones, navegación, estado, persistencia, recursos nativos, animaciones y consumo de API.

---

## 2. Requisitos y ejecución

* **Stack:** *Android Studio, Kotlin, Gradle, Jetpack (Compose, Navigation), Room, DataStore, Retrofit/OkHttp, Coil.*
* **Instalación:** *Clona el repo y ábrelo en Android Studio. Sincroniza Gradle. Configura la Base URL del backend (por variable local/BuildConfig).*
* **Ejecución:** *Emulador o dispositivo físico (API 28+).*

---

## 3. Arquitectura y flujo

* **Estructura carpetas:** *(definida en el proyecto; sin detallar MVVM ni implementaciones)*
* **Gestión de estado:** pantallas con Idle/Loading/Success/Error y mensajes entendibles.
* **Navegación:** bienvenida/auth → catálogo → detalle → pedidos/descargas → perfil (volver atrás funciona bien).

---

## 4. Funcionalidades

- Formulario validado (registro/login).
- Navegación y backstack.
- Gestión de estado (carga/éxito/error).
- **Persistencia local** (CRUD básico) y **avatar** guardado.
- **Recursos nativos:** cámara/galería con permisos y alternativa.
- **Animaciones** con propósito.
- **Consumo de API** (incluye `/me`/`/auth/me`).

---

## 5. Endpoints

**Base URL:** `https://x8ki-letl-twmt.n7.xano.io/api:Rfm_61dW`

| Método | Ruta         | Body                        | Respuesta (ejemplo)                       |
| -----: | ------------ | --------------------------- | ----------------------------------------- |
|   POST | /auth/signup | { email, password, name? }  | 201 { authToken, user: { id, email, … } } |
|   POST | /auth/login  | { email, password }         | 200 { authToken, user: { id, email, … } } |
|    GET | /auth/me     | – (Authorization: Bearer …) | 200 { id, email, name, avatarUrl?, … }    |


## 6. User flows

- **Registro/Login:** abrir → llenar datos → valida → API → guarda token → va a Catálogo.
- **Catálogo:** buscar/filtrar → ver Detalle → acciones (favorito/pedido/descarga).
- **Pedido/Descarga:** confirmar → feedback → ver en “Mis descargas/pedidos”.
- **Perfil:** editar datos → elegir foto (cámara/galería) → guardar.
- **Offline/Errores:** se muestra el último catálogo sin internet; si falla la red, hay mensaje y reintento. 
