# Weather Monitoring System Application

## Overview
The Weather Monitoring System is a real-time application built to retrieve, monitor, and display weather data for any city. Users can check daily weather summaries, set alert thresholds, and monitor real-time weather trends. The application uses the OpenWeatherMap API for data.

## Features
- Real-time Weather Data: Fetch and display current weather data for any specified city.
- Daily Weather Summaries: Calculate daily summaries (average, max, min values for temperature, humidity, etc.).
- Alert Notifications: User-configurable alerts for temperature, humidity, and wind speed.
- Data Visualization: View trends using charts for daily summaries and historical weather data.
- Database Persistence: Rules are stored and retrieved from a MySQL database.

## Prerequisites
- Java 17+
- Maven 3.6+
- MySQL database(for data storage)
- **(OpenWeatherMap API Key: Sign up at OpenWeatherMap to get your API key)**.

## Dependencies
The project uses the following dependencies, which are managed via Maven. Ensure you have these in your `pom.xml` file:

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
<dependency>
           <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
      <exclusions>
         <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
         </exclusion>
    </exclusions>
</dependency>


		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		
		<dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-security</artifactId>
       </dependency>
       
       
        <!-- Other dependencies -->

    <!-- JUnit 5 -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.9.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Mockito -->
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>4.5.1</version>
        <scope>test</scope>
    </dependency>

    <!-- Spring Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>
</project>


steps for installation:
**## Installation**

### Prerequisites
Before you begin, ensure you have the following installed on your machine:
- [Java 17+] (https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

- [Maven 3.6+] (https://maven.apache.org/download.cgi)

- [MySQL] (https://www.mysql.com/) database installed and running

- [Eclipse IDE](https://www.eclipse.org/downloads/) (preferably with EGit plugin)



### Steps to Clone the Repository Using Eclipse IDE

1. **Install Eclipse IDE**:
   - Download and install Eclipse from the [Eclipse Downloads](https://www.eclipse.org/downloads/) page.

2. **Open Eclipse**:
   - Launch the Eclipse IDE after installation.

3. **Open the Git Repositories View**:
   - Go to `Window` > `Show View` > `Other`.
   - In the dialog that appears, type "Git" in the filter box and select `Git Repositories`. Click `Open`.

4. **Clone the Repository**:
   - In the **Git Repositories** view, click on the `Clone a Git repository` button.
   - Enter the repository URL:
     ```plaintext
               https://github.com/yourusername/Zeotap_WeatherProject.git
     ```
   - Select the branches you want to clone and click `Next`. 
OR **Clone the Repository**:(another way to clone)
1. Go to File > Import.
2. Select Git > Projects from Git and click Next.
3. Choose Clone URI :  **(https://github.com/yourusername/Zeotap_WeatherProject.git)**  (enter this url to clone ) and click Next.
4. Enter the repository URI and your credentials, then select the branches you want to clone.

 

5. **Choose Local Destination**:
   - Select a local directory for the cloned repository and click `Finish`.

6. **Import the Project**:
   - Go to `File` > `Import`.
   - Select `Existing Maven Projects` under the `Maven` category and click `Next`.
   - Click `Browse` to select the root directory of the cloned repository and click `Finish`.



### Configure MySQL Database

1. **Create a New Database**:
   Open your MySQL command line or a MySQL client (like MySQL Workbench) and run:
   ```sql
   CREATE DATABASE weather_data; (weather_data is your database name you can change according to you)

2. **Update the database credentials in **(src/main/resources/application.properties)**: **(open application.properties and add below credentials)**

- spring.datasource.url=jdbc:mysql://localhost:3306/weather_data   (database name-weather_data)
- spring.datasource.username=yourusername  ( provide your mySql username)
- spring.datasource.password=yourpassword  (provide your mySql password)
- spring.jpa.hibernate.ddl-auto=update  
**(Replace yourusername and yourpassword with your actual MySQL username and password.)**

3.Add OpenWeatherMap API Key: Add your API key to application.properties:
-   **(openweather.api.key=your_openweather_api_key)**




### Build and Run the Project

1. Run the Application:
  - Right-click on the project in the Project Explorer.

  -  Select Run As > JAVA APPLICATION/SPRING BOOT APP/MAVEN BUILD

  - Enter spring-boot:run in the Goals field and click Run.

2.Access the Application
Once the application is running, you can access it in your web browser at:
**(http://localhost:8081 )**        **( 8081 IS PORT NUMBER - you have to modify the port number according to your application working on which port)**
(Ensure to modify the port number if your application uses a different one.)

### Frontend Setup
The frontend files are located in the src/main/resources/templates and src/main/resources/static folders.

HTML: **(The main UI file, index.html, is located in src/main/resources/templates.)**
CSS: Add styles in the src/main/resources/static directory for custom styling.
Once the Spring Boot application is running, access the frontend at **(http://localhost:8081)**  (index.html)  
**( if your system is working in other port so please modify the port in **(src/main/resources/application.properties)**--> server.port=your port number)**

### Usage
Home Page: **(Enter a city name and click Get Weather Summary to view the latest data.)**
Alerts: Set thresholds for temperature, humidity, and wind speed; receive alerts when conditions are exceeded.
Historical Data: View daily summaries and historical trends.

### API Endpoints
Fetch Weather Data: **(/weather/fetch?city={city_name})** - Retrieve and save real-time weather data.
Daily Summary: **(/weather/summary?city={city_name})** - Get daily weather summary.  ( ex- /weather/summary?city={Bangalore})
Set Alerts: /alerts/set?temp={temp}&humidity={humidity}&windSpeed={windSpeed} - Configure alert thresholds.


### Testing
Unit and Integration Tests: Run tests to validate functionality, including: **( go to src/main/test and click on file and run as --> junit)**

-Real-time data retrieval
- Alert functionality
- Temperature conversion
- Verify system starts successfully and connects to the OpenWeatherMap API, using a valid API key.

### Project Structure
Backend: Located in src/main/java

Frontend: HTML templates in src/main/resources/templates, CSS in src/main/resources/static

Database Config: MySQL with Hibernate ORM

### License
Distributed under the MIT License. See LICENSE for more information.

### Acknowledgments
- OpenWeatherMap API for weather data.
- Ui for data visualization.
