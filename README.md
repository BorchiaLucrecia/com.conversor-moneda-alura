# Convertidor de moneda

¡Bienvenido al Convertidor de Moneda!

Este proyecto te permite convertir monedas de manera rápida y sencilla utilizando las últimas tasas de cambio proporcionadas por ExchangeRate-API.
Desarrollado en el marco de especialización Back-End en java, para el programa ONE de Alura LATAM + Oracle. 
 
## Cómo empezar

**_Requisitos previos:_**
1. Asegúrate de tener Java instalado en tu computadora.
2. Descarga e instala IntelliJ IDEA, entorno de desarrollo recomendado.

**_Configuración del proyecto:_**
1. Clona o descarga este repositorio en tu máquina local.
2. Abre el proyecto en IntelliJ IDEA.

## Funcionamiento

**_Consumo de la API:_**

Se utilizó la API (ExchangeRate-API) de tasas de cambio para obtener los últimos datos de conversión.
Nuestra aplicación utiliza la biblioteca HttpClient para realizar solicitudes a la API.
Se utilizó  la clase HttpRequest para configurar y personalizar las solicitudes.
Se gestiono las respuestas recibidas de la API utilizando la interfaz HttpResponse.

**_Análisis de la respuesta JSON:_**

Se utilizó JsonParser y JsonObject para acceder a las distintas propiedades de la respuesta JSON.

**_Selección de moneda:_**
Se filtraron las monedas utilizando el atributo "Currency Code" del JSON.

Puedes elegir entre una variedad de monedas populares:

**ACTUALIZACIÓN: 03/05**
**Soporte para más monedas: **Se amplía la lista de monedas disponibles para la elección, permitiendo al usuario convertir entre las opciones que brinda la API.

**Peso Argentino (ARS),** **Real Brasileño (BRL),** **Dólar Estadounidense (USD).**

**_Interfaz de usuario:_**

La interfaz textual de usuario es simple y fácil de usar.
Simplemente sigue las instrucciones de la consola para seleccionar las monedas y realizar la conversión.

**ACTUALIZACIÓN: 03/05:**
**Historial de conversiones:**
El usuario podrá ver sus conversiones realizadas.
**Registro con marca de tiempo:** 
Utiliza las funciones de la biblioteca java.time para creae registro de las conversiones, sobré que monedas se convirtieron y en qué momento.
**Archivo**
El usuario tendra una dirección que deberá colocar en su explorador de archivos una vez terminadas las conversiones.
Allí podra visualizar estos dos ultimos items.
## Ejecución del proyecto
1- Ejecuta la clase Main para iniciar la aplicación.    
2- Sigue las instrucciones en la consola para realizar conversiones de moneda.

## Contribuciones y problemas
¡Me encantaría recibir tus contribuciones! Si encuentras algún problema o tienes alguna sugerencia de mejora, no dudes en abrir un problema o enviar una solicitud de extracción en el repositorio.

## ¡Gracias por usar el convertidor de moneda!
Espero que esta aplicación te sea útil y te ayude a realizar conversiones de moneda de manera eficiente. Si tienes alguna pregunta o necesitas ayuda adicional, no dudes en contactarme.

## Creado por:
Git: [Leila Lucrecia Borchia](https://github.com/BorchiaLucrecia).

LinkedInd: [Leila Lucrecia Borchia](https://www.linkedin.com/in/leila-lucrecia-borchia-209322283/).
