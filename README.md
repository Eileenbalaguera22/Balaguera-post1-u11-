# Post 9 – Refactorización de código y pruebas de caracterización

## Parte 1. Identificación y refactorización de Code Smells

### 1. Descripción del proyecto

Este proyecto corresponde al desarrollo de la primera parte del Post 9, cuyo propósito es identificar y mejorar problemas de diseño presentes en un sistema desarrollado con Java y Spring Boot, aplicando técnicas de refactorización y pruebas de caracterización.

En esta etapa se trabaja sobre el módulo de inventario, en el cual se identifican dos problemas principales de calidad del código: el método largo (*Long Method*) y las estructuras condicionales duplicadas (*Duplicate Switch Statements*).

El proceso busca mejorar la organización, legibilidad y mantenibilidad del código, procurando conservar el comportamiento que tenía originalmente la aplicación.

### 2. Objetivos

- Identificar los Code Smells presentes en el módulo de inventario.
- Implementar pruebas de caracterización para comprobar el comportamiento del código.
- Aplicar la técnica Extract Method para dividir responsabilidades dentro de métodos extensos.
- Aplicar polimorfismo para eliminar estructuras switch duplicadas.
- Verificar que los cambios realizados no alteren el funcionamiento esperado del sistema.
- Utilizar pruebas automatizadas para comprobar los resultados de la refactorización.

### 3. Tecnologías utilizadas

- Java.
- Spring Boot.
- Maven.
- JUnit 5.
- Spring Boot Starter Test.
- Visual Studio Code.
- Git y GitHub.

### 4. Estructura del proyecto

La estructura principal del proyecto se organiza de la siguiente manera:

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
│   │           │   └── ...
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
│               └── RefactoringU11ApplicationTests.java
│
├── pom.xml
└── README.md
```

### 5. Identificación de los problemas de diseño

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

### 7. Ejecución y resultados

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

### 8. Evidencias de ejecución

Se incluyen como evidencias de esta primera parte:

1.  resultados generados en la carpeta `target/surefire-reports`.

### 9. Conclusión

El desarrollo de esta primera parte permitió reconocer la importancia de identificar los problemas de diseño antes de realizar modificaciones en el código. Mediante las técnicas Extract Method y polimorfismo se busca mejorar la organización interna del sistema, reducir la duplicación y facilitar su mantenimiento.

Las pruebas automatizadas desempeñan un papel fundamental durante este proceso, porque permiten comprobar que las funcionalidades evaluadas continúan respondiendo correctamente después de los cambios.

Finalmente, los resultados obtenidos muestran una ejecución satisfactoria de las 15 pruebas implementadas, con cero fallos, errores y pruebas omitidas. Esto constituye una evidencia de validación de la Parte 1 del laboratorio.

---

