package introduction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class EditorFrame {
    private JFrame frame;
    private JEditorPane editorPane;
    private JTextField textField;

    public EditorFrame() {
        frame = new JFrame("JEditorPane Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Text input field
        textField = new JTextField();
        frame.add(textField, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        frame.add(buttonPanel, BorderLayout.EAST);

        // Button for opening the HTML
        JButton openButton = new JButton("Open");
        buttonPanel.add(openButton);
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String urlText = textField.getText();
                try {
                    URL url = new URL(urlText);
                    editorPane.setPage(url); // Set the HTML to JEditorPane
                    editorPane.repaint(); // Force a repaint of the JEditorPane
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Button for closing the HTML
        JButton closeButton = new JButton("Close");
        buttonPanel.add(closeButton);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorPane.setText(""); // Clear the JEditorPane content
            }
        });

        // Graphic interface (JEditorPane)
        editorPane = new JEditorPane();
        editorPane.setPreferredSize(new Dimension(800, 600));
        editorPane.setContentType("text/html");
        editorPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(editorPane);
        frame.add(scrollPane, BorderLayout.SOUTH);

        frame.setSize(1280, 720);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EditorFrame();
            }
        });
    }
}
