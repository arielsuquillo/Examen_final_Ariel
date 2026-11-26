# Buscaminas en Consola (Java - POO / MVC)

Proyecto académico desarrollado en Java para la evaluación práctica de Programación Orientada a Objetos. Implementa el clásico juego Buscaminas por consola sobre un tablero de $10 \times 10$ con 10 minas, aplicando arquitectura Modelo-Vista-Controlador (MVC), manejo de excepciones, persistencia de partidas y pruebas unitarias bajo TDD.

## Características principales

- Tablero de $10 \times 10$ casillas con distribución aleatoria de 10 minas.
- Cálculo automático de minas adyacentes (hasta 8 vecinas).
- Revelación recursiva en cadena al descubrir casillas vacías sin minas cercanas.
- Marcado y desmarcado preventivo de casillas sospechosas.
- Condiciones de victoria (descubrir las 90 casillas seguras) y derrota (activar una mina).
- Manejo de excepciones estándar (`InputMismatchException`, `ArrayIndexOutOfBoundsException`) y excepciones personalizadas (`CasillaYaDescubiertaException`, `CoordenadaInvalidaException`).
- Guardado y carga de partidas mediante archivos / serialización.
- Pruebas unitarias en `test/` aplicando buenas prácticas de código limpio y TDD.

## Estructura del proyecto

```text
├── src/          # Código fuente organizado por paquetes (modelo, vista, controlador, excepciones, persistencia, Main)
├── test/         # Pruebas unitarias del juego
├── .classpath    # Configuración de classpath de Eclipse
├── .project      # Configuración de proyecto de Eclipse
└── README.md     # Documentación del repositorio

git clone <URL_DEL_REPOSITORIO>
