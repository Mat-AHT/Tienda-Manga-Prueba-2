Este proyecto consiste en una API desarrollada con Spring Boot para la gestión integral de una tienda de manga. El sistema permite administrar el inventario, los catálogos de mangas, el registro de usuarios, el procesamiento de pagos y la gestión de reseñas de clientes.

Se destaca la integración de herramientas de trabajo colaborativo y el control de versiones mediante Git. El desarrollo respeta la estructura del patrón CSR , garantizando respuestas REST estandarizadas y correctas.

Modelado Inicial: El diseño original de las tablas y sus relaciones (modelo relacional) se realizó utilizando las herramientas de Oracle Database.

Migración a MySQL: Debido a los requerimientos del entorno local, el DDL generado en Oracle fue adaptado y transformado exitosamente para su ejecución en MySQL.

El proyecto se construyó bajo un enfoque de desarrollo distribuido y colaborativo. Nuestro primer paso fue definir en conjunto la entidad principal (Manga), acordando sus atributos y relaciones. A partir de ahí, dividimos el trabajo para que cada integrante desarrollara el flujo de sus respectivos módulos:

Mateo: Desarrollo de los módulos Género, Inventario y Origen.

Raúl: Desarrollo de los módulos Autor, Carrito y Demografía.

Jean: Desarrollo de los módulos Pago, Reseña y Usuario.

Junto con el código, uno de los obstáculos más grandes que tuvimos fue el uso de Git y GitHub. Al principio no sabíamos cómo se utilizaba ni cómo conectar nuestros repositorios. Nos costó prácticamente un día entero lograr que nos funcionara a los tres y entender cómo juntar el trabajo de todos sin perder nuestros avances.

El otro gran desafío como equipo fue adaptar todo el código de la base de datos que armamos en Oracle para que funcionara en MySQL. Cuando por fin logramos levantar la base de datos, Raúl se encargó de probar todas las rutas en Postman. Al mismo tiempo, Mateo y Jean estuvimos con él revisando por qué saltaban algunos errores y arreglándolos juntos, para asegurarnos que todo nos salga bien.
