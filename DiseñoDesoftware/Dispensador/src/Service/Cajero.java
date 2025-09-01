package Service;

import Interface.ITransaccion;
import Model.*;

public class Cajero {
    private ITransaccion cadenaDispensadores;
    
    public Cajero() {
        configurarCadena();
    }
    
    private void configurarCadena() {
        // Crear todos los dispensadores
        Dispensador100000 disp100k = new Dispensador100000();
        Dispensador50000 disp50k = new Dispensador50000();
        Dispensador20000 disp20k = new Dispensador20000();
        Dispensador10000 disp10k = new Dispensador10000();
        Dispensador5000 disp5k = new Dispensador5000();
        
        // Configurar la cadena de responsabilidad
        disp100k.setNextHandler(disp50k);
        disp50k.setNextHandler(disp20k);
        disp20k.setNextHandler(disp10k);
        disp10k.setNextHandler(disp5k);
        
        // El primer manejador de la cadena
        this.cadenaDispensadores = disp100k;
    }
    
    public void retirarDinero(Usuario usuario, int cantidad) {
        System.out.println("\n=== TRANSACCIÓN INICIADA ===");
        System.out.println("Usuario: " + usuario.getNombre());
        System.out.println("Cantidad solicitada: $" + cantidad);
        
        // Validar que la cantidad sea múltiplo de 5000
        if (cantidad <= 0) {
            System.out.println("Error: La cantidad debe ser mayor a cero.");
            return;
        }
        
        if (cantidad % 5000 != 0) {
            System.out.println("Error: La cantidad debe ser múltiplo de $5,000");
            return;
        }
        
        System.out.println("\nProcesando retiro...");
        cadenaDispensadores.manejarSolicitud(cantidad);
        System.out.println("\n=== TRANSACCIÓN COMPLETADA ===\n");
    }
}

