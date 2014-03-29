package navi.common.connector;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class TestDatagramClient {

    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 1234;
        DatagramSocket socket = new DatagramSocket();

        socket.connect(new InetSocketAddress(address, port));
        DatagramConnector client = new DatagramConnector(socket, address, port);

        client.setListener(new DatagramClientListener());
        client.start();
        client.send(new byte[]{0x0, 0x1, 0x2});
        client.interrupt();
    }
}
