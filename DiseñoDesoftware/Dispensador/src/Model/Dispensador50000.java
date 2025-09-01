package Model;

public class Dispensador50000 extends DispensadorBase {
    
    @Override
    public void manejarSolicitud(int cantidad) {
        if (cantidad >= 50000) {
            int billetes = cantidad / 50000;
            int restante = cantidad % 50000;
            
            System.out.println("Dispensando " + billetes + " billete(s) de $50,000");
            
            if (restante > 0) {
                pasarAlSiguiente(restante);
            }
        } else {
            pasarAlSiguiente(cantidad);
        }
    }
}

