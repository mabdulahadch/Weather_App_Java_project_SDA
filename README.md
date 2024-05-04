# Weather_App_Java_project_SDA



This JavaFX Weather App allows users to retrieve weather information for a specific location. It utilizes the OpenWeatherMap API to fetch weather data and displays it in a user-friendly interface as well as on the console.

Installation and Setup

Install JavaFX JavaFX is required to run the JavaFX Weather App. Follow these steps to install JavaFX: .Download JavaFX SDK from the official website: OpenJFX .Extract the downloaded archive to a location on your computer.
Set up JavaFX in VS Code To configure JavaFX in Visual Studio Code, follow these steps: Open your JavaFX project in Visual Studio Code. Open a .vscode directory in the root of your project. Open a launch.json file inside the .vscode directory. Add the following configuration to launch.json: { "vmArgs": "--module-path "//path//javafx-sdk-21.0.2/lib" --add-modules javafx.controls,javafx.fxml,javafx.web" }
Add JAR Files to Reference Libraries To include the necessary JAR files in the reference libraries of Visual Studio Code, follow these steps: Navigate to the dependency folder in the src directory of your Weather Project. Copy all the required JAR files, including: 08 JAR files of JavaFX 01 JAR file of json-simple version 1.1.1 01 JAR file of the MySQL Connector/J Open your JavaFX project in Visual Studio Code. Navigate the Reference Libraries folder and add these jar files
For SQL DataBase Connection:
Make sure to have MySQLWorkbench
RUN the schema from Weather.sql file in your ROOT Folder.
Make sure to change the connection string in Database.java file.
Just Alter your Pasword if you have created the schema from Weather.sql file.
Your SQL setup completed.
For Map.js Install Node js(from Browser - also add Environment Variables), Express Js(npm install express in cmd) , CORS(npm install CORS in cmd) Goto Windows Firewall add A port "3000" in inbound rules.
Files locations After setting all the project locate the "readMapLocation.java" file and add the path of "String filePath = "//path//to//value.txt";" according to your pc. locate the mainSceneController.java file and alter the path of " engine.load("file:///path//to//map.html");" acoording to your pc
Add Liberica jar Download Liberica JDK(Full) from this link "https://bell-sw.com/pages/downloads/" according to your OS environment
