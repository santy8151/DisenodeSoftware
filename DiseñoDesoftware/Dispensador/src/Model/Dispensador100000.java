package Model;

public class Dispensador100000 extends DispensadorBase {
    
    @Override
    public void manejarSolicitud(int cantidad) {
        if (cantidad >= 100000) {
            int billetes = cantidad / 100000;
            int restante = cantidad % 100000;
            
            System.out.println("Dispensando " + billetes + " billete(s) de $100,000");
            
            if (restante > 0) {
                pasarAlSiguiente(restante);
            }
        } else {
            pasarAlSiguiente(cantidad);
        }
    }

}
