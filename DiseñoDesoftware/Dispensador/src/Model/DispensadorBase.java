package Model;

import Interface.ITransaccion;

public abstract class DispensadorBase implements ITransaccion {
    protected ITransaccion nextHandler;
    
    @Override
    public void setNextHandler(ITransaccion next) {
        this.nextHandler = next;
    }
    
    protected void pasarAlSiguiente(int cantidad) {
        if (nextHandler != null) {
            nextHandler.manejarSolicitud(cantidad);
        }
    }
}

