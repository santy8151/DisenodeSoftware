# 🏧 Dispensador de Cajero Automático

> Ejercicio de patrones de diseño — **Chain of Responsibility**  
> Materia: Diseño de Software 

---

## 📌 Descripción

Sistema de cajero automático simulado por consola que implementa el patrón de diseño **Chain of Responsibility (Cadena de Responsabilidad)**. El sistema recibe una cantidad de dinero solicitada por el usuario y la distribuye automáticamente en billetes de las denominaciones disponibles, pasando la solicitud a lo largo de una cadena de dispensadores.

---

## 🎯 Patrón de Diseño Aplicado

### Chain of Responsibility (Cadena de Responsabilidad)

Este patrón permite pasar una solicitud a través de una cadena de manejadores. Cada manejador decide si puede procesar parte de la solicitud o si debe pasarla al siguiente en la cadena.

**¿Cómo se aplica aquí?**

Cada denominación de billete es un manejador independiente. Cuando el usuario solicita una cantidad:
1. El primer dispensador (`$100.000`) intenta entregar la mayor cantidad posible de sus billetes.
2. El sobrante se pasa al siguiente dispensador (`$50.000`), y así sucesivamente.
3. La cadena termina con el dispensador de `$5.000`.

```
Solicitud ($) ──► Disp100k ──► Disp50k ──► Disp20k ──► Disp10k ──► Disp5k
```

---

## 🗂️ Estructura del Proyecto

```
Dispensador/
├── src/
│   ├── App.java                        # Punto de entrada, interacción con el usuario
│   ├── Interface/
│   │   └── ITransaccion.java           # Contrato de la cadena (setNextHandler + manejarSolicitud)
│   ├── Model/
│   │   ├── DispensadorBase.java        # Clase abstracta base para todos los dispensadores
│   │   ├── Dispensador100000.java      # Manejador: billete de $100.000
│   │   ├── Dispensador50000.java       # Manejador: billete de $50.000
│   │   ├── Dispensador20000.java       # Manejador: billete de $20.000
│   │   ├── Dispensador10000.java       # Manejador: billete de $10.000
│   │   ├── Dispensador5000.java        # Manejador: billete de $5.000 (último de la cadena)
│   │   └── Usuario.java                # Entidad que representa al usuario del cajero
│   └── Service/
│       └── Cajero.java                 # Servicio que construye y gestiona la cadena
└── bin/                                # Archivos compilados (.class)
```

---

## ⚙️ Componentes Clave

| Clase / Interfaz | Rol en el patrón |
|---|---|
| `ITransaccion` | Interfaz del manejador: define `setNextHandler()` y `manejarSolicitud()` |
| `DispensadorBase` | Clase abstracta que implementa la lógica de encadenamiento (`pasarAlSiguiente`) |
| `Dispensador[X]` | Manejadores concretos, uno por denominación |
| `Cajero` | Construye la cadena y expone el método `retirarDinero()` |
| `Usuario` | Entidad de datos con nombre e ID |
| `App` | Punto de entrada con bucle de consola |

---

## ▶️ Cómo ejecutar

### Requisitos
- Java JDK 8 o superior

### Compilar
```bash
cd Dispensador/src
javac -d ../bin App.java Interface/ITransaccion.java Model/*.java Service/Cajero.java
```

### Ejecutar
```bash
cd Dispensador/bin
java App
```

### Ejemplo de uso
```
=== SISTEMA DE CAJERO AUTOMÁTICO ===
Denominaciones disponibles: $100,000, $50,000, $20,000, $10,000, $5,000
Nota: Solo se permiten cantidades múltiplos de $5,000

Ingrese su nombre (o 'salir' para terminar): Santiago
Ingrese su ID: 001
Ingrese la cantidad a retirar: $175000

=== TRANSACCIÓN INICIADA ===
Usuario: Santiago
Cantidad solicitada: $175000

Procesando retiro...
Dispensando 1 billete(s) de $100,000
Dispensando 1 billete(s) de $50,000
Dispensando 1 billete(s) de $20,000
Dispensando 1 billete(s) de $5,000

=== TRANSACCIÓN COMPLETADA ===
```

---

## ✅ Reglas de negocio

- Solo se aceptan cantidades **mayores a cero**.
- La cantidad debe ser **múltiplo de $5.000**.
- Los billetes se dispensan priorizando las **denominaciones más altas** primero.
- Si hay un residuo no dispensable, se muestra un mensaje de error.

---

## 👤 Autor

Estudiante — Diseño y programacion de soluciones de Software SaaS

