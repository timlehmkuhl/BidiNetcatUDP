public class DaytimeServer {

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
                String time = java.time.LocalTime.now().toString().substring(0,8);
                readerPrinter.reader.str = time;
                readerPrinter.reader.run();

                //Thread.sleep(5000);


            }



        }


    }

}
