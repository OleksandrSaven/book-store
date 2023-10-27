# Online-books-store ![](media/logo.jpg)
## Intro 
This is the "Online Books Store" application based on Spring Boot, which allows you to manage a catalog of books and 
handle user orders. The application includes the following features:

- Adding and editing books to the catalog.
- Managing user orders.
- User authentication and authorization.
- API documentation.
- Containerization.

## Technologies and Tools Used
![](media/tech.png)

This project is built using a variety of technologies and tools to ensure a robust and efficient online book store. Here's an overview of the key components:

- **Spring Boot**: This project is based on the Spring Boot framework, which simplifies the setup and development of Java applications.

- **Spring Security**: Spring Security is used to handle authentication and authorization, ensuring that only authorized users can access certain endpoints.

- **Spring Data JPA**: Spring Data JPA simplifies database access and management, allowing seamless interaction with the database using Java objects.

- **Swagger**: Swagger is integrated into the project to provide interactive API documentation. You can explore and test the API endpoints using the Swagger UI.

- **Docker**: Docker is used for containerization, making it easy to deploy and manage the application in various environments.

- **Database**: The project uses a relational database MySQL to store book and user data. The choice of database can be configured to fit your requirements.

- **Maven**: Maven is used for project management and dependency resolution. It simplifies the build and package process.


This project leverages these technologies and tools to provide a secure, efficient, and user-friendly online book store experience. Whether you're a developer or user, these technologies work together to make the online book store a seamless and enjoyable platform.
## How to use
To start this project, you'll need to follow these general steps. Keep in mind that the specific instructions may vary based on the project's structure and your environment. Here's a high-level overview of how you can start this Spring Boot-based online bookstore project:

- **Prerequisites**:

Ensure you have Java Development Kit v17 or latest (JDK) installed on your system.
Make sure you have Docker installed if you plan to run the application in a container.
- **Clone the Repository**:

Clone the project's repository from a version control system like Git.
- **Database Configuration**:
  - ***Local database:*** Check the project's configuration files (usually in the src/main/resources directory) to configure your database connection settings. Ensure you have a MySQL database set up and that you provide the correct credentials.
  - ***Using Docker:*** If you haven't already installed Docker, you can download and install it from the official Docker website: https://docs.docker.com/get-docker/. In the root directory you can find docker-compose.yml file and run it. This setup will allow you to run this application along with a MySQL database using Docker without the need to manually install and configure MySQL:

  <pre>docker-compose up </pre> 

- **Build the Project**:

Open a terminal/command prompt in the project's root directory and run the following command to build the project using Maven:
<pre>
mvn clean install
</pre>
- **Start the Application**:

If you're not using Docker, you can start the Spring Boot application directly by running the following command:
bash
<pre>
java -jar target/online-book-store-0.0.1-SNAPSHOT.jar
</pre>
If you're using Docker, you can create a Docker image for your application and then run a container. Ensure that Docker is properly configured on your system. Refer to your project's documentation for Docker-specific instructions.
Access the Application:

Once the application is up and running, you can access it by opening a web browser and navigating to the appropriate URL. The URL should be provided in the project's documentation or configuration.
API Documentation (Swagger):

Swagger is integrated into the project, you can access the interactive API documentation by navigating to the Swagger UI URL. It usually looks something like: http://localhost:8080/api/swagger-ui/index.html.
#### This application has a number of controllers that have the appropriate functionality:
 1. Authentication Controller:
    - register new user
    - login as user or admin
 2. Book Controller
    - find all books
    - get book by id
    - create book
    - delete book
    - update book
3. Category Controller
   - create category
   - get all category
   - update category
   - delete category
   - get books by category
4. Order Controller
   - create order
   - find all orders
   - find all items in specific order
   - find specific item in specific order
   - update order
5. Shopping Cart Controller
   - create cart
   - get cart
   - update cart 
   - delete cart
## API  Testing with Postman
![](media/postman.png)

I have provided a Postman collection that you can use to test the API endpoints easily.
This collection helps you set up contract tests to ensure that two separate systems are compatible and can communicate with one another.
Follow these steps to get started:
1. **Download Postman**: If you don't have Postman installed, you can [download it here](https://www.postman.com/downloads/).

2. **Import the Collection**:

    - Click the "Import" button in Postman.
    - Select the downloaded Postman collection file: [Postman_collection](media/postmanCollection.json).

3. **Configure Environment**:

    - Create a new environment in Postman and set the base URL to your local or deployed API URL.
    - You can use the default values for most variables, but make sure to set the `token` variable to your authentication token if required.

4. **Run the Requests**:

    - Open the imported collection in Postman.
    - Select the desired request.
    - Click the "Send" button to execute the request.
    - Review the response to ensure everything is working as expected.

5. **Explore Endpoints**:

    - The collection includes a variety of requests to different endpoints. Feel free to explore and test them to understand the API's capabilities.

6. **Note**: Some requests may require authentication. Ensure you are logged in and have the necessary permissions.

## Contribution
if you have a desire to participate in the improvement of the application, you can perform the following steps:
1. Fork the [GitHub repository](https://github.com/OleksandrSaven/book-store) to your own account.

2. Create a new branch for your feature or bug fix.

3. Make your changes in your branch.

4. Test your changes thoroughly to ensure they do not introduce new issues.

5. Submit a pull request to the [main repository](https://github.com/OleksandrSaven/book-store) following the template provided.

6. Your pull request will be reviewed and any necessary changes will be requested.

7. Once your pull request is approved, it will be merged into the project.


   Thank you for your interest in contributing to online-book-store! I appreciate your help in making the project better for everyone.
Feel free to customize this app to your project's specific needs and provide all the necessary information to make it easy for others to get involved.

## Additional
You can watch a video demonstration of how the application works by following the link: [link](https://www.loom.com/share/b22e5acdcf8d40f694ae9ba4246d4af7)