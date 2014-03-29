package navi.common.connector;

import navi.common.connector.client.ClientConnector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by paoolo on 27.03.14.
 */
public class TestStreamClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 1234;
        Socket socket = new Socket();

        socket.connect(new InetSocketAddress(address, port));
        ClientConnector client = new ClientConnector(socket);

        client.setListener(new StreamClientListener());
        client.start();
        client.send(new byte[]{0x0, 0x1, 0x2});

        Thread.sleep(1000);
        client.interrupt();
    }
}
