import org.apache.commons.math3.stat.descriptive.rank.Median;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Client {


    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 8000);
        Scanner scanner = new Scanner(System.in);
        InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferReader = new BufferedReader(inputStream);
        System.out.println("Please enter your Credentials ");
        System.out.println("ID: ");
        send(scanner.nextLine(), socket);
        System.out.println("Password: ");
        send(scanner.nextLine(), socket);
        if (bufferReader.readLine().equals("Wrong")) {
            System.out.println("Wrong Credentials");
            System.out.println("Please try again");
            System.exit(0);
        }
        String option = "";
        System.out.println("Welcome to Student Grades System");
        while (!option.equals("3")) {
            System.out.println("1. Show Your Grades");
            System.out.println("2. Show Statistics of a Course");
            System.out.println("3. Log Out");
            System.out.println("Enter an Option");
            option = scanner.nextLine();
            send(option, socket);

            switch (option) {
                case "1":
                    System.out.println(bufferReader.readLine());
                    break;
                case "2":
                    System.out.println(bufferReader.readLine());
                    send(scanner.nextLine(), socket);
                    System.out.println(bufferReader.readLine());
                    break;
            }

        }
    }

    public static void send(String message, Socket socket) throws IOException {
        PrintWriter pr = new PrintWriter(socket.getOutputStream());
        pr.println(message);
        pr.flush();

    }
}
