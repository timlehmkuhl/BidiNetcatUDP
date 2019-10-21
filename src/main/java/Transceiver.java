public class Transceiver<A> implements Actor<A> {

	private Actor transmitter;
	private Receiver receiver;
	private UDPSocket udpSocket;


	public Transceiver(int port, ReaderPrinter readerPrinter) throws Exception {
        udpSocket = new UDPSocket(port);
		receiver = new Receiver(udpSocket, readerPrinter,this);
		transmitter = new Transmitter(udpSocket);
		receiver.start();
	}
	

	public Transceiver(int port, String host) throws Exception {
        udpSocket = new UDPSocket(port, host);
		transmitter = new Transmitter(udpSocket);
	}


	@Override
	public void tell(String message, Actor sender) throws Exception {
		transmitter.tell(message, sender);

		if(receiver == null && sender !=null) {
			receiver = new Receiver(udpSocket,sender,this);
			receiver.start();
		}
	}

	@Override
	public void shutdown() {

	}


}
