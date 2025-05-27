public class Producto extends Entidad implements Calculable {
    private double precio;
    private int cantidad;

    public Producto(int id, String nombre, double precio, int cantidad) {
        super(id, nombre);
        this.precio = precio;
        this.cantidad = cantidad;
    }

    @Override
    public double calcularCostoTotal() {
        return precio * cantidad;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Producto ID: " + id + ", Nombre: " + nombre + ", Precio: " + precio + ", Cantidad: " + cantidad);
    }

    public int getId() {
        return id;
    }
}
