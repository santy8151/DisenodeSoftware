package Model;

public class Dispensador10000 extends DispensadorBase {
    
    @Override
    public void manejarSolicitud(int cantidad) {
        if (cantidad >= 10000) {
            int billetes = cantidad / 10000;
            int restante = cantidad % 10000;
            
            System.out.println("Dispensando " + billetes + " billete(s) de $10,000");
            
            if (restante > 0) {
                pasarAlSiguiente(restante);
            }
        } else {
            pasarAlSiguiente(cantidad);
        }
    }
}

