# adrianExchange Conversor de Moneda en Java
Este es un programa de conversión de moneda en Java que consume la API de Exchangerate-API para obtener las tasas de cambio actualizadas. Utiliza la biblioteca Gson para manejar datos JSON y un menú de selección estructurado con `switch-case` para facilitar la elección de monedas. 

## Requisitos

- **Java 12 o superior**: Se utiliza el operador `->` en `switch-case`, una característica introducida en Java 12.
- **Biblioteca Gson**: Para procesar y analizar datos JSON. [Descargar Gson](https://github.com/google/gson).
- **Clave de API de Exchangerate-API**: Regístrate en [Exchangerate-API](https://www.exchangerate-api.com/) para obtener una clave de API.

## Configuración

1. Clona el repositorio o descarga el archivo `CurrencyConverter.java`.
2. Incluye la biblioteca Gson en tu proyecto:
   - Si usas un IDE como IntelliJ o Eclipse, agrega Gson a las dependencias del proyecto.
   - Alternativamente, descarga el archivo `.jar` de Gson y agrégalo al classpath del proyecto.
3. Sustituye `YOUR_API_KEY` en `API_URL` con tu clave de API de Exchangerate-API. La URL debería verse así:
   ```java
   private static final String API_URL = "https://v6.exchangerate-api.com/v6/YOUR_API_KEY/latest/";
## Uso
Ejecuta el programa desde la terminal o tu IDE preferido. El menú interactivo permitirá al usuario:

Seleccionar la moneda de origen (USD, EUR, GBP, JPY).
Seleccionar la moneda de destino (USD, EUR, GBP, JPY).
Ingresar el monto a convertir.
Para salir del programa, selecciona la opción 0 en cualquiera de los menús de moneda.

Ejemplo de ejecución:

Seleccione la moneda de origen (0 para salir):
1. USD
2. EUR
3. GBP
4. JPY
El programa continuará ejecutándose y permitiendo nuevas conversiones hasta que se seleccione 0 en el menú.

## Código
El código realiza lo siguiente:

Obtiene la tasa de cambio a través de la API de Exchangerate-API.
Parsea la respuesta JSON usando Gson para extraer la tasa de conversión de la moneda de origen a la de destino.
Calcula y muestra el monto convertido.
Estructura del switch-case
El programa utiliza el operador -> en switch-case para mejorar la legibilidad. El switch-case permite la selección de monedas, y con el operador -> evita la necesidad de break en cada case, reduciendo errores potenciales. Cada opción (1, 2, 3, 4) representa una moneda específica, mientras que el caso default maneja entradas inválidas mostrando un mensaje de error.

## Manejo de Errores
Si el usuario selecciona la misma moneda como origen y destino, el programa muestra un mensaje de advertencia.
Si hay un error al conectar con la API o al obtener una tasa de cambio, el programa notifica el problema y vuelve al menú de inicio.
Contribuciones
Las contribuciones son bienvenidas. Si encuentras algún error o tienes sugerencias de mejora, abre un issue o envía un pull request.
