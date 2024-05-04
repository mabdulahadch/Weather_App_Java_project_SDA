// import java.util.Scanner;

// public class Gun {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.println("Choose a number (1, 2, 3, or 4):");
//         int choice = scanner.nextInt();

//         switch (choice) {
//             case 1:
//                 runJar("C:/Users/tower/OneDrive/Desktop/Fast-Nu/4th Semester/4.%SDA/Project/testjavafx/firstjavafx/Terminal+SQL.jar");
//                 break;
//             case 2:
//                 runJar("C:/Users/tower/OneDrive/Desktop/Fast-Nu/4th Semester/4. SDA/Project/testjavafx/firstjavafx/JavaUI+SQL.jar");
//                 break;
//             case 3:
//                 runJar("C:/Users/tower/OneDrive/Desktop/Fast-Nu/4th Semester/4. SDA/Project/testjavafx/firstjavafx/JavaUI+SQL.jar");
//                 break;
//             case 4:
//                 runJar("C:/Users/tower/OneDrive/Desktop/Fast-Nu/4th Semester/4. SDA/Project/testjavafx/firstjavafx/JavaUI+SQL.jar");
//                 break;
//             default:
//                 System.out.println("Invalid choice. Please choose a number between 1 and 4.");
//         }

//         scanner.close();
//     }

//     @SuppressWarnings("deprecation")
//     private static void runJar(String jarFilePath) {
//         try {
//             Process process = Runtime.getRuntime().exec("java -jar " + jarFilePath);
//             process.waitFor();
//         } catch (Exception e) {
//             System.out.println("Error running JAR file: " + e.getMessage());
//         }
//     }
// }
