public class ReaderPrinter<A> implements Actor<A>{

	 Reader reader;
	 Printer printer;


	public ReaderPrinter() {
		printer = new Printer();
	}
	

	public ReaderPrinter(Actor transceiver) {
		reader = new Reader(transceiver,this);
		printer = new Printer();
		reader.start();
	}

	@Override
	public void tell(String message, Actor<A> sender) throws Exception {
		printer.tell(message, null);

		if(reader ==null && sender!=null) {
			reader = new Reader(sender,this);
			reader.start();
		}
	}


	public boolean readerIsAlive() {
		return reader == null || reader.isAlive();
	}

	@Override
	public void shutdown() {

	}



}
