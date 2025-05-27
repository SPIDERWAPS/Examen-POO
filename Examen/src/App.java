import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static final ArrayList<Proveedor> proveedores = new ArrayList<>();
    private static final ArrayList<Producto> productos = new ArrayList<>();
    private static final ArrayList<SolicitudCompra> solicitudes = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero();
            procesarOpcion(opcion);
        } while (opcion != 11);
    }

    private static void mostrarMenu() {
        System.out.println("\n====== SISTEMA DE GESTIÓN DE COMPRAS ERP ======");
        System.out.println("1. Registrar proveedor");
        System.out.println("2. Registrar producto");
        System.out.println("3. Registrar solicitud de compra");
        System.out.println("4. Listar solicitudes");
        System.out.println("5. Aprobar/Rechazar solicitud");
        System.out.println("6. Listar proveedores");
        System.out.println("7. Listar productos");
        System.out.println("8. Buscar proveedor por ID");
        System.out.println("9. Buscar solicitud por número");
        System.out.println("10. Calcular total de una solicitud");
        System.out.println("11. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                registrarProveedor();
                break;
            case 2:
                registrarProducto();
                break;
            case 3:
                registrarSolicitud();
                break;
            case 4:
                listarSolicitudes();
                break;
            case 5:
                gestionarSolicitud();
                break;
            case 6:
                listarProveedores();
                break;
            case 7:
                listarProductos();
                break;
            case 8:
                buscarProveedorPorId();
                break;
            case 9:
                buscarSolicitudPorNumero();
                break;
            case 10:
                calcularTotalSolicitud();
                break;
            case 11:
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void registrarProveedor() {
        System.out.print("Ingrese ID del proveedor: ");
        int id = leerEntero();
        scanner.nextLine();
        System.out.print("Ingrese nombre del proveedor: ");
        String nombre = scanner.nextLine();
        proveedores.add(new Proveedor(id, nombre));
        System.out.println("Proveedor registrado.");
    }

    private static void registrarProducto() {
        System.out.print("Ingrese ID del producto: ");
        int id = leerEntero();
        scanner.nextLine();
        System.out.print("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese precio del producto: ");
        double precio = leerDouble();
        System.out.print("Ingrese cantidad: ");
        int cantidad = leerEntero();
        productos.add(new Producto(id, nombre, precio, cantidad));
        System.out.println("Producto registrado.");
    }

    private static void registrarSolicitud() {
        System.out.print("Ingrese número de solicitud: ");
        int numero = leerEntero();
        System.out.print("Ingrese ID del producto: ");
        int idProducto = leerEntero();
        Producto producto = productos.stream().filter(p -> p.getId() == idProducto).findFirst().orElse(null);
        if (producto != null) {
            solicitudes.add(new SolicitudCompra(numero, producto));
            System.out.println("Solicitud registrada.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void listarSolicitudes() {
        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes registradas.");
        } else {
            for (SolicitudCompra s : solicitudes) {
                s.mostrarInfo();
            }
        }
    }

    private static void gestionarSolicitud() {
        System.out.print("Ingrese número de solicitud: ");
        int numero = leerEntero();
        SolicitudCompra solicitud = solicitudes.stream().filter(s -> s.getNumero() == numero).findFirst().orElse(null);
        if (solicitud != null) {
            System.out.println("1. Aprobar  2. Rechazar");
            int opcion = leerEntero();
            if (opcion == 1) {
                solicitud.aprobar();
                System.out.println("Solicitud aprobada.");
            } else if (opcion == 2) {
                solicitud.rechazar();
                System.out.println("Solicitud rechazada.");
            } else {
                System.out.println("Opción inválida.");
            }
        } else {
            System.out.println("Solicitud no encontrada.");
        }
    }

    private static void listarProveedores() {
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            for (Proveedor p : proveedores) {
                p.mostrarInfo();
            }
        }
    }

    private static void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            for (Producto p : productos) {
                p.mostrarInfo();
            }
        }
    }

    private static void buscarProveedorPorId() {
        System.out.print("Ingrese ID del proveedor: ");
        int id = leerEntero();
        Proveedor proveedor = proveedores.stream().filter(p -> p.id == id).findFirst().orElse(null);
        if (proveedor != null) {
            proveedor.mostrarInfo();
        } else {
            System.out.println("Proveedor no encontrado.");
        }
    }

    private static void buscarSolicitudPorNumero() {
        System.out.print("Ingrese número de solicitud: ");
        int numero = leerEntero();
        SolicitudCompra solicitud = solicitudes.stream().filter(s -> s.getNumero() == numero).findFirst().orElse(null);
        if (solicitud != null) {
            solicitud.mostrarInfo();
        } else {
            System.out.println("Solicitud no encontrada.");
        }
    }

    private static void calcularTotalSolicitud() {
        System.out.print("Ingrese número de solicitud: ");
        int numero = leerEntero();
        SolicitudCompra solicitud = solicitudes.stream().filter(s -> s.getNumero() == numero).findFirst().orElse(null);
        if (solicitud != null) {
            System.out.println("Total: $" + solicitud.calcularCostoTotal());
        } else {
            System.out.println("Solicitud no encontrada.");
        }
    }

    private static int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor ingrese un número válido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double leerDouble() {
        while (true) {
            String entrada = scanner.next().replace(",", ".");
            try {
                double valor = Double.parseDouble(entrada);
                scanner.nextLine();
                return valor;
            } catch (NumberFormatException e) {
                scanner.nextLine();
                System.out.print("Ingrese un número válido: ");
            }
        }
    }
}
