public class ReaderPrinter<A> implements Actor<A>{

	 private Reader reader;
	 private Printer printer;


	public ReaderPrinter() {
		printer = new Printer();
	}
	

	public ReaderPrinter(Transceiver transceiver) {
		reader = new Reader(transceiver,this);
		printer = new Printer();
		reader.start();
	}

	@Override
	public void tell(String message, Actor sender) throws Exception {
		printer.tell(message, null);

		if(reader == null && sender != null) {
			reader = new Reader(sender,this);
			reader.start();
		}
	}



	@Override
	public void shutdown() {

	}

	public Reader getReader() {
		return reader;
	}

	public Printer getPrinter() {
		return printer;
	}
}
