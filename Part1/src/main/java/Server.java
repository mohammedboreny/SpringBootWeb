import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.rank.Median;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class Server {


    public static void main(String[] args)  {
     try {
         ServerSocket serverSocket = new ServerSocket(8000);

        StudentDAO studentDAO = new StudentDAO();
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("New client connected");

            new ServerThread(socket,studentDAO).start();
        }
    } catch (SQLException e) {
         System.out.println(e.getMessage());
     } catch (IOException e) {
         System.out.println(e.getMessage());     }
     catch (ClassNotFoundException e) {
         System.out.println(e.getMessage());
     }
    }





}
 class ServerThread extends Thread {
    private Socket socket;
    private StudentDAO studentDAO;

    public ServerThread(Socket socket, StudentDAO studentDAO) {
        this.socket = socket;
        this.studentDAO = studentDAO;
    }
     public static void send(String message, Socket socket) throws IOException {
         PrintWriter pr = new PrintWriter(socket.getOutputStream());
         pr.println(message);
         pr.flush();

     }

    public void run() {
        try {


                InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferReader = new BufferedReader(inputStream);
                String id = bufferReader.readLine();
                String password = bufferReader.readLine();
                if (!studentDAO.testCredentials(id, password)) {
                    send("Wrong",socket);
                    socket.close();
                    return;
                }
                    else {
                    send("Success", socket);
                }
                Boolean closedSession = false;

                while (!closedSession) {

                    String option = bufferReader.readLine();
                    switch (option) {
                        case "1":
                            send(studentDAO.showGrades(id), socket);
                            break;
                        case "2":
                            send("Course Name: ", socket);
                            String course = bufferReader.readLine();
                            send(studentDAO.showCourse(course), socket);
                            break;
                        case "3":
                            closedSession = true;
                            bufferReader.close();
                            socket.close();
                            break;
                    }



            }

            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}