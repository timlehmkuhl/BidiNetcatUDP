import org.junit.*;

import java.io.IOException;
import static org.junit.Assert.*;

public class Test {

    String nachrichtClient;
    String nachrichtServer;

    @Before
    public void server() throws Exception {

        Thread serverThread = new Thread(() -> {
            try {
                init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverThread.start();

        Thread client = new Thread(() -> {
            try {
                client();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        client.start();


    }

    public void init() throws Exception {

        int port = Integer.parseInt("2222");
        System.out.println("Server started!");

        ReaderPrinter readerPrinter= new ReaderPrinter();
        Transceiver transceiver = new Transceiver(port,readerPrinter);

        while(true) {

            Thread.sleep(100);

            if(readerPrinter.getPrinter().getStrOut() !=null) {
                String s= readerPrinter.getPrinter().getStrOut();
                nachrichtServer = s;
                readerPrinter.getReader().setStrIn(s);
                readerPrinter.getReader().run();
            }

        }
    }


    public void client() throws Exception {
        String host = "localhost";

        System.out.println("Client started!");

        int port = Integer.parseInt("2222");
        String nachricht = "test";
        nachrichtClient = nachricht;
        Transceiver transceiver = new Transceiver(port, host);
        ReaderPrinter readerPrinter = new ReaderPrinter(transceiver);
        readerPrinter.getReader().setStrIn(nachricht);
    }

    @org.junit.Test
    public void test(){
        assertEquals(nachrichtClient, nachrichtServer);
    }
}
