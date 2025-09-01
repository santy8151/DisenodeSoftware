import Service.Cajero;
import Model.Usuario;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Cajero cajero = new Cajero();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== SISTEMA DE CAJERO AUTOMÁTICO ===");
        System.out.println("Denominaciones disponibles: $100,000, $50,000, $20,000, $10,000, $5,000");
        System.out.println("Nota: Solo se permiten cantidades múltiplos de $5,000\n");
        
        while (true) {
            try {
                System.out.print("Ingrese su nombre (o 'salir' para terminar): ");
                String nombre = scanner.nextLine();
                
                if ("salir".equalsIgnoreCase(nombre)) {
                    System.out.println("¡Gracias por usar nuestro cajero!");
                    break;
                }
                
                System.out.print("Ingrese su ID: ");
                String id = scanner.nextLine();
                
                System.out.print("Ingrese la cantidad a retirar: $");
                int cantidad = Integer.parseInt(scanner.nextLine());
                
                Usuario usuario = new Usuario(nombre, id);
                cajero.retirarDinero(usuario, cantidad);
                
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un número válido.\n");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage() + "\n");
            }
        }
        
        scanner.close();
    }
}