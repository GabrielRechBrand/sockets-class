import socket.ClientSocket;

public class Main {
    public static void main(String[] args) {
        ClientSocket socket = new ClientSocket();
        socket.comunicateWithServer();
    }
}
