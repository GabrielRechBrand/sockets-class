import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {
    public static void main(String args[]){
        try{
            DatagramSocket con = new DatagramSocket();//cria o objeto
            System.out.println("Inicio cliente");
            ExecutorService executor = Executors.newFixedThreadPool(128); //create a thread pool of 6 threads
            while(true) {
                Thread.sleep(10);
                Runnable worker = new WorkerThread(con); // create a new worker thread with the DatagramSocket object
                executor.execute(worker); // submit the worker thread to the thread pool
            }
        } catch(IOException io) {
            System.err.println("Problemas de IO");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class WorkerThread implements Runnable {
    private DatagramSocket con;

    public WorkerThread(DatagramSocket con) {
        this.con = con;
    }

    public void run() {
        try {
            byte dado[] = UUID.randomUUID().toString().getBytes();
            DatagramPacket pacote = new DatagramPacket(dado,
                    dado.length, InetAddress.getByName("10.150.127.255"), 62720);
            con.send(pacote);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
