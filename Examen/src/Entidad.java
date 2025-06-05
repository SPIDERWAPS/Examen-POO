public abstract class Entidad {
    protected int id;
    protected String nombre;

    public Entidad(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public abstract void mostrarInfo();
}
