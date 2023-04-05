import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket connection = new Socket("10.150.40.37", 4444);

        PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
        String message = "";

        while (!message.equals("exit")) {
            message = JOptionPane.showInputDialog("Send a message");
            out.println(message);
        }
    }
}
