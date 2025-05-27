public class SolicitudCompra implements Calculable {
    private int numero;
    private EstadoSolicitud estado;
    private Producto producto;

    public SolicitudCompra(int numero, Producto producto) {
        this.numero = numero;
        this.estado = EstadoSolicitud.SOLICITADA;
        this.producto = producto;
    }

    public int getNumero() {
        return numero;
    }

    public void aprobar() {
        this.estado = EstadoSolicitud.APROBADA;
    }

    public void rechazar() {
        this.estado = EstadoSolicitud.RECHAZADA;
    }

    @Override
    public double calcularCostoTotal() {
        return producto.calcularCostoTotal();
    }

    public void mostrarInfo() {
        System.out.println("Solicitud Nº: " + numero + ", Estado: " + estado);
        producto.mostrarInfo();
    }
}
