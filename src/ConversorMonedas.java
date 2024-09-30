import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ConversorMonedas {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/USD";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.##");

        while (true) {
            System.out.println("***********************************************");
            System.out.println("1) Dolar ==> Quetzal Guatemalteco");
            System.out.println("2) Quetzal Guatemalteco ==> Dolar");
            System.out.println("3) Dolar ==> Peso Argentino");
            System.out.println("4) Peso Argentino ==> Dolar");
            System.out.println("5) Dolar ==> Real Brasileño");
            System.out.println("6) Real Brasileño ==> Dolar");
            System.out.println("7) Dolar ==> Peso Colombiano");
            System.out.println("8) Peso Colombiano ==> Dolar");
            System.out.println("9) Salir");
            System.out.println("************************************************");
            System.out.print("Elija una opcion valida: ");

            String opcion = scanner.nextLine();
            if (opcion.equals("9")) {
                break;
            }

            double tasaCambio;
            switch (opcion) {
                case "1":
                    tasaCambio = obtenerTasa("GTQ");
                    System.out.print("Ingrese monto en Dólares: ");
                    double monto7 = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Monto en Quetzales Guatemaltecos: " + df.format(monto7 * tasaCambio));
                    break;
                case "2":
                    tasaCambio = obtenerTasa("GTQ");
                    System.out.print("Ingrese monto en Quetzales Guatemaltecos: ");
                    double monto8 = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Monto en Dólares: " + df.format(monto8 / tasaCambio));
                    break;
                case "3":
                    tasaCambio = obtenerTasa("ARS");
                    System.out.print("Ingrese monto en Dólares: ");
                    double monto1 = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Monto en Pesos Argentinos: " + df.format(monto1 * tasaCambio));
                    break;
                case "4":
                    tasaCambio = obtenerTasa("ARS");
                    System.out.print("Ingrese monto en Pesos Argentinos: ");
                    double monto2 = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Monto en Dólares: " + df.format(monto2 / tasaCambio));
                    break;
                case "5":
                    tasaCambio = obtenerTasa("BRL");
                    System.out.print("Ingrese monto en Dólares: ");
                    double monto3 = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Monto en Reales Brasileños: " + df.format(monto3 * tasaCambio));
                    break;
                case "6":
                    tasaCambio = obtenerTasa("BRL");
                    System.out.print("Ingrese monto en Reales Brasileños: ");
                    double monto4 = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Monto en Dólares: " + df.format(monto4 / tasaCambio));
                    break;
                case "7":
                    tasaCambio = obtenerTasa("COP");
                    System.out.print("Ingrese monto en Dólares: ");
                    double monto5 = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Monto en Pesos Colombianos: " + df.format(monto5 * tasaCambio));
                    break;
                case "8":
                    tasaCambio = obtenerTasa("COP");
                    System.out.print("Ingrese monto en Pesos Colombianos: ");
                    double monto6 = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Monto en Dólares: " + df.format(monto6 / tasaCambio));
                    break;

                default:
                    System.out.println("Por favor, ingrese un numero valido");
            }
        }
        scanner.close();
    }

    private static double obtenerTasa(String moneda) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String jsonResponse = response.toString();
            String tasaStr = jsonResponse.split("\"" + moneda + "\":")[1].split(",")[0];
            return Double.parseDouble(tasaStr);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
