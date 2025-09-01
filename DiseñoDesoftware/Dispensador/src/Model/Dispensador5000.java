package Model;

public class Dispensador5000 extends DispensadorBase {
    
    @Override
    public void manejarSolicitud(int cantidad) {
        if (cantidad >= 5000) {
            int billetes = cantidad / 5000;
            int restante = cantidad % 5000;
            
            System.out.println("Dispensando " + billetes + " billete(s) de $5,000");
            
            if (restante > 0) {
                System.out.println("Error: Cantidad restante de $" + restante + " no puede ser dispensada.");
            }
        } else if (cantidad > 0) {
            System.out.println("Error: La cantidad mínima debe ser múltiplo de $5,000");
        }
    }
}

