import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(Integer.parseInt(JOptionPane.showInputDialog("Insert the port")));
            List<Socket> clients = new ArrayList<>();
            
            while (true) {
                Socket socket = server.accept();
                Server client = new Server(socket);
                System.out.println("Client connected");
                client.start();

                clients.add(socket);

                clients.forEach(c -> {
                    PrintWriter cOut;
                    try {
                        cOut = new PrintWriter(c.getOutputStream(), true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    cOut.println(client.getName().concat(" connected!"));
                });
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
