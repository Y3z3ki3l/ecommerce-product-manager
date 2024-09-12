## **Proyecto Capstone: Sistema de Gestión de Productos para E-commerce**

### **Descripción del Proyecto**
**Capstone project template** para crear una **API de microservicios** en Java que integra los temas que has aprendido. Este proyecto está diseñado para demostrar de manera integral tus conocimientos en programación funcional, RxJava, patrones de diseño, genéricos, y más, además de incluir pruebas unitarias con JUnit 5 y Mockito. También estará preparada para su implementación y despliegue en la nube.

**Objetivo**
Desarrollar una API de microservicios para gestionar productos en una plataforma de e-commerce. Este microservicio permitirá crear, leer, actualizar y eliminar (CRUD) productos, gestionar inventarios, y procesar órdenes. La API estará diseñada siguiendo principios de programación funcional y reactiva, incorporando patrones de diseño orientados a objetos, y utilizando genéricos para una mayor flexibilidad y reutilización de código. Además, se implementarán pruebas unitarias para asegurar la calidad del código.

### **Tecnologías y Herramientas Utilizadas**
- **Lenguaje de Programación:** Java 17+
- **Framework:** Spring Boot
- **Reactive Programming:** RxJava 3
- **Base de Datos:** MongoDB (NoSQL) o PostgreSQL (SQL)
- **ORM:** Spring Data
- **Construcción de Proyecto:** Maven o Gradle
- **Pruebas Unitarias:** JUnit 5, Mockito
- **Control de Versiones:** Git
- **Despliegue en la Nube:** Docker, Kubernetes, AWS/GCP/Azure
- **CI/CD:** GitHub Actions, Jenkins, o GitLab CI

### **Estructura del Proyecto**
```
ecommerce-product-service/
├── src/
│   ├── main/
│   │   ├── java/com/tuempresa/ecommerce/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── EcommerceProductServiceApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── logback-spring.xml
│   └── test/
│       └── java/com/tuempresa/ecommerce/
│           ├── controller/
│           ├── service/
│           └── repository/
├── Dockerfile
├── kubernetes/
│   ├── deployment.yaml
│   └── service.yaml
├── pom.xml / build.gradle
└── README.md
```

### **Componentes del Proyecto**

#### 1. **Configuración (`config/`)**
- **Propósito:** Configurar beans, seguridad, manejo de excepciones globales, y cualquier configuración específica del microservicio.
- **Implementación de Patrones de Diseño:** Utilizar el patrón Singleton para gestionar instancias de servicios globales.

#### 2. **Controladores (`controller/`)**
- **Propósito:** Exponer endpoints RESTful para la gestión de productos.
- **Ejemplo de Endpoint:**
  - `GET /api/products` - Obtener lista de productos.
  - `POST /api/products` - Crear un nuevo producto.
  - `PUT /api/products/{id}` - Actualizar un producto existente.
  - `DELETE /api/products/{id}` - Eliminar un producto.

#### 3. **DTOs (`dto/`)**
- **Propósito:** Definir objetos de transferencia de datos para solicitudes y respuestas.
- **Implementación de Genéricos:** Utilizar genéricos para crear DTOs reutilizables.

#### 4. **Modelos (`model/`)**
- **Propósito:** Definir las entidades de negocio, como `Product`, `Inventory`, etc.
- **Uso de Anotaciones de Spring Data:** Mapear modelos a la base de datos.

#### 5. **Repositorios (`repository/`)**
- **Propósito:** Interactuar con la base de datos utilizando Spring Data.
- **Implementación de Patrones de Diseño:** Utilizar el patrón Repository para abstraer la lógica de acceso a datos.

#### 6. **Servicios (`service/`)**
- **Propósito:** Contener la lógica de negocio.
- **Programación Funcional y Reactive:** Implementar operaciones usando programación funcional y RxJava para manejar flujos de datos asíncronos.
- **Patrones de Diseño:** Implementar el patrón Strategy para diferentes estrategias de pago o gestión de inventario.

#### 7. **Excepciones (`exception/`)**
- **Propósito:** Manejar excepciones personalizadas y respuestas de error.
- **Implementación de Patterns:** Utilizar el patrón de manejo de excepciones globales.

### **Implementación de los Temas Aprendidos**

A continuación, se detalla cómo integrar cada uno de los temas aprendidos en el proyecto:

#### **1. Programación Funcional y Expresiones Lambda**
- **Uso en Servicios y Controladores:**
  - Emplear lambdas para operaciones de transformación de datos.
  - Utilizar `Optional` para manejar valores nulos de manera segura.

```java
// Ejemplo de uso de Optional y lambdas en el servicio
public Optional<Product> getProductById(String id) {
    return productRepository.findById(id)
        .map(product -> {
            product.setLastAccessed(LocalDateTime.now());
            return product;
        });
}
```

#### **2. Interfaces Funcionales y Referencias de Métodos**
- **Uso en Repositorios y Servicios:**
  - Implementar interfaces funcionales para operaciones personalizadas.
  - Utilizar referencias a métodos para mayor claridad.

```java
// Uso de referencia a método en un stream
List<String> productNames = products.stream()
    .map(Product::getName)
    .collect(Collectors.toList());
```

#### **3. Streams y Parallel Streams**
- **Procesamiento de Colecciones:**
  - Manipular colecciones de productos utilizando streams para filtrado, mapeo y reducción.
  - Utilizar parallel streams para operaciones intensivas en datos.

```java
// Uso de parallelStream para procesar productos
List<Product> discountedProducts = products.parallelStream()
    .filter(p -> p.getPrice() > 100)
    .map(p -> p.applyDiscount(10))
    .collect(Collectors.toList());
```

#### **4. Reactive Programming con RxJava**
- **Implementación de Endpoints Reactivos:**
  - Utilizar `Flowable` o `Observable` para manejar flujos de datos.
  - Implementar backpressure para gestionar la demanda de datos.

```java
// Servicio reactivo usando RxJava
public Flowable<Product> streamAllProducts() {
    return Flowable.fromIterable(productRepository.findAll())
        .subscribeOn(Schedulers.io());
}
```

#### **5. Patrones de Diseño Orientados a Objetos**
- **Singleton, Strategy, Factory, etc.:**
  - **Singleton:** Para servicios globales como `ProductService`.
  - **Strategy:** Para diferentes métodos de filtrado o procesamiento de productos.

```java
// Ejemplo del patrón Strategy para diferentes estrategias de descuento
public interface DiscountStrategy {
    Product applyDiscount(Product product);
}

public class SeasonalDiscountStrategy implements DiscountStrategy {
    @Override
    public Product applyDiscount(Product product) {
        product.setPrice(product.getPrice() * 0.9);
        return product;
    }
}

// Uso en el servicio
public Product applyDiscount(Product product, DiscountStrategy strategy) {
    return strategy.applyDiscount(product);
}
```

#### **6. Java Generics**
- **Reutilización de Código:**
  - Crear clases y métodos genéricos para manejar diferentes tipos de datos.
  
```java
// Clase genérica para respuestas de API
public class ApiResponse<T> {
    private T data;
    private String message;
    private int status;

    // Constructor, getters y setters
}

// Uso en un controlador
@GetMapping("/{id}")
public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable String id) {
    Optional<Product> product = productService.getProductById(id);
    if (product.isPresent()) {
        return ResponseEntity.ok(new ApiResponse<>(product.get(), "Producto encontrado", 200));
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponse<>(null, "Producto no encontrado", 404));
    }
}
```

#### **7. Optional para Manejo de Nulos**
- **Prevención de NullPointerException:**
  - Utilizar `Optional` en servicios y repositorios para manejar valores que pueden ser nulos.

```java
// Uso de Optional en el repositorio
public Optional<Product> findById(String id);
```

#### **8. Programación Reactiva Avanzada**
- **Operadores y Combinación de Observables:**
  - Emplear operadores como `map`, `filter`, `zip`, y `merge` para manipular flujos de datos.

```java
// Combinación de dos observables usando zip
Observable<String> names = Observable.just("Producto A", "Producto B");
Observable<Double> prices = Observable.just(100.0, 200.0);

Observable.zip(names, prices, (name, price) -> new Product(name, price))
    .subscribe(System.out::println);
```

#### **9. Manejo de Concurrencia y Paralelismo**
- **Uso de Schedulers en RxJava:**
  - Controlar el contexto de ejecución de los Observables para operaciones concurrentes.

```java
// Uso de Schedulers para manejar concurrencia
Observable<Integer> observable = Observable.range(1, 5)
    .subscribeOn(Schedulers.computation())
    .observeOn(Schedulers.io());

observable.subscribe(System.out::println);
```

#### **10. Testing con JUnit 5 y Mockito**
- **Pruebas Unitarias y de Integración:**
  - Implementar pruebas para controladores, servicios y repositorios.
  - Utilizar Mockito para simular dependencias.

```java
// Ejemplo de prueba unitaria para ProductService
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetProductById_Found() {
        Product product = new Product("1", "Producto A", 100.0);
        Mockito.when(productRepository.findById("1")).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById("1");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Producto A", result.get().getName());
    }

    @Test
    public void testGetProductById_NotFound() {
        Mockito.when(productRepository.findById("2")).thenReturn(Optional.empty());

        Optional<Product> result = productService.getProductById("2");
        Assertions.assertFalse(result.isPresent());
    }
}
```

### **Implementación y Despliegue en la Nube**

#### **1. Dockerización**
- **Crear un Dockerfile para contenerizar la aplicación.**

```dockerfile
# Usar una imagen base de OpenJDK
FROM openjdk:17-jdk-alpine

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo jar construido
COPY target/ecommerce-product-service-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java","-jar","app.jar"]
```

#### **2. Kubernetes para Orquestación**
- **Desplegar la aplicación usando Kubernetes.**

```yaml
# deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: ecommerce-product-service
spec:
  replicas: 3
  selector:
    matchLabels:
      app: ecommerce-product-service
  template:
    metadata:
      labels:
        app: ecommerce-product-service
    spec:
      containers:
      - name: ecommerce-product-service
        image: tuusuario/ecommerce-product-service:latest
        ports:
        - containerPort: 8080
        env:
        - name: SPRING_DATASOURCE_URL
          value: jdbc:postgresql://postgres:5432/ecommerce
---
# service.yaml
apiVersion: v1
kind: Service
metadata:
  name: ecommerce-product-service
spec:
  type: LoadBalancer
  ports:
    - port: 80
      targetPort: 8080
  selector:
    app: ecommerce-product-service
```

#### **3. Despliegue en Proveedores de Nube**
- **Elegir un proveedor como AWS, GCP o Azure y desplegar el contenedor.**
- **Usar servicios como AWS EKS, GCP GKE o Azure AKS para gestionar el clúster de Kubernetes.**

#### **4. CI/CD**
- **Configurar pipelines de CI/CD para automatizar la construcción, pruebas y despliegue.**

```yaml
# Ejemplo de GitHub Actions workflow (ci-cd.yml)
name: CI/CD Pipeline

on:
  push:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - name: Checkout code
      uses: actions/checkout@v2

    - name: Set up JDK 17
      uses: actions/setup-java@v2
      with:
        java-version: '17'

    - name: Build with Maven
      run: mvn clean install

    - name: Run tests
      run: mvn test

    - name: Build Docker image
      run: docker build -t tuusuario/ecommerce-product-service:latest .

    - name: Push Docker image
      run: |
        echo "${{ secrets.DOCKER_PASSWORD }}" | docker login -u "${{ secrets.DOCKER_USERNAME }}" --password-stdin
        docker push tuusuario/ecommerce-product-service:latest

    - name: Deploy to Kubernetes
      uses: appleboy/k8s-action@master
      with:
        kubeconfig: ${{ secrets.KUBECONFIG }}
        args: kubectl apply -f kubernetes/
```

### **Documentación y Buenas Prácticas**

- **README.md:**
  - Descripción del proyecto.
  - Instrucciones para la configuración local.
  - Guía para ejecutar pruebas.
  - Detalles sobre el despliegue en la nube.

- **Documentación de API:**
  - Utilizar herramientas como Swagger/OpenAPI para documentar los endpoints.

```java
// Configuración de Swagger con Springfox
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
            .info(new Info().title("E-commerce Product Service API")
            .description("API para gestión de productos en una plataforma de e-commerce")
            .version("v1.0"));
    }
}
```

### **Prácticas Recomendadas**

1. **Seguridad:**
   - Implementar autenticación y autorización usando JWT o OAuth2.
   - Validar entradas de usuarios para prevenir inyecciones y otros ataques.

2. **Manejo de Configuraciones:**
   - Externalizar configuraciones usando Spring Cloud Config o variables de entorno.

3. **Monitoreo y Logging:**
   - Implementar logging estructurado con Logback o Log4j.
   - Integrar herramientas de monitoreo como Prometheus y Grafana.

4. **Escalabilidad:**
   - Diseñar el microservicio para escalar horizontalmente.
   - Utilizar balanceadores de carga y estrategias de autoescalado.

### **Conclusión**

Este proyecto capstone integra una amplia gama de conceptos y tecnologías aprendidos, proporcionando una experiencia completa en el desarrollo de microservicios modernos en Java. Al seguir esta plantilla, podrás demostrar tus habilidades en programación funcional, reactiva, patrones de diseño, pruebas unitarias, y despliegue en la nube, preparando así un portafolio sólido para futuras oportunidades profesionales.

---

### **Recursos Adicionales**

- **Documentación de Spring Boot:** [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
- **Guía de RxJava:** [https://github.com/ReactiveX/RxJava](https://github.com/ReactiveX/RxJava)
- **JUnit 5:** [https://junit.org/junit5/](https://junit.org/junit5/)
- **Mockito:** [https://site.mockito.org/](https://site.mockito.org/)
- **Docker:** [https://www.docker.com/](https://www.docker.com/)
- **Kubernetes:** [https://kubernetes.io/](https://kubernetes.io/)
- **GitHub Actions:** [https://github.com/features/actions](https://github.com/features/actions)
- **Swagger/OpenAPI:** [https://swagger.io/specification/](https://swagger.io/specification/)

Si tienes alguna pregunta o necesitas más detalles sobre alguna sección específica, ¡no dudes en preguntar!
