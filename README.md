# ms-builder
## Microservice Builder with Spring Boot and OpenAPI  

### 🚀 Automate Microservice Generation with Ease! 🚀

This project is a Microservice Builder that automates the generation of microservices based on an OpenAPI specification. It creates Spring Boot applications with reactive programming support, generates boilerplate code, and ensures consistency across multiple services.

### **Features**

✅ OpenAPI Integration: Parses OpenAPI specifications to generate RESTful APIs.  
✅ Reactive Programming: Built with Spring WebFlux for non-blocking, reactive applications.  
✅ Modular Structure: Supports multiple modules like api, services, persistence, gateway, and more.  
✅ Automated Code Generation: Saves time by generating boilerplate code for microservices.  
✅ Customizable: Easily extendable to include additional features or adapt to specific project needs.  
✅ Exception Handling: Includes custom exceptions and a global exception handler.  
✅ Logging: Integrated with SLF4J for logging.  

**Technologies Used**   

**Spring Boot**: For building the microservices.  

**Spring WebFlux**: For reactive programming.  

**OpenAPI (Swagger):** For API specification and code generation.  

**Apache Commons IO:** For file and directory operations.  

**SLF4J:** For logging.  

**Maven:** For dependency management and building the project.  

**Getting Started**
Prerequisites
Java 17 or higher

Maven 3.8.x or higher

OpenAPI Specification (YAML or JSON)

Installation
Clone the repository:

bash
Copy
git clone https://github.com/your-username/microservice-builder.git
cd microservice-builder
Build the project using Maven:

bash
Copy
mvn clean install
Run the application:

bash
Copy
mvn spring-boot:run
Usage
Generating Microservices
Place your OpenAPI specification file (swagger.yaml or swagger.json) in the src/main/resources directory.

Run the ApiModuleGeneratorService to generate the microservice structure:

java

The generated microservice structure will include:

Interfaces: REST API interfaces.

Controllers: Spring Boot controllers.

Services: Business logic implementations.

Models: Data transfer objects (DTOs).

Utils: Utility classes.

Exceptions: Custom exception handling.

Example Output
Copy
outputDir/
├── api/
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   │   └── com/
│       │   │       └── msname/
│       │   │           └── api/
│       │   └── resources/
│       └── test/
│           └── java/
│               └── com/
│                   └── msname/
│                       └── api/
├── services/
│   └── src/
│       └── main/
│           └── java/
│               └── com/
│                   └── msname/
│                       └── services/
└── exe/
└── src/
└── main/
└── java/
└── com/
└── msname/
└── exe/
└── Main.java
Configuration
Reactive Web Application
To ensure the application uses reactive programming, set the following property in application.properties:

properties  

spring.main.web-application-type=reactive
Customizing Package Names
You can customize the base package name by modifying the BASE_PACKAGE constant in the ApiModuleGeneratorService class.

Contributing
We welcome contributions! If you'd like to contribute to this project, please follow these steps:

Fork the repository.

Create a new branch for your feature or bugfix:

bash
Copy
git checkout -b feature/your-feature-name
Commit your changes:

bash
Copy
git commit -m "Add your feature or fix"
Push to the branch:

bash
Copy
git push origin feature/your-feature-name
Open a pull request and describe your changes.

License
This project is licensed under the MIT License. See the LICENSE file for details.

Acknowledgments
Spring Boot: For making microservice development a breeze.

OpenAPI: For providing a standardized way to define APIs.

Apache Commons IO: For simplifying file and directory operations.

Contact
If you have any questions, suggestions, or feedback, feel free to reach out:

Email: vishalth115@gmail.com

LinkedIn: www.linkedin.com/in/vishal-thakur-b394aa207

🌟 Happy Coding! 🌟