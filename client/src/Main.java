import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Main extends JFrame {

    private JTextArea outputArea;

    public Main() {
        super("Client");
        // Set the frame properties
        setSize(1280, 760);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Create the IP input field
        JLabel ipLabel = new JLabel("IP:");
        JTextField ipField = new JTextField("localhost", 20);

        // Create the port input field
        JLabel portLabel = new JLabel("Port:");
        JTextField portField = new JTextField("8080", 5);

        // Create the output text area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Redirect the System.out to the output text area
        PrintStream out = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                outputArea.append(String.valueOf((char) b));
            }
        });
        System.setOut(out);
        System.setErr(out);

        // Create the connect button
        JButton connectButton = new JButton("Connect");
        final Socket[] server = new Socket[1];
        connectButton.addActionListener(e -> {
            try {
                String ip = ipField.getText();
                int port = Integer.parseInt(portField.getText());
                server[0] = new Socket(ip, port);
                System.out.println("Connected to server at " + server[0].getInetAddress() + ":" + server[0].getPort());
                new Client(server[0]).start();
            } catch (IOException ex) {
                System.err.println("Error connecting to server: " + ex.getMessage());
            }
        });

        // Create the input panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(ipLabel);
        inputPanel.add(ipField);
        inputPanel.add(portLabel);
        inputPanel.add(portField);
        inputPanel.add(connectButton);

        // Create the message input field
        JTextField messageField = new JTextField(50);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> {
            String message = messageField.getText();
            if (!message.isEmpty()) {
                System.out.println("Sending message: " + message);
                try {
                    PrintWriter printWriter = new PrintWriter(server[0].getOutputStream(), true);
                    printWriter.println(message);
                } catch (Exception ex) {
                    System.out.println("ERROR: You're not connected to the server!");
                }
                messageField.setText("");
            }
        });

        JPanel messagePanel = new JPanel(new FlowLayout());
        messagePanel.add(messageField);
        messagePanel.add(sendButton);

        // Add the components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(messagePanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

}
