package calculadoraclient;

import com.calculadora.soap.Calculator;
import com.calculadora.soap.CalculatorSoap;

import java.net.URL;
import java.util.Scanner;
import javax.xml.namespace.QName;

public class CalculadoraClient {

    public static void main(String[] args) {
        try {
            // URL del servicio WSDL
            URL url = new URL("http://www.dneonline.com/calculator.asmx?WSDL");

            // Espacio de nombres y nombre del servicio
            QName qname = new QName("http://tempuri.org/", "Calculator");

            // Crear el servicio
            Calculator service = new Calculator(url, qname);

            // Obtener el puerto del servicio
            CalculatorSoap calculadora = service.getCalculatorSoap();

            // Escáner para leer entrada del usuario
            Scanner scanner = new Scanner(System.in);

            // Menú interactivo
            while (true) {
                System.out.println("\n--- Calculadora SOAP ---");
                System.out.println("1. Sumar");
                System.out.println("2. Restar");
                System.out.println("3. Multiplicar");
                System.out.println("4. Dividir");
                System.out.println("5. Salir");
                System.out.print("Elige una opción: ");
                int opcion = scanner.nextInt();

                if (opcion == 5) {
                    System.out.println("Saliendo...");
                    break;
                }

                // Pedir al usuario los dos números
                System.out.print("Introduce el primer número: ");
                int numero1 = scanner.nextInt();
                System.out.print("Introduce el segundo número: ");
                int numero2 = scanner.nextInt();

                // Variable para el resultado
                int resultado = 0;

                switch (opcion) {
                    case 1: // Sumar
                        resultado = calculadora.add(numero1, numero2);
                        System.out.println("Resultado de la suma: " + resultado);
                        break;
                    case 2: // Restar
                        resultado = calculadora.subtract(numero1, numero2);
                        System.out.println("Resultado de la resta: " + resultado);
                        break;
                    case 3: // Multiplicar
                        resultado = calculadora.multiply(numero1, numero2);
                        System.out.println("Resultado de la multiplicación: " + resultado);
                        break;
                    case 4: // Dividir
                        if (numero2 != 0) {
                            resultado = calculadora.divide(numero1, numero2);
                            System.out.println("Resultado de la división: " + resultado);
                        } else {
                            System.out.println("Error: No se puede dividir entre cero.");
                        }
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elige una opción entre 1 y 5.");
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
