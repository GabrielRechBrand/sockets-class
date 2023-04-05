import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        try {
            Socket sock = new Socket(JOptionPane.showInputDialog("Insert an URL"), 80);

            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            String line = "";

            out.println("GET / HTTP/1.0\n");

            while ((line = in.readLine()) != null) {
                System.out.println("echo: " + line);
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}
