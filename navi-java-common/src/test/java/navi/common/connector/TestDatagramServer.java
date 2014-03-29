package navi.common.connector;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TestDatagramServer {

    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 1234;
        DatagramSocket socket = new DatagramSocket(port);

        // socket.bind(new InetSocketAddress(address, port));
        DatagramConnector server = new DatagramConnector(socket, address, port);

        server.setListener(new DatagramServerListener(server));
        server.start();
    }
}
