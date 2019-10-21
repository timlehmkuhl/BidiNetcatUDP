import java.util.Scanner;

public class Reader extends Thread{

	private Actor transceiver;
    private Actor parent;
    String str = "";

	
	public Reader(Actor transceiver, Actor parent){
		this.transceiver = transceiver; 
		this.parent = parent;
	}

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        if(str != "") {
            try {
                transceiver.tell(str, parent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        while(in.hasNext()) {
            try {
                transceiver.tell(in.nextLine(), parent);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        in.close();
        try {
            transceiver.tell("\u0004", parent);
            parent.shutdown();
            transceiver.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
