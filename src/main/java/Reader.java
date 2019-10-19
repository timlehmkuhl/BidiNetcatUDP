import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Reader extends Thread{

	private Actor transceiver;
    private Actor parent;
    String str = "";
    //InputStream i;
	
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
        try {
            transceiver.tell("\u0004", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        transceiver.shutdown();

    }

/*	@Override
	public void run() {
        Scanner in = new Scanner(System.in);


        while(true) {
          //  if(str != null & !in.hasNext()) break;

            if(str != null) {
                try {
                    transceiver.tell(str, parent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                if(in.hasNext())
                transceiver.tell(in.nextLine(), parent);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
       /* try {
            transceiver.tell("\u0004", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        transceiver.shutdown();*/
	//}
	
	/*public void set(String s){
	    s = s + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
        System.setIn(in);
    }*/
	 
	
}
