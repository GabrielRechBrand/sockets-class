package socket;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {

    public void comunicateWithServer() {
        try {
            Socket connection = new Socket(JOptionPane.showInputDialog("Insert the IP"), Integer.parseInt(JOptionPane.showInputDialog("Insert the port.")));

            PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
            InputStream in = connection.getInputStream();

            String message = "";

            byte[] response = new byte[1024];
            int bytesRead = in.read(response);
            String responseString = new String(response, 0, bytesRead);

            while (!message.equals("exit")) {
                message = JOptionPane.showInputDialog("Send a message");
                out.println(message);
                System.out.println(responseString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readTicketInfo() {
        return null;
    }

    public void showResults() {

    }

}
