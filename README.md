# Examen-POO
Integrantes: Alexis Rodriguez, Juan Urdiales 

Descripción general del proyecto:
El sistema de Gestión de Compras ERP permite administrar de manera eficiente los siguientes elementos:
Proveedores: incluye el registro y la consulta de información.
Productos: permite registrar, listar y buscar productos.
Solicitudes de compra: contempla el registro de solicitudes, su aprobación o rechazo, y el cálculo automático de costos.

Características principales:

-Persistencia de datos: la información se almacena en archivos de texto (.txt), específicamente en los archivos proveedores.txt, productos.txt y solicitudes.txt.
-Validaciones: el sistema previene la duplicación de identificadores, evita campos vacíos y rechaza valores inválidos.
-Interfaz intuitiva: dispone de un menú interactivo con mensajes claros para facilitar su uso.
-Instrucciones para ejecutar el sistema

Requisitos:
Tener instalado Java JDK 8 o una versión superior.
Disponer de una terminal o consola de comandos.

Pasos:
-Guardar el código fuente en un archivo denominado App.java.

-Abrir una terminal en la carpeta donde se encuentra el archivo y compilarlo con el comando:
javac App.java

-Ejecutar el programa con el comando:
java App

Uso del sistema:
A través del menú principal, el usuario puede seleccionar entre las opciones disponibles (del 1 al 11).

Ejemplo de flujo de uso:

Registrar un proveedor ingresando su ID y nombre.

Registrar un producto ingresando ID, nombre, precio y cantidad.

Crear una solicitud de compra asociando un producto existente.

Aprobar o rechazar solicitudes de compra.

Datos persistentes:

Los archivos .txt se crean automáticamente en la misma carpeta del programa.

No se recomienda editar manualmente los archivos para evitar errores de lectura o corrupción de datos.

Notas adicionales:

En caso de errores en la lectura de datos, verificar que los archivos .txt mantengan el formato adecuado.

Si se elimina un producto vinculado a solicitudes previas, estas podrían generar errores al ser cargadas nuevamente.

Extensibilidad:

Es posible modificar las clases Proveedor, Producto y SolicitudCompra para incluir nuevos campos como categorías, fechas u otros datos adicionales.

El sistema ha sido diseñado para ser claro, práctico y adaptable a nuevas funcionalidades.








