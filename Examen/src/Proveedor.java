public class Proveedor extends Entidad {
    public Proveedor(int id, String nombre) {
        super(id, nombre);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Proveedor ID: " + id + ", Nombre: " + nombre);
    }
}
