public class Transmitter<A> implements Actor<A> {

	private UDPSocket udpSocket;
	
	public Transmitter(UDPSocket socket) {
		this.udpSocket = socket;
	}



	@Override
    public void tell(String message, Actor<A> sender)  {
        udpSocket.send(message);
	}

	@Override
	public void shutdown() {
	}

	
	
	

}
