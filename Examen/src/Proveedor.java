class Proveedor {
    private int id;
    private String nombre;

    public Proveedor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void mostrarInfo() {
        System.out.println("\nID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("----------------------------");
    }
}