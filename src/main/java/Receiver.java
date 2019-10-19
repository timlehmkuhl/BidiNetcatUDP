public class Receiver extends Thread{

	private UDPSocket socket;
	private Actor printer;
	private Actor parent;
	private String nachricht;
    private static final int BUFSIZE = 508;


	public Receiver(UDPSocket socket,Actor readprinter,Actor parent){
		this.socket = socket;
		this.printer = readprinter;
		this.parent = parent;
	}
	

	public void run() {
        while (true){
            try {
                this.nachricht = socket.receive(BUFSIZE);
                this.printer.tell(nachricht, parent);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(nachricht.equals("\u0004")){

                printer.shutdown();
                socket.shutdown();
                break;
            }
        }
	}

}
