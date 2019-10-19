import java.io.ByteArrayInputStream;

public class EchoServer {



    public static void main(String[] args) throws Exception {


        //Server
        //$ java Netcat -l 2222
        int port = Integer.parseInt(args[1]);
        System.out.println("Server started!");

        ReaderPrinter readerPrinter= new ReaderPrinter();
        Transceiver transceiver = new Transceiver(port,readerPrinter);

        while(readerPrinter.readerIsAlive() || transceiver.receiverIsAlive()) {

            Thread.sleep(100);

            if(readerPrinter.printer.str !=null) {
                String s= readerPrinter.printer.str;
                readerPrinter.reader.str = s;
                readerPrinter.reader.run();

                //Thread.sleep(5000);


            }



        }


    }
}
