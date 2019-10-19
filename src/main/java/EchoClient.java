public class EchoClient {

    public static void main(String[] args) throws Exception {
        ReaderPrinter readerPrinter;
        Transceiver transceiver;

        String host = args[0];

        System.out.println("Client started!");

        int port = Integer.parseInt(args[1]);
        String nachricht = args[2];

        transceiver = new Transceiver(port, host);
        readerPrinter = new ReaderPrinter(transceiver);
        readerPrinter.reader.str = nachricht;
        while (readerPrinter.readerIsAlive() || transceiver.receiverIsAlive()) {
            Thread.sleep(100);

        }
    }
}
