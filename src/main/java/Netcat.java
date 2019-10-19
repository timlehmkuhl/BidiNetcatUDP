
public class Netcat {

    public static void main(String[] args) throws Exception {

        ReaderPrinter readerPrinter;
        Transceiver transceiver;

        if (args.length != 2) {
            System.err.println("Zu wenig Parameter");
            return;
        }


        if (!args[0].equals("-l")) {
            //Client
            //$ java Netcat ip 5555
            String host = args[0];
            System.out.println("Client started!");

            int port = Integer.parseInt(args[1]);

            transceiver = new Transceiver(port,host);
            readerPrinter = new ReaderPrinter(transceiver);

            while(readerPrinter.readerIsAlive() || transceiver.receiverIsAlive()) {
                Thread.sleep(100);
            };
        }

        if(args[0].equals("-l")){
            //Server
            //$ java Netcat -l 2222
            int port = Integer.parseInt(args[1]);
            System.out.println("Server started!");

            readerPrinter= new ReaderPrinter();
            transceiver = new Transceiver(port,readerPrinter);

            while(readerPrinter.readerIsAlive() || transceiver.receiverIsAlive()) {
                Thread.sleep(100);
            };

        }
    }
}
