class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(int id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void mostrarInfo() {
        System.out.println("\nID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.printf("Precio: $%.2f%n", precio);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("----------------------------");
    }
}
