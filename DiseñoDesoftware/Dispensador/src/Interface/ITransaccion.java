package Interface;

public interface ITransaccion {
    void setNextHandler(ITransaccion next);
    void manejarSolicitud(int cantidad);
}
