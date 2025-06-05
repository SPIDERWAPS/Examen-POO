import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static ArrayList<Proveedor> proveedores = new ArrayList<>();
    private static ArrayList<Producto> productos = new ArrayList<>();
    private static ArrayList<SolicitudCompra> solicitudes = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final String PROVEEDORES_FILE = "proveedores.txt";
    private static final String PRODUCTOS_FILE = "productos.txt";
    private static final String SOLICITUDES_FILE = "solicitudes.txt";

    public static void main(String[] args) {
        cargarDatos();
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero();
            procesarOpcion(opcion);
        } while (opcion != 11);
    }

    private static void cargarDatos() {
        cargarProveedores();
        cargarProductos();
        cargarSolicitudes();
    }

    private static void cargarProveedores() {
        File archivo = new File(PROVEEDORES_FILE);
        if (!archivo.exists()) {
            System.out.println("Archivo de proveedores no encontrado. Se creará uno nuevo al guardar.");
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    int id = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    proveedores.add(new Proveedor(id, nombre));
                }
            }
            System.out.println("Proveedores cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar proveedores: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en formato de datos: " + e.getMessage());
        }
    }

    private static void cargarProductos() {
        File archivo = new File(PRODUCTOS_FILE);
        if (!archivo.exists()) {
            System.out.println("Archivo de productos no encontrado. Se creará uno nuevo al guardar.");
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    int id = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    double precio = Double.parseDouble(partes[2]);
                    int cantidad = Integer.parseInt(partes[3]);
                    productos.add(new Producto(id, nombre, precio, cantidad));
                }
            }
            System.out.println("Productos cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar productos: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en formato de datos: " + e.getMessage());
        }
    }

    private static void cargarSolicitudes() {
        File archivo = new File(SOLICITUDES_FILE);
        if (!archivo.exists()) {
            System.out.println("Archivo de solicitudes no encontrado. Se creará uno nuevo al guardar.");
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    int numero = Integer.parseInt(partes[0]);
                    int idProducto = Integer.parseInt(partes[1]);
                    String estadoStr = partes[2];
                    
                    Producto producto = buscarProductoPorId(idProducto);
                    if (producto != null) {
                        SolicitudCompra solicitud = new SolicitudCompra(numero, producto);
                        solicitud.setEstado(SolicitudCompra.Estado.valueOf(estadoStr));
                        solicitudes.add(solicitud);
                    } else {
                        System.out.println("Advertencia: Producto con ID " + idProducto + " no encontrado para solicitud " + numero);
                    }
                }
            }
            System.out.println("Solicitudes cargadas correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar solicitudes: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en formato de datos: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en estado de solicitud: " + e.getMessage());
        }
    }

    private static Producto buscarProductoPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private static void guardarProveedores() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PROVEEDORES_FILE))) {
            for (Proveedor p : proveedores) {
                pw.println(p.getId() + ";" + p.getNombre());
            }
            System.out.println("Proveedores guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar proveedores: " + e.getMessage());
        }
    }

    private static void guardarProductos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PRODUCTOS_FILE))) {
            for (Producto p : productos) {
                pw.println(p.getId() + ";" + p.getNombre() + ";" + p.getPrecio() + ";" + p.getCantidad());
            }
            System.out.println("Productos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar productos: " + e.getMessage());
        }
    }

    private static void guardarSolicitudes() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(SOLICITUDES_FILE))) {
            for (SolicitudCompra s : solicitudes) {
                pw.println(s.getNumero() + ";" + s.getProducto().getId() + ";" + s.getEstado().name());
            }
            System.out.println("Solicitudes guardadas correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar solicitudes: " + e.getMessage());
        }
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
                guardarProveedores();
                guardarProductos();
                guardarSolicitudes();
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void registrarProveedor() {
        System.out.print("\n--- REGISTRAR PROVEEDOR ---");
        System.out.print("\nIngrese ID del proveedor: ");
        int id = leerEntero();
        
        // Verificar si el ID ya existe
        for (Proveedor p : proveedores) {
            if (p.getId() == id) {
                System.out.println("Error: Ya existe un proveedor con este ID.");
                return;
            }
        }
        
        System.out.print("Ingrese nombre del proveedor: ");
        String nombre = scanner.nextLine().trim();
        
        if (nombre.isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío.");
            return;
        }
        
        proveedores.add(new Proveedor(id, nombre));
        guardarProveedores();
        System.out.println("Proveedor registrado exitosamente.");
    }

    private static void registrarProducto() {
        System.out.print("\n--- REGISTRAR PRODUCTO ---");
        System.out.print("\nIngrese ID del producto: ");
        int id = leerEntero();
        
        // Verificar si el ID ya existe
        for (Producto p : productos) {
            if (p.getId() == id) {
                System.out.println("Error: Ya existe un producto con este ID.");
                return;
            }
        }
        
        System.out.print("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine().trim();
        
        if (nombre.isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío.");
            return;
        }
        
        System.out.print("Ingrese precio del producto: ");
        double precio = leerDouble();
        
        if (precio <= 0) {
            System.out.println("Error: El precio debe ser mayor que cero.");
            return;
        }
        
        System.out.print("Ingrese cantidad: ");
        int cantidad = leerEntero();
        
        if (cantidad < 0) {
            System.out.println("Error: La cantidad no puede ser negativa.");
            return;
        }
        
        productos.add(new Producto(id, nombre, precio, cantidad));
        guardarProductos();
        System.out.println("Producto registrado exitosamente.");
    }

    private static void registrarSolicitud() {
        if (productos.isEmpty()) {
            System.out.println("Error: No hay productos registrados. Registre productos primero.");
            return;
        }
        
        System.out.print("\n--- REGISTRAR SOLICITUD DE COMPRA ---");
        System.out.print("\nIngrese número de solicitud: ");
        int numero = leerEntero();
        
        // Verificar si el número de solicitud ya existe
        for (SolicitudCompra s : solicitudes) {
            if (s.getNumero() == numero) {
                System.out.println("Error: Ya existe una solicitud con este número.");
                return;
            }
        }
        
        System.out.print("Ingrese ID del producto: ");
        int idProducto = leerEntero();
        
        Producto producto = buscarProductoPorId(idProducto);
        if (producto != null) {
            solicitudes.add(new SolicitudCompra(numero, producto));
            guardarSolicitudes();
            System.out.println("Solicitud registrada exitosamente.");
        } else {
            System.out.println("Error: Producto no encontrado.");
        }
    }

    private static void listarSolicitudes() {
        System.out.println("\n--- LISTADO DE SOLICITUDES ---");
        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes registradas.");
        } else {
            for (SolicitudCompra s : solicitudes) {
                s.mostrarInfo();
            }
        }
    }

    private static void gestionarSolicitud() {
        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes registradas.");
            return;
        }
        
        System.out.print("\n--- GESTIONAR SOLICITUD ---");
        System.out.print("\nIngrese número de solicitud: ");
        int numero = leerEntero();
        
        SolicitudCompra solicitud = null;
        for (SolicitudCompra s : solicitudes) {
            if (s.getNumero() == numero) {
                solicitud = s;
                break;
            }
        }
        
        if (solicitud != null) {
            System.out.println("\nSolicitud encontrada:");
            solicitud.mostrarInfo();
            
            System.out.println("\nSeleccione acción:");
            System.out.println("1. Aprobar");
            System.out.println("2. Rechazar");
            System.out.println("3. Cancelar");
            System.out.print("Opción: ");
            
            int opcion = leerEntero();
            switch (opcion) {
                case 1:
                    solicitud.aprobar();
                    guardarSolicitudes();
                    System.out.println("Solicitud aprobada exitosamente.");
                    break;
                case 2:
                    solicitud.rechazar();
                    guardarSolicitudes();
                    System.out.println("Solicitud rechazada exitosamente.");
                    break;
                case 3:
                    System.out.println("Operación cancelada.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } else {
            System.out.println("Error: Solicitud no encontrada.");
        }
    }

    private static void listarProveedores() {
        System.out.println("\n--- LISTADO DE PROVEEDORES ---");
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            for (Proveedor p : proveedores) {
                p.mostrarInfo();
            }
        }
    }

    private static void listarProductos() {
        System.out.println("\n--- LISTADO DE PRODUCTOS ---");
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            for (Producto p : productos) {
                p.mostrarInfo();
            }
        }
    }

    private static void buscarProveedorPorId() {
        System.out.print("\n--- BUSCAR PROVEEDOR POR ID ---");
        System.out.print("\nIngrese ID del proveedor: ");
        int id = leerEntero();
        
        Proveedor proveedor = null;
        for (Proveedor p : proveedores) {
            if (p.getId() == id) {
                proveedor = p;
                break;
            }
        }
        
        if (proveedor != null) {
            System.out.println("\nProveedor encontrado:");
            proveedor.mostrarInfo();
        } else {
            System.out.println("Error: Proveedor no encontrado.");
        }
    }

    private static void buscarSolicitudPorNumero() {
        System.out.print("\n--- BUSCAR SOLICITUD POR NÚMERO ---");
        System.out.print("\nIngrese número de solicitud: ");
        int numero = leerEntero();
        
        SolicitudCompra solicitud = null;
        for (SolicitudCompra s : solicitudes) {
            if (s.getNumero() == numero) {
                solicitud = s;
                break;
            }
        }
        
        if (solicitud != null) {
            System.out.println("\nSolicitud encontrada:");
            solicitud.mostrarInfo();
        } else {
            System.out.println("Error: Solicitud no encontrada.");
        }
    }

    private static void calcularTotalSolicitud() {
        System.out.print("\n--- CALCULAR TOTAL DE SOLICITUD ---");
        System.out.print("\nIngrese número de solicitud: ");
        int numero = leerEntero();
        
        SolicitudCompra solicitud = null;
        for (SolicitudCompra s : solicitudes) {
            if (s.getNumero() == numero) {
                solicitud = s;
                break;
            }
        }
        
        if (solicitud != null) {
            System.out.printf("\nTotal de la solicitud %d: $%.2f%n", 
                              solicitud.getNumero(), solicitud.calcularCostoTotal());
        } else {
            System.out.println("Error: Solicitud no encontrada.");
        }
    }

    private static int leerEntero() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Por favor ingrese un número entero válido: ");
            }
        }
    }

    private static double leerDouble() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Por favor ingrese un número decimal válido: ");
            }
        }
    }

}
