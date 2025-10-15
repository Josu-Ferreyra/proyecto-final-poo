# 📦 Tracker de Pedidos - Sistema de Gestión para Vendedores

![Java](https://img.shields.io/badge/Java-21-blue.svg?style=for-the-badge&logo=openjdk)
![Swing](https://img.shields.io/badge/UI-Java%20Swing-orange.svg?style=for-the-badge&logo=java)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue.svg?style=for-the-badge&logo=mysql)
![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)

Sistema de escritorio desarrollado en Java y Swing para la gestión de clientes, productos y pedidos, diseñado para vendedores independientes y pequeñas empresas. El proyecto sigue la arquitectura **MVC (Modelo-Vista-Controlador)** y se conecta a una base de datos **MySQL**.

## 📋 Tabla de Contenidos

1.  [Problemática](#-problemática-el-desorden-del-vendedor-independiente)
2.  [Solución Propuesta](#-solución-propuesta-una-herramienta-centralizada)
3.  [Funcionalidades Clave](#-funcionalidades-clave)
4.  [Arquitectura del Proyecto](#%EF%B8%8F-arquitectura-del-proyecto)
5.  [Esquema de la Base de Datos](#-esquema-de-la-base-de-datos)
6.  [Stack Tecnológico](#%EF%B8%8F-stack-tecnológico)
7.  [Cómo Empezar](#-cómo-empezar)
8.  [Autor](#%E2%80%8D-autor)

## 🎯 Problemática: El Desorden del Vendedor Independiente

Muchos vendedores independientes o dueños de pequeños negocios gestionan sus ventas de manera informal. Utilizan hojas de cálculo, anotadores físicos o incluso la memoria para llevar un registro de:

* **Clientes:** ¿Quién me compró? ¿Cuál es su contacto?
* **Productos:** ¿Qué productos tengo? ¿A qué precio?
* **Pedidos:** ¿Qué me pidió cada cliente? ¿Ya pagó? ¿Se lo envié? ¿Lo recibió?

Este método es propenso a errores, consume mucho tiempo y dificulta el seguimiento de cada venta, pudiendo llevar a pérdidas económicas y a una mala experiencia para el cliente.

## ✨ Solución Propuesta: Una Herramienta Centralizada

**Tracker de Pedidos** es una aplicación de escritorio que centraliza toda la gestión en un solo lugar. El sistema está diseñado como una plataforma **multi-vendedor**, donde cada usuario registrado puede gestionar su propio ecosistema de negocio sin interferir con los demás.

El objetivo es ofrecer una herramienta intuitiva y robusta que permita a cada vendedor:

* **Organizar** su cartera de clientes.
* **Administrar** su catálogo de productos personal.
* **Rastrear** el ciclo de vida completo de cada pedido, desde su creación hasta la entrega final.

## 🚀 Funcionalidades Clave

El sistema cuenta con dos roles de usuario con permisos bien definidos:

### **Rol Vendedor**
El usuario principal del sistema. Gestiona su propia unidad de negocio.

* ✅ **CRUD de Clientes:** Crear, ver, modificar y eliminar su propia lista de clientes.
* ✅ **CRUD de Productos:** Administrar su catálogo de productos personal. Los productos de un vendedor no son visibles para otros.
* ✅ **Gestión de Pedidos:**
    * Crear nuevos pedidos y asociarlos a un cliente.
    * Agregar múltiples productos a cada pedido.
    * Actualizar el estado del pedido (`En preparación`, `Enviado`, `Pagado`, `Entregado`, etc.).
* ✅ **Dashboard Intuitivo:** Visualizar todos sus pedidos en una tabla con filtros para una búsqueda rápida por estado.

### **Rol Administrador**
El superusuario que gestiona la plataforma.

* ✅ **CRUD de Vendedores:** Crear, ver, modificar y eliminar las cuentas de los vendedores que usarán el sistema.
* ✅ **Supervisión General:** Acceso de solo lectura a **todos los pedidos** de todos los vendedores para fines de auditoría o soporte.

## 🏛️ Arquitectura del Proyecto

El proyecto está construido siguiendo estrictamente el patrón de diseño **MVC (Modelo-Vista-Controlador)** para garantizar una separación clara de responsabilidades, facilitar el mantenimiento y promover la escalabilidad.

* **Modelo (`model`):** Contiene la lógica de negocio.
    * **POJOs:** Clases que representan las entidades (`Usuario`, `Cliente`, `Producto`, etc.).
    * **DAO (Data Access Objects):** Clases que gestionan toda la comunicación con la base de datos MySQL a través de JDBC.
    * **ConexionDB:** Clase Singleton para una gestión eficiente de la conexión a la BD.

* **Vista (`view`):** Compuesta por ventanas y paneles de **Java Swing**. Es la capa de presentación, responsable de mostrar los datos al usuario y capturar sus interacciones. No contiene lógica de negocio.

* **Controlador (`controller`):** Actúa como el intermediario entre el Modelo y la Vista. Recibe las acciones del usuario desde la Vista, procesa la información llamando a los métodos del Modelo y actualiza la Vista con los resultados.

## 💾 Esquema de la Base de Datos

La persistencia de los datos se realiza en una base de datos relacional MySQL. El esquema está diseñado para aislar la información de cada vendedor de forma segura.

```mermaid
erDiagram
    usuarios {
        int id PK
        varchar nombre_usuario
        varchar password
        enum rol
    }
    clientes {
        int id PK
        varchar nombre
        int vendedor_id FK
    }
    productos {
        int id PK
        varchar nombre_producto
        decimal precio
        int vendedor_id FK
    }
    pedidos {
        int id PK
        timestamp fecha_creacion
        varchar estado
        int cliente_id FK
    }
    detalle_pedido {
        int id PK
        int cantidad
        decimal precio_unitario
        int pedido_id FK
        int producto_id FK
    }

    usuarios ||--o{ clientes : "gestiona"
    usuarios ||--o{ productos : "posee"
    clientes ||--o{ pedidos : "realiza"
    pedidos ||--o{ detalle_pedido : "contiene"
    productos ||--o{ detalle_pedido : "es parte de"
````
## 🛠️ Stack Tecnológico

  * **Lenguaje:** Java (OpenJDK 24.0.2)
  * **Interfaz Gráfica:** Java Swing
  * **Base de Datos:** MySQL
  * **Conector:** MySQL Connector/J (JDBC Driver)
  * **IDE de Desarrollo:** JetBrains IntelliJ IDEA

## 🚀 Cómo Empezar

Para ejecutar este proyecto en tu máquina local, sigue estos pasos:

1.  **Prerrequisitos:**

      * Tener instalado [Java JDK](https://www.oracle.com/java/technologies/downloads/) (versión 21 o superior).
      * Tener un servidor de MySQL en ejecución (se recomienda [XAMPP](https://www.apachefriends.org/index.html)).
      * Tener el [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) (`.jar`) descargado.

2.  **Clonar el Repositorio:**

    ```bash
    git clone https://github.com/Josu-Ferreyra/proyecto-final-poo.git
    ```

3.  **Configurar la Base de Datos:**

      * Abre phpMyAdmin (o tu gestor de BD preferido).
      * Crea una nueva base de datos llamada `tracker_pedidos`.
      * Importa y ejecuta el script `database.sql` (que deberías crear y añadir al repo) para crear las tablas y el usuario administrador inicial.

4.  **Configurar el Proyecto en tu IDE:**

      * Abre el proyecto con IntelliJ IDEA (o Eclipse, NetBeans).
      * Agrega el archivo `.jar` del MySQL Connector/J a las librerías del proyecto.
      * Navega al archivo `src/com/tuempresa/model/ConexionDB.java` y asegúrate de que los datos de conexión (URL, usuario, contraseña) coincidan con los de tu servidor MySQL.

5.  **Ejecutar la Aplicación:**

      * Busca el archivo `App.java` que contiene el método `main`.
      * Ejecuta la aplicación. La ventana de login debería aparecer.
      * **Usuario Admin por defecto:** `usuario: admin`, `contraseña: admin` (o la que hayas definido en el script SQL).

## 🧑‍💻 Autor

  * **Josué Ferreyra**
  * **GitHub:** [@Josu-Ferreyra](https://github.com/Josu-Ferreyra)
  * **LinkedIn:** [Josué Ferreyra](https://www.linkedin.com/in/josue-ferreyra/)
