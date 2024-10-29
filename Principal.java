import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Principal {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/30bac7951c50dba46da20aa1/latest/";

    public static double getConversionRate(String fromCurrency, String toCurrency) throws Exception {
        String urlString = API_URL + fromCurrency;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);

        if (jsonResponse.has("conversion_rates") && !jsonResponse.get("conversion_rates").isJsonNull()) {
            JsonObject rates = jsonResponse.getAsJsonObject("conversion_rates");
            if (rates.has(toCurrency)) {
                return rates.get(toCurrency).getAsDouble();
            } else {
                throw new Exception("La moneda de destino no está disponible.");
            }
        } else {
            throw new Exception("No se pudo obtener la lista de tasas de cambio.");
        }
    }

    public static double convertCurrency(String fromCurrency, String toCurrency, double amount) throws Exception {
        double rate = getConversionRate(fromCurrency, toCurrency);
        return amount * rate;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Mostrar opciones de monedas de origen
            System.out.println("Seleccione la moneda de origen (0 para salir):");
            System.out.println("1. USD");
            System.out.println("2. EUR");
            System.out.println("3. GBP");
            System.out.println("4. JPY");
            System.out.println("5. DOP");
            int fromOption = scanner.nextInt();

            if (fromOption == 0) {
                System.out.println("Saliendo del programa...");
                break; // Termina el programa si el usuario selecciona 0
            }

            String fromCurrency;
            switch (fromOption) {
                case 1 -> fromCurrency = "USD";
                case 2 -> fromCurrency = "EUR";
                case 3 -> fromCurrency = "GBP";
                case 4 -> fromCurrency = "JPY";
                case 5 -> fromCurrency = "DOP";
                default -> {
                    System.out.println("Opción inválida.");
                    continue;
                }
            }
            // Mostrar opciones de monedas de destino
            System.out.println("Seleccione la moneda de destino (0 para salir):");
            System.out.println("1. USD");
            System.out.println("2. EUR");
            System.out.println("3. GBP");
            System.out.println("4. JPY");
            System.out.println("5. DOP");
            int toOption = scanner.nextInt();

            if (toOption == 0) {
                System.out.println("Saliendo del programa...");
                break; // Termina el programa si el usuario selecciona 0
            }

            String toCurrency;
            switch (toOption) {
                case 1 -> toCurrency = "USD";
                case 2 -> toCurrency = "EUR";
                case 3 -> toCurrency = "GBP";
                case 4 -> toCurrency = "JPY";
                case 5 -> toCurrency = "DOP";
                default -> {
                    System.out.println("Opción inválida.");
                    continue;
                }
            }

            // Verificar que las monedas de origen y destino sean diferentes
            if (fromCurrency.equals(toCurrency)) {
                System.out.println("La moneda de origen y destino no pueden ser iguales.");
                continue;
            }

            // Pedir la cantidad a convertir
            System.out.print("Ingrese la cantidad a convertir: ");
            double amount = scanner.nextDouble();

            // Realizar la conversión
            try {
                double convertedAmount = convertCurrency(fromCurrency, toCurrency, amount);
                System.out.printf("%.2f %s son equivalentes a %.2f %s%n", amount, fromCurrency, convertedAmount, toCurrency);
            } catch (Exception e) {
                System.err.println("Error al realizar la conversión de moneda: " + e.getMessage());
            }
        }
        scanner.close();
    }
}