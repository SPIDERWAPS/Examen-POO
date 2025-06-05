class SolicitudCompra {
    public enum Estado {
        PENDIENTE, APROBADA, RECHAZADA
    }

    private int numero;
    private Producto producto;
    private Estado estado;

    public SolicitudCompra(int numero, Producto producto) {
        this.numero = numero;
        this.producto = producto;
        this.estado = Estado.PENDIENTE;
    }

    public int getNumero() {
        return numero;
    }

    public Producto getProducto() {
        return producto;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void aprobar() {
        estado = Estado.APROBADA;
    }

    public void rechazar() {
        estado = Estado.RECHAZADA;
    }

    public double calcularCostoTotal() {
        return producto.getPrecio() * producto.getCantidad();
    }

    public void mostrarInfo() {
        System.out.println("\nNúmero de solicitud: " + numero);
        System.out.println("Estado: " + estado);
        System.out.println("Producto: " + producto.getNombre());
        System.out.printf("Costo total: $%.2f%n", calcularCostoTotal());
        System.out.println("----------------------------");
    }
}