# 🏧 Dispensador de Cajero Automático

> Ejercicio de patrones de diseño — **Chain of Responsibility**  
> Materia: Diseño de Software 

# 📚 Ejercicios de Diseño de Software

> Materia: Diseño de Software | Corporación Unificada Nacional de Educación Superior (CUN)  
> Tema: Patrones de Diseño y Arquitecturas Limpias — Java

---

## 📋 Índice de Ejercicios

| # | Nombre | Patrón / Concepto | Carpeta |
|---|--------|-------------------|---------|
| 1 | Dispensador Cajero | Chain of Responsibility | `Dispensador/` |
| 2 | Notificador de Transacciones | Observer | `CajeroObserver/` |
| 3 | Comisiones por Tipo de Cuenta | Strategy | `CajeroStrategy/` |
| 4 | Refactorización a Capas | Clean Architecture | `CajeroClean/` |

---

## 🏧 Ejercicio 1 — Dispensador Cajero

**Patrón:** Chain of Responsibility (Cadena de Responsabilidad)  
**Carpeta:** `Dispensador/`

### ¿Qué hace?
Simula un cajero automático que distribuye una cantidad de dinero en billetes. Cada denominación (`$100.000`, `$50.000`, `$20.000`, `$10.000`, `$5.000`) es un manejador independiente. Si no puede resolver el total, pasa el residuo al siguiente en la cadena.

### Estructura clave
```
ITransaccion (interfaz)
    └── DispensadorBase (abstracta)
            ├── Dispensador100000
            ├── Dispensador50000
            ├── Dispensador20000
            ├── Dispensador10000
            └── Dispensador5000
Cajero → construye y ejecuta la cadena
```

### Diagrama del flujo
```
Monto ($) ──► Disp100k ──► Disp50k ──► Disp20k ──► Disp10k ──► Disp5k
```

### ¿Qué aplica?
- Desacopla al emisor de la solicitud de sus receptores.
- Cada manejador solo conoce al siguiente; no conoce la cadena completa.
- Fácil de extender: agregar `Dispensador200000` solo requiere crear una clase y enlazarla.

### Compilar y ejecutar
```bash
cd Dispensador/src
javac -d ../bin App.java Interface/ITransaccion.java Model/*.java Service/Cajero.java
java -cp ../bin App
```

---

## 🔔 Ejercicio 2 — Notificador de Transacciones

**Patrón:** Observer (Observador)  
**Carpeta:** `CajeroObserver/`

### ¿Qué hace?
Cuando el cajero completa una transacción, notifica automáticamente a múltiples sistemas suscritos: auditoría, alerta por monto alto e historial del usuario. Agregar un nuevo sistema no requiere modificar `Cajero`.

### Estructura clave
```
IObservadorTransaccion (interfaz)
    ├── AuditoriaObserver        → imprime registro de cada retiro
    ├── AlertaMontoObserver      → alerta si el monto supera $500.000
    └── HistorialUsuarioObserver → guarda los últimos 5 retiros por usuario

GestorEventos → mantiene la lista y notifica a todos
Cajero        → llama a gestorEventos.notificarTodos() al completar
```

### ¿Qué aplica?
- El sujeto (`GestorEventos`) no conoce los detalles de sus observadores.
- Los observadores se suscriben/desuscriben en tiempo de ejecución.
- Principio Abierto/Cerrado: se agregan observadores sin modificar `Cajero`.

### Tarea
Implementar `HistorialUsuarioObserver` con un `Map<String, List<Integer>>` que almacene los últimos 5 retiros de cada usuario y los muestre al inicio de cada nueva transacción del mismo.

### Compilar y ejecutar
```bash
cd CajeroObserver/src
javac -d ../bin -sourcepath . App.java
java -cp ../bin App
```

---

## 💳 Ejercicio 3 — Comisiones por Tipo de Cuenta

**Patrón:** Strategy (Estrategia)  
**Carpeta:** `CajeroStrategy/`

### ¿Qué hace?
El cajero aplica un cálculo de comisión diferente según el tipo de cuenta seleccionado por el usuario antes del retiro. La estrategia se intercambia en tiempo de ejecución sin tocar la lógica del dispensador.

### Tipos de cuenta disponibles

| Opción | Tipo | Comisión |
|--------|------|----------|
| 1 | Básica | 2% del monto |
| 2 | Premium | 0.5% del monto |
| 3 | Empresarial | $3.000 fijo |

### Estructura clave
```
IEstrategiaComision (interfaz)
    ├── ComisionBasica        → monto × 0.02
    ├── ComisionPremium       → monto × 0.005
    └── ComisionEmpresarial   → $3.000 fijo

Cajero.setEstrategiaComision(estrategia) → intercambia en tiempo de ejecución
```

### ¿Qué aplica?
- Encapsula algoritmos intercambiables detrás de una interfaz común.
- `Cajero` depende de `IEstrategiaComision`, no de implementaciones concretas.
- Cumple el Principio Abierto/Cerrado (OCP) de SOLID: agregar una nueva cuenta no requiere modificar `Cajero`.

### Tarea
Agregar una cuarta estrategia `ComisionEstudiante` con tarifa fija de `$500` y registrarla como opción 4 en `App.java` sin modificar ninguna clase existente.

### Compilar y ejecutar
```bash
cd CajeroStrategy
javac -d bin -sourcepath src src/App.java
java -cp bin App
```

---

## 🏛️ Ejercicio 4 — Refactorización a Clean Architecture

**Concepto:** Clean Architecture (Robert C. Martin)  
**Carpeta:** `CajeroClean/`

### ¿Qué hace?
Refactoriza el proyecto del dispensador separando el código en capas independientes donde las reglas de negocio (`domain`) no conocen ni dependen de la UI ni de la infraestructura técnica.

### Capas y responsabilidades

```
CajeroClean/
├── domain/                          ← Capa central, sin dependencias externas
│   ├── entities/
│   │   ├── Usuario.java             ← Entidad pura
│   │   └── Billete.java             ← Value Object (denominación + cantidad)
│   └── usecases/
│       ├── RetirarDineroUseCase.java ← Lógica de negocio principal
│       └── ports/
│           └── IDispensadorPort.java ← Puerto de salida (interfaz hacia infra)
│
├── application/                     ← Orquesta casos de uso
│   └── CajeroService.java
│
└── infrastructure/                  ← Detalles técnicos, adaptadores
    ├── dispensadores/
    │   └── CadenaDispensadoresAdapter.java  ← Implementa IDispensadorPort
    └── ui/
        └── ConsolaUI.java           ← Solo se encarga de I/O
```

### Regla de dependencia
```
ConsolaUI  ──►  CajeroService  ──►  RetirarDineroUseCase  ──►  IDispensadorPort
                                                                      ▲
                                                          CadenaDispensadoresAdapter
```
Las flechas apuntan **hacia adentro**. El `domain` no sabe que existe una consola ni una cadena de dispensadores.

### ¿Qué aplica?
- **Dependency Rule:** las capas internas no conocen las externas.
- **Dependency Inversion (DIP):** `RetirarDineroUseCase` depende de `IDispensadorPort`, no de la implementación concreta.
- **Separación de responsabilidades:** cambiar la UI por una API REST no requiere tocar ninguna línea del dominio.

### Tarea
Implementar `SimuladorDispensadorAdapter` (segunda implementación de `IDispensadorPort`) que retorne los billetes en una `List<Billete>` sin imprimir nada. Usarla para escribir una prueba unitaria de `RetirarDineroUseCase` sin consola ni periféricos reales.

### Compilar y ejecutar
```bash
cd CajeroClean
javac -d bin -sourcepath src src/infrastructure/ui/ConsolaUI.java
java -cp bin infrastructure.ui.ConsolaUI
```

---

## 🔗 Relación entre ejercicios

```
Ejercicio 1 (Chain of Responsibility)
        │
        ├──► Ejercicio 2: se agrega Observer al mismo Cajero
        │
        ├──► Ejercicio 3: se agrega Strategy al mismo Cajero
        │
        └──► Ejercicio 4: toda la lógica se reorganiza en capas limpias
```

Los ejercicios 2 y 3 extienden el proyecto base del ejercicio 1.  
El ejercicio 4 lo refactoriza completo aplicando principios de arquitectura.

---

## 👤 Autor

Estudiante — Diseño y programacion de soluciones de Software SaaS-ITM

