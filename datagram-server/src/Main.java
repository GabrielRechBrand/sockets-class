import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        try{
            DatagramSocket con = new DatagramSocket(4444); //conexao(porta)
            while(true) {
                byte dado[] = new byte[100]; //Configura o pacote
                DatagramPacket pacote = new DatagramPacket(dado, dado.length); //Configura o pacote
                System.out.println("Inicio servidor");
                String mensagem="";
                while(!mensagem.equals("fim")) {
                    con.receive(pacote); //espera pacote
                    System.out.println("\n\nPacote recebido do Cliente :"+
                            "\n do Host: "+ pacote.getAddress()+
                            "\n da Porta: " + pacote.getPort()+
                            "\n de Tamanho: " + pacote.getLength()+
                            "\n contendo: " );
                    mensagem = new String(pacote.getData(),0,pacote.getLength() );
                    System.out.println(" "+mensagem); //escreve mensagem
                }
            }
        } catch(IOException io) {
            System.err.println("Problemas de IO");
        }
    }
}
