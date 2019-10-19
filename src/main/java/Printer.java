public class Printer<A> implements Actor<A>{

    String str;

    public Printer() {
    }


    /**
     * Gibt eingehende Nachrichten auf der Console aus
     * @param message
     * @param sender
     */
    @Override
    public void tell(String message, Actor<A> sender) {
        str = message;
        System.out.println(message);
    }

    @Override
    public void shutdown() {

    }
}
