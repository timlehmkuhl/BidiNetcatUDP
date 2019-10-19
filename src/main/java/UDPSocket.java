import java.io.IOException;
import java.net.*;

public class UDPSocket {

    private String ip;
    private int port;
    private DatagramSocket socket;

    /**
     * Konstruktor fuer Client
     * @param ip
     * @param port
     * @throws SocketException
     */
    public UDPSocket(int port, String ip) {
        this.ip = ip;
        this.port = port;
        try {
            this.socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * Konstruktor fuer Server
     * @param port
     * @throws SocketException
     */
    public UDPSocket(int port) {
        this.port = port;
        try {
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ein String wird vor dem Senden in ein Byte-Array gewandelt und
     * in ein DatagramPacket gelegt, welches dem Socket uebergeben wird
     * @param message
     * @throws IOException
     */
    public void send(String message)  {
        try {
            InetAddress addr = null;
            addr = InetAddress.getByName(this.ip);
            DatagramPacket packetOut = new DatagramPacket(message.getBytes(),message.length(),addr,this.port);
            packetOut.setData(message.getBytes());
            socket.send(packetOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * In der send- und receive-Methode
     * Ein Byte-Array aus dem empfangenden DatagramPacket wird in ein String gewandelt
     * @param maxBytes
     * @return String
     * @throws IOException
     */
    public String receive(int maxBytes)  {
        DatagramPacket packetIn = new DatagramPacket(new byte[maxBytes],maxBytes);
        try {
            socket.receive(packetIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(this.ip == null) {
            this.ip = packetIn.getAddress().getHostAddress();
            this.port = packetIn.getPort();
        }
        return new String(packetIn.getData(),0,packetIn.getLength());
    }

    public void shutdown() {
        this.socket.close();
    }



}
