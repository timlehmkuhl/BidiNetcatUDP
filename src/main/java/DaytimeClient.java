public class DaytimeClient {

    public static void main(String[] args) throws Exception {
        ReaderPrinter readerPrinter;
        Transceiver transceiver;

        String host = args[0];

        System.out.println("Client started!");

        int port = Integer.parseInt(args[1]);

        transceiver = new Transceiver(port, host);
        readerPrinter = new ReaderPrinter(transceiver);
        readerPrinter.reader.str = "zitat";
        while (readerPrinter.readerIsAlive() || transceiver.receiverIsAlive()) {
            Thread.sleep(100);

        }
    }


}
