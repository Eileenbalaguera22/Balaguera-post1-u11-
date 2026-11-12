# Post 11 – Refactorización de código y pruebas de caracterización

## Parte 1. Identificación y refactorización de Code Smells

### 1. Descripción del proyecto

Este proyecto corresponde al desarrollo del Post 9, cuyo propósito es identificar y mejorar problemas de diseño presentes en un sistema desarrollado con Java y Spring Boot, aplicando técnicas de refactorización, principios de diseño y pruebas de caracterización.

En la primera parte se trabaja sobre el módulo de inventario, en el cual se identifican dos problemas principales de calidad del código: el método largo (*Long Method*) y las estructuras condicionales duplicadas (*Duplicate Switch Statements*).

Posteriormente, en la segunda parte, se integra un módulo de reportes encargado de generar información relacionada con los productos que presentan niveles bajos de inventario. Durante esta integración se revisan las dependencias entre paquetes y se aplican mecanismos para reducir el acoplamiento.

El proceso busca mejorar la organización, legibilidad y mantenibilidad del código, procurando conservar el comportamiento esperado de la aplicación.

### 2. Objetivos

- Identificar los Code Smells presentes en el módulo de inventario.
- Implementar pruebas de caracterización para comprobar el comportamiento del código.
- Aplicar la técnica Extract Method para dividir responsabilidades dentro de métodos extensos.
- Aplicar polimorfismo para eliminar estructuras switch duplicadas.
- Verificar que los cambios realizados no alteren el funcionamiento esperado del sistema.
- Integrar un módulo de reportes con el módulo de inventario.
- Identificar y evitar dependencias circulares entre paquetes.
- Aplicar inversión de dependencias para reducir el acoplamiento.
- Validar los cambios mediante pruebas automatizadas y pruebas arquitectónicas.

### 3. Tecnologías utilizadas

- Java.
- Spring Boot.
- Maven.
- JUnit 5.
- Spring Boot Starter Test.
- ArchUnit.
- Visual Studio Code.
- Git y GitHub.

### 4. Estructura del proyecto

La estructura principal del proyecto se organiza mediante paquetes que separan las responsabilidades de la aplicación.

```text
refactoring-u11/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/universidad/refactoringu11/
│   │           ├── inventario/
│   │           │   ├── ClasificadorProducto.java
│   │           │   ├── EtiquetadoProducto.java
│   │           │   ├── InventarioService.java
│   │           │   ├── Producto.java
│   │           │   ├── TipoProducto.java
│   │           │   └── NotificadorBajoStock.java
│   │           │
│   │           ├── reportes/
│   │           │   └── ReporteBajoStockService.java
│   │           │
│   │           └── RefactoringU11Application.java
│   │
│   └── test/
│       └── java/
│           └── com/universidad/refactoringu11/
│               ├── inventario/
│               │   ├── ClasificadorProductoTest.java
│               │   ├── EtiquetadoProductoTest.java
│               │   └── InventarioServiceTest.java
│               │
│               ├── ArquitecturaPaquetesTest.java
│               └── RefactoringU11ApplicationTests.java
│
├── pom.xml
└── README.md
```

### 5. Identificación y refactorización de los problemas de diseño

#### 5.1. Long Method

Este problema se presenta cuando un método concentra demasiadas instrucciones y responsabilidades, haciendo que su comprensión, mantenimiento y modificación sean más difíciles.

En el módulo de inventario se identifica la necesidad de separar las diferentes operaciones que se realizan al actualizar la información de los productos.

**Solución aplicada:** se utiliza la técnica Extract Method, dividiendo el procedimiento principal en métodos auxiliares con responsabilidades más específicas. De esta manera, el código queda mejor organizado y cada operación resulta más sencilla de comprender y mantener.

#### 5.2. Duplicate Switch Statements

Este problema aparece cuando diferentes partes del sistema utilizan estructuras switch que repiten condiciones para determinar el comportamiento de un mismo tipo de objeto.

En este caso, las operaciones relacionadas con la clasificación y el etiquetado de productos pueden generar duplicación de lógica cuando dependen de las mismas categorías.

**Solución aplicada:** se utiliza polimorfismo para trasladar los comportamientos específicos a los tipos de producto correspondientes. Así, se reduce la repetición de estructuras condicionales y se facilita la incorporación de nuevos tipos de productos.

### 6. Pruebas de caracterización

Se implementaron pruebas automatizadas utilizando JUnit 5 para comprobar el comportamiento del sistema y verificar que las modificaciones realizadas mantuvieran los resultados esperados.

Las pruebas se distribuyen en las siguientes clases:

| Clase de prueba | Pruebas ejecutadas |
|---|---:|
| ClasificadorProductoTest | 4 |
| EtiquetadoProductoTest | 4 |
| InventarioServiceTest | 6 |
| RefactoringU11ApplicationTests | 1 |
| **Total** | **15** |

Las tres primeras clases contienen las pruebas relacionadas directamente con las funcionalidades del módulo de inventario. La cuarta comprueba el contexto de la aplicación Spring Boot.

### 7. Ejecución y resultados de la Parte 1

Para ejecutar las pruebas se utilizó Maven desde la terminal de PowerShell, ubicada en la carpeta raíz del proyecto.

Comando utilizado:

```bash
mvn test
```

También se consultaron los informes generados por Maven Surefire mediante:

```powershell
Get-Content target/surefire-reports/*.txt
```

**Resultados obtenidos en la ejecución:**

| Clase de prueba | Tests | Failures | Errors | Skipped |
|---|---:|---:|---:|---:|
| ClasificadorProductoTest | 4 | 0 | 0 | 0 |
| EtiquetadoProductoTest | 4 | 0 | 0 | 0 |
| InventarioServiceTest | 6 | 0 | 0 | 0 |
| RefactoringU11ApplicationTests | 1 | 0 | 0 | 0 |
| **Total** | **15** | **0** | **0** | **0** |

**Interpretación de los resultados:**

La ejecución finalizó correctamente. Se realizaron 15 pruebas automatizadas y ninguna presentó fallos o errores. Tampoco se omitieron pruebas.

Estos resultados permiten comprobar que las pruebas implementadas se ejecutaron satisfactoriamente y que, durante esta validación, no se detectaron comportamientos incorrectos en los casos evaluados.

### 8. Evidencias de la Parte 1

Se incluyen como evidencias de esta primera parte:

1. Estructura del proyecto en Visual Studio Code.
2. Clases de prueba implementadas.
3. Refactorización del método extenso mediante Extract Method.
4. Aplicación de polimorfismo para eliminar estructuras switch duplicadas.
5. Resultados generados en la carpeta `target/surefire-reports`.

### 9. Conclusión de la Parte 1

El desarrollo de esta primera parte permitió reconocer la importancia de identificar los problemas de diseño antes de realizar modificaciones en el código. Mediante las técnicas Extract Method y polimorfismo se busca mejorar la organización interna del sistema, reducir la duplicación y facilitar su mantenimiento.

Las pruebas automatizadas desempeñan un papel fundamental durante este proceso, porque permiten comprobar que las funcionalidades evaluadas continúan respondiendo correctamente después de los cambios.

Finalmente, los resultados obtenidos muestran una ejecución satisfactoria de las 15 pruebas implementadas, con cero fallos, errores y pruebas omitidas. Esto constituye una evidencia de validación de la primera parte del laboratorio.

---

## Parte 2. Integración del módulo de reportes y verificación arquitectónica

### 10. Descripción de la integración

En la segunda parte del laboratorio se incorpora un módulo de reportes encargado de generar información relacionada con los productos que presentan niveles bajos de inventario.

Esta integración permite que el módulo de inventario pueda activar la generación de reportes y las alertas cuando se identifica la necesidad de realizar un reordenamiento.

El módulo de reportes se encarga de generar la información correspondiente, mientras que el módulo de inventario conserva la responsabilidad de gestionar los productos y las operaciones relacionadas con sus existencias.

### 11. Flujo de reordenamiento

Cuando el sistema identifica que un producto necesita reordenarse, se ejecuta el siguiente bloque:

```java
if (necesitaReorden) {

    if (reporteBajoStockService != null) {
        System.out.println(
                reporteBajoStockService.generarReporte()
        );
    }

    emitirAlerta(producto, cantidadReorden);

    return "OK_BAJO_STOCK_REORDEN_" + cantidadReorden;
}
```

Este fragmento representa el siguiente comportamiento:

1. Se verifica si el producto necesita reordenamiento.
2. Se comprueba si el servicio de reportes está disponible.
3. Si está disponible, se genera e imprime el reporte de bajo stock.
4. Se emite una alerta relacionada con el producto y la cantidad que debe reordenarse.
5. Se devuelve un mensaje que confirma la operación y muestra la cantidad de reordenamiento.

La comprobación de disponibilidad permite contemplar el caso en el que el servicio de reportes no esté presente, sin impedir que se ejecute la alerta y se retorne el resultado de la operación.

### 12. Dependencias entre módulos e inversión de dependencias

La incorporación del módulo de reportes requiere revisar cuidadosamente las dependencias entre los paquetes.

Una dependencia circular aparece cuando dos módulos dependen directamente entre sí. Esta situación genera mayor acoplamiento y dificulta el mantenimiento, las pruebas y la evolución independiente de los componentes.

Para reducir este problema se utiliza una abstracción que permite separar la comunicación entre los módulos.

Se define la interfaz `NotificadorBajoStock` en el paquete de inventario, estableciendo un contrato para las notificaciones relacionadas con los productos que necesitan reordenamiento.

El módulo de reportes implementa esta interfaz mediante `ReporteBajoStockService`.

De esta manera, el módulo de inventario puede solicitar una notificación sin depender directamente de la implementación concreta del módulo de reportes.

Esta solución aplica el principio de inversión de dependencias, ya que la comunicación se realiza mediante una abstracción y no mediante una dependencia directa entre implementaciones.

**Decisión de diseño:** separar el contrato de notificación de su implementación concreta permite reducir el acoplamiento y facilita incorporar otras implementaciones en el futuro.

### 13. Verificación de la arquitectura con ArchUnit

Para comprobar que la integración del módulo de reportes no generara dependencias circulares entre los paquetes, se implementó una prueba arquitectónica utilizando ArchUnit.

La clase `ArquitecturaPaquetesTest` permite analizar las dependencias existentes en la estructura del proyecto y verificar que se cumpla la regla arquitectónica establecida.

Esta validación es importante porque permite detectar problemas de acoplamiento que podrían dificultar el mantenimiento y la evolución de la aplicación.

**Resultado:** la prueba `ArquitecturaPaquetesTest` se ejecutó satisfactoriamente, sin fallos ni errores.

### 14. Ejecución de pruebas y resultados de la Parte 2

Una vez realizada la integración del módulo de reportes y aplicada la solución arquitectónica, se ejecutaron nuevamente las pruebas automatizadas mediante Maven.

Comando utilizado:

```bash
mvn test
```

El resultado obtenido en la terminal fue:

```text
[INFO] Results:
[INFO]
[INFO] Tests run: 16, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] BUILD SUCCESS
[INFO] Total time: 13.600 s
```

#### 14.1. Resultados detallados

Los informes generados en `target/surefire-reports` muestran los siguientes resultados:

| Clase de prueba | Tests | Failures | Errors | Skipped |
|---|---:|---:|---:|---:|
| ArquitecturaPaquetesTest | 1 | 0 | 0 | 0 |
| ClasificadorProductoTest | 4 | 0 | 0 | 0 |
| EtiquetadoProductoTest | 4 | 0 | 0 | 0 |
| InventarioServiceTest | 6 | 0 | 0 | 0 |
| RefactoringU11ApplicationTests | 1 | 0 | 0 | 0 |
| **Total** | **16** | **0** | **0** | **0** |

#### 14.2. Análisis de los resultados

La ejecución finalizó con `BUILD SUCCESS`, lo que indica que Maven completó satisfactoriamente el proceso de pruebas.

Se ejecutaron 16 pruebas automatizadas, distribuidas en cinco clases, sin registrar fallos, errores ni pruebas omitidas.

Un resultado importante es la aprobación de `ArquitecturaPaquetesTest`, ya que permite comprobar que la regla arquitectónica definida para evitar ciclos entre paquetes se cumple en la estructura analizada.

Además, las 15 pruebas funcionales y de contexto que ya se habían implementado en la primera parte continuaron ejecutándose satisfactoriamente después de incorporar los cambios de la segunda parte.

Por lo tanto, los resultados respaldan que la integración realizada no afectó los casos de comportamiento cubiertos por las pruebas existentes y que la validación arquitectónica también fue superada.

Durante el proceso de desarrollo se presentó una ejecución inicial fallida de Maven. Para identificar el problema se revisaron los informes generados por Surefire. Posteriormente, se obtuvo una ejecución satisfactoria con las 16 pruebas aprobadas.

### 15. Evidencias de la Parte 2

Se incluyen como evidencias de esta segunda parte:

1. Integración del módulo de reportes.
2. Implementación de la interfaz `NotificadorBajoStock`.
3. Implementación de `ReporteBajoStockService`.
4. Código relacionado con el flujo de reordenamiento.
5. Clase `ArquitecturaPaquetesTest`.
6. Ejecución de Maven mostrando el resultado `BUILD SUCCESS`.
7. Informes de Surefire con las 16 pruebas aprobadas.

---

## 16. Conclusión general

El desarrollo de este laboratorio permitió aplicar técnicas de refactorización y principios de diseño para mejorar la calidad interna de una aplicación desarrollada con Java y Spring Boot.

En la primera parte se identificaron problemas de diseño relacionados con métodos extensos y estructuras condicionales duplicadas. Para solucionarlos se aplicaron las técnicas Extract Method y polimorfismo, acompañadas de pruebas de caracterización para verificar el comportamiento del sistema.

En la segunda parte se incorporó un módulo de reportes para gestionar la información relacionada con los productos con bajo stock. Esta integración permitió trabajar sobre la separación de responsabilidades y la inversión de dependencias, utilizando una abstracción para reducir el acoplamiento entre los módulos.

Finalmente, la ejecución de las pruebas automatizadas permitió validar los cambios realizados. Se obtuvieron 16 pruebas ejecutadas, cero fallos, cero errores y cero pruebas omitidas. También se obtuvo un resultado `BUILD SUCCESS` y la prueba arquitectónica de ArchUnit finalizó satisfactoriamente.

Estos resultados demuestran la importancia de combinar buenas prácticas de programación, principios de arquitectura y pruebas automatizadas para desarrollar sistemas más mantenibles, organizados y preparados para futuras modificaciones.

## 17. Resultado general del laboratorio

| Etapa | Pruebas ejecutadas | Fallos | Resultado |
|---|---:|---:|---|
| Parte 1 | 15 | 0 | Satisfactorio |
| Parte 2 | 16 | 0 | Satisfactorio |
| **Validación final** | **16** | **0** | **BUILD SUCCESS** |

**Estado final:** las dos partes del laboratorio cuentan con resultados satisfactorios de ejecución. La validación final incluye las pruebas funcionales, las pruebas de contexto de Spring Boot y la prueba arquitectónica de ArchUnit.

**Proyecto:** `refactoring-u11`
