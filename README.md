# Sistema de Gestión de Pacientes y Citas Médicas

Este proyecto es una aplicación de consola en **Java** que permite gestionar la información de pacientes y sus citas médicas. El programa persiste la información de pacientes y citas en archivos de texto plano, y utiliza **JOptionPane** para la interacción con el usuario.

## Características

- **Registro de pacientes**: Se pueden registrar nuevos pacientes con información básica.
- **Actualización de pacientes**: Los datos de los pacientes registrados se pueden actualizar.
- **Registro de citas**: Se pueden registrar citas médicas asociadas a pacientes existentes.
- **Eliminación de citas**: Se pueden eliminar citas existentes.
- **Visualización de pacientes**: Se puede listar todos los pacientes registrados.
- **Visualización de citas**: Se puede ver la lista de citas de un paciente específico.

## Estructura del Proyecto

El proyecto sigue una estructura de **arquitectura limpia**, dividida en las siguientes capas:

1. **Domain (Dominio)**:
    - Contiene las clases principales que representan las entidades del sistema (`Paciente`, `Cita`).

2. **Aplication (Aplicación)**:
    - Implementa los servicios que gestionan la lógica de negocio para pacientes y citas (`PacienteService`, `CitaService`).

3. **Infrastructure (Infraestructura)**:
    - Contiene la lógica para la persistencia de datos en archivos (`PacienteRepository`, `CitaRepository`).
    - Manejo de IDs persistentes a través de `IDManager`.

4. **Interfaces (Interfaz)**:
    - La clase `Main` que contiene el menú principal del programa, utilizando `JOptionPane` para la interacción con el usuario.

## Requisitos del Sistema

- **Java 8** o superior.
- **IntelliJ IDEA** (opcional pero recomendado).
- Herramientas de compilación como **Maven** o **Gradle** si es necesario agregar más dependencias (no se requieren en este proyecto básico).

